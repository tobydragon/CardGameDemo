package edu.ithaca.dragon.blackjack;

public class UserContainer {
    private String ID;
    private double balance;

    public UserContainer(String ID, double balance){
        this.ID = ID;
        this.balance = balance;
    }

    public UserContainer(){
        this.ID = "";
        this.balance = 0.0;
    }

    public double getBalance() {
        return balance;
    }

    public String getID() {
        return ID;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    @Override
    public String toString() {
        return "UserContainer{" +
                "ID='" + ID + '\'' +
                ", balance=" + balance +
                '}';
    }
}
