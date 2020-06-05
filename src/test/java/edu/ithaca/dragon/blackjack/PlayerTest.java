package edu.ithaca.dragon.blackjack;

import edu.ithaca.dragon.blackjack.Card;
import edu.ithaca.dragon.blackjack.Hand;
import edu.ithaca.dragon.blackjack.Player;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {
    @Test
    public void constructorTest(){
        AtomicInteger ID = new AtomicInteger(0);
        Player p1 = new Player(ID.getAndIncrement());
        assertEquals(0, p1.getID());
        assertEquals(0, p1.getHands().size());


        ArrayList<Card> c1 = new ArrayList<>();
        c1.add(new Card(Card.Suit.CLUB, 1));
        c1.add(new Card(Card.Suit.DIAMOND, 1));
        c1.add(new Card(Card.Suit.HEART, 1));
        c1.add(new Card(Card.Suit.CLUB, 13));
        c1.add(new Card(Card.Suit.SPADE, 2));

        Hand h1 = new Hand(c1);
        p1 = new Player(ID.getAndIncrement(), h1);
        assertEquals(1, p1.getID());
        assertEquals(1, p1.getHands().size());
        assertEquals(5, p1.getHands().get(0).getCards().size());
        assertArrayEquals(c1.toArray(), p1.getHands().get(0).getCards().toArray());

        ArrayList<Card> c2 = new ArrayList<>();
        c2.add(new Card(Card.Suit.CLUB, 2));
        c2.add(new Card(Card.Suit.DIAMOND, 2));
        c2.add(new Card(Card.Suit.HEART, 2));
        c2.add(new Card(Card.Suit.CLUB, 12));
        c2.add(new Card(Card.Suit.SPADE, 3));
        Hand h2 = new Hand(c2);
        ArrayList<Card> c3 = new ArrayList<>();
        c3.add(new Card(Card.Suit.CLUB, 3));
        c3.add(new Card(Card.Suit.DIAMOND, 3));
        c3.add(new Card(Card.Suit.HEART, 3));
        c3.add(new Card(Card.Suit.CLUB, 11));
        c3.add(new Card(Card.Suit.SPADE, 4));
        Hand h3 = new Hand(c3);
        ArrayList<Card> c4 = new ArrayList<>();
        c4.add(new Card(Card.Suit.CLUB, 4));
        c4.add(new Card(Card.Suit.DIAMOND, 4));
        c4.add(new Card(Card.Suit.HEART, 4));
        c4.add(new Card(Card.Suit.CLUB, 10));
        c4.add(new Card(Card.Suit.SPADE, 5));
        Hand h4 = new Hand(c4);
        ArrayList<Hand> hands = new ArrayList<>();
        hands.add(h1);
        hands.add(h2);
        hands.add(h3);
        hands.add(h4);
        p1 = new Player(ID.getAndIncrement(), hands);
        assertEquals(2, p1.getID());
        assertEquals(4, p1.getHands().size());
        assertEquals(5, p1.getHands().get(0).getCards().size());
        assertArrayEquals(c1.toArray(), p1.getHands().get(0).getCards().toArray());
        assertEquals(5, p1.getHands().get(1).getCards().size());
        assertArrayEquals(c2.toArray(), p1.getHands().get(1).getCards().toArray());
        assertEquals(5, p1.getHands().get(2).getCards().size());
        assertArrayEquals(c3.toArray(), p1.getHands().get(2).getCards().toArray());
        assertEquals(5, p1.getHands().get(3).getCards().size());
        assertArrayEquals(c4.toArray(), p1.getHands().get(3).getCards().toArray());
    }

    @Test
    public void CompareToTest(){
        Player p1 = new Player(0);
        Player p2 = new Player(1);
        Player p3 = new Player(2);
        Player p4 = new Player(1);
        assertEquals(1, p2.compareTo(p3));
        assertEquals(-1, p2.compareTo(p1));
        assertEquals(0, p2.compareTo(p4));
    }

    @Test
    public void numCardsTest(){
        Hand h1 = new Hand();
        Player p1 = new Player(0, h1);
        assertEquals(0, p1.numCards());


        ArrayList<Card> c1 = new ArrayList<>();
        c1.add(new Card(Card.Suit.CLUB, 1));
        c1.add(new Card(Card.Suit.DIAMOND, 1));
        c1.add(new Card(Card.Suit.HEART, 1));
        c1.add(new Card(Card.Suit.CLUB, 13));
        c1.add(new Card(Card.Suit.SPADE, 2));

        Hand h2 = new Hand(c1);
        Player p2 = new Player(0, h2);
        assertEquals(5, p2.numCards());
    }

}
