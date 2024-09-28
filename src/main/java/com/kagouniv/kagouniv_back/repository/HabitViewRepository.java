package com.kagouniv.kagouniv_back.repository;

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
public interface HabitViewRepository extends JpaRepository<HabitView, UUID> {


    Optional<HabitView> findById(UUID habitID);

    List<HabitView> findByUser(User user);
    // 특정 유저의 습관 총 횟수 조회
    @Query("SELECT SUM(h.targetCount) FROM HabitView h WHERE h.id = :userId")
    Long countHabit(@Param("userId") UUID userId);


    // 특정 유저의 성공적으로 완료된 습관 횟수 조회
    @Query("SELECT SUM(h.currentCount) FROM HabitView h WHERE h.id = :userId")
    Long countCompleteHabit(@Param("userId") UUID userId);

}


