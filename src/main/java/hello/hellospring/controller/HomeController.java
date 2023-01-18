package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    //localhost:8080으로 들어오면 home.html이 열림
    //template가 static보다 우선순위가 높아서 home이 열림
    @GetMapping("/")
    public String home(){
        return "home";
    }
}
