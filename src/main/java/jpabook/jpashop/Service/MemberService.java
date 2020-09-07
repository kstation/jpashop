package jpabook.jpashop.Service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
//@AllArgsConstructor
//final 변수에 대해 생성자 세팅을 자동 생성
@RequiredArgsConstructor
public class MemberService {
    
    private final MemberRepository memberRepository;

    /*
    이런식으로 java bean에 injection 가능하나 requireargsconstructor annotation가 요즘 대세
    private MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }
    @Autowired
    public void setMemberRepository(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }
    */

    //클래스 레벨의 트랜잭션 레벨보다 메소드 레벨이 우선시한다
    //회원 가입
    @Transactional
    public Long join(Member member){
        validateDuplicateMember(member);
        memberRepository.save(member);
        
        return member.getId();
    }

    //중복회원 검증
    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.findByName(member.getName());
        
        if(!findMembers.isEmpty()){
            throw new IllegalStateException("is exist member");
        }
    }

    //회원 전체 조회
    public List<Member> findMember(){
        return memberRepository.findAll();
    }
    
    //회원 한건 조회
    public Member findOne(Long memberId){
        return  memberRepository.findOne(memberId);
    }
     
    
}
