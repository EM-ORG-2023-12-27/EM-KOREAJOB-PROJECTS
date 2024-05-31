package com.example.jobKoreaIt.domain.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
	private String username;
	private String password;
	private String nickname;
	private String repassword;
	private String phone;
	private String zipcode;
	private String addr1;
	private String addr2;
	private String role;

	//OAUTH2
	private String provider;
	private String providerId;

//    //날짜
//    @DateTimeFormat(pattern = "yyyy-MM-dd")
//    private LocalDateTime rdate;
}