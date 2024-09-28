package com.kagouniv.kagouniv_back.dto.request;

import com.kagouniv.kagouniv_back.domain.FavoriteHabit;
import com.kagouniv.kagouniv_back.domain.Habit;
import com.kagouniv.kagouniv_back.domain.User;
import com.kagouniv.kagouniv_back.domain.enums.Theme;
import jakarta.validation.constraints.NotNull;
import org.aspectj.weaver.ast.Not;

import java.time.LocalDate;

public record HabitCreateRequestDto(

        @NotNull
        String habitName,

        LocalDate startAt,

        LocalDate endAt,

        Integer targetCount,
        Integer currentCount,
        @NotNull
        Theme theme,
        @NotNull
        Boolean favoriteState
        ) {

        public Habit toEntity(User user) {
            return Habit.builder()
                    .habitName(habitName)
                    .startAt(startAt)
                    .endAt(endAt)
                    .targetCount(targetCount)
                    .currentCount(currentCount)
                    .theme(theme)
                    .user(user)
                    .build();
        }

        public FavoriteHabit toFavoriteEntity(Habit habit, User user) {
            return FavoriteHabit.builder()
                    .habit(habit)
                    .user(user)
                    .build();
        }
}
