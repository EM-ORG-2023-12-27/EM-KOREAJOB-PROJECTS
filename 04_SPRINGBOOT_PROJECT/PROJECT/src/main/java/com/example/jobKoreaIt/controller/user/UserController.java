package com.example.jobKoreaIt.controller.user;


<<<<<<< HEAD
import com.example.jobKoreaIt.domain.common.service.UserService;
import com.example.jobKoreaIt.domain.offer.dto.OfferDto;
import com.example.jobKoreaIt.domain.offer.entity.JobOffer;
import com.example.jobKoreaIt.domain.offer.repository.JobOfferRepository;
import com.example.jobKoreaIt.domain.offer.service.JobOfferServiceImpl;
import com.example.jobKoreaIt.domain.seeker.dto.SeekerDto;
import com.example.jobKoreaIt.domain.seeker.entity.JobSeeker;
import com.example.jobKoreaIt.domain.seeker.repository.JobSeekerRepository;
import com.example.jobKoreaIt.domain.seeker.service.JobSeekerServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Random;
=======
>>>>>>> c96d487755f3033defd6534c4af81f9f88418dba

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
    private JobSeekerRepository jobSeekerRepository;

    @Autowired
    private JobSeekerServiceImpl jobSeekerService;

    @Autowired
    private JobOfferRepository jobOfferRepository;

    @Autowired
    private JobOfferServiceImpl jobOfferService;

    @GetMapping("/confirmIdOffer")
    public String confirmIdOffer_get() {
        log.info("GET /user/confirmIdOffer..");
        return "user/confirmIdOffer";
    }

    @GetMapping("/confirmId")
    public String confirmId_get() {
        log.info("GET /user/confirmId..");
        return "user/confirmId";
<<<<<<< HEAD
    }


    @PostMapping(value="/confirmId",produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<String> confirmId_post(
            @RequestParam("nickname") String nickname,
            @RequestParam("phone") String phone,
            @RequestParam("type") String type,
            Model model
    ) {
        log.info("POST /user/confirmId.." + nickname + " phone : " + phone + " type : " + type);

        if (type.equals("seekerUser")) {
            SeekerDto seekerDto = new SeekerDto();
            seekerDto.setNickname(nickname);
            seekerDto.setTel(phone);

            JobSeeker user = userService.getSeeker(seekerDto);
            System.out.println("REUTNED USER : " + user);
            String username = null;
            if (user != null) {
                username = user.getUsername();

                model.addAttribute("seekername", username);
            }
            return new ResponseEntity<>(username,HttpStatus.OK);
        } else  {
            log.info("POST /user/confirmIdOffer.." + nickname + " phone : " + phone + " type : " + type);

            OfferDto offerDto = new OfferDto();
            offerDto.setOffername(nickname);
            offerDto.setOffertel(phone);

            JobOffer offer = userService.getOffer(offerDto);
            System.out.println("REUTNED USER :" + offer);
            String username = null;
            if (offer != null) {
                username = offer.getUsername();

                model.addAttribute("offername", username);
            }
            return new ResponseEntity<>(username,HttpStatus.OK);
        }


=======
    }


    @PostMapping(value="/confirmId",produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<String> confirmId_post(
            @RequestParam("nickname") String nickname,
            @RequestParam("phone") String phone,
            @RequestParam("type") String type,
            Model model
    ) {
        log.info("POST /user/confirmId.." + nickname + " phone : " + phone + " type : " + type);

        if (type.equals("seekerUser")) {
            SeekerDto seekerDto = new SeekerDto();
            seekerDto.setNickname(nickname);
            seekerDto.setTel(phone);

            JobSeeker user = userService.getSeeker(seekerDto);
            System.out.println("REUTNED USER : " + user);
            String username = null;
            if (user != null) {
                username = user.getUsername();

                model.addAttribute("seekername", username);
            }
            return new ResponseEntity<>(username,HttpStatus.OK);
        } else  {
            log.info("POST /user/confirmIdOffer.." + nickname + " phone : " + phone + " type : " + type);

            OfferDto offerDto = new OfferDto();
            offerDto.setOffername(nickname);
            offerDto.setOffertel(phone);

            JobOffer offer = userService.getOffer(offerDto);
            System.out.println("REUTNED USER :" + offer);
            String username = null;
            if (offer != null) {
                username = offer.getUsername();

                model.addAttribute("offername", username);
            }
            return new ResponseEntity<>(username,HttpStatus.OK);
        }

    @PostMapping("/user/join")
    public void join_post(){
        log.info("POST /join...");
    }

>>>>>>> c96d487755f3033defd6534c4af81f9f88418dba
    }

    @GetMapping("/confirmPw")
    public String confirmPw() {
        log.info("GET /user/confirmPw..");
        return "user/confirmPw";
    }

    @PostMapping("/confirmPw")
    public @ResponseBody ResponseEntity<String> confirmPw_post(
            @RequestParam("phone") String phone,
            @RequestParam("username") String username,
            @RequestParam("nickname") String nickname
    ) {
        log.info("POST /user/confirmPw.. phone: " + phone + ", username: " + username + " nickname : " + nickname);

        Optional<JobSeeker> seekerOptional = jobSeekerRepository.findByUsernameAndTelAndNickname(username, phone, nickname);

        if (seekerOptional.isPresent()) {
            Random rand = new Random();
            int value = (int) (rand.nextDouble() * 100000);

            JobSeeker seeker = seekerOptional.get();
            seeker.setPassword(passwordEncoder.encode(String.valueOf(value)));
            jobSeekerRepository.save(seeker);

            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(seeker.getUsername());
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

    @PostMapping("/login")
    public String login_post() {
        log.info("POST /user/login..");
        return "/login";
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
