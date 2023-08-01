package com.example.miniProject.auth.config;

import com.example.miniProject.auth.jwt.JwtTokenFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;

@Configuration
public class WebSecurityConfig {
    private final JwtTokenFilter jwtTokenFilter;

    public WebSecurityConfig(JwtTokenFilter jwtTokenFilter) {
        this.jwtTokenFilter = jwtTokenFilter;
    }

    @Bean // 메소드의 결과를 Bean 객체로 등록해주는 어노테이션
    public SecurityFilterChain securityFilterChain(
            // DI 자동으로 설정됨, 빌더 패턴 처럼 쓴다.
            HttpSecurity http
    ) throws Exception {
        http
                // CSRF: Cross Site Request Forgery
                .csrf(AbstractHttpConfigurer::disable)
                // 1. requestMatchers를 통해 설정할 URL 지정
                // 2. permitAll(), authenticated() 등을 통해 어떤 사용자가
                //    접근 가능한지 설정
                .authorizeHttpRequests(
                        authHttp -> authHttp // HTTP 요청 허가 관련 설정을 하고 싶다.
                                // requestMatchers == 어떤 URL로 오는 요청에 대하여 설정하는지
                                // permitAll() == 누가 요청해도 허가한다.
                                .requestMatchers(
                                        "/token/issue"
                                )
                                .permitAll()
                                .requestMatchers(
                                        HttpMethod.GET, "/items"
                                ).permitAll()
                                .requestMatchers(
                                        HttpMethod.GET, "/items/**"
                                ).permitAll()

                                .requestMatchers(
                                        "/users/login",
                                        "/users/register"
                                )
                                .anonymous() // 인증이 되지 않은 사용자만 허가

                                .requestMatchers(
                                        "/users/my-profile",
                                        "/users/logout"
                                )
                                .authenticated() // 인증이 된 사용자만 허가
                                .requestMatchers(HttpMethod.POST, "/items")
                                .authenticated()
                                .requestMatchers(HttpMethod.POST, "/items/**")
                                .authenticated()
                                .requestMatchers(HttpMethod.PUT, "/items/**")
                                .authenticated()
                                .requestMatchers(HttpMethod.DELETE, "/items/**")
                                .authenticated()
                )
                .sessionManagement(
                        sessionManagement -> sessionManagement
                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .addFilterBefore(
                        jwtTokenFilter,
                        AuthorizationFilter.class
                );
        return http.build();
    }


    @Bean
    // 비밀번호 암호화를 위한 Bean
    public PasswordEncoder passwordEncoder(){
        // 기본적으로 사용자 비밀번호는 해독가능한 형태로 데이터베이스에
        // 저장되면 안된다. 그래서 기본적으로 비밀번호를 단방향 암호화 하는
        // 인코더를 사용한다.
        return new BCryptPasswordEncoder();
    }
}
