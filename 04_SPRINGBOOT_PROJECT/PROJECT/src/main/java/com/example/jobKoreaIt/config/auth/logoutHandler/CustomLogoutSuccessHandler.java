package com.example.jobKoreaIt.config.auth.logoutHandler;

import com.example.jobKoreaIt.config.auth.PrincipalDetails;
import com.example.jobKoreaIt.config.auth.jwt.JwtProperties;
import com.example.jobKoreaIt.config.auth.jwt.JwtTokenProvider;
import com.example.jobKoreaIt.domain.common.repository.UserRepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Arrays;

@Component
public class CustomLogoutSuccessHandler implements LogoutSuccessHandler {

    @Value("${spring.security.oauth2.client.registration.kakao.client-id}")
    private String kakaoClientId;


    @Value("${spring.security.oauth2.client.registration.google.client-id}")
    private String googleClientId;

    @Value("${spring.security.oauth2.client.registration.google.client-secret}")
    private String googleClientSecret;

    @Value("${app.redirect-uri:http://localhost:8080/login}")
    private String redirectUri;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private UserRepository userRepository;

    public CustomLogoutSuccessHandler(JwtTokenProvider jwtTokenProvider, UserRepository userRepository) {
    }

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication auth) throws IOException {
        System.out.println("[CustomLogoutSuccessHandler] onLogoutSuccess()");

        // JWT
        String token = Arrays.stream(request.getCookies())
                .filter(cookie -> cookie.getName().equals(JwtProperties.COOKIE_NAME)).findFirst()
                .map(Cookie::getValue)
                .orElse(null);

        if (token != null && jwtTokenProvider.validateToken(token)) {
            Authentication authentication = jwtTokenProvider.getAuthentication(token);

            if (authentication != null) {
                PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
                String provider = principalDetails.getUserDto().getProvider();
                System.out.println("[CustomLogoutSuccessHandler] onLogoutSuccess() provider : " + provider);

                if ("kakao".equals(provider)) {
                    String url = "https://kauth.kakao.com/oauth/logout?client_id=" + kakaoClientId + "&logout_redirect_uri=" + redirectUri;
                } else if ("google".equals(provider)) {
                    String url = "https://oauth2.googleapis.com/revoke";
                    RestTemplate restTemplate = createRestTemplate();
                    HttpHeaders headers = new HttpHeaders();
                    headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

                    String requestBody = "token=" + token;
                    HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

                    try {
                        restTemplate.postForObject(url, requestEntity, String.class);
                    } catch (Exception e) {
                        e.printStackTrace();
                        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Google 로그아웃 실패. 다시 시도해주세요.");
                    }
                }
            }
        }
        clearAuthCookie(request, response);
        response.sendRedirect("/");
    }

    private RestTemplate createRestTemplate() {
        return new RestTemplate();
    }

    private void clearAuthCookie(HttpServletRequest request, HttpServletResponse response) {
        Cookie cookie = new Cookie(JwtProperties.COOKIE_NAME, null);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);
    }
}