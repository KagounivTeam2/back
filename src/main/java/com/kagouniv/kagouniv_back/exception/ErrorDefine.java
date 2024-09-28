package com.kagouniv.kagouniv_back.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorDefine {

    // Bad Request
    INVALID_ARGUMENT("4000", HttpStatus.BAD_REQUEST, "Bad Request: Invalid Arguments"),
    ALREADY_EXIST_USER("4001", HttpStatus.CONFLICT, "Already Exists"),


    // Not Found
    USER_NOT_FOUND("4040", HttpStatus.NOT_FOUND, "User Not Found"),
    TOKEN_NOT_FOUND("4041", HttpStatus.NOT_FOUND, "Token Not Found"),
    HABIT_NOT_FOUND("4042", HttpStatus.NOT_FOUND, "Habit Not Found");


    private final String errorCode;
    private final HttpStatus httpStatus;
    private final String message;
    }
