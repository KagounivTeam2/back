package com.kagouniv.kagouniv_back.service;

import com.kagouniv.kagouniv_back.domain.*;
import com.kagouniv.kagouniv_back.dto.request.HabitCreateRequestDto;
import com.kagouniv.kagouniv_back.dto.response.HabitCompleteResponseDto;
import com.kagouniv.kagouniv_back.dto.response.HabitCreateResponseDto;
import com.kagouniv.kagouniv_back.dto.response.HabitViewResponseDto;
import com.kagouniv.kagouniv_back.dto.response.RecommendResponseDto;
import com.kagouniv.kagouniv_back.exception.ApiException;
import com.kagouniv.kagouniv_back.exception.ErrorDefine;
import com.kagouniv.kagouniv_back.repository.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class HabitService {

    private final HabitRepository habitRepository;
    private final UserRepository userRepository;
    private final FavoriteHabitRepository favoriteHabitRepository;
    private final RecommendRepository recommendRepository;
    private final HabitViewRepository habitViewRepository;
    // 사용자 습관 리스트 가져오기
    public Map<String, Object> getUserHabits(String userId) {

        User user = userRepository.findByLoginId(userId)
                .orElseThrow(() -> new ApiException(ErrorDefine.USER_NOT_FOUND));
        List<Habit> habits = habitRepository.findByUser(user);

        Map<String, Object> habitCreateResponseDtos = new HashMap<>();

        habitCreateResponseDtos.put("userHabits", habits.stream()
                .map(habit ->
                    HabitCreateResponseDto.of(habit, favoriteHabitRepository.existsByHabitId(habit.getId()))
                ).collect(Collectors.toList()));
        habitCreateResponseDtos.put("userId", user.getId());
        return habitCreateResponseDtos;
    }

    // 습관 정보 가져오기
    public HabitViewResponseDto getHabitById(UUID habitId, String userId) {
        User user = userRepository.findByLoginId(userId)
                .orElseThrow(() -> new ApiException(ErrorDefine.USER_NOT_FOUND));

        HabitView habitView = habitViewRepository.getHabit(habitId)
                .orElseThrow(()-> new ApiException(ErrorDefine.INVALID_ARGUMENT));

        Boolean isFavorite = favoriteHabitRepository.existsByHabitId(habitView.getId());

        log.info("sdf"+ habitView.getHabitName());
        return HabitViewResponseDto.of(habitView,isFavorite);
    }

    // 추천 습관 리스트 가져오기
    public Map<String, List<RecommendResponseDto>> getRecommendedHabits() {
        Map<String, List<RecommendResponseDto>> response = new HashMap<>();

        List<Recommend> recommendResponseDtos = recommendRepository.findAll();

        response.put("recommentList", recommendResponseDtos.stream()
                .map(RecommendResponseDto::of)
                .collect(Collectors.toList()));

        return response;
    }

    // 습관 생성
    public HabitCreateResponseDto createHabit(HabitCreateRequestDto habitCreateRequestDto, String userId) {
        User user = userRepository.findByLoginId(userId)
                .orElseThrow(() -> new ApiException(ErrorDefine.USER_NOT_FOUND));
        log.info("user : {}", user.getId());
        Habit habit = habitCreateRequestDto.toEntity(user);
        log.info("habit : {}", habit.getHabitName());
        habitRepository.save(habit);
        log.info("habit : {}", habit.getId());
        if(habitCreateRequestDto.favoriteState()) {
            FavoriteHabit favoriteHabit = habitCreateRequestDto.toFavoriteEntity(habit, user);
            favoriteHabitRepository.save(favoriteHabit);

            return HabitCreateResponseDto.of(habit, true);
        }

        return HabitCreateResponseDto.of(habit, false);
    }

    // 습관 삭제
    public boolean deleteHabit(UUID habitId) {
        Habit habit = habitRepository.findById(habitId)
                .orElseThrow(() -> new ApiException(ErrorDefine.HABIT_NOT_FOUND));
        habitRepository.delete(habit);
        return true;
    }

    // 습관 완료
    public HabitCompleteResponseDto completeHabit(UUID habitId, String userId) {
        User user = userRepository.findByLoginId(userId)
                .orElseThrow(() -> new ApiException(ErrorDefine.USER_NOT_FOUND));
        Habit habit = habitRepository.findById(habitId)
                .orElseThrow(() -> new ApiException(ErrorDefine.HABIT_NOT_FOUND));
        if(Objects.equals(habit.getCurrentCount(), habit.getTargetCount()) || habit.getTargetCount() == null)
            throw new ApiException(ErrorDefine.ALREADY_FINISH_HABIT);

        habit.updateCount();

//        FavoriteHabit favoriteHabit = FavoriteHabit.builder()
//                .habit(habit)
//                .user(user)
//                .build();
//        favoriteHabitRepository.save(favoriteHabit);

        return HabitCompleteResponseDto.of(habit);
    }

    // 습관 즐겨찾기 설정
    public HabitCreateResponseDto favoriteHabit(UUID habitId, String userId) {
        User user = userRepository.findByLoginId(userId)
                .orElseThrow(() -> new ApiException(ErrorDefine.USER_NOT_FOUND));
        Habit habit = habitRepository.findById(habitId)
                .orElseThrow(() -> new ApiException(ErrorDefine.HABIT_NOT_FOUND));

        FavoriteHabit favoriteHabit = FavoriteHabit.builder()
                .habit(habit)
                .user(user)
                .build();
        favoriteHabitRepository.save(favoriteHabit);


        return HabitCreateResponseDto.of(habit, true);
    }
}