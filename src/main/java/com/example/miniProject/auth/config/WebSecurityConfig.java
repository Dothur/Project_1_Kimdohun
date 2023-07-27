package com.example.miniProject.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurityConfig {

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
                                        "/",
                                        "/users/login"
                                )
                                .permitAll()

                                .requestMatchers(
                                        "/no-auth",
                                        "/users/register"
                                )
                                .anonymous() // 인증이 되지 않은 사용자만 허가

                                .requestMatchers(
                                        "re-auth", // ex
                                        "/users/my-profile",
                                        "users/logout"
                                )
                                .authenticated() // 인증이 된 사용자만 허가
                )
                // form 을 이용한 로그인 관련 설정
                .formLogin(
                        formLogin -> formLogin
                                // 로그인 하는 페이지 지정
                                .loginPage("/users/login")
                                // 성공시 이동하는 페이지
                                .defaultSuccessUrl("/users/my-profile")
                                // 실패시 이동하는 페이지
                                .failureUrl("/users/login?fail")
                                .permitAll()
                )
                .logout(
                        logout -> logout
                                // 로그아웃 요청을 보낼 url
                                // 어떤 UI에 로그아웃 기능을 연결하고 싶으면
                                // 해당 UI가 /users/logout 으로 POST 요청을 보내게끔
                                .logoutUrl("/users/logout")
                                // 성공시 이동하는 url
                                .logoutSuccessUrl("/users/login")
                )
        ;
        return http.build();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
