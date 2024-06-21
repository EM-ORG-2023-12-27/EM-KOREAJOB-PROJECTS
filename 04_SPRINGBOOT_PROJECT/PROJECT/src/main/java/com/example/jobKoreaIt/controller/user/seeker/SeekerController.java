package com.example.jobKoreaIt.controller.user.seeker;



@Controller
@Slf4j
@RequestMapping("/seeker")
public class SeekerController {


    private final JobSeekerServiceImpl jobSeekerServiceImpl;


    @Autowired
    public SeekerController(JobSeekerServiceImpl jobSeekerServiceImpl) {
        this.jobSeekerServiceImpl = jobSeekerServiceImpl;
    }

    @GetMapping("/join")

    public String registerForm(Model model) {
        model.addAttribute("registerRequest", new SeekerDto());
        model.addAttribute("userRequest", new UserDto());
        log.info("GET /seeker/join...");
        return "user/login";

    public String join_get(){
        log.info("GET /seeker/join...");

    }

    @PostMapping("/join")
    public String seekerJoin(
            @ModelAttribute @Valid SeekerDto seekerDto,
            BindingResult bindingResult,
            Model model)
    {
        log.info("POST /seeker/join..seekerDto : " + seekerDto + " seekerBindingResult : " + bindingResult);

        if (bindingResult.hasFieldErrors()) {
            for (FieldError error : bindingResult.getFieldErrors()) {
                log.info("ErrorField : " + error.getField() + " ErrorMsg : " + error.getDefaultMessage());
                model.addAttribute(error.getField(), error.getDefaultMessage());
            }
            return "/user/join";
        }


    //이력서 작성---
    @GetMapping("/resume/add")
    public String resume_add_get(Model model){
        log.info("GET /resume/add..");
        model.addAttribute("resumeForm", new ResumeFormDto());
        return "seeker/resume/add"; // return the view name
    }

    @PostMapping("/resume/add")
    public String resume_add_post(@ModelAttribute ResumeFormDto form){
        log.info("POST /resume/add..");
        jobSeekerServiceImpl.resume_add(form);
        log.info("Form : "+form);
        return "redirect:/seeker/resume/list"; // 이력서 추가 후 목록 페이지로 리다이렉트

    }

    //수정 ------------------------------------------------------------

    @Autowired
    CareerRepository careerRepository;
    @GetMapping("/resume/update/{id}")
    public String resume_update_get(@PathVariable("id") long id, Model model) {
        log.info("id : "+id);
        log.info("GET /resume/update..");
        Optional<Resume> resumeOptional = jobSeekerServiceImpl.resume_read(id);


        if (resumeOptional.isPresent()) {
            Resume resume= resumeOptional.get();
            System.out.println("/resume/update/{id} resume : " + resume);

            model.addAttribute("resume", resume);


            //------------------------
            List<Career> list =  careerRepository.findAllByResume(resume);

            System.out.println("Career list ! " + list);
            model.addAttribute("list",list);
            //------------------------

            return "seeker/resume/update"; // 수정 페이지 보여주기
        } else {
            model.addAttribute("notFound", "이력서를 찾을 수 없습니다.");
            return "error"; // 에러 페이지 보여주기
        }
    }

    @PostMapping("/resume/update")
    public String resume_update_post(ResumeFormDto formDto) {
        log.info("formDto : "+formDto);
        Long id=formDto.getResume().getId();
        log.info("formDto.id : "+id);
        // Update the resume
        jobSeekerServiceImpl.resume_update(id, formDto);



     return "redirect:/seeker/resume/list";

    }





    //상세읽기--------------------------------------------------------------
    @GetMapping("/resume/read/{id}")
    public String resume_read_get(@PathVariable("id") Long id, Model model) {
        log.info("GET /resume/read..");

        Optional<Resume> resumeOptional = jobSeekerServiceImpl.resume_read(id);
        if (resumeOptional.isPresent()) {
            Resume resume = resumeOptional.get();
            model.addAttribute("resume", resume);
        } else {
            model.addAttribute("notFound", "이력서를 찾을 수 없습니다.");
        }
        return "seeker/resume/read"; // return the view name
    }



    //이력서 항목 리스트 조회------------------------
    @GetMapping("/resume/list")
    public String resume_list_get(Model model){
        log.info("GET /resume/list..");
        List<ResumeDto> resumesList= jobSeekerServiceImpl.resume_list();
        model.addAttribute("resumeList",resumesList);
        return "seeker/resume/list"; // return the view name
    }

        boolean isRegistered = jobSeekerServiceImpl.memberRegistration(null, seekerDto);
        if (!isRegistered) {
            model.addAttribute("registrationError", "회원가입 중 오류가 발생했습니다.");
            return "/user/join";
        }

        return "redirect:/user/login";



    @PostMapping("/resume/delete/{id}")
    public String resume_post_delete(@PathVariable("id")long id){
        log.info("Post/resume/delete...."+id);
        jobSeekerServiceImpl.resume_delete(id);
        return "redirect:/seeker/resume/list";
    }
}
