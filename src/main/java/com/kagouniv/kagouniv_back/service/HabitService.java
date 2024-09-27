package com.kagouniv.kagouniv_back.service;

import com.kagouniv.kagouniv_back.domain.Habit;
import com.kagouniv.kagouniv_back.dto.HabitRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class HabitService {

    // 사용자 습관 리스트 가져오기
    public List<Habit> getUserHabits(UUID userId) {
        return new ArrayList<>();
    }

    // 습관 정보 가져오기
    public Habit getHabitById(UUID habitId) {
        return new Habit();
    }

    // 추천 습관 리스트 가져오기
    public List<Habit> getRecommendedHabits() {
        return new ArrayList<>();
    }

    // 습관 생성
    public Habit createHabit(HabitRequest habitRequest) {
        return new Habit();
    }

    // 습관 삭제
    public boolean deleteHabit(UUID habitId) {
        return true;
    }

    // 습관 완료
    public boolean completeHabit(UUID habitId) {
        return true;
    }

    // 습관 즐겨찾기 설정
    public Habit favoriteHabit(UUID habitId) {
        return new Habit();
    }
}