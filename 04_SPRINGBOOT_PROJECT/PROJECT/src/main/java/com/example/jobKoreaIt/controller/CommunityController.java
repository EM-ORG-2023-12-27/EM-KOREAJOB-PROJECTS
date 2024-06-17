package com.example.jobKoreaIt.controller;


import com.example.jobKoreaIt.config.auth.PrincipalDetails;
import com.example.jobKoreaIt.domain.common.dto.CommunityDto;
import com.example.jobKoreaIt.domain.common.dto.Criteria;
import com.example.jobKoreaIt.domain.common.dto.PageDto;
import com.example.jobKoreaIt.domain.common.dto.UserDto;
import com.example.jobKoreaIt.domain.common.entity.Community;
import com.example.jobKoreaIt.domain.common.service.CommunityServiceImpl;
import com.example.jobKoreaIt.domain.common.service.UserServiceImpl;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import jakarta.websocket.DeploymentException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@Slf4j
@RequestMapping("/community")
public class CommunityController {

    @Autowired
    private CommunityServiceImpl communityService;

    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/index")
    public void index(){
        log.info("GET /community/index...");
    }

    @GetMapping("/add")
    public void add(){
        log.info("GET /community/add.css...");
    }



    @PostMapping("/add")
    public String add_post(@ModelAttribute  @Valid CommunityDto dto, BindingResult bindingResult, Model model) throws IOException {
        log.info("GET /community/add..." + dto);
        //유효성 검사
        if(bindingResult.hasFieldErrors()) {
            for(FieldError error  : bindingResult.getFieldErrors()) {
                log.info(error.getField()+ " : " + error.getDefaultMessage());
                model.addAttribute(error.getField(), error.getDefaultMessage());
            }
            return "community/add";
        }
        //서비스 실행
        boolean isadded = communityService.addCommunity(dto);
        if(!isadded)
            return "community/add";

        return "redirect:/community/list";
    }

    @GetMapping("/list")
    public String list(@RequestParam(value = "pageNo" ,required = false)Integer pageNo, Model model, HttpServletResponse response) throws Exception {
        log.info("GET /community/list... " + pageNo + " " );

        //----------------
        //PageDto  Start
        //----------------
        Criteria criteria = null;
        if(pageNo==null) {
            //최초 /board/list 접근
            pageNo=1;
            criteria = new Criteria();  //pageno=1 , amount=10
        }
        else {
            criteria = new Criteria(pageNo,10); //페이지이동 요청 했을때
        }
        //--------------------
        //Search
        //--------------------
//        criteria.setType(type);
//        criteria.setKeyword(keyword);


        //서비스 실행
        Map<String,Object> map = communityService.GetCommunityList(criteria);

        PageDto pageDto = (PageDto) map.get("pageDto");
        int total = (int)map.get("total");

        List<Community> list = (List<Community>) map.get("list");


        //Entity -> Dto
//        List<Community>  communityList =  list.stream().map(community -> Community.Of(community)).collect(Collectors.toList());
//        System.out.println(communityList);

        //View 전달
        model.addAttribute("list",list);
        model.addAttribute("total",total);
        model.addAttribute("pageNo",pageNo);
        model.addAttribute("pageDto",pageDto);

        //--------------------------------
        //COUNT UP - //쿠키 생성(/board/read.do 새로고침시 조회수 반복증가를 막기위한용도)
        //--------------------------------
//        Cookie init = new Cookie("reading","true");
//        response.addCookie(init);



        return "community/list";
    }



    @GetMapping("/read")
    public void read(@RequestParam("no") Long no , @RequestParam("pageNo") Long pageNo,Model model){
        log.info("GET /community/read...no : " + no + " pageNo :" + pageNo);
        Community community =  communityService.getCommunity(no);

        model.addAttribute("community",community);
        model.addAttribute("pageNo",pageNo);



    }

    @GetMapping("/update")
    public void update(@RequestParam("no") Long no, @RequestParam("pageNo") Long pageNo,Model model){
        Community community =  communityService.getCommunity(no);
        model.addAttribute("community",community);
        model.addAttribute("pageNo",pageNo);
    }
    @PostMapping("/update")
    public @ResponseBody ResponseEntity<String> update_post(@ModelAttribute CommunityDto dto){
        log.info("POST /community/update..." + dto);
        boolean isUpdated =  communityService.updateCommunity(dto);

        if(!isUpdated) {
            return new ResponseEntity("fail.", HttpStatus.BAD_GATEWAY);
        }
        return new ResponseEntity("success", HttpStatus.OK);
    }

    @GetMapping("/delete")
    public void confirmIdPw_get(@RequestParam("no") Long no, @RequestParam("pageNo") Long pageNo,Model model)
    {
        log.info("GET /community/delete..");
        model.addAttribute("no",no);
        model.addAttribute("pageNo",pageNo);
    }

    //게시글의 ID/PW 확인
    @PostMapping("/delete")
    public @ResponseBody ResponseEntity<String> confirmIdPw(@RequestParam("no") Long no, @RequestParam("pageNo") Long pageNo,@RequestParam("password") String password,@AuthenticationPrincipal PrincipalDetails principalDetails )
    {

        log.info("POST /community/delete.." + no + " " + pageNo +" " + password);
        //UserDto userDto = principalDetails.getUserDto();
        //boolean ismatched =  passwordEncoder.matches(password,userDto.getPassword());

//        if(!ismatched){
//            return new ResponseEntity(false,HttpStatus.BAD_GATEWAY);
//        }
        communityService.removeCommunity(no);
        return new ResponseEntity(true,HttpStatus.OK);
    }

}
