package PFE.Project.services;

import PFE.Project.data.CommentsRepository;
import PFE.Project.data.ReclamationRepository;
import PFE.Project.data.UserRepository;
import PFE.Project.dto.CommentDto;
import PFE.Project.dto_convertor.CommentDtoConvertor;
import PFE.Project.dto_convertor.ReclamationConvertor;
import PFE.Project.models.Comment;
import PFE.Project.models.Reclamation;
import PFE.Project.models.User;
import PFE.Project.requests.CommentRequest;
import PFE.Project.requests.ReclamationRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class CommentsServices {
    CommentsRepository commentsRepository;
    ReclamationRepository reclamationRepository;
    UserRepository userRepository;

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
        return ResponseEntity.status(200).body("done");
    }

    public List<CommentDto> getComments(Integer reclamation_id) {
        List<Comment> comments=commentsRepository.getCommentByReclamationId(reclamation_id);
        return comments.stream().map(CommentDtoConvertor::commentDto).toList();

    }
}
