package com.kagouniv.kagouniv_back.controller;

import com.kagouniv.kagouniv_back.dto.request.UserRequest;
import com.kagouniv.kagouniv_back.dto.response.ResponseDto;
import com.kagouniv.kagouniv_back.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@Tag(name = "User Controller", description = "User API")
public class UserController {

    private final UserService userService;

    @Operation(summary = "회원 탈퇴", description = "회원 탈퇴를 처리합니다.")
    @DeleteMapping
    public ResponseDto<String> deleteUser(HttpServletRequest request) {
        userService.deleteUser(request);

        // 구현 코드
        return new ResponseDto<>("Bye");
    }

//    @Operation(summary = "회원 정보 수정", description = "회원 정보를 수정합니다.")
//    @PatchMapping
//    public ResponseDto<?> updateUser(HttpServletRequest request, @RequestBody UserRequest userRequest) {
//        // 구현 코드
//        return new ResponseDto<>(userId);
//    }
}
