package com.kagouniv.kagouniv_back.dto.response;

import com.kagouniv.kagouniv_back.domain.HabitView;
import com.kagouniv.kagouniv_back.domain.enums.Theme;

import java.time.LocalDate;

public record HabitViewResponseDto(
        String habitId,
        String habitName,
        LocalDate startAt,
        LocalDate endAt,
        Integer targetCount,
        Integer realCount,
        Theme theme,
        Boolean habitState,
        Boolean favoriteState
) {
    public static HabitViewResponseDto of(HabitView habit, Boolean favoriteState) {
        return new HabitViewResponseDto(
                habit.getId().toString(),
                habit.getHabitName(),
                habit.getStartAt(),
                habit.getEndAt(),
                habit.getTargetCount(),
                habit.getCurrentCount(),
                habit.getTheme(),
                false,
                favoriteState
        );
    }
}