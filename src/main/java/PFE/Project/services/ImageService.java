package PFE.Project.services;

import org.springframework.core.io.ClassPathResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import org.springframework.core.io.Resource;
@Service
public class ImageService {

    public List<String> saveImages(List<MultipartFile> images)  {
        List<String> paths = new ArrayList<>();
        try {
            for (MultipartFile image : images) {
                String filename = image.getOriginalFilename();
                paths.add(filename);
                System.out.println(filename);
                byte[] bytes = image.getBytes();
                Path path = Paths.get(System.getProperty("user.dir") + "/src/main/resources/static/images/" + filename);

                Files.write(path, bytes);
               }

            return  paths;
        }catch (Exception e){
            return  paths;
        }
        }


}
