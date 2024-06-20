package com.example.jobKoreaIt.config.auth.jwt;

import com.example.jobKoreaIt.domain.common.entity.User;
import com.example.jobKoreaIt.domain.common.repository.UserRepository;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

/**
 * JWT를 이용한 인증
 */
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    private final UserRepository memberRepository;
    private final JwtTokenProvider jwtTokenProvider;

    public JwtAuthorizationFilter(UserRepository memberRepository, JwtTokenProvider jwtTokenProvider) {
        this.memberRepository = memberRepository;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {

        System.out.println("[JWTAUTHORIZATIONFILTER] doFilterInternal");

        String token = null;

        try {
            if (request.getCookies() != null) {
                // cookie 에서 JWT token을 가져옴
                token = Arrays.stream(request.getCookies())
                        .filter(c -> JwtProperties.COOKIE_NAME.equals(c.getName()))
                        .map(Cookie::getValue)
                        .findFirst()
                        .orElse(null);
            }
        } catch (Exception ignored) {
            // 일반적으로 접근 요청 URI에 대한 쿠키 예외는 무시
        }

        if (token != null) {
            try {
                if (jwtTokenProvider.validateToken(token)) {
                    Authentication authentication = getUsernamePasswordAuthenticationToken(token);
                    if (authentication != null) {
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                        System.out.println("[JWTAUTHORIZATIONFILTER] : " + authentication);
                    }
                }
            } catch (ExpiredJwtException e) { // 토큰 만료 시 예외 처리 (쿠키 제거)
                System.out.println("[JWTAUTHORIZATIONFILTER] : ExpiredJwtException : " + e.getMessage());

                // 토큰 만료 시 처리(쿠키에서 제거)
                Cookie cookie = new Cookie(JwtProperties.COOKIE_NAME, null);
                cookie.setMaxAge(0);
                cookie.setPath("/");
                response.addCookie(cookie);

                // 만료된 토큰에 대한 추가 처리
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "JWT token has expired. Please log in again.");
                return;
            } catch (Exception e2) {
                System.out.println("[JWTAUTHORIZATIONFILTER] : Token validation failed : " + e2.getMessage());
                e2.printStackTrace();
            }
        }

        chain.doFilter(request, response);
    }

    /**
     * JWT 토큰으로 User를 찾아서 UsernamePasswordAuthenticationToken를 만들어서 반환한다.
     * User가 없다면 null
     */
    private Authentication getUsernamePasswordAuthenticationToken(String token) {
        Authentication authentication = jwtTokenProvider.getAuthentication(token);
        String username = authentication.getName();
        Optional<User> user = Optional.ofNullable(memberRepository.findByUsername(username));
        System.out.println("JwtAuthorizationFilter.getUsernamePasswordAuthenticationToken..authentication : " + authentication);
        return user.map(u -> authentication).orElse(null);
    }
}
