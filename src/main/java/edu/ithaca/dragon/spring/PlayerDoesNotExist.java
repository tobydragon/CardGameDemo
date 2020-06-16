package edu.ithaca.dragon.spring;

public class PlayerDoesNotExist extends RuntimeException {
    public PlayerDoesNotExist(String errMsg, Throwable err){
        super(errMsg, err);
    }
    public PlayerDoesNotExist(String errMsg){
        super(errMsg);
    }
    public PlayerDoesNotExist(){
        super();
    }
}
