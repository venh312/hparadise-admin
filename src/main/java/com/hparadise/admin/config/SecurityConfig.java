package com.hparadise.admin.config;

import com.hparadise.admin.handler.AuthFailureHandler;
import com.hparadise.admin.handler.AuthSuccessHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@RequiredArgsConstructor
@Configuration
public class SecurityConfig {
    private static final String[] ALLOW_PATH = {
        "/", "/assets/**", "/fonts/**", "/js/**", "/libs/**", "/scss/**", "/tasks/**", "/member/public/**"
    };

    private final AuthSuccessHandler authSuccessHandler;
    private final AuthFailureHandler authFailureHandler;

    @Bean
    public BCryptPasswordEncoder encryptPassword() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        /*
         csrf 토큰 활성화시 사용
         쿠키를 생성할 때 HttpOnly 태그를 사용하면 클라이언트 스크립트가 보호된 쿠키에 액세스하는 위험을 줄일 수 있으므로 쿠키의 보안을 강화할 수 있다.
        */
        http.csrf(csrf -> csrf.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
            .ignoringRequestMatchers(new AntPathRequestMatcher("/member/public/login")))
            .authorizeHttpRequests(authorizationManagerRequestMatcherRegistry -> authorizationManagerRequestMatcherRegistry
                .requestMatchers(ALLOW_PATH).permitAll()
                .anyRequest()
                .authenticated()
            )
            .formLogin(httpSecurityFormLoginConfigurer -> httpSecurityFormLoginConfigurer
                .loginPage("/member/public/signin")
                .usernameParameter("email")
                .passwordParameter("password")
                .loginProcessingUrl("/member/public/login")
                .successHandler(authSuccessHandler)
                .failureHandler(authFailureHandler)
            )
            .logout(httpSecurityLogoutConfigurer -> httpSecurityLogoutConfigurer
                .logoutRequestMatcher(new AntPathRequestMatcher("/member/public/logout"))
                .logoutSuccessUrl("/member/public/signin")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID", "remember-me") // JSESSIONID, remember-me 쿠키 삭제
            )
            .sessionManagement(httpSecuritySessionManagementConfigurer -> httpSecuritySessionManagementConfigurer
                .maximumSessions(1) // 세션 최대 허용 수 1, -1인 경우 무제한 세션 허용
                .maxSessionsPreventsLogin(false) // true면 중복 로그인을 막고, false면 이전 로그인의 세션을 해제
                .expiredUrl("/member/public/signin?msg=msg.member.sessionExpired")
            )
            .rememberMe(httpSecurityRememberMeConfigurer -> httpSecurityRememberMeConfigurer
                .alwaysRemember(false)
                .tokenValiditySeconds(43200) // in seconds, 12시간 유지
                .rememberMeParameter("remember-me")
            );
        return http.build();
    }
}
