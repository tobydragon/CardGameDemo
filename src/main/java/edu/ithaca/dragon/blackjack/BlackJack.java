package edu.ithaca.dragon.blackjack;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class BlackJack {
    private final String ID;
    private List<Player> players;
    private List<Hand> hands;
    private Deck deck;
    private Hand dealer;

    public BlackJack(String IDin, Player playerIn){
        ID = IDin;
        players = new ArrayList<>();
        players.add(playerIn);
        if(playerIn.getHands().size() <= 0)
            playerIn.addHand(new Hand());
        hands = new ArrayList<>(playerIn.getHands());
        deck = new Deck();
        dealer = new Hand();
    }
    public BlackJack(String IDin, ArrayList<Player> playerIn)throws IllegalArgumentException{
        ID = IDin;

        //This section duplicates playerIn and sorts it so that if there are duplicates they would be next to each other.
        //The duplication is to preserve the original order of the incoming players.
        //It then checks all side by side paris and if any of them are the same it throws an error
        List<Player> toSort = new ArrayList<>(playerIn);
        Collections.sort(toSort);
        for(int x = 0; x < toSort.size()-1; x++){
            if(toSort.get(x).compareTo(toSort.get(x+1)) == 0)
                throw new IllegalArgumentException("Cannot have 2 players with the same ID");
        }


        players = new ArrayList<>(playerIn);
        hands = new ArrayList<>();
        for(Player p : players){
            if(p.getHands().size() <= 0)
                p.addHand(new Hand());
            hands.addAll(p.getHands());
        }
        deck = new Deck();
        dealer = new Hand();
    }

    public String getID() {
        return ID;
    }

    public List<Hand> getHands() {
        return hands;
    }

    public Hand getHand(int handIndex){ return hands.get(handIndex);    }

    public Hand getDealerHand() { return dealer; }

    public List<Player> getPlayers() {
        return players;
    }

    public Deck getDeck() { return deck; }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }

    public void deal(){
        hands.clear();
        for(Player p : players){
            p.clearHands();
            p.addHand(new Hand());
            hands.add(p.getHands().get(0));
        }
        dealer = new Hand();
        deck.shuffle();
        for(int x =0; x < 2; x++){
            for(Hand h: hands){
                h.addCard( deck.getNextCard());
            }
            dealer.addCard(deck.getNextCard());
        }

    }

    public enum BlackJackState{
        UNDER,BLACKJACK,BUST;
        public static BlackJackState toState(int val){
            if(val > 21) return BUST;
            if(val < 21) return UNDER;
            return BLACKJACK;
        }
    }
    public enum WinState{
        WIN, LOSE, TIE, NONE
    }

    public BlackJackState hit() throws NoMoreCardsException{
        try{
            hands.get(0).addCard(deck.getNextCard());
        }
        catch(NoMoreCardsException e){
            throw new NoMoreCardsException(e.getMessage());
        }
        return BlackJackState.toState(assessHand(hands.get(0)));
    }

    public static int assessHand(Hand h){
        List<Card> cards = h.getCards();
        int ace = 0;
        int total = 0;
        for(Card c: cards){
            if(c.getValue() == 1){
                total += 11;
                ace++;
            }
            else total += Math.min(c.getValue(), 10);
        }
        while(ace > 0 && total > 21){
            total -= 10;
            ace --;
        }
        return total;
    }

    public void takeDealerTurn(){
        while (assessHand(dealer) < 17 && deck.getDeck().size() > 0){
            dealer.addCard(deck.getNextCard());
        }
    }

    public WinState stay(){
        if(assessHand(hands.get(0)) > 21) return WinState.LOSE;
        takeDealerTurn();
        if(assessHand(dealer) > 21) return WinState.WIN;
        int win = compareHands(hands.get(0), dealer);
        if(win == 0) return WinState.TIE;
        return win > 0 ? WinState.LOSE:WinState.WIN;
    }

    public static int compareHands(Hand rhs, Hand lhs){
        if (assessHand(rhs) == assessHand(lhs)) return 0;
        return assessHand(rhs) > assessHand(lhs) ? -1 : 1;
    }
}
