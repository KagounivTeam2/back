package com.kagouniv.kagouniv_back.dto;

import com.kagouniv.kagouniv_back.domain.User;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UserRequest(

        @NotNull
        @Size(max = 15)
        String loginId,

        @NotNull
        @Size(min = 8, max = 50, message = "비밀번호 8자리 이상, 50자리 이하로 입력해주세요")
        String password
) {

    public User toEntity() {
        return User.builder()
                .loginId(loginId)
                .password(password).build();
    }
}
