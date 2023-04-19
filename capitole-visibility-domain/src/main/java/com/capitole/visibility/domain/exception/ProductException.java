package com.capitole.visibility.domain.exception;

public class ProductException extends GeneralException {

    private ProductException(String businessCode, String message) {
        super(businessCode, message);
    }

    public static ProductException productNotFound() {
        return new ProductException(ErrorsCode.VS_100.name(), ErrorsCode.VS_100.getMessage());
    }

}
