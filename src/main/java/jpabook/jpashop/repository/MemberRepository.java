package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

//    @PersistenceContext     //jpa가 지원해주는 표준 entityManager  자동으로 주입해줌 @RequiredArgsConstructor 로 인해서 생략 가능
    private final EntityManager em;

    //test 코드 단축키 커맨드 쉬프트 t

    public void save(Member member){
        em.persist(member);     //save member
    }

    public Member findOne(Long id){
        return em.find(Member.class, id);       //id값을 넣어서 Member을 반환해줌
    }

    public List<Member> findAll(){
        // 옵션 커맨드 v  = 변수 정해줌
        // 옵션 커맨드 n = 합쳐줌
        return em.createQuery("select m from Member m", Member.class).getResultList();      //jpql
    }

    public List<Member> findByName(String name){
        return em.createQuery("select m from Member m where m.name = :name",Member.class)
                .setParameter("name",name)
                .getResultList();
    }
}
