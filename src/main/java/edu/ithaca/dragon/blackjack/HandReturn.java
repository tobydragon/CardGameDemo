package edu.ithaca.dragon.blackjack;

public class HandReturn {
    private Hand playerHand;
    private Hand dealerHand;
    private BlackJack.BlackJackState state;
    private int playerValue;
    private int dealerValue;
    private String user;
    private BlackJack.WinState winState;

    public HandReturn (Hand playerHand,Hand dealerHand, BlackJack.BlackJackState state, int playerValue,int dealerValue, String user, BlackJack.WinState winState){
        this.playerHand = playerHand;
        this.dealerHand = dealerHand;
        this.state = state;
        this.playerValue = playerValue;
        this.dealerValue = dealerValue;
        this.user = user;
        this.winState = winState;
    }

    public HandReturn (Hand playerHand,Hand dealerHand, BlackJack.BlackJackState state, int playerValue, int dealerValue, String user){
        this.playerHand = playerHand;
        this.dealerHand = dealerHand;
        this.state = state;
        this.playerValue = playerValue;
        this.dealerValue = dealerValue;
        this.user = user;
        this.winState = BlackJack.WinState.NONE;
    }
    public HandReturn (){
        this.playerHand = null;
        this.dealerHand = null;
        this.state = null;
        this.playerValue = 0;
        this.dealerValue = 0;
        this.user = "";
        this.winState = BlackJack.WinState.NONE;
    }

    public int getPlayerValue() {
        return playerValue;
    }

    public int getDealerValue() {
        return dealerValue;
    }

    public BlackJack.BlackJackState getState() {
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

    public BlackJack.WinState getWinState() {
        return winState;
    }

    public void setDealerHand(Hand dealerHand) {
        this.dealerHand = dealerHand;
    }

    public void setPlayerHand(Hand playerHand) {
        this.playerHand = playerHand;
    }

    public void setState(BlackJack.BlackJackState state) {
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

    public void setWinState(BlackJack.WinState winState) {
        this.winState = winState;
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
                ", winState=" + winState +
                '}';
    }
}
