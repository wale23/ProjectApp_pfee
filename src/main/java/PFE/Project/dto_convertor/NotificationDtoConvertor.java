package PFE.Project.dto_convertor;

import PFE.Project.dto.CommentDto;
import PFE.Project.dto.NotificationDto;
import PFE.Project.models.Comment;
import PFE.Project.models.Notifcation;

public class NotificationDtoConvertor {
    public static NotificationDto commentDto(Notifcation notifcation) {
        NotificationDto notificationDto= new NotificationDto();
        notificationDto.setNotification(notifcation.getNotification());
        notificationDto.setId(notifcation.getId());
        notificationDto.setSender(UserConvertor.userToDto(notifcation.getSender()));
        notificationDto.setReceiver(UserConvertor.userToDto(notifcation.getReceiver()));
        notificationDto.setDate(notifcation.getDate());
        return notificationDto;
    }
}
