package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String name;

    @Embedded   //내장 타입
    private Address address;

    @OneToMany(mappedBy = "member")     //연관관계에서 당하는입장 order테이블에 있는 member에 의해서 움직인다 라는 뜻 거울
    private List<Order> orders = new ArrayList<>();
}
