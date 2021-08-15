package hello.spring.service;

import hello.spring.domain.Member;
import hello.spring.repository.MemberRepository;
import hello.spring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
class MemberServiceIntegrationTest {

    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository repository;


    @Test
    void 회원가입() {
        // given
        Member member = new Member();
        member.setName("hello2");
        // when
        Long saveId = memberService.join(member);
        // then
        Member member1 = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(member1.getName());
    }

    @Test
    public void 중복_회원_예외(){
        //given
        Member member = new Member();
        member.setName("spring");

        Member member1 = new Member();
        member1.setName("spring");

        //when
        memberService.join(member);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member1));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        //        try {
//            memberService.join(member1);
//            fail();
//        }catch(IllegalStateException e){
//            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.2323");
//        }

        //then

    }
}