package PFE.Project.controllers;

import PFE.Project.dto.NotificationDto;
import PFE.Project.models.Notifcation;
import PFE.Project.services.NotificationServices;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(path = "api/v1")


@RestController
public class NotificationController {
    final NotificationServices notificationServices;

    public NotificationController(NotificationServices notificationServices) {
        this.notificationServices = notificationServices;
    }

    @GetMapping("/notifications/{user_id}")
    List<NotificationDto> getMyNotifications(@PathVariable Integer user_id){
    return notificationServices.getMyNotificationsService(user_id);
    }

    @GetMapping("/delete-notif/{id}")
    public ResponseEntity deleteNotification(@PathVariable Integer id){
        return notificationServices.deleteNotification(id);
    }
}
