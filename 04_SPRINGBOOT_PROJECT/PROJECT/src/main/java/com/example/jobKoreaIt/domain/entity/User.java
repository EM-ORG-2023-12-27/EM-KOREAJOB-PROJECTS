package com.example.jobKoreaIt.domain.entity;


import com.example.jobKoreaIt.domain.dto.UserDto;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class User {
    @Id
    private String username;
    private String password;
    private String role;

    private String nickname;
    private String zipcode;
    private String addr1;
    private String addr2;
    private String phone;

    private LocalDateTime createAt;

    //OAUTH2
    private String provider;
    private String providerId;

    public static UserDto entityToDto(User user){
        UserDto dto = UserDto.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .role(user.getRole())
                .provider(user.getProvider())
                .providerId(user.getProviderId())
                .nickname(user.getNickname())
                .zipcode(user.getZipcode())
                .addr1(user.getAddr1())
                .addr2(user.getAddr2())
                .phone(user.getPhone())
                .build();
        return dto;
    }


}