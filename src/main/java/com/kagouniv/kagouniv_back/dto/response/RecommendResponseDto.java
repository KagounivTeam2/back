package com.kagouniv.kagouniv_back.dto.response;

import com.kagouniv.kagouniv_back.domain.Recommend;

import java.util.UUID;

public record RecommendResponseDto(
        UUID recommendId,
        String recommentName
) {
    public static RecommendResponseDto of(Recommend recommend){
        return new RecommendResponseDto(recommend.getId(), recommend.getRecommendHabitName());
    }
}
