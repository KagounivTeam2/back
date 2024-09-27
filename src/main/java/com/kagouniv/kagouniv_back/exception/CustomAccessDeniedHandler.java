package com.kagouniv.kagouniv_back.exception;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException)
            throws IOException, ServletException {

        // 에러 페이지 경로 설정
        String errorPage = "/access-denied";

        // 요청에 에러 메시지 설정
        request.setAttribute("errorMessage", accessDeniedException.getMessage());

        // 에러 페이지로 포워딩
        RequestDispatcher dispatcher = request.getRequestDispatcher(errorPage);
        dispatcher.forward(request, response);
    }
}