package edu.ithaca.dragon.spring;

import edu.ithaca.dragon.blackjack.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class BlackJackController {
    private Map<String, BlackJack> games;
    private Map<String, Player> players;
    private AtomicLong ID;

    public BlackJackController(){
        games = createTestGames();
        players = new HashMap<>();

        ID = new AtomicLong(3);
    }

    @GetMapping("/api/blackjack/{id}")
    public GameStateResponse getHand(@PathVariable String id){
        if(!games.containsKey(id)) throw new GameDoesNotExist("Game does not exist");
        return createHandReturn(id, BlackJack.RoundState.BETTING);
    }

    @PostMapping(path = "/api/blackjack/newgame", consumes = MediaType.APPLICATION_JSON_VALUE)
    public TextInJsonResponse newGame(@RequestBody UserContainer in){
        String player = in.getID(); //todo: fix this to not change user ID to lower case
        Player p1 = new Player(player, 0);
        if(players.containsKey(player)) {
            p1 = players.get(player);
        }
        else {
            players.put(player, p1);
        }
        p1.setBalance(in.getBalance());
        String id = String.format("%07d", this.ID.getAndIncrement());
        if(games.containsKey(id)) throw new GameAlreadyExist("Game already Exists"); // This should never happen
        games.put(id, new BlackJack(id, p1));
        p1.setGame(games.get(id));
        return new TextInJsonResponse(id);
    }

    @PostMapping(path = "/api/blackjack/{id}/deal", consumes = MediaType.APPLICATION_JSON_VALUE)
    public GameStateResponse deal(@PathVariable String id, @RequestBody TextInJsonResponse bet) throws InsufficientFunds{
        if(!games.containsKey(id)) throw new GameDoesNotExist("Game does not exist");
        if(games.get(id).getGameState() != BlackJack.GameState.DEALING) throw new InvalidGameAction("Cannot deal in game state "+ games.get(id).getGameState());
        games.get(id).deal();
        double bets = 0;
        try{
            bets = Double.parseDouble(bet.getText());
        }
        catch(NumberFormatException e){
            throw new NumberFormatException(e.getMessage());
        }
        try{
            games.get(id).getPlayers().get(0).addBet(bets);
        }
        catch(IllegalArgumentException e){
            throw new InsufficientFunds("not enough money to make bet");
        }
        games.get(id).setGameState(BlackJack.GameState.PLAYING);
        GameStateResponse hr = createHandReturn(id, BlackJack.RoundState.PLAYING);
        if(hr.getPlayerValue() == 21){
            if(hr.getDealerValue() == 21)
                hr.setState(BlackJack.RoundState.PUSH);
            else
                hr.setState(BlackJack.RoundState.WON_BLACKJACK);
            games.get(id).setGameState(BlackJack.GameState.DEALING);
            games.get(id).getPlayers().get(0).dealWithBet();
        }
        else if(hr.getDealerValue() == 21){
            hr.setState(BlackJack.RoundState.LOST_DEALER_BEATS_PLAYER);
            games.get(id).setGameState(BlackJack.GameState.DEALING);
            games.get(id).getPlayers().get(0).dealWithBet();
        }
        hr.getUser().setBalance(games.get(id).getPlayers().get(0).getBalance());
        return hr;
    }

    @PostMapping(path = "/api/blackjack/{id}/hit")
    public GameStateResponse hit(@PathVariable("id") String id) throws NoMoreCardsException{
        if(!games.containsKey(id)) throw new GameDoesNotExist("Game does not exist");
        if(games.get(id).getGameState() != BlackJack.GameState.PLAYING) throw new InvalidGameAction("Cannot hit in game state "+ games.get(id).getGameState());
        BlackJack.RoundState state = BlackJack.RoundState.PLAYING;
        games.get(id).hit();
        if(BlackJack.assessHand(games.get(id).getPlayerHand()) > 21){
            state = BlackJack.RoundState.LOST_PLAYER_BUST;
            games.get(id).setGameState(BlackJack.GameState.DEALING);
            games.get(id).getPlayers().get(0).dealWithBet();
        }
        GameStateResponse hr = createHandReturn(id, state);
        return hr;
    }

    @PutMapping(path = "/api/blackjack/{id}/stay")
    public GameStateResponse stay(@PathVariable("id") String id){
        if(!games.containsKey(id)) throw new GameDoesNotExist("Game does not exist");
        if(games.get(id).getGameState() != BlackJack.GameState.PLAYING) throw new InvalidGameAction("Cannot stay in game state "+ games.get(id).getGameState());
        games.get(id).setGameState(BlackJack.GameState.DEALING);
        BlackJack.RoundState state = games.get(id).stay();
        games.get(id).getPlayers().get(0).dealWithBet();
        GameStateResponse hr = createHandReturn(id, state);
        return hr;
    }

    @GetMapping(value = "/api/blackjack/user/game", consumes = MediaType.APPLICATION_JSON_VALUE)
    public TextInJsonResponse getGame(@RequestBody TextInJsonResponse in){
        String id = in.getText();
        if(!players.containsKey(id)) throw new PlayerDoesNotExist("Player: " + id + " does not exist");
        if(players.get(id).getGame() == null) return new TextInJsonResponse("");
        return new TextInJsonResponse(players.get(id).getGame().getID());
    }

    @PutMapping(value = "/api/blackjack/user/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public boolean createPlayer(@RequestBody TextInJsonResponse in){
        String id = in.getText();
        if(!players.containsKey(id)){
            players.put(id, new Player(id));
            return true;
        }
        return false;
    }

    public GameStateResponse createHandReturn(String id, BlackJack.RoundState state){
        BettingHand h1 = games.get(id).getPlayerHand();
        Hand h2 = games.get(id).getDealerHand();
        int val = BlackJack.assessHand(h1);
        int dealerVal = BlackJack.assessHand(h2);
        String ID = games.get(id).getPlayers().get(0).getID();
        return new GameStateResponse(h1,h2, state,val, dealerVal, ID, games.get(id).getPlayers().get(0).getBalance());
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Game does not exist")
    @ExceptionHandler(GameDoesNotExist.class)
    public void noGameException(){}

    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Insufficient Funds to make bet")
    @ExceptionHandler(InsufficientFunds.class)
    public void InsufficientFundsHandler(){}

    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Cannot parse input bet to double")
    @ExceptionHandler(NumberFormatException.class)
    public void badBetValue(){}

    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Game already exist")
    @ExceptionHandler(GameAlreadyExist.class)
    public void gameExistException(){}

    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "No more cards in deck")
    @ExceptionHandler(NoMoreCardsException.class)
    public void noCardsException(){}

    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Player Already Exist")
    @ExceptionHandler(PlayerAlreadyExist.class)
    public void playerExistsAlready(){}

    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Player Does Not Exist")
    @ExceptionHandler(PlayerDoesNotExist.class)
    public void playerDoesNotExist(){}

    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Invalid Action in Current Game State")
    @ExceptionHandler(InvalidGameAction.class)
    public void InvalidGameState(){}


    public static Map<String, BlackJack> createTestGames(){
        Map<String, BlackJack> games = new HashMap<>();
        Player p1 = new Player("0", new BettingHand());
        BlackJack b1 = new BlackJack("0", p1);
        Deck d1 = b1.getDeck();
        d1.getDeck().remove(12);
        d1.getDeck().add(1, new Card(Card.Suit.SPADE, 13));
        p1.addCardToHand(d1.getNextCard());
        p1.addCardToHand(d1.getNextCard());
        games.put(b1.getID(), b1);


        Player p2 = new Player("0", new BettingHand());
        BlackJack b2 = new BlackJack("2", p2);
        d1 = b2.getDeck();
        d1.getDeck().remove(35);
        d1.getDeck().remove(36);
        d1.getDeck().add(0,new Card(Card.Suit.DIAMOND, 12));
        d1.getDeck().add(0, new Card(Card.Suit.DIAMOND, 10));
        p2.addCardToHand(d1.getNextCard());
        p2.addCardToHand(d1.getNextCard());

        BlackJack b3 = new BlackJack("test", new Player("0"));
        BlackJack b4 = new BlackJack("test2", new Player("0"));
        b4.setGameState(BlackJack.GameState.PLAYING);
        games.put(b2.getID(), b2);
        games.put(b3.getID(), b3);
        games.put(b4.getID(), b4);

        Player p3 = new Player("0", new BettingHand());
        BlackJack b5 = new BlackJack("stayLose", p3);
        b5.setGameState(BlackJack.GameState.PLAYING);
        p3.setGame(b5);
        d1 = b5.getDeck();
        b5.getDealerHand().addCard(d1.getNextCard());
        p3.addCardToHand(d1.getNextCard());
        b5.getDealerHand().addCard(d1.getNextCard());
        p3.addCardToHand(d1.getNextCard());
        b5.hit();
        b5.hit();
        games.put(b5.getID(), b5);
        return games;
    }


}
