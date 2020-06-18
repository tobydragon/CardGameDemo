package edu.ithaca.dragon.blackjack;

import edu.ithaca.dragon.blackjack.Card;
import edu.ithaca.dragon.blackjack.Hand;
import edu.ithaca.dragon.blackjack.Player;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {
    @Test
    public void constructorTest(){
        Player p1 = new Player("0");
        assertEquals("0", p1.getID());


        ArrayList<Card> c1 = new ArrayList<>();
        c1.add(new Card(Card.Suit.CLUB, 1));
        c1.add(new Card(Card.Suit.DIAMOND, 1));
        c1.add(new Card(Card.Suit.HEART, 1));
        c1.add(new Card(Card.Suit.CLUB, 13));
        c1.add(new Card(Card.Suit.SPADE, 2));

        BettingHand h1 = new BettingHand(c1);
        p1 = new Player("1", h1);
        assertEquals("1", p1.getID());
        assertEquals(5, p1.getBettingHand().getCards().size());
        assertArrayEquals(c1.toArray(), p1.getBettingHand().getCards().toArray());

        /*ArrayList<Card> c2 = new ArrayList<>();
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
        p1 = new Player("2", hands);
        assertEquals("2", p1.getID());
        assertEquals(4, p1.getHands().size());
        assertEquals(5, p1.getHands().get(0).getCards().size());
        assertArrayEquals(c1.toArray(), p1.getHands().get(0).getCards().toArray());
        assertEquals(5, p1.getHands().get(1).getCards().size());
        assertArrayEquals(c2.toArray(), p1.getHands().get(1).getCards().toArray());
        assertEquals(5, p1.getHands().get(2).getCards().size());
        assertArrayEquals(c3.toArray(), p1.getHands().get(2).getCards().toArray());
        assertEquals(5, p1.getHands().get(3).getCards().size());
        assertArrayEquals(c4.toArray(), p1.getHands().get(3).getCards().toArray());*/
    }

    @Test
    public void CompareToTest(){
        Player p1 = new Player("0");
        Player p2 = new Player("1");
        Player p3 = new Player("2");
        Player p4 = new Player("1");
        assertEquals(-1, p2.compareTo(p3));
        assertEquals(1, p2.compareTo(p1));
        assertEquals(0, p2.compareTo(p4));
    }

    @Test
    public void numCardsTest(){
        BettingHand h1 = new BettingHand();
        Player p1 = new Player("0", h1);
        assertEquals(0, p1.numCards());


        ArrayList<Card> c1 = new ArrayList<>();
        c1.add(new Card(Card.Suit.CLUB, 1));
        c1.add(new Card(Card.Suit.DIAMOND, 1));
        c1.add(new Card(Card.Suit.HEART, 1));
        c1.add(new Card(Card.Suit.CLUB, 13));
        c1.add(new Card(Card.Suit.SPADE, 2));

        BettingHand h2 = new BettingHand(c1);
        Player p2 = new Player("0", h2);
        assertEquals(5, p2.numCards());
    }

    @Test
    void addCardTest(){
        BettingHand h1 = new BettingHand();
        Player p1 = new Player("0", h1);
        assertEquals(0, p1.numCards());


        ArrayList<Card> c1 = new ArrayList<>();
        c1.add(new Card(Card.Suit.CLUB, 1));
        c1.add(new Card(Card.Suit.DIAMOND, 1));
        c1.add(new Card(Card.Suit.HEART, 1));
        c1.add(new Card(Card.Suit.CLUB, 13));
        c1.add(new Card(Card.Suit.SPADE, 2));

        p1.addCardToHand(c1.get(0));
        assertEquals(1, p1.numCards());
        assertEquals(c1.get(0).getSuit(), p1.getBettingHand().getCards().get(0).getSuit());
        assertEquals(c1.get(0).getValue(), p1.getBettingHand().getCards().get(0).getValue());

        p1.addCardToHand(c1.get(1));
        assertEquals(2, p1.numCards());
        assertEquals(c1.get(1).getSuit(), p1.getBettingHand().getCards().get(1).getSuit());
        assertEquals(c1.get(1).getValue(), p1.getBettingHand().getCards().get(1).getValue());

        p1.addCardToHand( c1.get(2));
        assertEquals(3, p1.numCards());
        assertEquals(c1.get(2).getSuit(), p1.getBettingHand().getCards().get(2).getSuit());
        assertEquals(c1.get(2).getValue(), p1.getBettingHand().getCards().get(2).getValue());

        p1.addCardToHand(c1.get(3));
        assertEquals(4, p1.numCards());
        assertEquals(c1.get(3).getSuit(), p1.getBettingHand().getCards().get(3).getSuit());
        assertEquals(c1.get(3).getValue(), p1.getBettingHand().getCards().get(3).getValue());

        p1.addCardToHand(c1.get(4));
        assertEquals(5, p1.numCards());
        assertEquals(c1.get(4).getSuit(), p1.getBettingHand().getCards().get(4).getSuit());
        assertEquals(c1.get(4).getValue(), p1.getBettingHand().getCards().get(4).getValue());

        Exception e = assertThrows(IllegalArgumentException.class, ()-> p1.addCardToHand( c1.get(4)));
        //assertEquals("Cannot add duplicate card to hand", e.getMessage());

        e = assertThrows(IllegalArgumentException.class, ()-> p1.addCardToHand(null));
        //assertEquals("Cannot add null card to hand", e.getMessage());
    }

    @Test
    public void bettingHandTest(){
        Player p1 = new Player("test");
        p1.setBalance(60);
        p1.addBet(20.001);
        assertEquals(20.00, p1.getBet());
        assertEquals(40, p1.getBalance());

        assertThrows(IllegalArgumentException.class, ()-> p1.addBet(50));
        p1.clearBet();

        assertEquals(0, p1.getBet());
        assertEquals(40, p1.getBalance());
    }

    @Test
    public void dealWithBetTest(){
        Player p1 = new Player("test", 10000);
        BlackJack b1 = new BlackJack("Test", p1);
        p1.setGame(b1);
        Hand dh = b1.getDealerHand();
        BettingHand ph = p1.getBettingHand();

        dh.addCard(new Card(Card.Suit.SPADE, 8)); //lose
        dh.addCard(new Card(Card.Suit.SPADE, 12));
        ph.addBet(1000);
        ph.addCard(new Card(Card.Suit.SPADE, 7));
        ph.addCard(new Card(Card.Suit.SPADE, 11));
        p1.dealWithBet();
        assertEquals(9000, p1.getBalance());
        assertEquals(0, ph.getBet());
        dh.clearCards();
        ph.clearCards();

        dh.addCard(new Card(Card.Suit.SPADE, 8)); //win
        dh.addCard(new Card(Card.Suit.SPADE, 12));
        ph.addBet(1000);
        ph.addCard(new Card(Card.Suit.SPADE, 9));
        ph.addCard(new Card(Card.Suit.SPADE, 11));
        p1.dealWithBet();
        assertEquals(10000, p1.getBalance());
        assertEquals(0, ph.getBet());
        dh.clearCards();
        ph.clearCards();

        dh.addCard(new Card(Card.Suit.SPADE, 8));//blackjack
        dh.addCard(new Card(Card.Suit.SPADE, 12));
        ph.addBet(1000);
        ph.addCard(new Card(Card.Suit.SPADE, 1));
        ph.addCard(new Card(Card.Suit.SPADE, 11));
        p1.dealWithBet();
        assertEquals(11500, p1.getBalance());
        assertEquals(0, ph.getBet());
        dh.clearCards();
        ph.clearCards();

        dh.addCard(new Card(Card.Suit.SPADE, 8));//blackjack on not first turn so counts as win not blackjack
        dh.addCard(new Card(Card.Suit.SPADE, 12));
        ph.addBet(1000);
        ph.addCard(new Card(Card.Suit.SPADE, 9));
        ph.addCard(new Card(Card.Suit.SPADE, 11));
        ph.addCard(new Card(Card.Suit.SPADE, 2));
        p1.dealWithBet();
        assertEquals(12500, p1.getBalance());
        assertEquals(0, ph.getBet());
        dh.clearCards();
        ph.clearCards();

        dh.addCard(new Card(Card.Suit.SPADE, 8));//blackjack on not first turn so counts as win not blackjack
        dh.addCard(new Card(Card.Suit.SPADE, 12));
        ph.addBet(1000);
        ph.addCard(new Card(Card.Suit.SPADE, 6));
        ph.addCard(new Card(Card.Suit.SPADE, 11));
        ph.addCard(new Card(Card.Suit.SPADE, 2));
        p1.dealWithBet();
        assertEquals(12500, p1.getBalance());
        assertEquals(0, ph.getBet());
    }

}
