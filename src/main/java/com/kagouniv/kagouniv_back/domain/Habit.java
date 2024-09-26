package com.kagouniv.kagouniv_back.domain;


import com.kagouniv.kagouniv_back.domain.enums.Theme;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.objenesis.instantiator.util.UnsafeUtils;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Table(name = "habit")
@Entity
@Getter
@NoArgsConstructor
@DynamicUpdate
public class Habit {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String habitName;

    @Column(nullable = false)
    private LocalDate startAt;

    @Column
    private LocalDate endAt;

    @Column
    private Integer targetCount;

    @Column(nullable = false)
    private Integer currentCount;

    @Column(nullable = false)
    private Theme theme;

    @Column(nullable = false)
    private Boolean isDone;

    //---------------------------------------------------------

    @OneToMany(mappedBy = "habit", cascade = CascadeType.ALL)
    private List<FavoriteHabit> favoriteHabits;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    //---------------------------------------------------------

    @Builder
    public Habit(String habitName, LocalDate startAt, LocalDate endAt, Integer targetCount, Integer currentCount, Theme theme, User user) {
        this.habitName = habitName;
        this.startAt = startAt;
        this.endAt = endAt;
        this.targetCount = targetCount;
        this.currentCount = currentCount;
        this.theme = theme;
        this.isDone = false;
        this.user = user;
    }

}
