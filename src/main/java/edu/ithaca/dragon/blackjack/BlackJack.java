package edu.ithaca.dragon.blackjack;

import java.util.*;

public class BlackJack {
    private final String ID;
    private Map<String, Player> players;
    private BettingHand playerHand;
    private Deck deck;
    private Hand dealer;
    private int gameState;

    public BlackJack(String IDin, Player playerIn){
        ID = IDin;
        players = new HashMap<>();
        players.put(playerIn.getID(), playerIn);
        playerIn.setBettingHand(new BettingHand(0.00));
        playerHand = playerIn.getBettingHand();
        deck = new Deck();
        dealer = new Hand();
        gameState = 0;
    }

    public String getID() {
        return ID;
    }

    public Hand getDealerHand() { return dealer; }

    public List<Player> getPlayers() {
        return new ArrayList<>(players.values());
    }

    public Deck getDeck() { return deck; }

    public BettingHand getPlayerHand() {
        return playerHand;
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }

    public int getGameState() {
        return gameState;
    }

    public void setGameState(int gameState) {
        this.gameState = gameState;
    }

    public void deal(){
        playerHand.clearCards();
        dealer.clearCards();
        deck.shuffle();
        for(int x =0; x < 2; x++){
            playerHand.addCard(deck.getNextCard());
            dealer.addCard(deck.getNextCard());
        }

    }

    public enum RoundState{
        BETTING, PLAYING, WON_BLACKJACK, WON_DEALER_BUST, WON_BEAT_DEALER, LOST_PLAYER_BUST, LOST_DEALER_BEATS_PLAYER, PUSH
    }

    public void hit() throws NoMoreCardsException{
        try{
            playerHand.addCard(deck.getNextCard());
        }
        catch(NoMoreCardsException e){
            throw new NoMoreCardsException(e.getMessage());
        }
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

    public RoundState stay(){
        if(assessHand(playerHand) > 21) return RoundState.LOST_PLAYER_BUST;
        takeDealerTurn();
        if(assessHand(dealer) > 21) return RoundState.WON_DEALER_BUST;
        int win = compareHands(playerHand, dealer);
        if(win == 0) return RoundState.PUSH;
        return win > 0 ? RoundState.LOST_DEALER_BEATS_PLAYER:RoundState.WON_BEAT_DEALER;
    }

    public static int compareHands(Hand rhs, Hand lhs){
        if (assessHand(rhs) == assessHand(lhs)) return 0;
        return assessHand(rhs) > assessHand(lhs) ? -1 : 1;
    }
}
