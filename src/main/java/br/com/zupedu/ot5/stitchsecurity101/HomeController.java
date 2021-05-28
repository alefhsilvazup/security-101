package br.com.zupedu.ot5.stitchsecurity101;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller // -> MVC -> Retornar uma jsp/html
public class HomeController {


    @GetMapping("/")
    public String home() {
        return "home";
    }
}

