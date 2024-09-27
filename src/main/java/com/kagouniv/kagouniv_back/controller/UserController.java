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

}
