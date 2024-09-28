package com.kagouniv.kagouniv_back.controller;

import com.kagouniv.kagouniv_back.dto.request.UserRequest;
import com.kagouniv.kagouniv_back.dto.response.ResponseDto;
import com.kagouniv.kagouniv_back.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Tag(name="Auth Controller", description = "Auth API")
public class AuthController {

    private final AuthService authService;

    @Operation(summary = "회원가입", description = "회원가입")
    @PostMapping
    public ResponseDto<String> register(@RequestBody @Valid UserRequest userRequest) {
        String createdId = authService.register(userRequest);
        return new ResponseDto<>(createdId.toString());
    }

    @Operation(summary = "로그아웃", description = "로그아웃")
    @PostMapping("/logout")
    public ResponseDto<String> logout(HttpServletRequest request) {
        authService.logout(request);
        return new ResponseDto<>("로그아웃이 성공적으로 처리되었습니다");
    }
}
