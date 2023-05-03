package PFE.Project.data;

import PFE.Project.enumerate.Status;
import PFE.Project.models.Reclamation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReclamationRepository extends JpaRepository<Reclamation,Integer> {
    List<Reclamation> getReclamationByUserIdAndStatus(Integer userId,String status);
    List<Reclamation> getReclamationByStatus(String status);
    List<Reclamation> getReclamationByUserId(Integer userId);
    List<Reclamation> getReclamationByUserIdAndAndArchive(Integer userId,boolean archive);
}
