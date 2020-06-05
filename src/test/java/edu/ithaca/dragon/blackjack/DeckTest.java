package edu.ithaca.dragon.blackjack;

import edu.ithaca.dragon.blackjack.Card;
import edu.ithaca.dragon.blackjack.Deck;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

public class DeckTest {

    @Test
    public void constructorTest(){
        Deck d1 = new Deck();
        ArrayList<Card> c1 = d1.getDeck();
        assertEquals(52, c1.size());
        for(int x = 0; x < 4; x ++){
            for(int y = 1; y < 14; y++){
                assertEquals(Card.Suit.values()[x], c1.get(x * 13 + y - 1).getSuit());
                assertEquals(y, c1.get(x * 13 + y - 1).getValue());
            }
        }
        assertEquals(0, d1.getNotInDeck().size());
    }

    @Test
    public void getNextCardTest(){
        Deck d1 = new Deck();
        Card c1 = d1.getNextCard();
        assertEquals(0, c1.compareTo(new Card(Card.Suit.SPADE, 1)));
        assertEquals(51, d1.getDeck().size());
        assertEquals(1, d1.getNotInDeck().size());
        c1 = d1.getNextCard();
        assertEquals(0, c1.compareTo(new Card(Card.Suit.SPADE, 2)));
        assertEquals(50, d1.getDeck().size());
        assertEquals(2, d1.getNotInDeck().size());
        for(int x = 0; x < 50; x++){
            d1.getNextCard();
        }
        assertThrows(NoMoreCardsException.class, ()-> d1.getNextCard());
    }

    @Test
    public void numCardsInDeckTest(){
        Deck d1 = new Deck();
        assertEquals(52, d1.numCardsInDeck());
        for(int x = 0; x < 20; x++){
            d1.getNextCard();
        }
        assertEquals(32, d1.numCardsInDeck());

        for(int x = 0; x < 32; x ++){
            d1.getNextCard();
        }
        assertEquals(0, d1.numCardsInDeck());
    }

    @Test
    public void shuffleTest(){
        Deck d1 = new Deck();

        d1.shuffle();
        ArrayList<Card> c1 = new ArrayList<>();
        for(int y = 0; y < 4; y++){
            Card.Suit suit = Card.Suit.values()[y];
            for(int x = 1; x < 14; x++){
                c1.add(new Card(suit, x));
            }
        }
        ArrayList<Card> deck = new ArrayList<>(d1.getDeck());
        assertEquals(52, deck.size());
        for(int x = 0; x < 52; x++){
            boolean found = false;
            for(int y = 0; y < 52; y++){
                if(c1.get(x).compareTo(deck.get(y)) == 0)
                    found = true;
            }
            assertTrue(found);
        }
        int count = 0;
        for(int x = 0; x < 52; x ++){
            if(c1.get(x).compareTo(deck.get(x)) == 0)
                count ++;
        }
        assertTrue(count < 4);
        d1.shuffle();
        ArrayList<Card> deck2 = d1.getDeck();
        int count1 = 0;
        int count2 = 0;
        for(int x = 0; x < 52; x++){
            if(c1.get(x).compareTo(deck2.get(x)) == 0)
                count1 ++;
            if(deck2.get(x).compareTo(deck.get(x)) == 0)
                count2 ++;
        }
        //assertEquals(4, count2);
        assertTrue(count1 < 4);
        assertTrue(count2 < 4);
        for(int x = 0; x < 20; x ++){
            d1.getNextCard();
        }
        assertEquals(32, d1.numCardsInDeck());
        d1.shuffle();
        assertEquals(52, d1.numCardsInDeck());

    }


}
