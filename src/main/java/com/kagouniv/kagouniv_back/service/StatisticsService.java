package com.kagouniv.kagouniv_back.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class StatisticsService {

    // 습관 생성 횟수 조회
    public int getHabitCount(UUID userId) {
        return 0;
    }

    // 습관 수행 성공 횟수 조회
    public int getCompleteCount(UUID userId) {
        return 0;
    }
}
