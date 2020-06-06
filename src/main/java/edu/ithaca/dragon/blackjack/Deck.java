package edu.ithaca.dragon.blackjack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Deck {
    private ArrayList<Card> deck;
    private ArrayList<Card> notInDeck;

    public Deck(){
        deck = new ArrayList<>();
        notInDeck = new ArrayList<>();
        for(int y = 0; y < 4; y++){
            Card.Suit suit = Card.Suit.values()[y];
            for(int x = 1; x < 14; x++){
                deck.add(new Card(suit, x));
            }
        }
    }

    public ArrayList<Card> getDeck() {
        return deck;
    }

    public ArrayList<Card> getNotInDeck() {
        return notInDeck;
    }

    public Card getNextCard() throws NoMoreCardsException{
        if(deck.size() <= 0)throw new NoMoreCardsException("No More Cards");
        Card rtn = deck.get(0);
        notInDeck.add(rtn);
        deck.remove(0);
        return rtn;
    }

    public void shuffle(){
        if(notInDeck.size() > 0) {
            for (int x = 0; x < notInDeck.size(); x++) {
                deck.add(notInDeck.get(x));
            }
            notInDeck.clear();
        }
        for(int x = 0; x < 7; x ++){
            Collections.shuffle(deck);
        }
    }
    public void shuffleRemaining(){
        for(int x = 0; x < 7; x ++)
            Collections.shuffle(deck);
    }

    public int numCardsInDeck(){
        return deck.size();
    }

    public Card lookTopCard() throws NoMoreCardsException{
        if(deck.size() == 0)throw new NoMoreCardsException("No more cards in deck");
        return deck.get(0);
    }
}
