package PFE.Project.dto_convertor;

import PFE.Project.dto.ReclamationDto;
import PFE.Project.dto.UserDto;
import PFE.Project.models.Reclamation;
import PFE.Project.models.User;
import org.springframework.stereotype.Component;

@Component
public class ReclamationConvertor {
    public static ReclamationDto reclamationToDto(Reclamation reclamation) {
        ReclamationDto reclamationDto = new ReclamationDto();
        reclamationDto.setId(reclamation.getId());
        reclamationDto.setUser(UserConvertor.userToDto(reclamation.getUser()));
        reclamationDto.setDate(reclamation.getDate());
        reclamationDto.setDescription(reclamation.getDescription());
        reclamationDto.setImages(reclamation.getImages());
        reclamationDto.setStatus(reclamation.getStatus());
        reclamationDto.setPriorirty(reclamation.getPriorirty());
        reclamationDto.setSubject(reclamation.getSubject());
        reclamationDto.setPriorirty(reclamation.getPriorirty());
        reclamationDto.setArchived(reclamation.isArchive());

        return reclamationDto;
    }
}
