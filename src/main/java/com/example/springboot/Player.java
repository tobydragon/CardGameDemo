package com.example.springboot;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class Player implements Comparable {
    private final int ID;
    private ArrayList<Hand> hands;

    public Player(int IDin){
        hands = new ArrayList<>();
        ID = IDin;
    }
    public Player(int IDin, Hand startingHand){
        ID = IDin;
        hands = new ArrayList<>();
        hands.add(startingHand);
    }
    public Player(int IDin, ArrayList<Hand> startingHands){
        ID = IDin;
        hands = (ArrayList<Hand>)startingHands.clone();
    }

    public ArrayList<Hand> getHands() {
        return hands;
    }

    public int getID() {
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
