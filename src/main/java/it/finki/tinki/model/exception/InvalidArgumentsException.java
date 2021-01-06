package it.finki.tinki.model.exception;

public class InvalidArgumentsException extends RuntimeException{
    public InvalidArgumentsException(){
        super("Invalid arguments entered!");
    }
}
