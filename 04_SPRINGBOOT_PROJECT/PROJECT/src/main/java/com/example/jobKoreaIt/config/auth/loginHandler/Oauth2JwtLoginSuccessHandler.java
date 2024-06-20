package com.example.jobKoreaIt.config.auth.loginHandler;


import com.example.jobKoreaIt.config.auth.PrincipalDetails;
import com.example.jobKoreaIt.config.auth.jwt.JwtProperties;
import com.example.jobKoreaIt.config.auth.jwt.JwtTokenProvider;
import com.example.jobKoreaIt.config.auth.jwt.TokenInfo;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.io.IOException;
import java.util.Collection;

public class Oauth2JwtLoginSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        System.out.println("[onAuthenticationSuccess] onAuthenticationSuccess()");

        //-------------------------------------
        //JWT ADD
        //-------------------------------------
        System.out.println("authentiction : " + authentication);



        TokenInfo tokenInfo = jwtTokenProvider.generateToken(authentication);
        //쿠키생성
        Cookie cookie = new Cookie(JwtProperties.COOKIE_NAME, tokenInfo.getAccessToken());
        cookie.setMaxAge(JwtProperties.EXPIRATION_TIME); // 쿠키 만료시간
        cookie.setPath("/");
        response.addCookie(cookie);
        //-------------------------------------

        Collection<? extends GrantedAuthority> collection = authentication.getAuthorities();
        collection.forEach( (role->{
            System.out.println("[onAuthenticationSuccess] onAuthenticationSuccess() role : " + role);
            String role_str = role.getAuthority();


            try {
                response.sendRedirect("/");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }


        }));

    }

}

