package com.kagouniv.kagouniv_back.controller;

import com.kagouniv.kagouniv_back.dto.response.ResponseDto;
import com.kagouniv.kagouniv_back.service.StatisticsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/statistics")
@RequiredArgsConstructor
@Tag(name = "Statistics Controller", description = "Statistics API")
public class StatisticsController {

    private final StatisticsService statisticsService;

    @Operation(summary = "구름(습관) 생성 횟수 조회", description = "사용자가 생성한 구름(습관)의 총 횟수를 조회합니다.")
    @GetMapping("/habit_count/{userId}")
    public ResponseDto<?> getHabitCount(@PathVariable UUID userId) {
        Long habitCount = statisticsService.getHabitCount(userId);
        return new ResponseDto<>(habitCount);
    }

    @Operation(summary = "습관 수행 성공 횟수 조회", description = "사용자가 성공적으로 완료한 습관들의 횟수를 조회합니다.")
    @GetMapping("/complete_count/{userId}")
    public ResponseDto<?> getCompleteCount(@PathVariable UUID userId) {
        Long completeCount = statisticsService.getCompleteCount(userId);
        return new ResponseDto<>(completeCount);
    }
}
