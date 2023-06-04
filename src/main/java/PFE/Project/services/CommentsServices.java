package PFE.Project.services;

import PFE.Project.data.CommentsRepository;
import PFE.Project.data.ReclamationRepository;
import PFE.Project.data.UserRepository;
import PFE.Project.dto.CommentDto;
import PFE.Project.dto_convertor.CommentDtoConvertor;
import PFE.Project.dto_convertor.ReclamationConvertor;
import PFE.Project.models.Comment;
import PFE.Project.models.Notifcation;
import PFE.Project.models.Reclamation;
import PFE.Project.models.User;
import PFE.Project.requests.CommentRequest;
import PFE.Project.requests.ReclamationRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class CommentsServices {
    NotificationServices notificationServices;
    CommentsRepository commentsRepository;
    ReclamationRepository reclamationRepository;
    UserRepository userRepository;
    UserServices userServices;

    public ResponseEntity createComment(CommentRequest commentRequest) {
        Comment comment = new Comment();
        Optional<Reclamation> reclamation = reclamationRepository.findById(commentRequest.getReclamation_id());
        Optional<User> user = userRepository.findById(commentRequest.getUser_id());
        comment.setComment(commentRequest.getComment());
        comment.setReclamation(reclamation.get());
        comment.setDate(LocalDateTime.now().toString());
        comment.setComment(commentRequest.getComment());
        comment.setUser(user.get());
        commentsRepository.save(comment);

        User sender=reclamation.get().getSender();
        User receiver=reclamation.get().getReceiver();


        // manage notification


        if(sender==user.get()){
            Notifcation notifcation = new Notifcation();
            notifcation.setDate(LocalDateTime.now().toString());
            notifcation.setReceiver(reclamation.get().getReceiver());
            notifcation.setSender(reclamation.get().getSender());
            notifcation.setType("comment");
            notifcation.setNotification(String.format("%s a ajouté un commentaire pour la  reclamation #%s", reclamation.get().getSender().getFull_name(), reclamation.get().getId()));
            notifcation.setReclamation(reclamation.get());
            notificationServices.createNotification(notifcation);

            // send email to receiver
            userServices.sendEmail(
                    reclamation.get().getReceiver().getEmail(), reclamation.get().getSubject(), notifcation.getNotification()
            );
        }else{
            Notifcation notifcation = new Notifcation();
            notifcation.setDate(LocalDateTime.now().toString());
            notifcation.setReceiver(reclamation.get().getSender());
            notifcation.setSender(reclamation.get().getReceiver());
            notifcation.setType("comment");
            notifcation.setNotification(String.format("%s a ajouté un commentaire pour la  reclamation #%s", reclamation.get().getReceiver().getFull_name(), reclamation.get().getId()));
            notifcation.setReclamation(reclamation.get());
            notificationServices.createNotification(notifcation);

            // send email to receiver
            userServices.sendEmail(
                    reclamation.get().getSender().getEmail(), reclamation.get().getSubject(), notifcation.getNotification()
            );
        }
        return ResponseEntity.status(200).body("done");
    }

    public List<CommentDto> getComments(Integer reclamation_id) {
        List<Comment> comments=commentsRepository.getCommentByReclamationId(reclamation_id);
        comments.sort(Comparator.comparing(this::parseDate).reversed());

        return comments.stream().map(CommentDtoConvertor::commentDto).toList();

    }
    private LocalDateTime parseDate(Comment comment) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.parse(comment.getDate(), formatter);
    }
}
