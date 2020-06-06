package edu.ithaca.dragon.spring;

import edu.ithaca.dragon.blackjack.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
public class BlackJackController {

    private Map<Long, BlackJack> games;

    public BlackJackController(){
        games = new HashMap<>();
        Player p1 = new Player(0, new Hand());
        p1.addCardToHand(0, new Card(Card.Suit.SPADE, 1));
        p1.addCardToHand(0, new Card(Card.Suit.SPADE, 13));
        BlackJack b1 = new BlackJack(0, p1);
        games.put(b1.getID(), b1);
        Player p2 = new Player(0, new Hand());
        p2.addCardToHand(0, new Card(Card.Suit.DIAMOND, 10));
        p2.addCardToHand(0, new Card(Card.Suit.DIAMOND, 12));
        BlackJack b2 = new BlackJack(2, p2);
        games.put(b2.getID(), b2);
    }

    @GetMapping("/api/blackjack/{id}")
    public Hand getHand(@PathVariable Long id){
        if(!games.containsKey(id)) return null;
        return games.get(id).getHands().get(0);
    }


}
