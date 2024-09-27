package com.kagouniv.kagouniv_back.controller;

import com.kagouniv.kagouniv_back.annotation.UserId;
import com.kagouniv.kagouniv_back.dto.HabitRequest;
import com.kagouniv.kagouniv_back.dto.response.ResponseDto;
import com.kagouniv.kagouniv_back.service.HabitService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/habit")
@RequiredArgsConstructor
@Tag(name = "Habit Controller", description = "Habit API")
public class HabitController {

    private final HabitService habitService;

    @Operation(summary = "사용자 습관 리스트 가져오기", description = "사용자의 모든 습관을 조회합니다.")
    @GetMapping("/")
    public ResponseDto<?> getUserHabits(@UserId String userId) {
        // 구현 코드
        return new ResponseDto<>(userId);
    }

    @Operation(summary = "습관 정보 가져오기", description = "습관 정보를 조회합니다.")
    @GetMapping("/{habitId}")
    public ResponseDto<?> getHabitInfo(@PathVariable UUID habitId) {
        // 구현 코드
        return new ResponseDto<>(null);
    }

    @Operation(summary = "추천 습관 리스트 가져오기", description = "추천 습관 리스트를 조회합니다.")
    @GetMapping("/recommends")
    public ResponseDto<?> getRecommendedHabits() {
        // 구현 코드
        return new ResponseDto<>(null);
    }

    @Operation(summary = "습관 생성", description = "새로운 습관을 생성합니다.")
    @PostMapping("/create")
    public ResponseDto<?> createHabit(@RequestBody @Valid HabitRequest habitRequest) {
        // 구현 코드
        return new ResponseDto<>(null);
    }

    @Operation(summary = "습관 삭제", description = "습관을 삭제합니다.")
    @DeleteMapping("/")
    public ResponseDto<?> deleteHabit(@PathVariable UUID habitId) {
        // 구현 코드
        return new ResponseDto<>(null);
    }

    @Operation(summary = "습관 완료", description = "습관을 완료합니다.")
    @PostMapping("/done")
    public ResponseDto<?> completeHabit(@RequestBody UUID habitId) {
        // 구현 코드
        return new ResponseDto<>(null);
    }

    @Operation(summary = "습관 즐겨찾기 설정", description = "습관을 즐겨찾기로 설정합니다.")
    @PostMapping("/favorite")
    public ResponseDto<?> favoriteHabit(@RequestBody UUID habitId) {
        // 구현 코드
        return new ResponseDto<>(null);
    }
}