package com.lms.core.handler;

import com.lms.core.exception.FormValidationException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(FormValidationException.class)
    public ResponseEntity<Object> handleFormException(FormValidationException ex) {
        return this.buildValidationResponse(ex.getErrors());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleDtoValidation(MethodArgumentNotValidException ex) throws FormValidationException {
        return this.buildValidationResponse(ex.getBindingResult().getAllErrors());
    }

    private ResponseEntity<Object> buildValidationResponse(List<ObjectError> errors) {
        Map<String, List<String>> errorsMap = new HashMap<>();

        for (ObjectError err : errors) {
            String field = "";
            if (err instanceof FieldError fi) {
                field = fi.getField();
            }

            List<String> existingErrors = errorsMap.get(field);
            if (existingErrors == null) {
                existingErrors = new ArrayList<>();
            }

            existingErrors.add(err.getDefaultMessage());
            errorsMap.put(field, existingErrors);
        }
        return ResponseEntity.badRequest().body(errorsMap);
    }
}
