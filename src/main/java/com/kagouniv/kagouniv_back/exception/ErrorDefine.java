package com.kagouniv.kagouniv_back.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorDefine {

    // Bad Request
    INVALID_ARGUMENT("4000", HttpStatus.BAD_REQUEST, "Bad Request: Invalid Arguments"),
    ALREADY_FINISH_HABIT("4001", HttpStatus.ALREADY_REPORTED, "이미 완료된 미션이거나, 카운트미션이 아닙니다.."),
    ALREADY_EXIST_MEMBER("4002", HttpStatus.BAD_REQUEST, "이미 존재하는 회원입니다."),


    // Not Found
    USER_NOT_FOUND("4040", HttpStatus.NOT_FOUND, "User Not Found"),
    TOKEN_NOT_FOUND("4041", HttpStatus.NOT_FOUND, "Token Not Found"),
    HABIT_NOT_FOUND("4042", HttpStatus.NOT_FOUND, "Habit Not Found");

    //습관이 완료 되었습니다.


    private final String errorCode;
    private final HttpStatus httpStatus;
    private final String message;
    }
