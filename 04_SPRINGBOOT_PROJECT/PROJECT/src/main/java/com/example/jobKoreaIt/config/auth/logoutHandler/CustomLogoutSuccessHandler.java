package com.example.jobKoreaIt.config.auth.logoutHandler;

import com.example.jobKoreaIt.config.auth.PrincipalDetails;
import com.example.jobKoreaIt.config.auth.jwt.JwtProperties;
import com.example.jobKoreaIt.config.auth.jwt.JwtTokenProvider;
import com.example.jobKoreaIt.domain.common.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Arrays;

@Component
public class CustomLogoutSuccessHandler implements LogoutSuccessHandler {

    @Value("${spring.security.oauth2.client.registration.kakao.client-id}")
    private String kakaoClientid;

    private final String REDIRECT_URI = "http://localhost:8080/login";

    private JwtTokenProvider jwtTokenProvider;

    private UserRepository userRepository;

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication auth) throws IOException {
        System.out.println("[CustomLogoutSuccessHandler] onLogoutSuccess()");

        // JWT
        String token = Arrays.stream(request.getCookies())
                .filter(cookie -> cookie.getName().equals(JwtProperties.COOKIE_NAME))
                .findFirst()
                .map(cookie -> cookie.getValue())
                .orElse(null);
        Authentication authentication = jwtTokenProvider.getAuthentication(token);

        PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
        String provider = principalDetails.getUserDto().getProvider();
        System.out.println("[CustomLogoutSuccessHandler] onlogoutSucces() provider : " + provider);

        if (provider != null && provider.equals("kakao")) {
            String url = "http://kauth.kakao.com/oauth/logout?client_id=" + kakaoClientid + "&logout_redirect_uri=" + REDIRECT_URI;
            response.sendRedirect(url);
            return;
        } else if (provider != null && provider.equals("google")) {
            String url = "http://accounts.google.com/logout";
            response.sendRedirect(url);
            return;
        }
        response.sendRedirect("/");
    }
}
