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
}
