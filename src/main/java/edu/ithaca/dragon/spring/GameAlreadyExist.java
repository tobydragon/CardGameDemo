package edu.ithaca.dragon.spring;

public class GameAlreadyExist extends RuntimeException {
    public GameAlreadyExist(String errMsg, Throwable err){
        super(errMsg, err);
    }
    public GameAlreadyExist(String errMsg){
        super(errMsg);
    }
    public GameAlreadyExist(){
        super();
    }
}
