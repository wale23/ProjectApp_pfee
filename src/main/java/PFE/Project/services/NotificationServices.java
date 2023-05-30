package PFE.Project.services;

import PFE.Project.data.NotificationRepository;
import PFE.Project.dto.NotificationDto;
import PFE.Project.dto_convertor.CommentDtoConvertor;
import PFE.Project.dto_convertor.NotificationDtoConvertor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationServices {
    final NotificationRepository notificationRepository;

    public NotificationServices(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public List<NotificationDto> getMyNotificationsService(Integer user_id){
        return notificationRepository.getNotifcationByReceiverId(user_id).stream().map(NotificationDtoConvertor::notificationDto).toList();

    }
}
