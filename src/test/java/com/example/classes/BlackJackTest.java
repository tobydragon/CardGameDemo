package com.example.classes;

import com.example.springboot.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.*;

public class BlackJackTest {

    @Test
    public void ConstructorTest(){
        BlackJack b1 = new BlackJack(0, new Player(0));
        assertEquals(0, b1.getID());
        assertEquals(0, b1.getHands().size());
        assertEquals(1, b1.getPlayers().size());
        assertNotEquals(null, b1.getDeck());

        Player p1 = new Player(2, new Hand());
        b1 = new BlackJack(1, p1);
        assertEquals(1, b1.getID());
        assertEquals(1, b1.getHands().size());
        assertEquals(1, b1.getPlayers().size());
        assertNotEquals(null, b1.getDeck());


        ArrayList<Card> c1 = new ArrayList<>();
        c1.add(new Card(Card.Suit.CLUB, 1));
        c1.add(new Card(Card.Suit.DIAMOND, 1));
        c1.add(new Card(Card.Suit.HEART, 1));
        c1.add(new Card(Card.Suit.CLUB, 13));
        c1.add(new Card(Card.Suit.SPADE, 2));
        Hand h1 = new Hand(c1);
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

        Player p2 = new Player(1, hands);
        BlackJack b2 = new BlackJack(0, p2 );
        assertEquals(0, b2.getID());
        assertEquals(4, b2.getHands().size());
        assertEquals(1, b2.getPlayers().size());
        assertNotEquals(null, b2.getDeck());

        ArrayList<Player> players = new ArrayList<>();
        players.add(p1);
        players.add(p2);
        BlackJack b3 = new BlackJack(0, players);
        assertEquals(0, b3.getID());
        assertEquals(5, b3.getHands().size());
        assertEquals(2, b3.getPlayers().size());
        assertNotEquals(null, b3.getDeck());

        Player p3 = new Player(1);
        players.add(p3);
        try{
            BlackJack b4 = new BlackJack(0, players);
            fail();
        }
        catch(IllegalArgumentException e){
            assertEquals("Cannot have 2 players with the same ID", e.getMessage());
        }

    }

}
