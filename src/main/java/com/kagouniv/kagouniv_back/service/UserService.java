package com.kagouniv.kagouniv_back.service;

import com.kagouniv.kagouniv_back.dto.UserRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    // 회원 탈퇴
    public void deleteUser(UUID userId) {
    }

    // 회원 정보 수정
    public void updateUser(UUID userId, UserRequest userRequest) {
    }
}
