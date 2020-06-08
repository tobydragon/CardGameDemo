package edu.ithaca.dragon.blackjack;

import java.util.ArrayList;
import java.util.List;

public class Player implements Comparable {
    private final String ID;
    private List<Hand> hands;

    public Player(String IDin){
        hands = new ArrayList<>();
        ID = IDin;
    }
    public Player(String IDin, Hand startingHand){
        ID = IDin;
        hands = new ArrayList<>();
        hands.add(startingHand);
    }
    public Player(String IDin, List<Hand> startingHands){
        ID = IDin;
        hands = new ArrayList<>(startingHands);
    }

    public List<Hand> getHands() {
        return hands;
    }

    public int numCards(int index){
        return hands.get(index).numCards();
    }

    public void addCardToHand(int index, Card card) throws IllegalArgumentException{
        hands.get(index).addCard(card);
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

    @Override
    public int compareTo(Object o) {
        Player rhs = (Player)o;
        return this.ID.compareTo(rhs.ID);
    }
}
