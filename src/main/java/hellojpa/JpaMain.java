package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try{
            Team team = new Team();
            team.setName("TeamA");
            em.persist(team);

            Member member = new Member();
            member.setName("member1");

            // 연관관계의 주인은 아니지만 객체지향을 위햐 아래와 같이 값을 추가 해야한다.
            // 대신 아래 코드는 연관관계 편의 메서드로 대체하자
            // team.getMembers().add(member);
            member.changeTeam(team);

            em.persist(member);

            // em.flush();
            // em.clear();

            // 만약 em.flush() & em.clear()가 없다면 1차 캐시에서 조회를 하게된다.
            // 이 떄에 team.getMembers().add(member);를 하지 않는다면 team의 members의 값을 알 수가 없다
            Member findMember = em.find(Member.class, member.getId());

            System.out.println("==================================");
            // jpa에서 members의 데이터를 사용하는 시점에 쿼리를 날림
            List<Member> members = findMember.getTeam().getMembers();
            for(Member m : members) {
                System.out.println("m = " + m.getName());
            }
            System.out.println("==================================");


            tx.commit();
        }catch(Exception e) {
            System.out.println(e.getMessage());
            tx.rollback();
        }finally {
            em.close();
        }
        emf.close();

    }
}
