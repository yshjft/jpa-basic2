package hellojpa;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Member {
    @Id @GeneratedValue
    @Column(name = "MEMEBER_ID")
    private Long id;

    @Column(name = "USERNAME")
    private String name;

    // 다대일 관계
    @ManyToOne
    @JoinColumn(name ="TEAM_ID")
    private Team team;

    // 일대다 관계 양방향 설정
    // 쓰지 말자
    // @ManyToOne
    // @JoinColumn(name="TEAM_ID", insertable = false, updatable = false)
    // private Team team;

    // 일대일 관계
    @OneToOne
    @JoinColumn(name ="LOCKER_ID")
    private Locker locker;

    // 다대다 관계
    // @ManyToMany
    // @JoinTable(name ="MEMBER_PRODUCT")
    // private List<Product> products = new ArrayList<>();

    // 다대다 관계 -> 일대다 관게
    @OneToMany(mappedBy = "member")
    private List<MemberProduct> memberProducts = new ArrayList<>();


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //    public Team getTeam() {
    //        return team;
    //    }

    // 연관관계 편의 메서드
    // 단순 setter 보다는 따로 이름을 주자
    //    public void changeTeam(Team team) {
    //        this.team = team;
    //        team.getMembers().add(this);
    //    }
}
