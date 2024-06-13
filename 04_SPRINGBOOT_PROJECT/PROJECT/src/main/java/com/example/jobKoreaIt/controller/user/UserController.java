package com.example.jobKoreaIt.controller.user;

import com.example.jobKoreaIt.domain.common.dto.UserDto;
import com.example.jobKoreaIt.domain.common.entity.User;
import com.example.jobKoreaIt.domain.common.service.UserService;
import com.example.jobKoreaIt.domain.offer.dto.OfferDto;
import com.example.jobKoreaIt.domain.offer.entity.JobOffer;
import com.example.jobKoreaIt.domain.seeker.dto.SeekerDto;
import com.example.jobKoreaIt.domain.seeker.entity.JobSeeker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

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
    public String confirmId_post(
            @RequestParam("nickname") String nickname,
            @RequestParam("phone") String phone,
            @RequestParam("type") String type,
            Model model
    ) {
        log.info("POST /user/confirmId.." + nickname + " phone : " + phone + " type : " + type);
        //구직자 ID확인
        if(type.equals("seekerUser"))
        {
            SeekerDto seekerDto = new SeekerDto();
            seekerDto.setNickname(nickname);
            seekerDto.setTel(phone);


            JobSeeker user =  userService.getSeeker(seekerDto);
            System.out.println("REUTNED USER : " + user);
            if(user!=null){
                String username = user.getUsername();
                username = username.substring(0, username.indexOf("@")-2);
                username = username+"**";
                log.info("USERNAME : " + username);
                model.addAttribute("username",username);
            }
        }else if(type.equals("offerUser"))
        {
            OfferDto offerDto = new OfferDto();
            offerDto.setOffername(nickname);
            offerDto.setOffertel(phone);

            JobOffer offer = userService.getOffer(offerDto);
            System.out.println("REUTNED USER :" + offer);
            if(offer!=null){
                String username = offer.getOffername();
                username = username.substring(0, username.indexOf("@")-2);
                username = username+"**";
                log.info("OFFERNAME : " + username);
                model.addAttribute("offername",username);
            }
        }
        return "user/confirmId";
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