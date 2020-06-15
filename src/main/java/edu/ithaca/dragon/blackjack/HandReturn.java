package edu.ithaca.dragon.blackjack;

public class HandReturn {
    private Hand playerHand;
    private Hand dealerHand;
    private BlackJack.BlackJackState state;
    private int value;
    private String user;
    private WinState winState;

    public HandReturn (Hand playerHand,Hand dealerHand, BlackJack.BlackJackState state, int value, String user, WinState winState){
        this.playerHand = playerHand;
        this.dealerHand = dealerHand;
        this.state = state;
        this.value = value;
        this.user = user;
        this.winState = winState;
    }

    public HandReturn (Hand playerHand,Hand dealerHand, BlackJack.BlackJackState state, int value, String user){
        this.playerHand = playerHand;
        this.dealerHand = dealerHand;
        this.state = state;
        this.value = value;
        this.user = user;
        this.winState = WinState.NONE;
    }
    public HandReturn (){
        this.playerHand = null;
        this.dealerHand = null;
        this.state = null;
        this.value = 0;
        this.user = "";
        this.winState = null;
    }

    public int getValue() {
        return value;
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

    public void setValue(int value) {
        this.value = value;
    }

    public enum WinState{
        WIN, LOSE, TIE, NONE
    }
}
