package com.kagouniv.kagouniv_back.repository;

import com.kagouniv.kagouniv_back.domain.Habit;
import com.kagouniv.kagouniv_back.domain.HabitView;
import com.kagouniv.kagouniv_back.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface HabitRepository extends JpaRepository<Habit, UUID> {
    List<Habit> findByUser(User user);

    @Query("SELECT SUM(h.currentCount) FROM Habit h WHERE h.user = :userId")
    Optional<Long> countCompleteHabit(@Param("userId") User userId);

    @Query("SELECT SUM(h.endAt - h.startAt) FROM Habit h WHERE h.user = :userId")
    Optional<Long> percentDate(@Param("userId") User userId);

    @Query("SELECT SUM(h.targetCount) FROM Habit h WHERE h.user = :userId")
    Optional<Long> percentCount(@Param("userId") User userId);
}
