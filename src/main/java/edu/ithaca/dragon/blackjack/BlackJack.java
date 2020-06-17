package edu.ithaca.dragon.blackjack;

import java.util.*;

public class BlackJack {
    private final String ID;
    private Map<String, Player> players;
    private List<Hand> hands;
    private BettingHand playerHand;
    private Deck deck;
    private Hand dealer;

    public BlackJack(String IDin, Player playerIn){
        ID = IDin;
        players = new HashMap<>();
        players.put(playerIn.getID(), playerIn);
        if(playerIn.getHands().size() <= 0)
            playerIn.addHand(new Hand());
        hands = new ArrayList<>(playerIn.getHands());
        playerIn.setBettingHand(new BettingHand(0.0));
        playerHand = playerIn.getBettingHand();
        deck = new Deck();
        dealer = new Hand();
    }
    public BlackJack(String IDin, ArrayList<Player> playerIn)throws IllegalArgumentException{
        ID = IDin;

        players = new HashMap<>();
        for(Player p: playerIn){
            if(players.containsKey(p.getID())) throw new IllegalArgumentException("Cannot have 2 players with the same ID");
            players.put(p.getID(), p);
        }
        hands = new ArrayList<>();
        for(Player p : players.values()){
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
        return new ArrayList<>(players.values());
    }

    public Deck getDeck() { return deck; }

    public BettingHand getPlayerHand() {
        return playerHand;
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }

    public void deal(){
        hands.clear();
        for(Player p : players.values()){
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

    public enum RoundState{
        BETTING, PLAYING, WON_BLACKJACK, WON_DEALER_BUST, WON_BEAT_DEALER, LOST_PLAYER_BUST, LOST_DEALER_BEATS_PLAYER, PUSH
    }

    public void hit() throws NoMoreCardsException{
        try{
            hands.get(0).addCard(deck.getNextCard());
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
        if(assessHand(hands.get(0)) > 21) return RoundState.LOST_PLAYER_BUST;
        takeDealerTurn();
        if(assessHand(dealer) > 21) return RoundState.WON_DEALER_BUST;
        int win = compareHands(hands.get(0), dealer);
        if(win == 0) return RoundState.PUSH;
        return win > 0 ? RoundState.LOST_DEALER_BEATS_PLAYER:RoundState.WON_BEAT_DEALER;
    }

    public static int compareHands(Hand rhs, Hand lhs){
        if (assessHand(rhs) == assessHand(lhs)) return 0;
        return assessHand(rhs) > assessHand(lhs) ? -1 : 1;
    }
}
