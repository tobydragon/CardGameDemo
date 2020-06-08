package edu.ithaca.dragon.spring;

import edu.ithaca.dragon.blackjack.*;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class BlackJackController {

    private Map<String, BlackJack> games;
    private AtomicLong ID;

    public BlackJackController(){
        games = new HashMap<>();
        Player p1 = new Player("0", new Hand());
        p1.addCardToHand(0, new Card(Card.Suit.SPADE, 1));
        p1.addCardToHand(0, new Card(Card.Suit.SPADE, 13));
        BlackJack b1 = new BlackJack("0", p1);
        games.put(b1.getID(), b1);
        Player p2 = new Player("0", new Hand());
        p2.addCardToHand(0, new Card(Card.Suit.DIAMOND, 10));
        p2.addCardToHand(0, new Card(Card.Suit.DIAMOND, 12));
        BlackJack b2 = new BlackJack("2", p2);
        BlackJack b3 = new BlackJack("test", new Player("0"));
        games.put(b2.getID(), b2);
        games.put(b3.getID(), b3);
        ID = new AtomicLong(3);
    }

    @GetMapping("/api/blackjack/{id}")
    public Hand getHand(@PathVariable String id){
        if(!games.containsKey(id)) return null;
        return games.get(id).getHands().get(0);
    }

    @PostMapping(path = "/api/blackjack/newgame")
    public String newGame(String player){
        Player p1 = new Player(player);
        String id = String.format("%07d", this.ID.getAndIncrement());
        if(games.containsKey(id)) throw new IllegalArgumentException("Game already Exists"); // This should never happen
        games.put(id, new BlackJack(id, p1));
        return id;
    }

    @PostMapping(path = "/api/blackjack/{id}/deal")
    public Hand deal(@PathVariable String id){
        if(!games.containsKey(id)) throw new GameDoesNotExist("Game does not exist");
        games.get(id).deal();
        return games.get(id).getHands().get(0);
    }


}
