package it.finki.tinki.model.exception;

public class UserExistsException extends RuntimeException{
    public UserExistsException(){
        super("User with that email already exists!");
    }
}
