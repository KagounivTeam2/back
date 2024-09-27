package com.kagouniv.kagouniv_back.auth;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.PrintWriter;

@Component
@Slf4j
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        // 유효한 자격증명을 하지 않고 접근하려 할 때 403 인증 실패
        log.info("JwtAuthenticationEntryPoint 진입");

        PrintWriter writer = response.getWriter();
        writer.write("Access Denied. Please Login");
        response.sendError(HttpServletResponse.SC_FORBIDDEN, authException.getMessage());
    }
}