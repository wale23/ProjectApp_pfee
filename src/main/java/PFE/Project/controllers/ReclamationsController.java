package PFE.Project.controllers;

import PFE.Project.dto.ReclamationDto;
import PFE.Project.enumerate.Status;
import PFE.Project.models.Reclamation;
import PFE.Project.models.StringListRequest;
import PFE.Project.requests.ReclamationRequest;
import PFE.Project.services.ReclamationServices;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(path = "api/v1")
@RestController
@AllArgsConstructor
public class ReclamationsController {
    final ReclamationServices reclamationServices;

    @PostMapping("/create-reclamation")
    public ResponseEntity createReclamation(@RequestBody ReclamationRequest reclamationRequest){
        return reclamationServices.createReclamation(reclamationRequest);
    }
    @GetMapping("/reclamations/{sender}/{status}")
    public List<ReclamationDto> getMyReclamationsWithStatus(@PathVariable int  sender, @PathVariable String status){
        return reclamationServices.getMyReclamationsWithStatus(sender,status);
    }
    @GetMapping("/reclamations/{user_id}")
    public List<ReclamationDto> getMyReclamations(@PathVariable int  user_id){
        return reclamationServices.getMyReclamations(user_id);
    }
    @GetMapping("/archived/{user_id}")
    public List<ReclamationDto> archived(@PathVariable int  user_id){
        return reclamationServices.getArchived(user_id);
    }

    @PostMapping("/delete")
    public ResponseEntity handleStringListRequest(@RequestBody StringListRequest request) {
        List<Integer> ids = request.getIds();
         return reclamationServices.deleteReclamations(ids);
    }
    @GetMapping("/status/{id}/{status}")
    public ResponseEntity changeStatus(@PathVariable int  id,@PathVariable  String status){
        return reclamationServices.changeStatus(id,status);
    }
    @PostMapping("/archive/{archive}")
    public ResponseEntity changeArchive(@RequestBody StringListRequest request,@PathVariable boolean archive){
        List<Integer> ids = request.getIds();
        return reclamationServices.changeArchive(ids,archive);
    }
    @GetMapping("/bystatus/{status}")
    public List<ReclamationDto> getAllByStatus(@PathVariable String status){
        return reclamationServices.getAllReclamationsWithStatus(status);
    }
    @GetMapping("/all")
    public List<ReclamationDto> getAll(){
        return reclamationServices.getAll();
    }



    // my recieved reclamations
    @GetMapping("/reclamations/recu/{receiver}/{status}")
    public List<ReclamationDto> getMyReceivedReclamationsWithStatus(@PathVariable int  receiver, @PathVariable String status){
        return reclamationServices.getMyRecievedReclamationsWithStatus(receiver,status);
    }

    @GetMapping("/reclamations/recu/{user_id}")
    public List<ReclamationDto> getMyReceivedReclamations(@PathVariable int  user_id){
        return reclamationServices.getMyReclamations(user_id);
    }

}
