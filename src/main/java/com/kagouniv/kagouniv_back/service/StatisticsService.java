package com.kagouniv.kagouniv_back.service;

import com.kagouniv.kagouniv_back.repository.HabitViewRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class StatisticsService {

    private final HabitViewRepository habitViewRepository;

     //구름(습관) 생성 횟수 조회
    public Long getHabitCount(UUID userId) {
        return habitViewRepository.countHabit(userId);
    }

    // 습관 수행 성공 횟수 조회
    public Long getCompleteCount(UUID userId) {
        return habitViewRepository.countCompleteHabit(userId);
    }
}
