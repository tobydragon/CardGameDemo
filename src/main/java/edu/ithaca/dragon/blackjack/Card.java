package edu.ithaca.dragon.blackjack;

public class Card implements Comparable{
    @Override
    public int compareTo(Object o) {
        Card rhs = (Card)o;
        if(this.value > rhs.value)
            return -1;
        else if(this.value < rhs.value)
            return 1;
        else if(this.suit == rhs.suit)
            return 0;
        else{
            int lI = -1, rI = -1;
            for(int x = 0; x < 4; x ++){
                if(this.suit == Suit.values()[x])
                    lI = x;
                if(rhs.suit == Suit.values()[x])
                    rI = x;
            }
            return lI > rI ? 1:-1;
        }
    }

    public enum Suit{
        SPADE,HEART,DIAMOND,CLUB
    }

    private Suit suit;
    private int value;

    public Card(Suit suit, int value){
        int val = value;
        val = Math.min(val, 13);
        val = Math.max(val, 1);
        this.suit= suit;
        this.value= val;
    }

    public Card(){
        suit = null;
        value = 0;
    }

    public int getValue() {
        return value;
    }

    public Suit getSuit() {
        return suit;
    }

    public void setSuit(Suit suit){
        this.suit = suit;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "" + value + " of " + suit;
    }
}
