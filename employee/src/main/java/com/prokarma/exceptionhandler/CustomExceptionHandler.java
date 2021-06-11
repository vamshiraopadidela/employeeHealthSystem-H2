package com.prokarma.exceptionhandler;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.prokarama.beans.Response;
import com.prokarma.errorcodes.ErrorCodes;
import com.prokarma.errorcodes.ErrorMessages;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Response<List<FieldError>>> methodArgumentNotValidException(MethodArgumentNotValidException ex){
        BindingResult result = ex.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors().stream()
                .map(f -> new FieldError(f.getObjectName(), f.getField(), f.getDefaultMessage()))
                .collect(Collectors.toList());
        Response<List<FieldError>> res=new Response<>();
        res.setJsonObject(fieldErrors);
        res.setResponseCode(ErrorCodes.BadRequest.getValue());
        res.setResponseMessage(ErrorMessages.BadRequest.getValue());
        return ResponseEntity.ok(res);
	}
}
