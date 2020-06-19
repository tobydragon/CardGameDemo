package edu.ithaca.dragon.blackjack;

import edu.ithaca.dragon.spring.GameAlreadyExist;
import edu.ithaca.dragon.spring.GameDoesNotExist;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Player implements Comparable {
    private final String ID;
    private BlackJack game;
    private BettingHand bettingHand;
    private double balance;

    public Player(String IDin){
        ID = IDin;
        game = null;
        bettingHand = new BettingHand(0.00);
        balance = 0.00;
    }
    public Player(String IDin, double balance){
        this.ID = IDin;
        this.game = null;
        this.bettingHand = new BettingHand(0.00);
        this.balance = Double.parseDouble(BettingHand.df.format(balance));
    }
    public Player(String IDin, BettingHand startingHand){
        ID = IDin;
        game = null;
        bettingHand = startingHand;
        balance = 0.00;
    }

    public int numCards(){
        return bettingHand.numCards();
    }

    public void addCardToHand(Card card) throws IllegalArgumentException{
        bettingHand.addCard(card);
    }

    public String getID() {
        return ID;
    }

    public BettingHand getBettingHand() {
        return bettingHand;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setBettingHand(BettingHand bettingHand) {
        this.bettingHand = bettingHand;
    }

    public void setGame(BlackJack game){
        this.game = game;
    }

    public void removeGame(String id){
        this.game = null;
    }

    public BlackJack getGame(){
        return this.game;
    }

    public void addBet(double amount){
        if(balance - amount < 0.0) throw new IllegalArgumentException("Cannot subtract " + amount + " from balance of " + balance);
        balance -= amount;
        balance = Double.parseDouble(BettingHand.df.format(balance));
        bettingHand.addBet(amount);
    }

    public double getBet() {
        return bettingHand.getBet();
    }

    public void clearBet(){
        bettingHand.clearBet();
    }

    public void dealWithBet(){
        double bet = bettingHand.getBet();
        bettingHand.clearBet();
        if(bettingHand.numCards() == 2 && BlackJack.assessHand(bettingHand) == 21){
            balance += (bet * 2.5);
            balance = Double.parseDouble(BettingHand.df.format(balance));
        }
        else{
            int win = BlackJack.compareHands(bettingHand, game.getDealerHand()); //-1 if bettingHand is bigger, 0 if equal, 1 if dealer hand is larger
            if(BlackJack.assessHand(bettingHand) > 21){} // player bust so they lose
            else if(BlackJack.assessHand(game.getDealerHand()) > 21){ //dealer bust so they win but only if they don't bust
                balance += (bet * 2);
            }
            else if (win == -1) balance += bet * 2; // player wins with bigger hand
            else if (win == 0) balance += bet; //tie so player gets bet back.
        }

    }



    @Override
    public int compareTo(Object o) {
        Player rhs = (Player)o;
        return this.ID.compareTo(rhs.ID);
    }
}
