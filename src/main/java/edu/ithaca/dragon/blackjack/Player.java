package edu.ithaca.dragon.blackjack;

import edu.ithaca.dragon.spring.GameAlreadyExist;
import edu.ithaca.dragon.spring.GameDoesNotExist;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Player implements Comparable {
    private final String ID;
    private List<Hand> hands;
    private Map<String, BlackJack> games;

    public Player(String IDin){
        hands = new ArrayList<>();
        ID = IDin;
        games = new HashMap<>();
    }
    public Player(String IDin, Hand startingHand){
        ID = IDin;
        hands = new ArrayList<>();
        hands.add(startingHand);
        games = new HashMap<>();
    }
    public Player(String IDin, List<Hand> startingHands){
        ID = IDin;
        hands = new ArrayList<>(startingHands);
        games = new HashMap<>();
    }

    public List<Hand> getHands() {
        return hands;
    }

    public int numCards(int index){
        return hands.get(index).numCards();
    }

    public void addCardToHand(int handIndex, Card card) throws IllegalArgumentException{
        hands.get(handIndex).addCard(card);
    }

    public String getID() {
        return ID;
    }

    public void addHand(Hand hand){
        hands.add(hand);
    }

    public void clearHands(){
        hands.clear();
    }

    /**
     * Only add the game to player if player is not already in a game with ID of game.
     * See replaceGame to replace a game that already exist or add if no game with same ID exist in player
     * @param game to add
     * @throws GameAlreadyExist if player is already in game with game's ID.
     */
    public void addGame(BlackJack game) throws GameAlreadyExist {
        if(games.containsKey(game.getID())) throw new GameAlreadyExist("The Player: " + this.ID + " is already in the game with ID: " + game.getID());
        this.games.put(game.getID(), game);
    }

    public void replaceGame(BlackJack game){
        this.games.put(game.getID(), game);
    }
    public void removeGame(String id) throws GameDoesNotExist{
        if(!games.containsKey(id))throw new GameDoesNotExist("The Player: " + this.ID + " is not in game with ID: " + id);
        games.remove(id);
    }

    public BlackJack getGame(String id) throws GameDoesNotExist{
        if(!games.containsKey(ID))throw new GameDoesNotExist("The Player: " + this.ID + " is not in game with ID: " + id);
        return games.get(ID);
    }

    @Override
    public int compareTo(Object o) {
        Player rhs = (Player)o;
        return this.ID.compareTo(rhs.ID);
    }
}
