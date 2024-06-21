package com.picpaysimplificado.picpaysimplificado.model.exception;

public class TransactionAuthorizationException extends RuntimeException{
    public TransactionAuthorizationException(String message){
        super(message);
    }
}
