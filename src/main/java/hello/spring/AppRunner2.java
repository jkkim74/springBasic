package hello.spring;

import hello.spring.domain.Member;
import hello.spring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AppRunner2 implements ApplicationRunner {

    @Autowired
    MemberService memberService;
    @Override
    public void run(ApplicationArguments args) throws Exception {

        List<Member> members = memberService.findMembers();
        members.forEach(System.out::println);


    }
}
