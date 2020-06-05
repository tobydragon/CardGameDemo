package edu.ithaca.dragon.blackjack;

import java.util.ArrayList;
import java.util.Collections;

public class Hand {
    private ArrayList<Card> cards;

    public Hand(ArrayList<Card> startingCards) throws IllegalArgumentException{
        cards = new ArrayList<>();
        ArrayList<Card> toSort = (ArrayList<Card>)startingCards.clone();
        Collections.sort(toSort);
        for(int x = 0; x < toSort.size() -1; x ++){
            if(toSort.get(x).compareTo(toSort.get(x + 1)) == 0)
                throw new IllegalArgumentException("Cannot have duplicate cards in hand");
        }
        cards = (ArrayList<Card>)startingCards.clone();
    }
    public Hand(){
        cards = new ArrayList<>();
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public int numCards(){return cards.size();}

    public void addCard(Card cardIn){}
}
