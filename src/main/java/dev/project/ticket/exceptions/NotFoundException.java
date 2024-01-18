package dev.project.ticket.exceptions;

public class NotFoundException extends RuntimeException{

    public NotFoundException(){
        super();
    }
    public NotFoundException(String message) {
        super(message);
    }

}
