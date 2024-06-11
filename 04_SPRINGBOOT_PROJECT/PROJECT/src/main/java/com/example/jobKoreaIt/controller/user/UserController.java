package com.example.jobKoreaIt.controller.user;



import com.example.jobKoreaIt.domain.common.dto.UserDto;
import com.example.jobKoreaIt.domain.common.entity.User;
import com.example.jobKoreaIt.domain.common.repository.UserRepository;
import com.example.jobKoreaIt.domain.common.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
import java.util.Random;

@Controller
@Slf4j
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/confirmId")
    public String confirmId_get() {
        log.info("GET /user/confirmId..");
        return "user/confirmId";
    }

    // ID 찾기
    @PostMapping("/confirmId")
    public @ResponseBody ResponseEntity<String> confirmId_post(@RequestParam Map<String, String> formData) {
        log.info("POST /user/confirmId.." + formData);

        UserDto userDto = new UserDto();
        userDto.setUsername(formData.get("username"));
        // 필요한 다른 필드도 설정

        User user = userService.getUser(userDto);

        if (user != null) {
            String username = user.getUsername();
            username = username.substring(0, username.indexOf("@") - 2);
            username = username + "**"; // @기호 전 2번째 문자열까지 * 처리
            log.info("USERNAME : " + username);

            // 화면에 * 처리된 사용자명 반환
            return new ResponseEntity<>(username, HttpStatus.OK);
        } else {
            // 사용자가 존재하지 않는 경우 일치하는 계정이 없을 경우 반환
            return new ResponseEntity<>("계정이 없습니다.", HttpStatus.BAD_GATEWAY);
        }
    }

    // PW 찾기
    @GetMapping("/confirmPw")
    public String confirmPw() {
        log.info("GET /user/confirmPw..");
        return "user/confirmPw";
    }

    @PostMapping("/confirmPw")
    public @ResponseBody ResponseEntity<String> confirmPw_post(@RequestParam Map<String, String> formData) {
        log.info("POST /user/confirmPw.." + formData);

        UserDto userDto = new UserDto();
        userDto.setUsername(formData.get("username"));

        User user = userService.getUser(userDto);

        if (user != null) {
            // 난수 패스워드
            Random rand = new Random();
            int value = (int) (rand.nextDouble() * 100000);

            // DB 저장
            user.setPassword(passwordEncoder.encode(String.valueOf(value)));
            userRepository.save(user);

            // 이메일 발송
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(user.getUsername());
            message.setSubject("임시패스워드");
            message.setText(value + "\n\n 임시 패스워드 입니다. 패스워드 변경요망");
            javaMailSender.send(message);

            return new ResponseEntity<>(user.getUsername() + " 메일로 임시 패스워드 전송완료 ", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("계정을 찾을 수 없습니다.", HttpStatus.BAD_GATEWAY);
        }
    }

    @GetMapping("/login")
    public String login_get() {
        log.info("GET /user/login..");
        return "user/login";
    }

    @GetMapping("/join")
    public String join_get() {
        log.info("GET /user/join..");
        return "user/join";
    }

    @PostMapping("/myinfo")
    public void myinfo() {
        log.info("POST /user/myinfo..");
    }
}