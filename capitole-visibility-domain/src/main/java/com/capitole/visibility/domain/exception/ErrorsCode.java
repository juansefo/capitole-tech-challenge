package com.capitole.visibility.domain.exception;

public enum ErrorsCode {

    VS_500("unknown error"),
    VS_100("Product Not Found"),
    VS_101("Size Not Found"),
    VS_102("Stock Not Found"),
    VS_103("Error with Product repository"),
    VS_104("Error with Size repository"),
    VS_105("Error with Stock repository");

    private final String message;

    ErrorsCode(String message){
        this.message=message;
    }

    public String getMessage(){
        return message;
    }
}
