package edu.ithaca.dragon.blackjack;

public class NoMoreCardsException extends Exception{
    public NoMoreCardsException(String erMsg, Throwable err){
        super(erMsg, err);
    }
}
