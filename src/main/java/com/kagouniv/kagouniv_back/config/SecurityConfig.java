package com.kagouniv.kagouniv_back.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kagouniv.kagouniv_back.auth.JwtAuthenticationEntryPoint;
import com.kagouniv.kagouniv_back.auth.filter.jwt.JsonUsernamePasswordAuthenticationFilter;
import com.kagouniv.kagouniv_back.auth.filter.jwt.JwtAuthenticationProcessingFilter;
import com.kagouniv.kagouniv_back.auth.handler.JwtLoginSuccessHandler;
import com.kagouniv.kagouniv_back.auth.handler.LoginFailureHandler;
import com.kagouniv.kagouniv_back.auth.service.JwtService;
import com.kagouniv.kagouniv_back.dto.response.ResponseDto;
import com.kagouniv.kagouniv_back.exception.CustomAccessDeniedHandler;
import com.kagouniv.kagouniv_back.repository.UserRepository;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.filters.CorsFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final UserDetailsService userDetailsService;
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final ObjectMapper objectMapper;
    private final CustomAccessDeniedHandler customAccessDeniedHandler; //커스텀 접근 거부 핸들러

    private final String[] allowedUrls = {
            "/swagger-ui/**",
            "/swagger-resources/**",
            "/v3/api-docs/**",
    };


    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .httpBasic(AbstractHttpConfigurer::disable)
            .csrf(AbstractHttpConfigurer::disable)
            .sessionManagement(sessionManagement ->
                            sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .formLogin(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests(authorize -> authorize
                    .requestMatchers("/login").permitAll()
                    .requestMatchers("/api/**").permitAll()
                    .requestMatchers("/**").permitAll()
                    .requestMatchers("/swagger-ui/**", "swagger-resources/**").permitAll()
                    .requestMatchers("/v3/api-docs/**").permitAll()
                    .anyRequest().authenticated())
            .headers(
                headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin))
            .logout(logout -> logout.
                logoutUrl("/api/auth/logout")
//                logoutSuccessUrl("/").
                .invalidateHttpSession(true)
                .logoutSuccessHandler((request, response, authentication) -> {
                    response.setStatus(HttpServletResponse.SC_OK);
                    response.getWriter().write(new ObjectMapper().writeValueAsString(new ResponseDto<>(null)));
                    response.getWriter().flush();
                }))
            .exceptionHandling(req -> req.authenticationEntryPoint(jwtAuthenticationEntryPoint()))
                .addFilterAfter(jsonUsernamePasswordAuthenticationFilter(), LogoutFilter.class)
                .addFilterBefore(jwtAuthenticationProcessingFilter(), JsonUsernamePasswordAuthenticationFilter.class)

                // // 커스텀 접근 거부 핸들러 설정
                .exceptionHandling(exception -> exception
                        .accessDeniedHandler(customAccessDeniedHandler)
                );

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(userDetailsService);
        return new ProviderManager(provider);
    }

    @Bean
    public JwtLoginSuccessHandler jwtLoginSuccessHandler() {
        return new JwtLoginSuccessHandler(jwtService, userRepository);
    }

    @Bean
    public JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint() {
        return new JwtAuthenticationEntryPoint();
    }

    @Bean
    public JwtAuthenticationProcessingFilter jwtAuthenticationProcessingFilter() {
        return new JwtAuthenticationProcessingFilter(jwtService, userRepository);
    }

    @Bean
    public LoginFailureHandler loginFailureHandler() {
        return new LoginFailureHandler();
    }

    @Bean
    public JsonUsernamePasswordAuthenticationFilter jsonUsernamePasswordAuthenticationFilter() throws Exception {
        JsonUsernamePasswordAuthenticationFilter filter = new JsonUsernamePasswordAuthenticationFilter(objectMapper);
        filter.setAuthenticationManager(authenticationManager());
        filter.setAuthenticationSuccessHandler(jwtLoginSuccessHandler());
        filter.setAuthenticationFailureHandler(loginFailureHandler());
        return filter;
    }

}