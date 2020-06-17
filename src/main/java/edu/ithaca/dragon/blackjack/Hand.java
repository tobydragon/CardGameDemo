package edu.ithaca.dragon.blackjack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Hand {
    private ArrayList<Card> cards;

    public Hand(List<Card> startingCards) throws IllegalArgumentException{
        cards = new ArrayList<>();
        ArrayList<Card> toSort = new ArrayList<>(startingCards);
        Collections.sort(toSort);
        for(int x = 0; x < toSort.size() -1; x ++){
            if(toSort.get(x).compareTo(toSort.get(x + 1)) == 0)
                throw new IllegalArgumentException("Cannot have duplicate cards in hand");
        }
        cards = new ArrayList<>(startingCards);
    }
    public Hand(){
        cards = new ArrayList<>();
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public void setCards(ArrayList<Card> cards) {
        this.cards = cards;
    }

    public int numCards(){return cards.size();}

    public void addCard(Card cardIn) throws IllegalArgumentException{
        if(cardIn == null) throw new IllegalArgumentException("Cannot add null card to hand");
        for(Card c: cards){
            if(c.compareTo(cardIn) == 0)
                throw new IllegalArgumentException("Cannot add duplicate card [" + c + "] to hand: " + this);
        }
        cards.add(cardIn);
    }

    public void clearCards(){
        cards.clear();
    }

    @Override
    public String toString() {
        return cards.toString();
    }
}
