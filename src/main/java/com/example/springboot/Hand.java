package com.example.springboot;

import java.util.ArrayList;
import java.util.Collections;

public class Hand {
    private ArrayList<Card> cards;

    public Hand(ArrayList<Card> startingCards) throws IllegalArgumentException{
        cards = new ArrayList<>();
        Collections.sort(startingCards);
        for(int x = 0; x < startingCards.size() -1; x ++){
            if(startingCards.get(x).compareTo(startingCards.get(x + 1)) == 0)
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
}
