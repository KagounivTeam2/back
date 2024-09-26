package com.kagouniv.kagouniv_back.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Table(name = "favorite_habit")
@Entity
@Getter
@NoArgsConstructor
public class FavoriteHabit {

    @Id
    @GeneratedValue(strategy =GenerationType.UUID)
    private UUID id;

    //---------------------------------------------------------
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "habit_id", nullable = false)
    private Habit habit;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    //---------------------------------------------------------

    @Builder
    public FavoriteHabit(Habit habit, User user) {
        this.habit = habit;
        this.user = user;
    }


}
