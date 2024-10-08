package com.kagouniv.kagouniv_back.repository;

import com.kagouniv.kagouniv_back.domain.HabitView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface HabitViewRepository extends JpaRepository<HabitView, UUID> {


    Optional<HabitView> findById(UUID habitId);

    List<HabitView> findByUserId(UUID userId);
    // 특정 유저의 습관 총 횟수 조회
    @Query("SELECT COUNT(h.targetCount) FROM HabitView h WHERE h.userId = :userId")
    Long countHabit(@Param("userId") UUID userId);

    Optional<Integer> countByIsDoneTrueAndUserId(UUID uuid);

    // 특정 유저의 성공적으로 완료된 습관 횟수 조회
    @Query("SELECT SUM(h.currentCount) FROM HabitView h WHERE h.userId = :userId")
    Long countCompleteHabit(@Param("userId") UUID userId);

}


