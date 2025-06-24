package com.lms.core.exception;

import lombok.Getter;
import org.springframework.validation.ObjectError;

import java.util.List;

@Getter
public class FormValidationException extends RuntimeException {

    private final List<ObjectError> errors;

    public FormValidationException(List<ObjectError> errors) {
        this.errors = errors;
    }
}
