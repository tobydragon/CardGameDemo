package com.example.springboot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

public class BlackJack {
    private final int ID;
    private ArrayList<Player> players;
    private ArrayList<Hand> hands;
    private Deck deck;

    public BlackJack(int IDin, Player playerIn){
        ID = IDin;
        players = new ArrayList<>();
        players.add(playerIn);
        hands = (ArrayList<Hand>)playerIn.getHands().clone();
        deck = new Deck();
    }
    public BlackJack(int IDin, ArrayList<Player> playerIn)throws IllegalArgumentException{
        ID = IDin;
        Collections.sort(playerIn);
        for(int x = 0; x < playerIn.size()-1; x++){
            if(playerIn.get(x).compareTo(playerIn.get(x+1)) == 0)
                throw new IllegalArgumentException("Cannot have 2 players with the same ID");
        }
        players = (ArrayList<Player>)playerIn.clone();
        hands = new ArrayList<>();
        for(Player p : players){
            for(Hand h: p.getHands()){
                hands.add(h);
            }
        }
        deck = new Deck();
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
