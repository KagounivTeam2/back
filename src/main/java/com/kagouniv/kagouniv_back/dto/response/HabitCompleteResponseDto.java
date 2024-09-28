package com.kagouniv.kagouniv_back.dto.response;

import com.kagouniv.kagouniv_back.domain.Habit;

import java.util.UUID;

public record HabitCompleteResponseDto(
        UUID habitId,
        Integer realCount,
        Integer targetCount
) {
    public static HabitCompleteResponseDto of(Habit habit) {
        return new HabitCompleteResponseDto(habit.getId(), habit.getCurrentCount(), habit.getTargetCount());
    }
}
