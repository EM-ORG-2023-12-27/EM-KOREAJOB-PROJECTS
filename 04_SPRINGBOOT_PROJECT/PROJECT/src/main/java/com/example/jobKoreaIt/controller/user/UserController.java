package com.example.jobKoreaIt.controller.user;

import com.example.jobKoreaIt.domain.common.entity.User;
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

    @GetMapping("/confirmId")
    public String confirmId_get() {
        log.info("GET /user/confirmId..");
        return "user/confirmId";
    }

    @PostMapping("/confirmId")
    public @ResponseBody ResponseEntity<String> confirmId_post(@RequestParam("email") String email) {
        log.info("POST /user/confirmId.. email: " + email);

        User user = userService.getUserByEmail(email);

        if (user != null) {
            String username = user.getUsername();
            int atIndex = username.indexOf("@");

            if (atIndex > 2) {
                username = username.substring(0, atIndex - 2) + "**";
            } else if (atIndex > 0) {
                username = username.substring(0, 1) + "*";
            } else {
                username = username + "**";
            }

            log.info("USERNAME : " + username);
            return new ResponseEntity<>("사용자 이름은 " + username + "입니다.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("계정을 찾을 수 없습니다.", HttpStatus.BAD_GATEWAY);
        }
    }

    @GetMapping("/confirmPw")
    public String confirmPw() {
        log.info("GET /user/confirmPw..");
        return "user/confirmPw";
    }

    @PostMapping("/confirmPw")
    public @ResponseBody ResponseEntity<String> confirmPw_post(@RequestParam("email") String email, @RequestParam("username") String username) {
        log.info("POST /user/confirmPw.. email: " + email + ", username: " + username);

        User user = userService.getUserByEmail(email);

        if (user != null && user.getUsername().equals(username)) {
            Random rand = new Random();
            int value = (int) (rand.nextDouble() * 100000);

            user.setPassword(passwordEncoder.encode(String.valueOf(value)));
            userService.saveUser(user);

            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(user.getEmail());
            message.setSubject("임시 패스워드");
            message.setText("임시 패스워드는 " + value + " 입니다.\n\n 패스워드를 변경해주시기 바랍니다.");
            javaMailSender.send(message);

            return new ResponseEntity<>("임시 패스워드가 이메일로 전송되었습니다.", HttpStatus.OK);
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
