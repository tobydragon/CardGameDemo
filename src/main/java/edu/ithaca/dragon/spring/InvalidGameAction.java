package edu.ithaca.dragon.spring;

public class InvalidGameAction extends RuntimeException {
    public InvalidGameAction (String err){
        super(err);
    }
}
