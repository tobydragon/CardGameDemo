package edu.ithaca.dragon.spring;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Controller
public class ReactController  implements WebMvcConfigurer {

    @GetMapping("/")
    public String getIndex() {
        return "index";
    }
}
