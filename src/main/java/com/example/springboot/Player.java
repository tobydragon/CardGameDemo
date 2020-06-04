package com.example.springboot;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class Player {
    private final int ID;
    private ArrayList<Hand> hands;

    public Player(int IDin){
        ID = 0;
    }
    public Player(int IDin, Hand startingHand){
        ID = 0;
    }
    public Player(int IDin, ArrayList<Hand> startingHands){
        ID = 0;
    }

    public ArrayList<Hand> getHands() {
        return hands;
    }

    public int getID() {
        return ID;
    }
}
