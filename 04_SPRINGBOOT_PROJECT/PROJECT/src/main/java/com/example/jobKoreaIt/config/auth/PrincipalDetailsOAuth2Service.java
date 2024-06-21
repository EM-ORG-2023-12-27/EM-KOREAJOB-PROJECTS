package com.example.jobKoreaIt.config.auth;

import com.example.jobKoreaIt.config.auth.provider.GoogleUserInfo;
import com.example.jobKoreaIt.config.auth.provider.KakaoUserInfo;
import com.example.jobKoreaIt.config.auth.provider.OAuth2UserInfo;
import com.example.jobKoreaIt.domain.common.dto.UserDto;
import com.example.jobKoreaIt.domain.common.entity.User;
import com.example.jobKoreaIt.domain.common.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;

@Service
public class PrincipalDetailsOAuth2Service extends DefaultOAuth2UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) {
        // OAuth2User 정보 로드
        OAuth2User oAuthUser = super.loadUser(userRequest);

        // provider 선별하기
        String provider = userRequest.getClientRegistration().getRegistrationId();
        String accessToken = userRequest.getAccessToken().getTokenValue();
        OAuth2UserInfo oauth2UserInfo = null;
        if ("kakao".equals(provider)) {
            oauth2UserInfo = new KakaoUserInfo(oAuthUser.getAttributes());
        } else if ("google".equals(provider)) {
            oauth2UserInfo = new GoogleUserInfo(oAuthUser.getAttributes());
        }

        // DB 조회 및 사용자 생성/업데이트
        String username = oauth2UserInfo.getProvider() + "_" + oauth2UserInfo.getProviderId();
        String password = passwordEncoder.encode("1234");

        Optional<User> userOptional = userRepository.findById(username);
        UserDto userDto = null;
        if (userOptional.isEmpty()) {
            User user = new User();
            user.setUsername(username);
            user.setPassword(password);
            user.setRole("ROLE_USER");
            user.setProvider(oauth2UserInfo.getProvider());
            user.setProviderId(oauth2UserInfo.getProviderId());
            userRepository.save(user);

            userDto = new UserDto();
            userDto.setUsername(username);
            userDto.setPassword(password);
            userDto.setRole("ROLE_USER");
            userDto.setProvider(oauth2UserInfo.getProvider());
            userDto.setProviderId(oauth2UserInfo.getProviderId());
        } else {
            User user = userOptional.get();
            userDto = new UserDto();
            userDto.setUsername(user.getUsername());
            userDto.setPassword(user.getPassword());
            userDto.setRole(user.getRole());
            userDto.setProvider(user.getProvider());
            userDto.setProviderId(user.getProviderId());
        }

        // PRINCIPALDETAILS 생성/반환
        PrincipalDetails principalDetails = new PrincipalDetails();
        principalDetails.setUserDto(userDto);
        principalDetails.setAccessToken(accessToken);
        principalDetails.setAttributes(oauth2UserInfo.getAttributes());


        return principalDetails;
    }
}
