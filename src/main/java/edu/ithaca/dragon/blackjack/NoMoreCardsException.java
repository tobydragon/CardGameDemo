package edu.ithaca.dragon.blackjack;

public class NoMoreCardsException extends RuntimeException{
    public NoMoreCardsException(String erMsg, Throwable err){
        super(erMsg, err);
    }
}
