package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional       //test실행 하기 전에 transactional을 실행하고 다 끝나고 rollback 시켜줌 => 반복적으로 test code를 실핼할 수 있게 함
class MemberServiceIntegrationTest {
    //cmd + shift + T 사용해 create test code

    @Autowired MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

    @Test
    //특별히 영어를 사용해야하는 경우가 아니라면 한글로 함수명 작성 가능 -> test code 부분은 build 에서 제외됨.
    void 회원가입() throws Exception{
        Member member = new Member();
        member.setName("spring 100");

        Long saveId = memberService.join(member);

        Member findMember = memberRepository.findById(saveId).get();
        assertEquals(member.getName(), findMember.getName());
    }

    @Test
    void 중복_회원_예외() throws Exception{
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        //예외가 발생해야한다.

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
    }
}