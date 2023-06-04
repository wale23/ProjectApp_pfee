package PFE.Project.data;

import PFE.Project.enumerate.Status;
import PFE.Project.models.Reclamation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReclamationRepository extends JpaRepository<Reclamation,Integer> {
    List<Reclamation> getReclamationBySenderIdAndStatus(Integer userId,String status);
    List<Reclamation> getReclamationByReceiverIdAndStatus(Integer userId,String status);
    List<Reclamation> getReclamationByStatus(String status);
    List<Reclamation> getReclamationBySenderId(Integer userId);
    List<Reclamation> getReclamationByReceiverId(Integer userId);
    List<Reclamation> getReclamationBySenderIdAndAndArchive(Integer userId,boolean archive);
    List<Reclamation> findAllByOrderByDateDesc();
}
