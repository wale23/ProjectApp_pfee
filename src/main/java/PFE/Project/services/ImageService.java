package PFE.Project.services;

import org.springframework.core.io.ClassPathResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import org.springframework.core.io.Resource;
@Service
public class ImageService {

    public ResponseEntity saveImages(List<MultipartFile> images)  {

        try {
            for (MultipartFile image : images) {
                String filename = image.getOriginalFilename();
                System.out.println(filename);
                byte[] bytes = image.getBytes();
                Path path = Paths.get(System.getProperty("user.dir") + "/src/main/resources/static/images/" + filename);

                Files.write(path, bytes);
               }

            return  ResponseEntity.status(200).body("done");
        }catch (Exception e){
            return  ResponseEntity.status(200).body(e.toString());
        }
        }


}
