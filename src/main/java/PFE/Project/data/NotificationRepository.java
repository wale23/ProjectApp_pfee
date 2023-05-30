package PFE.Project.data;

import PFE.Project.models.Notifcation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends JpaRepository<Notifcation,Integer> {
}
