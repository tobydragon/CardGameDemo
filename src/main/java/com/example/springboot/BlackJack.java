package com.example.springboot;

import java.util.ArrayList;
import java.util.Map;

public class BlackJack {
    private final int ID;
    private ArrayList<Player> players;
    private ArrayList<Hand> hands;
    private Deck deck;

    public BlackJack(int IDin, Player playerIn){
        ID = 0;
    }
    public BlackJack(int IDin, ArrayList<Player> playerIn){
        ID = 0;
    }

    public int getID() {
        return ID;
    }

    public ArrayList<Hand> getHands() {
        return hands;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public Deck getDeck() {
        return deck;
    }
}
