package edu.ithaca.dragon.spring;

import edu.ithaca.dragon.blackjack.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class BlackJackController {


    //ToDO: need to add a few mappings to make new player, get games of player and make sure that updater player/game/server card is complete about 1.5 hours work so far"
    private Map<String, BlackJack> games;
    private Map<String, Player> players;
    private AtomicLong ID;

    public BlackJackController(){
        games = new HashMap<>();
        players = new HashMap<>();

        Player p1 = new Player("0", new Hand());
        BlackJack b1 = new BlackJack("0", p1);
        Deck d1 = b1.getDeck();
        d1.getDeck().remove(12);
        d1.getDeck().add(1, new Card(Card.Suit.SPADE, 13));
        p1.addCardToHand(0,d1.getNextCard());
        p1.addCardToHand(0,d1.getNextCard());
        games.put(b1.getID(), b1);


        Player p2 = new Player("0", new Hand());
        BlackJack b2 = new BlackJack("2", p2);
        d1 = b2.getDeck();
        d1.getDeck().remove(35);
        d1.getDeck().remove(36);
        d1.getDeck().add(0,new Card(Card.Suit.DIAMOND, 12));
        d1.getDeck().add(0, new Card(Card.Suit.DIAMOND, 10));
        p2.addCardToHand(0,d1.getNextCard());
        p2.addCardToHand(0,d1.getNextCard());

        BlackJack b3 = new BlackJack("test", new Player("0"));
        BlackJack b4 = new BlackJack("test2", new Player("0"));
        games.put(b2.getID(), b2);
        games.put(b3.getID(), b3);
        games.put(b4.getID(), b4);

        Player p3 = new Player("0", new Hand());
        BlackJack b5 = new BlackJack("stayLose", p3);
        d1 = b5.getDeck();
        b5.getDealerHand().addCard(d1.getNextCard());
        p3.addCardToHand(0, d1.getNextCard());
        b5.getDealerHand().addCard(d1.getNextCard());
        p3.addCardToHand(0, d1.getNextCard());
        b5.hit();
        b5.hit();
        games.put(b5.getID(), b5);
        ID = new AtomicLong(3);
    }

    @GetMapping("/api/blackjack/{id}")
    public HandReturn getHand(@PathVariable String id){
        if(!games.containsKey(id)) throw new GameDoesNotExist("Game does not exist");
        return createHandReturn(id);
    }

    @PostMapping(path = "/api/blackjack/newgame", consumes = "text/plain")
    public TextInJsonResponse newGame(@RequestBody String player){
        Player p1 = new Player(player);
        if(players.containsKey(player)) p1 = players.get(player);
        else players.put(player, p1);
        String id = String.format("%07d", this.ID.getAndIncrement());
        if(games.containsKey(id)) throw new IllegalArgumentException("Game already Exists"); // This should never happen
        games.put(id, new BlackJack(id, p1));
        p1.addGame(games.get(id));
        return new TextInJsonResponse(id);
    }

    @PostMapping(path = "/api/blackjack/{id}/deal")
    public HandReturn deal(@PathVariable String id){
        if(!games.containsKey(id)) throw new GameDoesNotExist("Game does not exist");
        games.get(id).deal();
        HandReturn hr = createHandReturn(id);
        if(hr.getPlayerValue() == 21){
            if(hr.getDealerValue() == 21)
                hr.setWinState(BlackJack.WinState.TIE);
            else
                hr.setWinState(BlackJack.WinState.WIN);
        }
        else if(hr.getDealerValue() == 21){
            hr.setWinState(BlackJack.WinState.LOSE);
        }
        return hr;
    }

    @PostMapping(path = "/api/blackjack/{id}/hit")
    public HandReturn hit(@PathVariable("id") String id) throws NoMoreCardsException{
        if(!games.containsKey(id)) throw new GameDoesNotExist("Game does not exist");
        games.get(id).hit();
        HandReturn hr = createHandReturn(id);
        if(hr.getState() == BlackJack.BlackJackState.BUST)
            hr.setWinState(BlackJack.WinState.LOSE);
        return hr;
    }

    @PutMapping(path = "/api/blackjack/{id}/stay")
    public HandReturn stay(@PathVariable("id") String id){
        if(!games.containsKey(id)) throw new GameDoesNotExist("Game does not exist");
        BlackJack.WinState win = games.get(id).stay();
        HandReturn hr = createHandReturn(id);
        hr.setWinState(win);
        return hr;
    }

    public HandReturn createHandReturn(String id){
        Hand h1 = games.get(id).getHand(0);
        Hand h2 = games.get(id).getDealerHand();
        int val = BlackJack.assessHand(h1);
        int dealerVal = BlackJack.assessHand(h2);
        String ID = games.get(id).getPlayers().get(0).getID();
        return new HandReturn(h1,h2, BlackJack.BlackJackState.toState(val), val, dealerVal, ID);
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Game does not exist")
    @ExceptionHandler(GameDoesNotExist.class)
    public void noGameException(){}

    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "No more cards in deck")
    @ExceptionHandler(NoMoreCardsException.class)
    public void noCardsException(){}

    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Player Already Exist")
    @ExceptionHandler(PlayerAlreadyExist.class)
    public void playerExistsAlready(){}


}
