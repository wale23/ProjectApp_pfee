package PFE.Project.services;

import PFE.Project.data.ReclamationRepository;
import PFE.Project.data.UserRepository;
import PFE.Project.dto.ReclamationDto;
import PFE.Project.dto_convertor.ReclamationConvertor;
import PFE.Project.enumerate.Status;
import PFE.Project.models.Reclamation;
import PFE.Project.models.User;
import PFE.Project.requests.ReclamationRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service

public class ReclamationServices {
    final UserRepository userRepository;
    final ReclamationRepository reclamationRepository;

    public ReclamationServices(UserRepository userRepository, ReclamationRepository reclamationRepository) {
        this.userRepository = userRepository;
        this.reclamationRepository = reclamationRepository;
    }

    public ResponseEntity createReclamation(ReclamationRequest reclamationRequest) {
        Optional<User> user = userRepository.findById(reclamationRequest.getUser_id());

        Reclamation reclamation = new Reclamation();
        reclamation.setDate(LocalDateTime.now().toString());
        System.out.println(LocalDateTime.now());
        reclamation.setDescription(reclamationRequest.getDescription());
        reclamation.setPriority(reclamationRequest.getPriority());
        reclamation.setSubject(reclamationRequest.getSubject());
        reclamation.setUser(user.get());
        reclamation.setStatus("Aucune");
        reclamation.setArchive(false);
        reclamation.setPriority(reclamationRequest.getPriority());
        reclamation.setImages(reclamationRequest.getImages());
        reclamationRepository.save(reclamation);


        return ResponseEntity.status(200).body("done");
    }

    public List<ReclamationDto> getMyReclamationsWithStatus(Integer user_id, String status) {
        List<Reclamation> list;
        list = reclamationRepository.getReclamationByUserIdAndStatus(user_id, status);
        for (Reclamation reclamation : list) {

        }

        return list.stream().map(ReclamationConvertor::reclamationToDto).toList();
    }
    public List<ReclamationDto> getAllReclamationsWithStatus(String status) {
        List<Reclamation> list;
        list = reclamationRepository.getReclamationByStatus(status);


        return list.stream().map(ReclamationConvertor::reclamationToDto).toList();
    }

    public List<ReclamationDto> getMyReclamations(Integer user_id) {

        List<Reclamation> list = reclamationRepository.getReclamationByUserId(user_id);
        list.removeIf(Reclamation::isArchive);
        return list.stream().map(ReclamationConvertor::reclamationToDto).toList();
    }

    public ResponseEntity deleteReclamations(List<Integer> reclamations) {
        for (Integer index : reclamations) {
            Reclamation reclamation = reclamationRepository.findById(index).orElse(null);

            reclamation.getImages().clear();


            reclamationRepository.delete(reclamation);
        }
        return ResponseEntity.status(200).body("done");
    }

    public ResponseEntity changeStatus(int id, String status) {
        Reclamation reclamation = reclamationRepository.findById(id).orElse(null);
        reclamation.setStatus(status);
        reclamationRepository.save(reclamation);
        return ResponseEntity.status(200).body("done");
    }

    public ResponseEntity changeArchive(List<Integer> reclamations, boolean archive) {
        for (Integer index : reclamations) {
            Reclamation reclamation = reclamationRepository.findById(index).orElse(null);

            reclamation.setArchive(archive);
            reclamationRepository.save(reclamation);


        }
        return ResponseEntity.status(200).body("done");


    }

    public List<ReclamationDto> getArchived(int user_id) {
        List<Reclamation> list = reclamationRepository.getReclamationByUserIdAndAndArchive(user_id, true);
        return list.stream().map(ReclamationConvertor::reclamationToDto).toList();

    }

    public List<ReclamationDto> getAll() {
        List<Reclamation> list = reclamationRepository.findAll();
        return list.stream().map(ReclamationConvertor::reclamationToDto).toList();

    }
}
