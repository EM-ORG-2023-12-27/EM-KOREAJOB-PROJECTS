package com.example.jobKoreaIt.controller.kakao;

import jakarta.servlet.http.HttpServletResponse;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.time.LocalDateTime;

@Controller
@Slf4j
@RequestMapping("/kakao")
public class KakaoLogin {

    private String CLIENT_ID = "908854c810171ec695803ed82b8720b5";
    private String REDIRECT_URI = "http://localhost:8080/kakao/callback";
    private String LOGOUT_REDIRECT_URI = "http://localhost:8080/kakao/main";

    private KakaoResponse kakaoResponse;

    @GetMapping("/getCode")
    public String getCode() {
        log.info("GET /kakao/getCode...");
        return "redirect:https://kauth.kakao.com/oauth/authorize?client_id=" + CLIENT_ID + "&redirect_uri=" + REDIRECT_URI + "&response_type=code";
    }

    @GetMapping("/callback")
    public String callback(@RequestParam("code") String code) {
        log.info("GET /kakao/code... " + code);

        // URL
        String url = "https://kauth.kakao.com/oauth/token";

        // RequestHeader
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        // Request Params
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", CLIENT_ID);
        params.add("redirect_uri", REDIRECT_URI);
        params.add("code", code);

        // Entity (HEADER + PARAM)
        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(params, headers);

        // Request
        RestTemplate rt = new RestTemplate();
        ResponseEntity<KakaoResponse> response = rt.exchange(url, HttpMethod.POST, entity, KakaoResponse.class);

        System.out.println(response);
        this.kakaoResponse = response.getBody();

        return "redirect:/kakao/main";
    }

    @GetMapping("/main")
    public void main() {
        log.info("GET /kakao/main...");
    }

    @GetMapping(value = "/profile", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody KakaoProfile getProfile() {
        log.info("GET /kakao/profile...");

        // URL
        String url = "https://kapi.kakao.com/v2/user/me";

        // RequestHeader
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + kakaoResponse.getAccess_token());

        // Entity
        HttpEntity<String> entity = new HttpEntity<>(headers);

        // Request
        RestTemplate rt = new RestTemplate();
        ResponseEntity<KakaoProfile> response = rt.exchange(url, HttpMethod.GET, entity, KakaoProfile.class);
        System.out.println(response.getBody());

        return response.getBody();
    }

    // 로그아웃 (토큰 만료)
    @GetMapping("/logout")
    public void logout() {
        log.info("GET /kakao/logout...");

        // URL
        String url = "https://kapi.kakao.com/v1/user/logout";

        // RequestHeader
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + kakaoResponse.getAccess_token());

        // Entity
        HttpEntity<String> entity = new HttpEntity<>(headers);

        // Request
        RestTemplate rt = new RestTemplate();
        ResponseEntity<String> response = rt.exchange(url, HttpMethod.POST, entity, String.class);
        System.out.println(response.getBody());
    }

    @GetMapping("/logoutWithKakao")
    public @ResponseBody void logoutWithKakao(HttpServletResponse response) throws IOException {
        log.info("GET /kakao/logoutWithKakao");
        response.sendRedirect("https://kauth.kakao.com/oauth/logout?client_id=" + CLIENT_ID + "&logout_redirect_uri=" + LOGOUT_REDIRECT_URI);
    }

    // 연결 끊기 (카카오 서버와의)
    @GetMapping("/unlink")
    public void unlink() {
        log.info("GET /kakao/unlink...");

        // URL
        String url = "https://kapi.kakao.com/v1/user/unlink";

        // RequestHeader
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + kakaoResponse.getAccess_token());

        // Entity
        HttpEntity<String> entity = new HttpEntity<>(headers);

        // Request
        RestTemplate rt = new RestTemplate();
        ResponseEntity<String> response = rt.exchange(url, HttpMethod.POST, entity, String.class);
        System.out.println(response.getBody());
    }

    //-----------------------------------
    // KAKAO ACCESS TOKEN 보관용 RESPONSE
    //-----------------------------------
    @Data
    private static class KakaoResponse {
        public String access_token;
        public String token_type;
        public String refresh_token;
        public int expires_in;
        public String scope;
        public int refresh_token_expires_in;
    }

    //-----------------------------------
    // KAKAO PROFILE 확인용 RESPONSE
    //-----------------------------------
    @Data
    private static class KakaoAccount {
        public boolean profile_nickname_needs_agreement;
        public boolean profile_image_needs_agreement;
        public Profile profile;
        public boolean has_email;
        public boolean email_needs_agreement;
        public boolean is_email_valid;
        public boolean is_email_verified;
        public String email;
    }

    @Data
    private static class Profile {
        public String nickname;
        public String thumbnail_image_url;
        public String profile_image_url;
        public boolean is_default_image;
        public boolean is_default_nickname;
    }

    @Data
    private static class Properties {
        public String nickname;
        public String profile_image;
        public String thumbnail_image;
    }

    @Data
    private static class KakaoProfile {
        public long id;
        public LocalDateTime connected_at;
        public Properties properties;
        public KakaoAccount kakao_account;
    }
}
