package edu.ithaca.dragon.blackjack;

public class HandReturn {
    private Hand playerHand;
    private Hand dealerHand;
    private BlackJack.RoundState state;
    private int playerValue;
    private int dealerValue;
    private String user;

    public HandReturn (Hand playerHand, Hand dealerHand, BlackJack.RoundState state, int playerValue, int dealerValue, String user ){
        this.playerHand = playerHand;
        this.dealerHand = dealerHand;
        this.state = state;
        this.playerValue = playerValue;
        this.dealerValue = dealerValue;
        this.user = user;
    }

    public HandReturn (){
        this.playerHand = null;
        this.dealerHand = null;
        this.state = null;
        this.playerValue = 0;
        this.dealerValue = 0;
        this.user = "";
    }

    public int getPlayerValue() {
        return playerValue;
    }

    public int getDealerValue() {
        return dealerValue;
    }

    public BlackJack.RoundState getState() {
        return state;
    }

    public Hand getDealerHand() {
        return dealerHand;
    }

    public Hand getPlayerHand() {
        return playerHand;
    }

    public String getUser() {
        return user;
    }

    public void setDealerHand(Hand dealerHand) {
        this.dealerHand = dealerHand;
    }

    public void setPlayerHand(Hand playerHand) {
        this.playerHand = playerHand;
    }

    public void setState(BlackJack.RoundState state) {
        this.state = state;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setPlayerValue(int value) {
        this.playerValue = value;
    }

    public void setDealerValue(int dealerValue) {
        this.dealerValue = dealerValue;
    }


    @Override
    public String toString() {
        return "HandReturn{" +
                "playerHand=" + playerHand +
                ", dealerHand=" + dealerHand +
                ", state=" + state +
                ", playerValue=" + playerValue +
                ", dealerValue=" + dealerValue +
                ", user='" + user + '\'' +
                '}';
    }
}
