package com.capitole.visibility.domain.exception;

public class GeneralException extends RuntimeException{

    private final String businessCode;
    private final String message;

    public GeneralException (String businessCode, String message){
        this.businessCode=businessCode;
        this.message=message;
    }

    public String getBusinessCode() {
        return businessCode;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
