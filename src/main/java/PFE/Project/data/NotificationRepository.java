package PFE.Project.data;

import PFE.Project.models.Notifcation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notifcation,Integer> {
    List<Notifcation> getNotifcationByReceiverId(Integer id);
}
