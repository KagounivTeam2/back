package com.kagouniv.kagouniv_back.service;

import com.kagouniv.kagouniv_back.domain.User;
import com.kagouniv.kagouniv_back.exception.ApiException;
import com.kagouniv.kagouniv_back.exception.ErrorDefine;
import com.kagouniv.kagouniv_back.repository.HabitViewRepository;
import com.kagouniv.kagouniv_back.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class StatisticsService {

    private final HabitViewRepository habitViewRepository;
    private final UserRepository userRepository;

     //구름(습관) 생성 횟수 조회
    public Long getHabitCount(String userId) {
        User user = userRepository.findByLoginId(userId)
                .orElseThrow(() -> new ApiException(ErrorDefine.USER_NOT_FOUND));
        return habitViewRepository.countHabit(user.getId());
    }

    // 습관 수행 성공 횟수 조회
    public Long getCompleteCount(String userId) {
        User user = userRepository.findByLoginId(userId)
                .orElseThrow(() -> new ApiException(ErrorDefine.USER_NOT_FOUND));
        return habitViewRepository.countCompleteHabit(user.getId());
    }
}
