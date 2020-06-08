package edu.ithaca.dragon.spring;

public class GameDoesNotExist extends RuntimeException {
    public GameDoesNotExist(String errMsg, Throwable err){
        super(errMsg, err);
    }
    public GameDoesNotExist(String errMsg){
        super(errMsg);
    }
    public GameDoesNotExist(){
        super();
    }
}
