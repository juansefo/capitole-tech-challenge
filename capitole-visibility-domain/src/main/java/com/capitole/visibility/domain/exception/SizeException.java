package com.capitole.visibility.domain.exception;

public class SizeException extends GeneralException {

    private SizeException(String businessCode, String message) {
        super(businessCode, message);
    }

    public static SizeException sizeNotFound() {
        return new SizeException(ErrorsCode.VS_101.name(), ErrorsCode.VS_101.getMessage());
    }

    public static SizeException sizeRepositoryError() {
        return new SizeException(ErrorsCode.VS_104.name(), ErrorsCode.VS_104.getMessage());
    }
}
