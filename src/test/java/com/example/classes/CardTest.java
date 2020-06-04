package com.example.classes;

import com.example.springboot.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;

public class CardTest {

    @Test
    public void CompareToTest(){
        Card c1 = new Card(Card.Suit.DIAMOND, 12);
        assertEquals(-1, c1.compareTo(new Card(Card.Suit.DIAMOND, 11)));
        assertEquals(1, c1.compareTo(new Card(Card.Suit.DIAMOND, 13)));

        assertEquals(-1, c1.compareTo(new Card(Card.Suit.CLUB, 11)));
        assertEquals(-1, c1.compareTo(new Card(Card.Suit.SPADE, 11)));
        assertEquals(-1, c1.compareTo(new Card(Card.Suit.HEART, 11)));

        assertEquals(1, c1.compareTo(new Card(Card.Suit.CLUB, 13)));
        assertEquals(1, c1.compareTo(new Card(Card.Suit.SPADE, 13)));
        assertEquals(1, c1.compareTo(new Card(Card.Suit.HEART, 13)));

        assertEquals(-1, c1.compareTo(new Card(Card.Suit.CLUB, 12)));
        assertEquals(1, c1.compareTo(new Card(Card.Suit.SPADE, 12)));
        assertEquals(1, c1.compareTo(new Card(Card.Suit.HEART, 12)));

        assertEquals(0, c1.compareTo(new Card(Card.Suit.DIAMOND, 12)));
    }


}
