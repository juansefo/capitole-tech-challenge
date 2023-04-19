package com.capitole.visibility.infra.adapters.config;


import com.capitole.visibility.domain.exception.ErrorsCode;
import com.capitole.visibility.domain.exception.GeneralException;
import com.capitole.visibility.infra.adapters.config.dto.ErrorDTO;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(GeneralException.class)
    public ResponseEntity<ErrorDTO> postNotFound(GeneralException ex) {

        if (Objects.equals(ex.getBusinessCode(), ErrorsCode.VS_100.name())||
                Objects.equals(ex.getBusinessCode(), ErrorsCode.VS_101.name())||
                Objects.equals(ex.getBusinessCode(), ErrorsCode.VS_102.name())){
            return ResponseEntity.notFound().build();
        }else if(Objects.equals(ex.getBusinessCode(), ErrorsCode.VS_103.name())||
                Objects.equals(ex.getBusinessCode(), ErrorsCode.VS_104.name())||
                Objects.equals(ex.getBusinessCode(), ErrorsCode.VS_105.name())){
            return ResponseEntity.internalServerError().body(new ErrorDTO(ex.getMessage()));
        }else{
            return ResponseEntity.internalServerError().body(new ErrorDTO(ex.getMessage()));
        }

    }
}
