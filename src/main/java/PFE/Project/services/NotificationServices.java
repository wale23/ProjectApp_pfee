package PFE.Project.services;

import PFE.Project.data.NotificationRepository;
import PFE.Project.dto.NotificationDto;
import PFE.Project.dto_convertor.NotificationDtoConvertor;
import PFE.Project.models.Notifcation;
import PFE.Project.models.Reclamation;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
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
        List<Notifcation> notif=notificationRepository.getNotifcationByReceiverId(user_id);
        notif.sort(Comparator.comparing(this::parseDate).reversed());
        return notif.stream().map(NotificationDtoConvertor::notificationDto).toList();

    }

    public void createNotification(Notifcation notifcation){
        fcmService.sendNotification(notifcation.getReceiver().getToken(), "#" + notifcation.getReclamation().getId().toString(), notifcation.getNotification());
        notificationRepository.save(notifcation);
    }

    public ResponseEntity deleteNotification(List<Integer> ids){
        for (Integer index : ids) {
            Notifcation notifcation=notificationRepository.findById(index).orElse(null);
            notificationRepository.delete(notifcation);
        }
        return ResponseEntity.status(200).body("done");


    }

    private LocalDateTime parseDate(Notifcation notifcation) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.parse(notifcation.getDate(), formatter);
    }
}
