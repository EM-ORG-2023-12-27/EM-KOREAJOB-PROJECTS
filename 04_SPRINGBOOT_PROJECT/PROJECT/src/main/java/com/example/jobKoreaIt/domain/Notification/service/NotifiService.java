package com.example.jobKoreaIt.domain.Notification.service;

import com.example.jobKoreaIt.domain.Notification.dto.notificationDto;
import com.example.jobKoreaIt.domain.Notification.entity.NotifiEntity;
import com.example.jobKoreaIt.domain.Notification.repository.NotifiRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class NotifiService {

    private final NotifiRepository notifiRepository;

    @Autowired
    public NotifiService(NotifiRepository notifiRepository) {
        this.notifiRepository = notifiRepository;
    }
    @Transactional(rollbackFor = Exception.class)
    public void addNotification(notificationDto dto) {
        log.info("Received notification DTO: {}", dto);

        // Create a new NotifiEntity instance
        NotifiEntity entity = new NotifiEntity();
        entity.setTitle(dto.getTitle());
        entity.setAuthor(dto.getAuthor());
        entity.setCreateAt(LocalDateTime.now());
        entity.setId(dto.getId());
        entity.setContents(dto.getContents());

        // Save the entity using the repository
        NotifiEntity savedEntity = notifiRepository.save(entity);

        log.info("Saved notification entity: {}", savedEntity);
    }
    @Transactional(rollbackFor = Exception.class)
    public List<notificationDto> notifi_list() {
        log.info("NotifiService/notifi_list..");
        List<NotifiEntity> entity=notifiRepository.findAll();

        List<notificationDto> dtos=entity.stream()
                .map(entities->{
                    notificationDto dto=new notificationDto();
                    dto.setId(entities.getId());
                    dto.setTitle(entities.getTitle());
                    dto.setCreateAt(entities.getCreateAt());
                    return dto;
                })
                .collect(Collectors.toList());
        log.info("dtos : " +dtos);
                return dtos;
    }

    @Transactional(rollbackFor = Exception.class)
    public Optional<NotifiEntity> notifi_read(Long id) {
        log.info("Notifi_read_id : "+id);
        log.info("notifiService/notifi_read...!");
        return notifiRepository.findById(id);
    }
}

