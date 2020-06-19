package edu.ithaca.dragon.blackjack;

public class HandReturn {
    private BettingHand playerHand;
    private Hand dealerHand;
    private BlackJack.RoundState state;
    private int playerValue;
    private int dealerValue;
    private UserContainer user;

    public HandReturn (BettingHand playerHand, Hand dealerHand, BlackJack.RoundState state, int playerValue, int dealerValue, String user, double balance ){
        this.playerHand = playerHand;
        this.dealerHand = dealerHand;
        this.state = state;
        this.playerValue = playerValue;
        this.dealerValue = dealerValue;
        this.user = new UserContainer(user, balance);
    }

    public HandReturn (){
        this.playerHand = null;
        this.dealerHand = null;
        this.state = null;
        this.playerValue = 0;
        this.dealerValue = 0;
        this.user = new UserContainer();
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

    public UserContainer getUser() {
        return user;
    }

    public void setDealerHand(Hand dealerHand) {
        this.dealerHand = dealerHand;
    }

    public void setPlayerHand(BettingHand playerHand) {
        this.playerHand = playerHand;
    }

    public void setState(BlackJack.RoundState state) {
        this.state = state;
    }

    public void setUser(UserContainer user) {
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
                ", user='" + user.toString() + '\'' +
                '}';
    }
}
