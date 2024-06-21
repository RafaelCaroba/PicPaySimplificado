package com.picpaysimplificado.picpaysimplificado.model.exception;

public class InvalidTransactionException extends RuntimeException{
    public InvalidTransactionException(String message){
        super(message);
    }
}
