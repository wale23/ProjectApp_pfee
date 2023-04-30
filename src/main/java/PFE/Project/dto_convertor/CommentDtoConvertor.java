package PFE.Project.dto_convertor;

import PFE.Project.dto.CommentDto;
import PFE.Project.dto.ReclamationDto;
import PFE.Project.models.Comment;
import PFE.Project.models.Reclamation;
import org.springframework.stereotype.Component;

@Component
public class CommentDtoConvertor {
    public static CommentDto commentDto(Comment comment) {
        CommentDto commentDto= new CommentDto();
        commentDto.setId(comment.getId());
        commentDto.setUser(UserConvertor.userToDto(comment.getUser()));
        commentDto.setReclamation(ReclamationConvertor.reclamationToDto(comment.getReclamation()));
        commentDto.setDate(comment.getDate());
        commentDto.setComment(comment.getComment());


        return commentDto;
    }
}
