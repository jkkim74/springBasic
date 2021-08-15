package hello.spring.controller;

import hello.spring.domain.Member;
import hello.spring.service.EmployeeService;
import hello.spring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {

    private final MemberService memberService;

    private final EmployeeService employeeService;

    @Autowired
    public MemberController(MemberService memberService, EmployeeService employeeService) {
        this.memberService = memberService;
        this.employeeService = employeeService;
    }

    @GetMapping("/members/new")
    public String createMemberForm(){
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form){
        Member member = new Member();
        member.setName(form.getName());
        memberService.join(member);
        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members",members);
        return "members/memberList";
    }
}
