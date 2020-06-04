package com.example.springboot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Deck {
    private final Card[] deck;
    private final Card[] notInDeck;

    public Deck(){
        deck = new Card[52];
        notInDeck = new Card[0];
        /*for(int y = 0; y < 4; y++){
            Card.Suit suit = Card.Suit.values()[y];
            for(int x = 1; x < 14; x++){
                deck[(y * 13) + x - 1] = new Card(suit, x);
            }
        }*/
    }

    public Card[] getDeck() {
        return deck;
    }

    public Card[] getNotInDeck() {
        return notInDeck;
    }
}
