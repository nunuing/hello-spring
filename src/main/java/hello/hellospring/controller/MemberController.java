package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
//Controller annotation이 있으면 객체를 생성해서 spring container 안에 넣어둠
public class MemberController {
    //new로 생성하면 여러 controller에서 memberservice를 생성해서 쓰게 됨.
    private final MemberService memberService;


    @Autowired
    //자동으로 연결을 시켜 줌 Dependency Injection
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
        System.out.println("memberService : " + memberService.getClass());      //aop를 사용하면 실제 클래스가 아닌 프록시가 호출되는 것을 확인 할 수 있음
    }

    @GetMapping("/members/new")
    //조회할 때
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    //값 전달할 때
    public String create(MemberForm form){
        //spring이 setter를 통해 post로 받은 name 값을 설정해줌
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
