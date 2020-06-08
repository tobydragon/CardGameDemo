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
        if(playerIn.getHands().size() <= 0)
            playerIn.addHand(new Hand());
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
            if(p.getHands().size() <= 0)
                p.addHand(new Hand());
            hands.addAll(p.getHands());
        }
        deck = new Deck();
    }

    public String getID() {
        return ID;
    }

    public List<Hand> getHands() {
        return hands;
    }

    public Hand getHand(int handIndex){
        return hands.get(handIndex);
    }

    public List<Player> getPlayers() {
        return players;
    }

    public Deck getDeck() {
        return deck;
    }

    public void deal(){
        hands.clear();
        for(Player p : players){
            p.clearHands();
            p.addHand(new Hand());
            hands.add(p.getHands().get(0));
        }
        deck.shuffle();
        for(int x =0; x < 2; x++){
            for(Player p: players){
                p.addCardToHand(0, deck.getNextCard());
            }
        }

    }
    public void hit() throws NoMoreCardsException{
        try{
            hands.get(0).addCard(deck.getNextCard());
        }
        catch(NoMoreCardsException e){
            throw new NoMoreCardsException(e.getMessage());
        }
    }
}
