package edu.ithaca.dragon.blackjack;

public class HandReturn {
    private Hand playerHand;
    private Hand dealerHand;
    private BlackJack.BlackJackState state;
    private int value;
    private String user;
    private BlackJack.WinState winState;

    public HandReturn (Hand playerHand,Hand dealerHand, BlackJack.BlackJackState state, int value, String user, BlackJack.WinState winState){
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
        this.winState = BlackJack.WinState.NONE;
    }
    public HandReturn (){
        this.playerHand = null;
        this.dealerHand = null;
        this.state = null;
        this.value = 0;
        this.user = "";
        this.winState = BlackJack.WinState.NONE;
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

    public void setValue(int value) {
        this.value = value;
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
                ", value=" + value +
                ", user='" + user + '\'' +
                ", winState=" + winState +
                '}';
    }
}
