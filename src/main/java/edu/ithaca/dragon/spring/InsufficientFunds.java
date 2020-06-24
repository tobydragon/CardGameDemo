package edu.ithaca.dragon.spring;

public class InsufficientFunds extends Exception{

    public InsufficientFunds(){
        super();
    }

    public InsufficientFunds(String mssg){
        super(mssg);
    }
}
