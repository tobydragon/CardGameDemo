package com.example.classes;


import com.example.springboot.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.*;

public class HandTest {

    @Test
    public void ConstructorTest(){
        Hand h1 = new Hand();
        assertEquals(0, h1.getCards().size());

        ArrayList<Card> c1 = new ArrayList<>();
        c1.add(new Card(Card.Suit.CLUB, 1));
        c1.add(new Card(Card.Suit.DIAMOND, 1));
        c1.add(new Card(Card.Suit.HEART, 1));
        c1.add(new Card(Card.Suit.CLUB, 13));
        c1.add(new Card(Card.Suit.SPADE, 2));

        h1 = new Hand(c1);
        assertEquals(5, h1.getCards().size());
        assertArrayEquals(c1.toArray(), h1.getCards().toArray());

        c1.add(new Card(Card.Suit.CLUB, 1));
        Exception e = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Hand h2 = new Hand(c1);
        });
        assertEquals("Cannot have duplicate cards in hand", e.getMessage());
    }



}
