package com.kagouniv.kagouniv_back.repository;

import com.kagouniv.kagouniv_back.domain.Habit;
import com.kagouniv.kagouniv_back.domain.HabitView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface HabitViewRepository extends JpaRepository<HabitView, UUID> {
}

