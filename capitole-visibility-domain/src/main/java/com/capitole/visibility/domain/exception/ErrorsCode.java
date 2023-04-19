package com.capitole.visibility.domain.exception;

public enum ErrorsCode {

    VS_500("unknown error"),
    VS_100("Product Not Found"),
    VS_101("Size Not Found"),
    VS_102("Stock Not Found");

    private String message;

    ErrorsCode(String message){
        this.message=message;
    }

    public String getMessage(){
        return message;
    }
}
