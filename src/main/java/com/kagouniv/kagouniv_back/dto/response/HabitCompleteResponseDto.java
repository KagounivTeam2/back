package com.kagouniv.kagouniv_back.dto.response;

import java.util.UUID;

public record HabitCompleteResponseDto(
        UUID habitId,
        Boolean favoriteState
) {
    public static HabitCompleteResponseDto of(UUID habitId, Boolean favoriteState) {
        return new HabitCompleteResponseDto(habitId, favoriteState);
    }
}
