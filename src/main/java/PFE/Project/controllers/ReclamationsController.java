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
    @GetMapping("/reclamations/{user_id}/{status}")
    public List<ReclamationDto> getMyReclamationsWithStatus(@PathVariable int  user_id, @PathVariable String status){
        return reclamationServices.getMyReclamationsWithStatus(user_id,status);
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
    @PutMapping("/status/{id}/{status}")
    public ResponseEntity changeStatus(@PathVariable int  id,@PathVariable  String status){
        return reclamationServices.changeStatus(id,status);
    }
    @PutMapping("/archive/{id}/{archive}")
    public ResponseEntity changeArchive(@PathVariable int  id,@PathVariable  boolean archive){
        return reclamationServices.changeArchive(id,archive);
    }

}
