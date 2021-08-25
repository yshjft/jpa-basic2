package hellojpa;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Product {
    @Id @GeneratedValue
    @Column(name= "PRODUCT_ID")
    private Long id;

    private String name;

    // 다대다 관계 양방향 매핑
    // @ManyToMany(mappedBy = "products")
    // private List<Member> members = new ArrayList<>();

    @OneToMany(mappedBy = "product")
    private List<MemberProduct> memberProducts = new ArrayList<>();
}
