package com.example.jobKoreaIt.domain.common.service;



import com.example.jobKoreaIt.domain.common.entity.User;
import com.example.jobKoreaIt.domain.common.repository.UserRepository;
import com.example.jobKoreaIt.domain.offer.dto.OfferDto;
import com.example.jobKoreaIt.domain.offer.entity.JobOffer;
import com.example.jobKoreaIt.domain.offer.repository.JobOfferRepository;
import com.example.jobKoreaIt.domain.seeker.dto.SeekerDto;
import com.example.jobKoreaIt.domain.seeker.entity.JobSeeker;
import com.example.jobKoreaIt.domain.seeker.repository.JobSeekerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JobSeekerRepository jobSeekerRepository;

    @Autowired
    private JobOfferRepository jobOfferRepository;


    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public JobSeeker getSeeker(SeekerDto seekerDto) {
        Optional<JobSeeker> op = jobSeekerRepository.findFirstByNicknameAndTel(seekerDto.getNickname(), seekerDto.getTel());
        return op.get();
    }

    @Override
    public JobOffer getOffer(OfferDto offerDto) {
        Optional<JobOffer> jp = jobOfferRepository.findFirstByOffernameAndOffertel(offerDto.getOffername(), offerDto.getOffertel());
        return jp.get();
    }

    @Override
    public User getUser(SeekerDto userDto, String type) {
        return null;
    }

    @Override
    public User getUser(OfferDto userDto, String type) {
        return null;
    }

    @Override
    public boolean confirmIdPw(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user != null) {
            return passwordEncoder.matches(password, user.getPassword());
        }
        return false;
    }

    @Override
    public User getUserByEmail(String email) {
        Optional<User> userOptional = Optional.ofNullable(userRepository.findByEmail(email));
        return userOptional.orElse(null);
    }

    @Override
    public User getUserByUsername(String username) {
        Optional<User> userOptional = userRepository.findFirstByUsername(username);
        return userOptional.orElse(null);
    }

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }
}
