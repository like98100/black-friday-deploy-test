package com.flab.blackfriday.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authz -> authz
                        // 헬스 체크 관련 경로는 모든 사용자에게 허용
                        .requestMatchers("/actuator/health", "/actuator/info").permitAll()
                        // 게임 관련 경로는 모든 사용자에게 허용
                        .requestMatchers("/", "/games", "/api/games/**").permitAll()
                        // 정적 리소스 허용
                        .requestMatchers("/css/**", "/js/**", "/images/**", "/games/**").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .defaultSuccessUrl("/", true)
                        .permitAll()
                )
                .logout(logout -> logout.permitAll())
                .build();
    }
}