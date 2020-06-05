package edu.ithaca.dragon.blackjack;

import edu.ithaca.dragon.blackjack.Card;
import edu.ithaca.dragon.blackjack.Deck;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

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


}
