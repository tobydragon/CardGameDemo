package edu.ithaca.dragon.blackjack;


import edu.ithaca.dragon.blackjack.Card;
import edu.ithaca.dragon.blackjack.Hand;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

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

    @Test
    public void numCardsTest(){
        Hand h1 = new Hand();
        assertEquals(0, h1.numCards());


        ArrayList<Card> c1 = new ArrayList<>();
        c1.add(new Card(Card.Suit.CLUB, 1));
        c1.add(new Card(Card.Suit.DIAMOND, 1));
        c1.add(new Card(Card.Suit.HEART, 1));
        c1.add(new Card(Card.Suit.CLUB, 13));
        c1.add(new Card(Card.Suit.SPADE, 2));

        Hand h2 = new Hand(c1);
        assertEquals(5, h2.numCards());
    }

    @Test
    public void AddCardTest(){
        Hand h1 = new Hand();
        assertEquals(0, h1.numCards());


        ArrayList<Card> c1 = new ArrayList<>();
        c1.add(new Card(Card.Suit.CLUB, 1));
        c1.add(new Card(Card.Suit.DIAMOND, 1));
        c1.add(new Card(Card.Suit.HEART, 1));
        c1.add(new Card(Card.Suit.CLUB, 13));
        c1.add(new Card(Card.Suit.SPADE, 2));

        h1.addCard(c1.get(0));
        assertEquals(1, h1.numCards());
        assertEquals(c1.get(0).getSuit(), h1.getCards().get(0).getSuit());
        assertEquals(c1.get(0).getValue(), h1.getCards().get(0).getValue());

        h1.addCard(c1.get(1));
        assertEquals(2, h1.numCards());
        assertEquals(c1.get(1).getSuit(), h1.getCards().get(1).getSuit());
        assertEquals(c1.get(1).getValue(), h1.getCards().get(1).getValue());

        h1.addCard(c1.get(2));
        assertEquals(3, h1.numCards());
        assertEquals(c1.get(2).getSuit(), h1.getCards().get(2).getSuit());
        assertEquals(c1.get(2).getValue(), h1.getCards().get(2).getValue());

        h1.addCard(c1.get(3));
        assertEquals(4, h1.numCards());
        assertEquals(c1.get(3).getSuit(), h1.getCards().get(3).getSuit());
        assertEquals(c1.get(3).getValue(), h1.getCards().get(3).getValue());

        h1.addCard(c1.get(4));
        assertEquals(5, h1.numCards());
        assertEquals(c1.get(4).getSuit(), h1.getCards().get(4).getSuit());
        assertEquals(c1.get(4).getValue(), h1.getCards().get(4).getValue());

        Exception e = assertThrows(IllegalArgumentException.class, ()-> h1.addCard(c1.get(4)));
        assertEquals("Cannot add duplicate card to hand", e.getMessage());

        e = assertThrows(IllegalArgumentException.class, ()-> h1.addCard(null));
        assertEquals("Cannot add null card to hand", e.getMessage());

    }



}
