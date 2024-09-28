package com.kagouniv.kagouniv_back.controller;

import com.kagouniv.kagouniv_back.annotation.UserId;
import com.kagouniv.kagouniv_back.dto.request.HabitCreateRequestDto;
import com.kagouniv.kagouniv_back.dto.response.ResponseDto;
import com.kagouniv.kagouniv_back.service.HabitService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/habit")
@RequiredArgsConstructor
@Tag(name = "Habit Controller", description = "Habit API")
@Slf4j
public class HabitController {

    private final HabitService habitService;

    @Operation(summary = "사용자 습관 리스트 가져오기", description = "사용자의 모든 습관을 조회합니다.")
    @GetMapping("/")
    public ResponseDto<?> getUserHabits(@UserId String userId) {
        // 구현 코드
        return new ResponseDto<>(habitService.getUserHabits(userId));
    }

    @Operation(summary = "습관 정보 가져오기", description = "습관 정보를 조회합니다.")
    @GetMapping("/{habitId}")
    public ResponseDto<?> getHabitInfo(
            @PathVariable("habitId") UUID habitId
    ) {
        // 구현 코드
        return new ResponseDto<>(habitService.getHabitById(habitId));
    }

    @Operation(summary = "추천 습관 리스트 가져오기", description = "추천 습관 리스트를 조회합니다.")
    @GetMapping("/recommends")
    public ResponseDto<?> getRecommendedHabits(

    ) {
        // 구현 코드
        return new ResponseDto<>(habitService.getRecommendedHabits());
    }

    @Operation(summary = "습관 생성", description = "새로운 습관을 생성합니다.")
    @PostMapping("/create")
    public ResponseDto<?> createHabit(
            @RequestBody @Valid HabitCreateRequestDto habitRequest,
            @UserId String userId
    ) {
        log.info("userId : {}", userId);
        // 구현 코드
        return new ResponseDto<>(habitService.createHabit(habitRequest, userId));
    }

    @Operation(summary = "습관 삭제", description = "습관을 삭제합니다.")
    @DeleteMapping("/{habitId}")
    public ResponseDto<?> deleteHabit(@PathVariable("habitId") String habitId) {
        // 구현 코드
        return new ResponseDto<>(habitService.deleteHabit(UUID.fromString(habitId)));
    }

    @Operation(summary = "습관 완료", description = "습관을 완료합니다.")
    @PostMapping("/done/{habitId}")
    public ResponseDto<?> completeHabit(
            @PathVariable("habitId") UUID habitId,
            @UserId String userId
    ) {
        // 구현 코드
        return new ResponseDto<>(habitService.completeHabit(habitId, userId));
    }

    @Operation(summary = "습관 즐겨찾기 설정", description = "습관을 즐겨찾기로 설정합니다.")
    @PostMapping("/favorite/{habitId}")
    public ResponseDto<?> favoriteHabit(
            @PathVariable("habitId") UUID habitId,
            @UserId String userId
    ) {
        // 구현 코드
        return new ResponseDto<>(habitService.favoriteHabit(habitId, userId));
    }
}