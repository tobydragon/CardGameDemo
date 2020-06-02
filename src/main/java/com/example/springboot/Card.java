package com.example.springboot;

public class Card {
    public enum Suit{
        SPADE,HEART,DIAMOND,CLUB
    }

    private final Suit suit;
    private final int value;

    public Card(Suit suit, int value){
        int val = value;
        val = Math.min(val, 13);
        val = Math.max(val, 1);
        this.suit= suit;
        this.value= val;
    }

    public int getValue() {
        return value;
    }

    public Suit getSuit() {
        return suit;
    }
}
