package com.kagouniv.kagouniv_back.repository;

import static org.junit.jupiter.api.Assertions.*;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.UUID;

import com.kagouniv.kagouniv_back.domain.HabitView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class HabitViewRepositoryTest {

    @Autowired
    private HabitViewRepository habitViewRepository;

    private UUID userId;

    @BeforeEach
    void setUp() {
        // 테스트 데이터 생성
        userId = UUID.randomUUID();

        // 기본적으로 유저가 10개의 targetCount와 5개의 완료된 습관을 가진다고 가정
        HabitView habit1 = new HabitView();
        habit1.setId(UUID.randomUUID());
        habit1.setUserId(userId);
        habit1.setTargetCount(10);
        habit1.setCurrentCount(5);
        habit1.setIsDone(true);

        habitViewRepository.save(habit1);
    }

    @Test
    void testCountHabitsByUserId() {
        Long totalTargetCount = habitViewRepository.sumTargetCountByUserId(userId);
        assertThat(totalTargetCount).isEqualTo(10L); // targetCount가 10이어야 함
    }

    @Test
    void testCountCompletedHabitsByUserId() {
        Long completedHabitCount = habitViewRepository.sumCompletedCountByUserId(userId);
        assertThat(completedHabitCount).isEqualTo(5L); // 완료된 습관이 5이어야 함
    }
}