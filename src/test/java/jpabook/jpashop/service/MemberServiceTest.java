package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional      //Test 할때는 기본적으로 롤백시켜버림
public class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;
    @Autowired EntityManager em;

    @Test
    public void join() throws Exception{        //회원가입 테스트
        //given
        Member member = new Member();
        member.setName("Kim");

        //when
        Long saveId = memberService.join(member);

        //then
//        em.flush();     //insert query를 볼수 있음   transactional 때문에 기본적으로 롤백을 하기때문에
        assertEquals(member,memberRepository.findOne(saveId));
    }

    @Test(expected = IllegalStateException.class)       //간편하게 쓸수있음
    public void duplicateMembers() {
        //given
        Member member1 = new Member();
        member1.setName("Kim1");

        Member member2 = new Member();
        member2.setName("Kim");
        
        //when
        memberService.join(member1);
        memberService.join(member2);    //예외가 발생해야한다.!!

        //then
        fail("예외가 발생해야 한다.");

    }

    @Test
    public void findOne() {
    }
}