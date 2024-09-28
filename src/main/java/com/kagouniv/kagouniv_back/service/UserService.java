package com.kagouniv.kagouniv_back.service;

import com.kagouniv.kagouniv_back.auth.service.JwtService;
import com.kagouniv.kagouniv_back.dto.request.UserRequest;
import com.kagouniv.kagouniv_back.exception.ApiException;
import com.kagouniv.kagouniv_back.exception.ErrorDefine;
import com.kagouniv.kagouniv_back.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final JwtService jwtService;
    private final UserRepository userRepository;
    // 회원 탈퇴
    public void deleteUser(HttpServletRequest request) {
        String userName = jwtService.extractUsername(request)
                .orElseThrow(() -> new ApiException(ErrorDefine.USER_NOT_FOUND));

        // 더 이상 사용하지 않는 JWT 제거
        jwtService.destroyRefreshToken(userName);

        // 사용자 제거
        userRepository.deleteUserByLoginId(userName);
    }

    // 회원 정보 수정
//    public void updateUser(HttpServletRequest request, UserRequest userRequest) {
//
//    }
}
