package com.kagouniv.kagouniv_back.dto.response;

import com.kagouniv.kagouniv_back.domain.Habit;
import com.kagouniv.kagouniv_back.domain.HabitView;
import com.kagouniv.kagouniv_back.domain.enums.Theme;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public record HabitCreateResponseDto(
        String habitId,
        String habitName,
        String startAt,
        String endAt,
        Integer targetCount,
        Integer realCount,
        Theme theme,
        Boolean habitState,
        Boolean favoriteState


) {
    public static HabitCreateResponseDto of(Habit habit, Boolean favoriteState) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"); // 원하는 포맷 설정

        return new HabitCreateResponseDto(
                habit.getId().toString(),
                habit.getHabitName(),
                habit.getStartAt() != null ? habit.getStartAt().atStartOfDay().format(formatter) : "",  // 값이 없을 때 빈 문자열 반환
                habit.getEndAt() != null ? habit.getEndAt().atStartOfDay().format(formatter) : "",      // LocalDateTime을 문자열로 변환,  // 값이 없을 때 빈 문자열 반환
                habit.getTargetCount(),
                habit.getCurrentCount(),
                habit.getTheme(),
                habit.getIsDone(),
                favoriteState
        );
    }
}
