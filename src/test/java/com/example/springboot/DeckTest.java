package com.example.springboot;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DeckTest {

    @Test
    public void constructorTest(){
        Deck d1 = new Deck();
        Card[] c1 = d1.getDeck();
        assertEquals(52, c1.length);
        for(int x = 0; x < 4; x ++){
            for(int y = 0; y < 14; y++){
                assertEquals(Card.Suit.values()[x], c1[x * 13 + y].getSuit());
                assertEquals(y, c1[x * 13 + y].getValue());
            }
        }
        assertEquals(0, d1.getNotInDeck().length);
    }
}
