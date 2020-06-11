package edu.ithaca.dragon.spring;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class HelloController {

    @RequestMapping("/api")
    public String apiIndex() {
        return "Greetings from Spring Boot!";
    }

}