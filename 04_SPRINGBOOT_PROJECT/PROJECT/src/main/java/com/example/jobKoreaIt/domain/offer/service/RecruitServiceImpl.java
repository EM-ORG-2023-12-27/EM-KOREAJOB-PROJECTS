package com.example.jobKoreaIt.domain.offer.service;

import com.example.jobKoreaIt.domain.common.repository.UserRepository;
import com.example.jobKoreaIt.domain.offer.dto.RecruitDto;
import com.example.jobKoreaIt.domain.offer.dto.RecruitFormDto;
import com.example.jobKoreaIt.domain.offer.entity.Company;
import com.example.jobKoreaIt.domain.offer.entity.Recruit;
import com.example.jobKoreaIt.domain.offer.repository.CompanyRepository;
import com.example.jobKoreaIt.domain.offer.repository.JobOfferRepository;
import com.example.jobKoreaIt.domain.seeker.repository.RecruitRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

@Service
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
public class RecruitServiceImpl {

    @Autowired
    private JobOfferRepository jobOfferRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RecruitRepository recruitRepository;
    private CompanyRepository companyRepository;

    RecruitFormDto recruitFormDto;
    private RecruitFormDto recruitForm;

    @Autowired
    public void JobOfferServiceImpl(RecruitRepository recruitRepository, CompanyRepository companyRepository){
        this.recruitRepository = recruitRepository;
        this.companyRepository = companyRepository;
        this.recruitForm = new RecruitFormDto();
    }




    @Transactional(rollbackOn = Exception.class)
    public void recruit_add(RecruitFormDto recruitForm){
        log.info("JobOfferRecruitRepository/Recruit_add...!");
        log.info("Received company in service : " + recruitForm.getCompany());

        // Recruit 객체를 가져와서 저장
        Recruit recruit = recruitForm.getRecruit();

        // 각 Compnay 객체에 Recruit 객체 설정
        List<Company> companies = recruitForm.getCompanies();
        if (companies != null ){
            //CopyOnWriteArrayList 사용
            List<Company> companiesToSave = new CopyOnWriteArrayList<>(companies);
            log.info("Original companies list size : " + companies.size());;
            log.info("Copied companies list size : " + companiesToSave.size());

            for(Company company : companiesToSave){
                log.info("Setting recruit for company : " + company);
                company.setRecruit(recruit);
            }

            //Recruit 객체에 company 리스트 설정
            recruit.setCompanies(companiesToSave);
            
           //Recruit 저장
            log.info("Saving recruit : " + recruit);
            recruitRepository.save(recruit);

           // 각  Company 객체 저장 (CascadeType.ALL을 사용하여 자동으러 저장되도록 할 수 있습니다) 
            for (Company company : companiesToSave){
                log.info("Saving company : " + company);
                companyRepository.save(company);
            }
        }else { 
            //Recruit 저장 ( companies 가 없는 경우 ) 
            log.info("Saving recruit without companies: " + recruit);
            recruitRepository.save(recruit);
    }

}

@Transactional(rollbackOn = Exception.class)
public List<RecruitDto> recruit_list(){
    log.info("JobOfferRepository/recruitListAll...");
    List<Recruit> recruitList = recruitRepository.findAll();
    return recruitList.stream()
            .map(this::recruit_dto_list) //Recruit을 list로 반환 
            .collect(Collectors.toList());
    }

    //필요한 정보만을 꺼내서 dto에 넣어준다
    public  RecruitDto recruit_dto_list(Recruit recruit){
        RecruitDto dto = new RecruitDto();
        dto.setId(recruit.getId());
        dto.setJobplace(recruit.getJobplace());
        dto.setJobzone(recruit.getJobzone());
        return dto;
    }

    //채용공고 상세보기위한 서비스
    @Transactional(rollbackOn = Exception.class)
    public Optional<Recruit> recruit_read(Long id)
    {
        log.info("JobOfferRepository/read...");
        return recruitRepository.findById(id);
    }

    //채용공고 삭제
    @Transactional(rollbackOn = Exception.class)
    public void recruit_delete(long id){
        log.info("recruit_delete invoke.." + id);
        Optional<Recruit> optionalRecruit = recruitRepository.findById(id);
        if(optionalRecruit.isPresent()){
            Recruit recruit = optionalRecruit.get();
            recruitRepository.delete(recruit);
            companyRepository.deleteAllByRecruit(recruit);
            log.info("채용공고 삭제성공");
        }else {
            log.info("채용공고 삭제 실패",id);
        }
    }

    @Transactional(rollbackOn = Exception.class)
    public void recruit_update(long id, RecruitFormDto updatedRecruit){
        //채용공고 업데이트 처리
        Optional<Recruit> optionalRecruit = recruitRepository.findById(id);

        if(optionalRecruit.isPresent()){
            Recruit recruit = optionalRecruit.get();
            Recruit updatedRecruitDto =  updatedRecruit.getRecruit();
            System.out.println("updatedRecruitDto : " + updatedRecruitDto);

            //수정된 내용 업데이트
            recruit.setTitle(updatedRecruitDto.getTitle());
            recruit.setAbility(updatedRecruitDto.getAbility());
            recruit.setCareer(updatedRecruitDto.getCareer());
            recruit.setJobwork(updatedRecruitDto.getJobwork());
            recruit.setMoney(updatedRecruitDto.getMoney());
            recruit.setJobspecial(updatedRecruitDto.getJobspecial());
            recruit.setJobzone(updatedRecruitDto.getJobzone());
            recruit.setWelfare(updatedRecruitDto.getWelfare());
            recruit.setJobplace(updatedRecruitDto.getJobplace());
            recruit.setTime(updatedRecruitDto.getTime());
            recruit.setJobway(updatedRecruitDto.getJobway());
            recruit.setJobpapers(updatedRecruitDto.getJobpapers());
            recruit.setFiles(updatedRecruitDto.getFiles());

            recruitRepository.save(recruit);
            log.info("Recruit with id [] updated successfully", id);
            } else {
            log.info("Recruit with id [] not found ", id);



        }
    }
}
