package edu.ithaca.dragon.blackjack;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class BlackJack {
    private final String ID;
    private List<Player> players;
    private List<Hand> hands;
    private Deck deck;

    public BlackJack(String IDin, Player playerIn){
        ID = IDin;
        players = new ArrayList<>();
        players.add(playerIn);
        hands = new ArrayList<>(playerIn.getHands());
        deck = new Deck();
    }
    public BlackJack(String IDin, ArrayList<Player> playerIn)throws IllegalArgumentException{
        ID = IDin;

        //This section duplicates playerIn and sorts it so that if there are duplicates they would be next to each other.
        //The duplication is to preserve the original order of the incoming players.
        //It then checks all side by side paris and if any of them are the same it throws an error
        List<Player> toSort = new ArrayList<>(playerIn);
        Collections.sort(toSort);
        for(int x = 0; x < toSort.size()-1; x++){
            if(toSort.get(x).compareTo(toSort.get(x+1)) == 0)
                throw new IllegalArgumentException("Cannot have 2 players with the same ID");
        }


        players = new ArrayList<>(playerIn);
        hands = new ArrayList<>();
        for(Player p : players){
            for(Hand h: p.getHands()){
                hands.add(h);
            }
        }
        deck = new Deck();
    }

    public String getID() {
        return ID;
    }

    public List<Hand> getHands() {
        return hands;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public Deck getDeck() {
        return deck;
    }
}
