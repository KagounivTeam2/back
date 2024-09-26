package com.kagouniv.kagouniv_back.dto.exception;

import com.kagouniv.kagouniv_back.exception.ErrorDefine;
import lombok.Getter;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.Map;
import java.util.stream.Collectors;

@Getter
public class InvalidateArgumentExceptionDto extends ExceptionDto {
    private final Map<String, String> errorFields;

    public InvalidateArgumentExceptionDto(MethodArgumentNotValidException invalidException) {
        super(ErrorDefine.INVALID_ARGUMENT);

        this.errorFields = invalidException.getBindingResult().getFieldErrors()
                .stream().collect(Collectors
                                .toMap(FieldError::getField, FieldError::getDefaultMessage));
    }
}
