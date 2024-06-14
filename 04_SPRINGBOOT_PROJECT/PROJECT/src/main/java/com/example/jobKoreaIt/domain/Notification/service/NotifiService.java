package com.example.jobKoreaIt.domain.Notification.service;

import com.example.jobKoreaIt.domain.Notification.dto.notificationDto;
import com.example.jobKoreaIt.domain.Notification.entity.NotifiEntity;
import com.example.jobKoreaIt.domain.Notification.repository.NotifiRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class NotifiService {


    private final NotifiRepository notifiRepository;

    @Autowired
    NotifiEntity notifiEntity;

    private notificationDto notificationDto=new notificationDto();

    public NotifiService(NotifiRepository notifiRepository) {
        this.notifiRepository = notifiRepository;
    }


    public void notifi_add(notificationDto notificationDto){
        log.info("notificationDto : "+notificationDto);

        notifiEntity.setAuthor(notificationDto.getAuthor());
        notifiEntity.setId(notificationDto.getId());
        notifiEntity.setTitle(notificationDto.getTitle());
        notifiEntity.setCreateAt(notificationDto.getCreateAt());

        log.info("notifiEntity : "+notifiEntity);
        notifiRepository.save(notifiEntity);

    }
}
