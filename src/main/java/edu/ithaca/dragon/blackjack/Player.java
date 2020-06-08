package edu.ithaca.dragon.blackjack;

import java.util.ArrayList;

public class Player implements Comparable {
    private final String ID;
    private ArrayList<Hand> hands;

    public Player(String IDin){
        hands = new ArrayList<>();
        ID = IDin;
    }
    public Player(String IDin, Hand startingHand){
        ID = IDin;
        hands = new ArrayList<>();
        hands.add(startingHand);
    }
    public Player(String IDin, ArrayList<Hand> startingHands){
        ID = IDin;
        hands = (ArrayList<Hand>)startingHands.clone();
    }

    public ArrayList<Hand> getHands() {
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

    @Override
    public int compareTo(Object o) {
        Player rhs = (Player)o;
        return this.ID.compareTo(rhs.ID);
    }
}
