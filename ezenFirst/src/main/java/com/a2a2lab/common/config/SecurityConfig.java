package com.a2a2lab.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/**", "/login").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(login -> login
                        .loginPage("/tableOrder/sign/loginView")         // 커스텀 로그인 페이지
                        .loginProcessingUrl("/login")
                        .defaultSuccessUrl("/tableOrder/shop/list") // 로그인 성공 후 이동할 페이지
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
                .requestMatchers("/usr", "/xdm");
    }
	  
}
