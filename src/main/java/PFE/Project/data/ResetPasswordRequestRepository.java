package PFE.Project.data;

import PFE.Project.models.ResetPasswordRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface ResetPasswordRequestRepository extends JpaRepository<ResetPasswordRequest , Long> {
   Optional<ResetPasswordRequest> getResetPasswordRequestByCode(Long code);
    @Transactional
    @Modifying
   // @Query("delete from ResetPasswordRequest r where r.code = ?1")
    int deleteByCode(Long code);
}
