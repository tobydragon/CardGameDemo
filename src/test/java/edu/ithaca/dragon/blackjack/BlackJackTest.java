package edu.ithaca.dragon.blackjack;

import edu.ithaca.dragon.blackjack.BlackJack;
import edu.ithaca.dragon.blackjack.Card;
import edu.ithaca.dragon.blackjack.Hand;
import edu.ithaca.dragon.blackjack.Player;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.swing.text.BadLocationException;
import java.util.ArrayList;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

public class BlackJackTest {

    @Test
    public void ConstructorTest(){
        BlackJack b1 = new BlackJack("0", new Player("0"));
        assertEquals("0", b1.getID());
        assertEquals(1, b1.getPlayers().size());
        assertNotEquals(null, b1.getDeck());

        Player p1 = new Player("2", new BettingHand());
        b1 = new BlackJack("1", p1);
        assertEquals("1", b1.getID());
        assertEquals(1, b1.getPlayers().size());
        assertNotEquals(null, b1.getDeck());


        /*ArrayList<Card> c1 = new ArrayList<>();  //NOT CURRENTLY RELEVENT AS WE ONLY HAVE ONE HAND PER BLACKJACKGAME CURRENTLY
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

        Player p2 = new Player("1", hands);
        BlackJack b2 = new BlackJack("0", p2 );
        assertEquals("0", b2.getID());
        assertEquals(4, b2.getHands().size());
        assertEquals(1, b2.getPlayers().size());
        assertNotEquals(null, b2.getDeck());*/
    }

    @Test
    public void dealTest(){
        /*Player p1 = new Player("2", new BettingHand());
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
        Player p2 = new Player("1", hands);
        ArrayList<Player> players = new ArrayList<>();
        players.add(p1);
        players.add(p2);
        BlackJack b1 = new BlackJack("0", players );

        assertEquals(5, b1.getHands().size());
        b1.deal();
        assertEquals(2, b1.getHands().size());*/
        BlackJack b1 = new BlackJack("id", new Player("woot"));
        b1.deal();
        assertEquals(2, b1.getDealerHand().numCards());
        ArrayList<Card> c = new ArrayList<>(b1.getDealerHand().getCards());
        assertEquals(2, b1.getPlayerHand().numCards());
        c.addAll(b1.getPlayerHand().getCards());

        assertEquals(4, c.size());
        Collections.sort(c);
        for(int x = 0; x < c.size()-1; x ++){
            assertNotEquals(0, c.get(x).compareTo(c.get(x + 1)));
        }

        assertEquals(48, b1.getDeck().numCardsInDeck());

    }

    @Test
    public void hitTest(){
        BlackJack b1 = new BlackJack("0", new Player("0"));

        b1.deal();
        assertEquals(2, b1.getPlayerHand().numCards());
        b1.hit();
        assertEquals(3, b1.getPlayerHand().numCards());
        b1.hit();
        assertEquals(4, b1.getPlayerHand().numCards());
        for(int x = 0; x < 46; x++){
            b1.hit();
        }
        assertThrows(NoMoreCardsException.class, ()-> b1.hit());

        BlackJack b2 = new BlackJack("0", new Player("0"));
        b2.hit();
        assertTrue(BlackJack.assessHand(b2.getPlayerHand()) < 21);
        b2.hit();
        assertTrue(BlackJack.assessHand(b2.getPlayerHand()) < 21);
        b2.hit();
        assertTrue(BlackJack.assessHand(b2.getPlayerHand()) < 21);
        b2.hit();
        assertTrue(BlackJack.assessHand(b2.getPlayerHand()) < 21);
        b2.hit();
        assertTrue(BlackJack.assessHand(b2.getPlayerHand()) < 21);
        b2.hit();
        assertTrue(BlackJack.assessHand(b2.getPlayerHand()) == 21);
        b2.hit();
        assertTrue(BlackJack.assessHand(b2.getPlayerHand()) > 21);

    }

    @Test
    public void assessHandTest(){
        Hand h = new Hand();
        h.addCard(new Card(Card.Suit.SPADE, 2));
        h.addCard(new Card(Card.Suit.SPADE, 9));
        h.addCard(new Card(Card.Suit.SPADE, 10));
        assertEquals(21, BlackJack.assessHand(h));
        h.clearCards();
        h.addCard(new Card(Card.Suit.DIAMOND, 2));
        h.addCard(new Card(Card.Suit.SPADE, 9));
        h.addCard(new Card(Card.Suit.SPADE, 10));
        assertEquals(21, BlackJack.assessHand(h));
        h.clearCards();
        h.addCard(new Card(Card.Suit.HEART, 2));
        h.addCard(new Card(Card.Suit.SPADE, 9));
        h.addCard(new Card(Card.Suit.SPADE, 10));
        assertEquals(21, BlackJack.assessHand(h));
        h.clearCards();
        h.addCard(new Card(Card.Suit.CLUB, 2));
        h.addCard(new Card(Card.Suit.SPADE, 9));
        h.addCard(new Card(Card.Suit.SPADE, 10));
        assertEquals(21, BlackJack.assessHand(h));
        h.clearCards();
        h.addCard(new Card(Card.Suit.SPADE, 2));
        h.addCard(new Card(Card.Suit.DIAMOND, 9));
        h.addCard(new Card(Card.Suit.SPADE, 10));
        assertEquals(21, BlackJack.assessHand(h));


        h.clearCards();
        h.addCard(new Card(Card.Suit.SPADE, 1));
        h.addCard(new Card(Card.Suit.SPADE, 10));
        assertEquals(21, BlackJack.assessHand(h));
        h.clearCards();
        h.addCard(new Card(Card.Suit.SPADE, 1));
        h.addCard(new Card(Card.Suit.SPADE, 11));
        assertEquals(21, BlackJack.assessHand(h));
        h.clearCards();
        h.addCard(new Card(Card.Suit.SPADE, 1));
        h.addCard(new Card(Card.Suit.SPADE, 12));
        assertEquals(21, BlackJack.assessHand(h));
        h.clearCards();
        h.addCard(new Card(Card.Suit.SPADE, 1));
        h.addCard(new Card(Card.Suit.SPADE, 13));
        assertEquals(21, BlackJack.assessHand(h));

        h.clearCards();
        h.addCard(new Card(Card.Suit.SPADE, 2));
        h.addCard(new Card(Card.Suit.SPADE, 10));
        h.addCard(new Card(Card.Suit.CLUB, 10));
        assertEquals(22, BlackJack.assessHand(h));
        h.clearCards();
        h.addCard(new Card(Card.Suit.SPADE, 6));
        h.addCard(new Card(Card.Suit.SPADE, 11));
        h.addCard(new Card(Card.Suit.CLUB, 8));
        assertEquals(24, BlackJack.assessHand(h));
        h.clearCards();
        h.addCard(new Card(Card.Suit.SPADE, 2));
        h.addCard(new Card(Card.Suit.SPADE, 10));
        h.addCard(new Card(Card.Suit.CLUB, 7));
        assertEquals(19, BlackJack.assessHand(h));
        h.clearCards();
        h.addCard(new Card(Card.Suit.SPADE, 2));
        h.addCard(new Card(Card.Suit.CLUB, 3));
        assertEquals(5, BlackJack.assessHand(h));

        h.clearCards();
        h.addCard(new Card(Card.Suit.SPADE, 1));
        h.addCard(new Card(Card.Suit.SPADE, 10));
        h.addCard(new Card(Card.Suit.CLUB, 9));
        assertEquals(20, BlackJack.assessHand(h));
        h.clearCards();
        h.addCard(new Card(Card.Suit.SPADE, 2));
        h.addCard(new Card(Card.Suit.SPADE, 10));
        h.addCard(new Card(Card.Suit.CLUB, 11));
        assertEquals(22, BlackJack.assessHand(h));

        h.clearCards();
        h.addCard(new Card(Card.Suit.SPADE, 1));
        h.addCard(new Card(Card.Suit.SPADE, 5));
        assertEquals(16, BlackJack.assessHand(h));
        h.addCard(new Card(Card.Suit.SPADE, 13));
        assertEquals(16, BlackJack.assessHand(h));

    }

    @Test
    public void dealerTest(){
        BlackJack b1 = new BlackJack("0", new Player("test"));
        b1.deal();
        assertEquals(2,b1.getDealerHand().numCards());
        ArrayList<Card> c1 = b1.getDeck().getNotInDeck();
        assertEquals(0, c1.get(1).compareTo(b1.getDealerHand().getCards().get(0)));
        assertEquals(0, c1.get(3).compareTo(b1.getDealerHand().getCards().get(1)));
        //not in deck should be the first four cards in order of the deck after it was shuffled
        //As such the second and four cards should be the dealers because of dealing protocol (player then dealer then player then dealer)
        b1.deal();
        assertEquals(2, b1.getDealerHand().numCards());


        //take dealer turn
        b1 = new BlackJack("0", new Player("test"));
        ArrayList<Card> deck = new ArrayList<>(new Deck().getDeck());
        Card card = deck.get(2);
        deck.remove(2);
        deck.add(1,card);
        card = deck.get(0);
        deck.remove(0);
        deck.add(card);
        Deck d = new Deck();
        d.setDeck(deck);
        b1.setDeck(d);
        b1.getDealerHand().addCard(b1.getDeck().getNextCard());
        b1.getDealerHand().addCard(b1.getDeck().getNextCard());
        assertEquals(0, b1.getDealerHand().getCards().get(0).compareTo(new Card(Card.Suit.SPADE, 3)));
        assertEquals(0, b1.getDealerHand().getCards().get(1).compareTo(new Card(Card.Suit.SPADE, 2)));

        b1.takeDealerTurn();
        assertEquals(20, BlackJack.assessHand(b1.getDealerHand()));

        b1 = new BlackJack("0", new Player("0"));
        b1.getDealerHand().addCard(b1.getDeck().getNextCard());
        b1.getDealerHand().addCard(b1.getDeck().getNextCard());
        b1.takeDealerTurn();
        assertEquals(20, BlackJack.assessHand(b1.getDealerHand()));

        for(int x = 0;x < 100; x ++){
            b1.deal();
            b1.takeDealerTurn();
            assertTrue(BlackJack.assessHand(b1.getDealerHand()) >= 17);
        }
    }

    @Test
    public void compareHandsTest(){
        Hand h1 = new Hand();
        h1.addCard(new Card(Card.Suit.SPADE, 1));
        h1.addCard(new Card(Card.Suit.DIAMOND, 13));
        Hand h2 = new Hand();
        h2.addCard(new Card(Card.Suit.DIAMOND, 3));
        h2.addCard(new Card(Card.Suit.DIAMOND, 4));
        h2.addCard(new Card(Card.Suit.DIAMOND, 5));
        h2.addCard(new Card(Card.Suit.DIAMOND, 6));
        Hand h3 = new Hand();
        h3.addCard(new Card(Card.Suit.HEART, 8));
        h3.addCard(new Card(Card.Suit.CLUB, 11));

        assertEquals(-1, BlackJack.compareHands(h1, h2));
        assertEquals(1, BlackJack.compareHands(h2, h1));
        assertEquals(0, BlackJack.compareHands(h2, h3));
        assertEquals(0, BlackJack.compareHands(h2, h2));
    }

    @Test
    public void stayTest(){
        BlackJack b1 = new BlackJack("0", new Player(""));
        Deck deck = b1.getDeck();
        b1.getDealerHand().addCard(deck.getNextCard());
        b1.getPlayerHand().addCard(deck.getNextCard());
        b1.getDealerHand().addCard(deck.getNextCard());
        b1.getPlayerHand().addCard(deck.getNextCard());

        b1.hit();
        b1.hit();
        assertEquals(14,BlackJack.assessHand(b1.getDealerHand()));
        assertEquals(17, BlackJack.assessHand(b1.getPlayerHand()));
        assertEquals(BlackJack.RoundState.LOST_DEALER_BEATS_PLAYER, b1.stay());

        b1 = new BlackJack("0", new Player(""));
        deck = b1.getDeck();
        b1.getPlayerHand().addCard(deck.getNextCard());
        b1.getDealerHand().addCard(deck.getNextCard());
        b1.getPlayerHand().addCard(deck.getNextCard());
        b1.getDealerHand().addCard(deck.getNextCard());

        b1.hit();

        assertEquals(6,BlackJack.assessHand(b1.getDealerHand()));
        assertEquals(19, BlackJack.assessHand(b1.getPlayerHand()));
        assertEquals(BlackJack.RoundState.PUSH, b1.stay());

        b1 = new BlackJack("0", new Player(""));
        deck = b1.getDeck();
        deck.getNextCard();
        b1.getPlayerHand().addCard(deck.getNextCard());
        b1.getDealerHand().addCard(deck.getNextCard());
        b1.getPlayerHand().addCard(deck.getNextCard());
        b1.getDealerHand().addCard(deck.getNextCard());

        b1.hit();
        b1.hit();

        assertEquals(8,BlackJack.assessHand(b1.getDealerHand()));
        assertEquals(19, BlackJack.assessHand(b1.getPlayerHand()));
        assertEquals(BlackJack.RoundState.WON_DEALER_BUST, b1.stay());

    }

}
