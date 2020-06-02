package com.example.springboot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Deck {
    private final Card[] deck;

    public Deck(boolean shuffle){
        deck = new Card[52];
        for(int y = 0; y < 4; y++){
            Card.Suit suit = Card.Suit.values()[y];
            for(int x = 1; x < 14; x++){
                deck[(y * 13) + x - 1] = new Card(suit, x);
            }
        }

        if(shuffle){
            Random r = new Random();
            for(int x = 7; x > 0; x--){
                for(int y = 0; y < 52; y++){
                    int index = r.nextInt(52);
                    Card temp = deck[y];
                    deck[y] = deck[index];
                    deck[index] = temp;
                }
            }
        }

    }

    public Card[] getDeck() {
        return deck;
    }
}
