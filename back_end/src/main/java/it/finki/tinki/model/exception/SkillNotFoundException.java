package it.finki.tinki.model.exception;

public class SkillNotFoundException extends RuntimeException{
    public SkillNotFoundException(Long id){
        super(String.format("Skill with id: %d was not found!", id));
    }
}
