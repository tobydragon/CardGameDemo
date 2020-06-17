package edu.ithaca.dragon.blackjack;

import edu.ithaca.dragon.spring.GameAlreadyExist;
import edu.ithaca.dragon.spring.GameDoesNotExist;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Player implements Comparable {
    private final String ID;
    private List<Hand> hands;
    private BlackJack game;

    public Player(String IDin){
        hands = new ArrayList<>();
        ID = IDin;
        game = null;
    }
    public Player(String IDin, Hand startingHand){
        ID = IDin;
        hands = new ArrayList<>();
        hands.add(startingHand);
        game = null;
    }
    public Player(String IDin, List<Hand> startingHands){
        ID = IDin;
        hands = new ArrayList<>(startingHands);
        game = null;
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
     * This replaces the current game with the input game
     * @param game
     *
     */
    public void setGame(BlackJack game){
        this.game = game;
    }

    public void removeGame(String id){
        this.game = null;
    }

    public BlackJack getGame(){
        return this.game;
    }

    @Override
    public int compareTo(Object o) {
        Player rhs = (Player)o;
        return this.ID.compareTo(rhs.ID);
    }
}
