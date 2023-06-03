package PFE.Project.services;

import PFE.Project.data.ReclamationRepository;
import PFE.Project.data.UserRepository;
import PFE.Project.dto.ReclamationDto;
import PFE.Project.dto_convertor.ReclamationConvertor;
import PFE.Project.models.Notifcation;
import PFE.Project.models.Reclamation;
import PFE.Project.models.User;
import PFE.Project.requests.ReclamationRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ReclamationServices {
    final UserRepository userRepository;
    final UserServices userServices;
    final ReclamationRepository reclamationRepository;
    final NotificationServices notificationServices;



    public ResponseEntity createReclamation(ReclamationRequest reclamationRequest) {
        Optional<User> sender = userRepository.findById(reclamationRequest.getSender());
        Optional<User> receiver = userRepository.findById(reclamationRequest.getReceiver());

        Reclamation reclamation = new Reclamation();
        reclamation.setDate(LocalDateTime.now());
        reclamation.setDescription(reclamationRequest.getDescription());
        reclamation.setSubject(reclamationRequest.getSubject());
        reclamation.setReceiver(receiver.get());
        reclamation.setSender(sender.get());
        reclamation.setStatus("Aucune");
        reclamation.setArchive(false);
        reclamation.setPriority(reclamationRequest.getPriority());
        reclamation.setDepartement(reclamationRequest.getDepartement());
        reclamation.setImages(reclamationRequest.getImages());
        reclamationRepository.save(reclamation);

        // manage notification
        Notifcation notifcation = new Notifcation();
        notifcation.setDate(LocalDateTime.now());
        notifcation.setReceiver(receiver.get());
        notifcation.setSender(sender.get());
        notifcation.setType("create");
        notifcation.setNotification(String.format("Vous avez recu la reclamation #%s de la part de %s ", reclamation.getId(), sender.get().getFull_name()));
        notifcation.setReclamation(reclamation);
        notificationServices.createNotification(notifcation);

        // send email to receiver
        userServices.sendEmail(
                receiver.get().getEmail(), reclamation.getSubject(), notifcation.getNotification()
        );

        //send firebase notif
        return ResponseEntity.status(200).body("done");
    }

    public List<ReclamationDto> getMyReclamationsWithStatus(Integer sender, String status) {
        List<Reclamation> list;
        list = reclamationRepository.getReclamationBySenderIdAndStatusOrderByDateDesc(sender, status);
        list.removeIf(Reclamation::isArchive);


        return list.stream().map(ReclamationConvertor::reclamationToDto).toList();
    }


    public List<ReclamationDto> getAllReclamationsWithStatus(String status) {
        List<Reclamation> list;
        list = reclamationRepository.getReclamationByStatusOrderByDateDesc(status);

        list.removeIf(Reclamation::isArchive);
        return list.stream().map(ReclamationConvertor::reclamationToDto).toList();
    }

    public List<ReclamationDto> getMyReclamations(Integer sender) {

        List<Reclamation> list = reclamationRepository.getReclamationBySenderIdOrderByDateDesc(sender);
        list.removeIf(Reclamation::isArchive);
        return list.stream().map(ReclamationConvertor::reclamationToDto).toList();
    }


    public List<ReclamationDto> getMyReceivedReclamations(Integer sender) {

        List<Reclamation> list = reclamationRepository.getReclamationByReceiverIdOrderByDateDesc(sender);
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
        String oldStatus=reclamation.getStatus();
        reclamation.setStatus(status);

        reclamationRepository.save(reclamation);

        // manage notification
        Notifcation notifcation = new Notifcation();
        notifcation.setDate(LocalDateTime.now());
        notifcation.setReceiver(reclamation.getSender());
        notifcation.setSender(reclamation.getReceiver());
        notifcation.setType("edit");
        notifcation.setNotification(String.format("%s a change le statut de la  reclamation #%s de %s à %s", reclamation.getReceiver().getFull_name(), reclamation.getId(), oldStatus, status));
        notifcation.setReclamation(reclamation);
        notificationServices.createNotification(notifcation);

        // send email to receiver
        userServices.sendEmail(
                reclamation.getSender().getEmail(), reclamation.getSubject(), notifcation.getNotification()
        );


        return ResponseEntity.status(200).body("done");
    }

    public ResponseEntity changeArchive(List<Integer> reclamations, boolean archive) {
        for (Integer index : reclamations) {
            System.out.println(index);
            Reclamation reclamation = reclamationRepository.findById(index).orElse(null);

            reclamation.setArchive(archive);
            reclamationRepository.save(reclamation);


        }
        return ResponseEntity.status(200).body("done");


    }

    public List<ReclamationDto> getArchived(int user_id) {
        List<Reclamation> list = reclamationRepository.getReclamationBySenderIdAndAndArchiveOrderByDateDesc(user_id, true);
        return list.stream().map(ReclamationConvertor::reclamationToDto).toList();

    }

    public List<ReclamationDto> getAll() {
        List<Reclamation> list = reclamationRepository.findAllByOrderByDateDesc();
        return list.stream().map(ReclamationConvertor::reclamationToDto).toList();

    }

    // My received reclamations
    public List<ReclamationDto> getMyRecievedReclamationsWithStatus(Integer receiver, String status) {
        List<Reclamation> list;
        list = reclamationRepository.getReclamationByReceiverIdAndStatusOrderByDateDesc(receiver, status);
        list.removeIf(Reclamation::isArchive);


        return list.stream().map(ReclamationConvertor::reclamationToDto).toList();
    }
}
