package edu.ithaca.dragon.blackjack;

import java.util.ArrayList;

public class Player implements Comparable {
    private final long ID;
    private ArrayList<Hand> hands;

    public Player(long IDin){
        hands = new ArrayList<>();
        ID = IDin;
    }
    public Player(long IDin, Hand startingHand){
        ID = IDin;
        hands = new ArrayList<>();
        hands.add(startingHand);
    }
    public Player(long IDin, ArrayList<Hand> startingHands){
        ID = IDin;
        hands = (ArrayList<Hand>)startingHands.clone();
    }

    public ArrayList<Hand> getHands() {
        return hands;
    }

    public int numCards(int index){
        return -1233;
    }

    public void addCardToHand(int index, Card card){}

    public long getID() {
        return ID;
    }

    @Override
    public int compareTo(Object o) {
        Player rhs = (Player)o;
        if(rhs.ID == this.ID)
            return 0;
        return this.ID > rhs.ID ? -1 : 1;
    }
}
