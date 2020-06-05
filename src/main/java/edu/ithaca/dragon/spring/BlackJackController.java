package edu.ithaca.dragon.spring;

import edu.ithaca.dragon.blackjack.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class BlackJackController {

    private Map<Long, BlackJack> games;

    public BlackJackController(){
        games = new HashMap<>();
    }

    @GetMapping("/api/blackjack/{id}")
    public Hand getHand(@PathVariable Long id){
        return null;
    }


}
