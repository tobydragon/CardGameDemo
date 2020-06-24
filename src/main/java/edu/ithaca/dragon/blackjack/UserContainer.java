package edu.ithaca.dragon.blackjack;

public class UserContainer {
    private String id;
    private double balance;

    public UserContainer(String id, double balance){
        this.id = id;
        this.balance = balance;
    }

    public UserContainer(){
        this.id = "";
        this.balance = 0.0;
    }

    public double getBalance() {
        return balance;
    }

    public String getId() {
        return id;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "UserContainer{" +
                "id='" + id + '\'' +
                ", balance=" + balance +
                '}';
    }
}
