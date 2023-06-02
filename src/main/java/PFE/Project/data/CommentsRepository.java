package PFE.Project.data;

import PFE.Project.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentsRepository extends JpaRepository<Comment,Integer> {
    List<Comment> getCommentByReclamationIdOrderByDateDesc(Integer reclamationId);
}
