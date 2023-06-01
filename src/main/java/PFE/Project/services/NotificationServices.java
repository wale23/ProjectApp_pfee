package PFE.Project.services;

import PFE.Project.data.NotificationRepository;
import PFE.Project.dto.NotificationDto;
import PFE.Project.dto_convertor.NotificationDtoConvertor;
import PFE.Project.models.Notifcation;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationServices {
    final NotificationRepository notificationRepository;
    final FcmService fcmService;


    public NotificationServices(NotificationRepository notificationRepository, FcmService fcmService) {
        this.notificationRepository = notificationRepository;
        this.fcmService = fcmService;
    }

    public List<NotificationDto> getMyNotificationsService(Integer user_id){
        return notificationRepository.getNotifcationByReceiverId(user_id).stream().map(NotificationDtoConvertor::notificationDto).toList();

    }

    public void createNotification(Notifcation notifcation){
        fcmService.sendNotification(notifcation.getReceiver().getToken(), "#" + notifcation.getReclamation().getId().toString(), notifcation.getNotification());
        notificationRepository.save(notifcation);
    }

    public ResponseEntity deleteNotification(Integer notif_id){
        Notifcation notifcation=notificationRepository.findById(notif_id).orElse(null);
        notificationRepository.delete(notifcation);
        return ResponseEntity.status(200).body("done");

    }


}
