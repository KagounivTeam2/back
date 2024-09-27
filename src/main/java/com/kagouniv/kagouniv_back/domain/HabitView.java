package com.kagouniv.kagouniv_back.domain;

import com.kagouniv.kagouniv_back.domain.enums.Theme;
import jakarta.persistence.*;
import org.hibernate.annotations.Immutable;

import java.time.LocalDate;
import java.util.UUID;

@Immutable
@Entity
@Table(name = "habit_view")
public class HabitView {
    @Id
    private UUID id;

    private String habitName;

    private LocalDate startAt;

    private LocalDate endAt;

    private Integer targetCount;

    private Integer currentCount;

    private Theme theme;

    private Boolean isDone;

}
