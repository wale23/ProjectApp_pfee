package PFE.Project.data;

import PFE.Project.enumerate.Status;
import PFE.Project.models.Reclamation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReclamationRepository extends JpaRepository<Reclamation,Integer> {
    List<Reclamation> getReclamationBySenderIdAndStatusOrderByDateDesc(Integer userId,String status);
    List<Reclamation> getReclamationByReceiverIdAndStatusOrderByDateDesc(Integer userId,String status);
    List<Reclamation> getReclamationByStatusOrderByDateDesc(String status);
    List<Reclamation> getReclamationBySenderIdOrderByDateDesc(Integer userId);
    List<Reclamation> getReclamationByReceiverIdOrderByDateDesc(Integer userId);
    List<Reclamation> getReclamationBySenderIdAndAndArchiveOrderByDateDesc(Integer userId,boolean archive);
    List<Reclamation> findAllByOrderByDateDesc();
}
