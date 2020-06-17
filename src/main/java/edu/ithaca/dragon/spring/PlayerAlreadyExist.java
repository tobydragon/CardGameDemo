package edu.ithaca.dragon.spring;

public class PlayerAlreadyExist extends RuntimeException {
    public PlayerAlreadyExist(String errMsg, Throwable err){
        super(errMsg, err);
    }
    public PlayerAlreadyExist(String errMsg){
        super(errMsg);
    }
    public PlayerAlreadyExist(){
        super();
    }
}
