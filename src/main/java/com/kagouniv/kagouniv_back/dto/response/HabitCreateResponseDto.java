package com.kagouniv.kagouniv_back.dto.response;

import com.kagouniv.kagouniv_back.domain.Habit;
import com.kagouniv.kagouniv_back.domain.HabitView;
import com.kagouniv.kagouniv_back.domain.enums.Theme;

import java.time.LocalDate;

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
        return new HabitCreateResponseDto(
                habit.getId().toString(),
                habit.getHabitName(),
                habit.getStartAt().toString(),
                habit.getEndAt().toString(),
                habit.getTargetCount(),
                habit.getCurrentCount(),
                habit.getTheme(),
                habit.getIsDone(),
                favoriteState
        );
    }
}
