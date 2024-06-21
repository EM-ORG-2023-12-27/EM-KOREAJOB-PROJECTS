package com.example.jobKoreaIt.config.auth.jwt;

import com.example.jobKoreaIt.config.auth.PrincipalDetails;
import com.example.jobKoreaIt.domain.common.dto.UserDto;
import com.example.jobKoreaIt.properties.DBCONN;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.stream.Collectors;

@Slf4j
@Component
public class JwtTokenProvider {

    private final Key key;

    public JwtTokenProvider() throws Exception {
        String url = DBCONN.URL;
        String username = DBCONN.ID;
        String password = DBCONN.PW;

        try (Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement pstmt = conn.prepareStatement("select * from signature");
             ResultSet rs = pstmt.executeQuery()) {

            if (rs.next()) {
                byte[] keyByte = rs.getBytes("signature"); // DB로 서명Key꺼내옴
                this.key = Keys.hmacShaKeyFor(keyByte); // this.key에 저장
            } else {
                byte[] keyBytes = KeyGenerator.getKeygen(); // 난수키값 가져오기
                this.key = Keys.hmacShaKeyFor(keyBytes); // 생성된 키를 사용하여 알고리즘에 기반한 Key 객체 생성
                try (PreparedStatement insertPstmt = conn.prepareStatement("insert into signature values(?,now())")) {
                    insertPstmt.setBytes(1, keyBytes);
                    insertPstmt.executeUpdate();
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize JwtTokenProvider", e);
        }
    }

    // 유저 정보를 가지고 AccessToken, RefreshToken 을 생성하는 메서드
    public TokenInfo generateToken(Authentication authentication) {
        // 권한 가져오기
        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));
        long now = (new Date()).getTime();

        PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
        UserDto userDto = principalDetails.getUserDto();

        // Access Token 생성
        Date accessTokenExpiresIn = new Date(now + 60 * 5 * 1000); // 60*5 초후 만료
        String accessToken = Jwts.builder()
                .setSubject(authentication.getName())
                .claim("username", authentication.getName()) // 정보저장
<<<<<<< HEAD
=======
                .claim("phone", userDto.getPhone())
>>>>>>> c96d487755f3033defd6534c4af81f9f88418dba
                .claim("nickname", userDto.getNickname())
                .claim("password", userDto.getPassword())
                .claim("role", userDto.getRole())
                .claim("auth", authorities)
                .claim("principal", authentication.getPrincipal())
                .claim("credentials", authentication.getCredentials())
                .claim("details", authentication.getDetails())
                .claim("provider", userDto.getProvider())
                .claim("providerId", userDto.getProviderId())
                .claim("accessToken", principalDetails.getAccessToken())
                .setExpiration(accessTokenExpiresIn)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

        // Refresh Token 생성
        String refreshToken = Jwts.builder()
                .setExpiration(new Date(now + 86400000)) //1일: 24 * 60 * 60 * 1000 = 86400000
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

        return TokenInfo.builder()
                .grantType("Bearer")
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    public TokenInfo generateToken(String Claimkey, String id, boolean isAuth) {
        long now = (new Date()).getTime();

        // Access Token 생성
        Date accessTokenExpiresIn = new Date(now + 60 * 5 * 1000); // 60*5 초후 만료
        String accessToken = Jwts.builder()
                .setSubject(Claimkey + "JWT TOKEN")
                .claim(Claimkey, isAuth) // 정보저장
                .claim("id", id)
                .setExpiration(accessTokenExpiresIn)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

        // Refresh Token 생성
        String refreshToken = Jwts.builder()
                .setExpiration(new Date(now + 86400000)) //1일: 24 * 60 * 60 * 1000 = 86400000
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

        return TokenInfo.builder()
                .grantType("Bearer")
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    // JWT 토큰을 복호화하여 토큰에 들어있는 정보를 꺼내는 메서드
    public Authentication getAuthentication(String accessToken) {
        // 토큰 복호화
        Claims claims = parseClaims(accessToken);

        if (claims.get("auth") == null) {
            throw new RuntimeException("권한 정보가 없는 토큰입니다.");
        }
        // 클레임에서 권한 정보 가져오기
        Collection<? extends GrantedAuthority> authorities =
                Arrays.stream(claims.get("auth").toString().split(","))
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());

        String username = claims.getSubject(); // username

        // JWT Added
        String provider = (String) claims.get("provider");
        String providerId = (String) claims.get("providerId");
        String password = (String) claims.get("password");
        String auth = (String) claims.get("auth");
        String oauthAccessToken = (String) claims.get("accessToken");
        String phone = (String) claims.get("phone");
        String nickname = (String) claims.get("nickname");

        LinkedHashMap principal = (LinkedHashMap) claims.get("principal");
        String credentials = (String) claims.get("credentials");
        LinkedHashMap details = (LinkedHashMap) claims.get("details");

        UserDto userDto = new UserDto();
        userDto.setUsername(username);
        userDto.setPassword(password);
        userDto.setProvider(provider);
        userDto.setProviderId(providerId);
<<<<<<< HEAD
=======
        userDto.setPhone(phone);
>>>>>>> c96d487755f3033defd6534c4af81f9f88418dba
        userDto.setNickname(nickname);
        userDto.setRole(auth);

        PrincipalDetails principalDetails = new PrincipalDetails();
        principalDetails.setUserDto(userDto);
        principalDetails.setAccessToken(oauthAccessToken); // Oauth AccessToken

        System.out.println("JWT GETAUTHENTICATION DTO : " + userDto);

        // JWT + NO REMEMBERME
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(principalDetails, credentials, authorities);
        usernamePasswordAuthenticationToken.setDetails(details);

        return usernamePasswordAuthenticationToken;
    }

    public Claims parseClaims(String accessToken) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(key)
                    .setAllowedClockSkewSeconds(60) // 허용할 클럭 스큐를 60초로 설정
                    .build()
                    .parseClaimsJws(accessToken)
                    .getBody();
        } catch (ExpiredJwtException e) {
            log.info("Expired JWT Token", e);
            return e.getClaims();
        }
    }

    // 토큰 정보를 검증하는 메서드
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(key)
                    .setAllowedClockSkewSeconds(60) // 허용할 클럭 스큐를 60초로 설정
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            log.info("Invalid JWT Token", e);
        } catch (ExpiredJwtException e) {
            log.info("Expired JWT Token", e);
            return false;
        } catch (UnsupportedJwtException e) {
            log.info("Unsupported JWT Token", e);
        } catch (IllegalArgumentException e) {
            log.info("JWT claims string is empty.", e);
        } catch (Exception etc) {
            log.info("기타예외", etc);
            return false;
        }
        return false;
    }
}
