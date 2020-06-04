package com.example.springboot;

import java.util.ArrayList;

public class Hand {
    private ArrayList<Card> cards;

    public Hand(ArrayList<Card> startingCards) throws IllegalArgumentException{}
    public Hand(){}

    public ArrayList<Card> getCards() {
        return cards;
    }
}
