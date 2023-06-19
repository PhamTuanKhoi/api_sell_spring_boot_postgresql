package com.sell.tea.exceptions;

public class DataConstraintConflictException extends RuntimeException{
    public DataConstraintConflictException(String message){
        super(message);
    }
}
