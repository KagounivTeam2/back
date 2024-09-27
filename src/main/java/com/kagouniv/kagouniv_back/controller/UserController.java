package com.kagouniv.kagouniv_back.controller;

import com.kagouniv.kagouniv_back.dto.UserRequest;
import com.kagouniv.kagouniv_back.dto.response.ResponseDto;
import com.kagouniv.kagouniv_back.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@Tag(name = "User Controller", description = "User API")
public class UserController {

    private final UserService userService;

    @Operation(summary = "회원 탈퇴", description = "회원 탈퇴를 처리합니다.")
    @DeleteMapping("/{userId}")
    public ResponseDto<String> deleteUser(@PathVariable UUID userId) {
        // 구현 코드
        return new ResponseDto<>("Bye");
    }

    @Operation(summary = "회원 정보 수정", description = "회원 정보를 수정합니다.")
    @PatchMapping("/{userId}")
    public ResponseDto<?> updateUser(@PathVariable UUID userId, @RequestBody UserRequest userRequest) {
        // 구현 코드
        return new ResponseDto<>(userId);
    }
}
