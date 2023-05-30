package PFE.Project.requests;

import PFE.Project.enumerate.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
@Getter
@Setter
public class ReclamationRequest {
    String subject;
    String priority;
    String departement;
    String description;
    List<String> images;
    Integer sender;
    Integer receiver;
}
