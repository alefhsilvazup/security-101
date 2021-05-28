package br.com.zupedu.ot5.stitchsecurity101;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SeguroController {

    @GetMapping("/seguro")
    public String seguro() {
        return "segura";
    }
}
