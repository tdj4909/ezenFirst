package com.a2a2lab.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                        				"/xdm/**", 
                        				"/tableOrder/sign/loginView", 
                        				"/tableOrder/sign/registerView",
                        				"/tableOrder/sign/register",
                        				"/tableOrder/emailChk").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(login -> login
                        .loginPage("/tableOrder/sign/loginView")         // 커스텀 로그인 페이지
                        .loginProcessingUrl("/login")
                        .failureUrl("/tableOrder/sign/loginView?error")
                        .defaultSuccessUrl("/tableOrder/shop/list", true) // 로그인 성공 후 이동할 페이지
                )
                .oauth2Login(oauth2Login -> oauth2Login
                        .loginPage("/tableOrder/sign/loginView") // OAuth2 로그인 페이지도 커스텀 로그인 페이지로 설정
                        .userInfoEndpoint(userInfo -> userInfo
                            .userService(oauth2UserService()) // OAuth2 사용자 정보를 처리할 서비스 설정
                        )
                        .failureUrl("/tableOrder/sign/loginView?error")
                        .defaultSuccessUrl("/tableOrder/shop/list", true) // OAuth2 로그인 성공 시 기본 이동 경로
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/tableOrder/sign/loginView") // 로그아웃 후 이동할 URL
                        .deleteCookies("JSESSIONID")   // 쿠키 삭제
                )
                .sessionManagement(session -> session
                		.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED) // 기본값, 세션 생성됨
                )
        ;

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring()
                .requestMatchers("/usr/**", "/xdm/**");
    }
    
    @Bean
    public OAuth2UserService<OAuth2UserRequest, OAuth2User> oauth2UserService() {
        return new CustomOAuth2UserService();
    }
	  
}
