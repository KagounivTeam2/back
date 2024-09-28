package com.kagouniv.kagouniv_back.dto.request;

import com.kagouniv.kagouniv_back.domain.User;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UserRequest(

        @NotNull
        @Size(min = 6, max = 12, message = "아이디 6자리 이상, 12자리 이하로 입력해주세요")
        @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "영어와 숫자만 입력해주세요.")
        String loginId,

        @NotNull
        @Size(min = 8, max = 20, message = "비밀번호 8자리 이상, 20자리 이하로 입력해주세요")
        String password
) {

    public User toEntity() {
        return User.builder()
                .loginId(loginId)
                .password(password).build();
    }
}
