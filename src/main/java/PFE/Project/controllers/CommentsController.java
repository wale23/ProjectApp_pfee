package PFE.Project.controllers;

import PFE.Project.dto.CommentDto;
import PFE.Project.requests.CommentRequest;
import PFE.Project.requests.ReclamationRequest;
import PFE.Project.services.CommentsServices;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RequestMapping(path = "api/v1")

@AllArgsConstructor
@RestController
public class CommentsController {
    CommentsServices commentsServices;
    @PostMapping("/comment")
    public ResponseEntity createReclamation(@RequestBody CommentRequest commentRequest){
        return commentsServices.createComment(commentRequest);
    }
    @GetMapping("/comments/{reclamation_id}")
    public List<CommentDto> getComments(@PathVariable Integer reclamation_id){
        return commentsServices.getComments(reclamation_id);
    }
}
