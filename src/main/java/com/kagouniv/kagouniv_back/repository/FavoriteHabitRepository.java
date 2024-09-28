package com.kagouniv.kagouniv_back.repository;

import com.kagouniv.kagouniv_back.domain.FavoriteHabit;
import com.kagouniv.kagouniv_back.domain.Habit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FavoriteHabitRepository extends JpaRepository<FavoriteHabit, UUID> {
    Boolean existsByHabitId(UUID habitId);
}
