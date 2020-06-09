package edu.ithaca.dragon.blackjack;

public class HandReturn {
    private Hand hand;
    private BlackJack.BlackJackState state;
    private int value;
    private String user;

    public HandReturn (Hand hand, BlackJack.BlackJackState state, int value, String user){
        this.hand = hand;
        this.state = state;
        this.value = value;
        this.user = user;
    }
    public HandReturn (){
        this.hand = null;
        this.state = null;
        this.value = 0;
        this.user = "";
    }

    public int getValue() {
        return value;
    }

    public BlackJack.BlackJackState getState() {
        return state;
    }

    public Hand getHand() {
        return hand;
    }

    public String getUser() {
        return user;
    }

    public void setHand(Hand hand) {
        this.hand = hand;
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
}
