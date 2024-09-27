package com.kagouniv.kagouniv_back.auth;

import com.kagouniv.kagouniv_back.annotation.UserId;
import com.kagouniv.kagouniv_back.exception.ApiException;
import com.kagouniv.kagouniv_back.exception.ErrorDefine;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.UUID;

@Component
@Slf4j
public class HttpUserIdArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(UserId.class) &&
                parameter.getParameterType().equals(String.class);
    }

    @Override
    public String resolveArgument(
            MethodParameter parameter,
            ModelAndViewContainer mavContainer,
            NativeWebRequest webRequest,
            WebDataBinderFactory binderFactory
    ) {
        Object userDetails = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String loginId = ((CustomUserDetails) userDetails).getUsername();


        if (loginId == null) {
            throw new ApiException(ErrorDefine.INVALID_ARGUMENT);
        }

        return loginId;
    }
}
