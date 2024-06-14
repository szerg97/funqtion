package com.szalai.funqtion;

public class FunqtionException extends RuntimeException {

    public FunqtionException(String message) {
        super(message);
    }

    public static FunqtionException invalidOperationException(String operation) {
        return new FunqtionException("Invalid operation: " + operation);
    }
}
