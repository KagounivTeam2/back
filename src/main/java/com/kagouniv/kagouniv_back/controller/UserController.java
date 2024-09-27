package com.kagouniv.kagouniv_back.controller;

import com.kagouniv.kagouniv_back.domain.User;
import com.kagouniv.kagouniv_back.dto.UserRequest;
import com.kagouniv.kagouniv_back.dto.response.ResponseDto;
import com.kagouniv.kagouniv_back.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@Tag(name = "User Controller", description = "User API")
public class UserController {

    private final UserService userService;

    @Operation(summary = "회원가입", description = "회원가입")
    @PostMapping("/")
    public ResponseDto<String> register(@RequestBody @Valid UserRequest userRequest) {
        String createdId = userService.register(userRequest);
        return new ResponseDto<>(createdId.toString());
    }

    @Operation(summary = "로그아웃", description = "로그아웃")
    @PostMapping("/logout")
    public ResponseDto<String> logout(HttpServletRequest request) {
        userService.logout(request);
        return new ResponseDto<>("로그아웃이 성공적으로 처리되었습니다");
    }
}
