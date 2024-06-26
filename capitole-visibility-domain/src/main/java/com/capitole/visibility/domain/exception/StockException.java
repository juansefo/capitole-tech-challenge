package com.capitole.visibility.domain.exception;

public class StockException extends GeneralException {

    private StockException(String businessCode, String message) {
        super(businessCode, message);
    }

    public static StockException stockNotFound() {
        return new StockException(ErrorsCode.VS_102.name(), ErrorsCode.VS_102.getMessage());
    }

    public static StockException stockRepositoryError() {
        return new StockException(ErrorsCode.VS_105.name(), ErrorsCode.VS_105.getMessage());
    }
}
