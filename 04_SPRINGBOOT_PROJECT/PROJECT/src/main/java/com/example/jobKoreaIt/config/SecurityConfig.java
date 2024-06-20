package com.example.jobKoreaIt.config;

import com.example.jobKoreaIt.config.auth.PrincipalDetailsOAuth2Service;
import com.example.jobKoreaIt.config.auth.exceptionHandler.CustomAccessDeniedHandler;
import com.example.jobKoreaIt.config.auth.exceptionHandler.CustomAuthenticationEntryPoint;
import com.example.jobKoreaIt.config.auth.jwt.JwtAuthorizationFilter;
import com.example.jobKoreaIt.config.auth.jwt.JwtProperties;
import com.example.jobKoreaIt.config.auth.jwt.JwtTokenProvider;
import com.example.jobKoreaIt.config.auth.loginHandler.CustomAuthenticationFailureHandler;
import com.example.jobKoreaIt.config.auth.loginHandler.CustomLoginSuccessHandler;
import com.example.jobKoreaIt.config.auth.loginHandler.Oauth2JwtLoginSuccessHandler;
import com.example.jobKoreaIt.config.auth.logoutHandler.CustomLogoutHandler;
import com.example.jobKoreaIt.config.auth.logoutHandler.CustomLogoutSuccessHandler;
import com.example.jobKoreaIt.domain.common.repository.UserRepository;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private HikariDataSource dataSource;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private CustomLoginSuccessHandler customLoginSuccessHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(authorize -> {
                    authorize.requestMatchers("/favicon.ico").permitAll();
                    authorize.requestMatchers("/js/**", "/css/**", "/images/**", "/templates", "/assets/**").permitAll();
                    authorize.requestMatchers("/", "/user/login", "/user/join").permitAll();
                    authorize.requestMatchers("/oauth2/**").permitAll();
                    authorize.anyRequest().authenticated();
                });

        // 로그인
        http.formLogin(login -> {
            login.permitAll();
            login.loginPage("/user/login");
            login.loginProcessingUrl("/user/login");
            login.successHandler(customLoginSuccessHandler());
            login.failureHandler(new CustomAuthenticationFailureHandler());
        });

        // 로그아웃
        http.logout(logout -> {
            logout.permitAll();
            logout.logoutUrl("/logout");
            logout.addLogoutHandler(customLogoutHandler());
            logout.logoutSuccessHandler(customLogoutSuccessHandler());
            logout.deleteCookies("JSESSIONID", JwtProperties.COOKIE_NAME);
            logout.invalidateHttpSession(true);
        });

        // 예외 처리
        http.exceptionHandling(ex -> {
            ex.authenticationEntryPoint(customAuthenticationEntryPoint());
            ex.accessDeniedHandler(new CustomAccessDeniedHandler());
        });

        // RememberMe
        http.rememberMe(rm -> {
            rm.key("rememberMeKey");
            rm.rememberMeParameter("remember-me");
            rm.alwaysRemember(false);
            rm.tokenValiditySeconds(3600);  // 60*60
            rm.tokenRepository(tokenRepository());
        });

        // Oauth2
        http.oauth2Login(oauth2 -> {
            oauth2.loginPage("/user/login");
            oauth2.successHandler(oauth2JwtLoginSuccessHandler());
        });

        // Session Management
        http.sessionManagement(session -> {
            session.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        });

        // JWT Filter
        http.addFilterBefore(new JwtAuthorizationFilter(userRepository, jwtTokenProvider), BasicAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public CustomLogoutSuccessHandler customLogoutSuccessHandler() {
        return new CustomLogoutSuccessHandler(jwtTokenProvider, userRepository);
    }

    @Bean
    public CustomLogoutHandler customLogoutHandler() {
        return new CustomLogoutHandler();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public PersistentTokenRepository tokenRepository() {
        JdbcTokenRepositoryImpl repo = new JdbcTokenRepositoryImpl();
        repo.setDataSource(dataSource);
        return repo;
    }

    @Bean
    public PrincipalDetailsOAuth2Service principalDetailsOAuth2Service() {
        return new PrincipalDetailsOAuth2Service();
    }

    @Bean
    public Oauth2JwtLoginSuccessHandler oauth2JwtLoginSuccessHandler() {
        return new Oauth2JwtLoginSuccessHandler();
    }

    @Bean
    public CustomLoginSuccessHandler customLoginSuccessHandler() {
        return new CustomLoginSuccessHandler();
    }

    @Bean
    public CustomAuthenticationEntryPoint customAuthenticationEntryPoint() {
        return new CustomAuthenticationEntryPoint();
    }
}
