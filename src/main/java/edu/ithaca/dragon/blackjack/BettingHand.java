package edu.ithaca.dragon.blackjack;

import java.text.DecimalFormat;
import java.util.List;

public class BettingHand extends Hand{

    private double bet;
    public static DecimalFormat df = new DecimalFormat("0.00");

    public BettingHand(List<Card> cards, double bet){
        super(cards);
        this.bet = Double.parseDouble(df.format(bet));
    }
    public BettingHand(double bet){
        super();
        this.bet = Double.parseDouble(df.format(bet));
    }
    public BettingHand(){
        super();
        this.bet = 0.00;
    }

    public BettingHand(List<Card> cards){
        super(cards);
        this.bet = 0.00;
    }

    public void addBet(double bet){
        this.bet += bet;
        this.bet = Double.parseDouble(df.format(this.bet));
    }

    public double getBet() {
        return bet;
    }

    public void clearBet(){
        this.bet = 0.00;
    }
}
