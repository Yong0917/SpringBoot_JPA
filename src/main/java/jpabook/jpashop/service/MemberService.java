package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)     //읽기에는 웬만하면 readonly넣어줌
@RequiredArgsConstructor      //final잡혀있는거는 자동으로 생성자 만들어줌
public class MemberService {    //test 커맨드 쉬프트 t

    private final MemberRepository memberRepository;        //final넣어주는거 추천 웬만하면 안바뀌기때문에

//    @Autowired  //생성자가 하나일경우는 생략해도댐
//    public MemberService(MemberRepository memberRepository) {       //생성자 메소드 생성할때 단축키 커맨드 n  생성자에서 injection해주기때문에 더 안전
//        this.memberRepository = memberRepository;
//    }

    /**
     *  회원가입
     */
    @Transactional
    public Long join(Member member){
        validateDuplicateMember(member);        //중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        //EXCEPTION
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if(!findMembers.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    /**
     *  회원 전체 조회
     */
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    /**
     *  회원 한명 조회
     */
    public Member findOne(Long memberId){
        return memberRepository.findOne(memberId);
    }
}
