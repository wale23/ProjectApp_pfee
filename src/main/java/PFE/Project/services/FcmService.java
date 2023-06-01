package PFE.Project.services;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import org.springframework.stereotype.Service;

@Service
public class FcmService {

    public void sendNotification(String token, String title, String body) {
        Message message = Message.builder()
                .setNotification(new Notification(title, body))
                .setToken(token)
                .build();

        try {
            FirebaseMessaging.getInstance().send(message);
        } catch (Exception e) {
            throw new RuntimeException("Failed to send FCM notification.", e);
        }
    }
}
