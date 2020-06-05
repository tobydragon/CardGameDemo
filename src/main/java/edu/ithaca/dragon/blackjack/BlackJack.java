package edu.ithaca.dragon.blackjack;

import java.util.ArrayList;
import java.util.Collections;

public class BlackJack {
    private final long ID;
    private ArrayList<Player> players;
    private ArrayList<Hand> hands;
    private Deck deck;

    public BlackJack(long IDin, Player playerIn){
        ID = IDin;
        players = new ArrayList<>();
        players.add(playerIn);
        hands = (ArrayList<Hand>)playerIn.getHands().clone();
        deck = new Deck();
    }
    public BlackJack(long IDin, ArrayList<Player> playerIn)throws IllegalArgumentException{
        ID = IDin;
        ArrayList<Player> toSort = (ArrayList<Player>)playerIn.clone();
        Collections.sort(toSort);
        for(int x = 0; x < toSort.size()-1; x++){
            if(toSort.get(x).compareTo(toSort.get(x+1)) == 0)
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

    public long getID() {
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
