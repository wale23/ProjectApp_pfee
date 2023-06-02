package PFE.Project.controllers;


import PFE.Project.data.ResetPasswordRequestRepository;

import PFE.Project.dto.UserDto;
import PFE.Project.requests.ChangePasswordRequest;
import PFE.Project.requests.LoginRequest;
import PFE.Project.requests.RegistrationRequest;
import PFE.Project.services.ImageService;
import PFE.Project.services.UserServices;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.Path;
import java.nio.file.Paths;

@RequestMapping(path = "api/v1")
@RestController

public class UserController {


    private UserServices userServices;
    private ImageService imageService;
    private ResetPasswordRequestRepository resetPasswordRequestRepository;

    public UserController(ImageService imageService,UserServices userServices, ResetPasswordRequestRepository resetPasswordRequestRepository) {
        this.userServices = userServices;
        this.imageService = imageService;
        this.resetPasswordRequestRepository = resetPasswordRequestRepository;
    }

    @PostMapping("/register")
    public ResponseEntity Register(@RequestBody RegistrationRequest registrationRequest) {

        return userServices.registerService(registrationRequest);
    }

    @PostMapping("/login")
    public ResponseEntity Login(@RequestBody LoginRequest loginRequest) {
        return userServices.loginService(loginRequest);
    }

    @PostMapping("/change-password")
    public ResponseEntity ResetPassword(@RequestBody ChangePasswordRequest changePasswordRequest) {
        return userServices.changePassword(changePasswordRequest);
    }

    @GetMapping("/check-otp/{code}")
    public ResponseEntity CheckOtp(@PathVariable Long code) {
        return userServices.checkOtp(code);
    }
    @GetMapping("/users/{id}")
    public List<UserDto> getUsers(@PathVariable Integer id) {
        return userServices.getAllUsers(id);
    }

    @GetMapping("/reset-password/{email}")
    public ResponseEntity ResetPassword(@PathVariable String email) {
        return userServices.resetPasswordService(email);
    }


    @PostMapping("/upload")
    public List<String> handleImageUpload(@RequestParam("images") List<MultipartFile> images) throws IOException {
       return imageService.saveImages(images);

    }

    @GetMapping("/images/{filename}")
    public ResponseEntity<byte[]> serveImage(@PathVariable String filename) {
        Path imagePath = Paths.get(System.getProperty("user.dir") + "/src/main/resources/static/images/" + filename);
        try {
            byte[] imageBytes = Files.readAllBytes(imagePath);
            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_JPEG)
                    .body(imageBytes);
        } catch (IOException e) {
            throw new RuntimeException("Could not read image file: " + filename, e);
        }
    }

}
/*
@Controller
@RequestMapping(path = "api/v1")

class  WebController{
    private JavaMailSender mailSender;
    private UserRepository userRepository;

    public WebController(UserRepository userRepository, JavaMailSender mailSender) {
        this.userRepository = userRepository;
        this.mailSender = mailSender;
    }
    @GetMapping("/reset-password")
    public String  ResetPassword(*/
/*@RequestBody String email*//*
) {

        */
/*  return userServices.resetPassword(email);*//*

        return "reset-password";
    }
    @PostMapping("/submit-form")
      public ResponseEntity<String> submitForm(@RequestParam("email") String email) {
       Optional<User>  user= userRepository.findByEmail(email);
        if(user.isPresent()){
            return ResponseEntity.status(200).body("An email with password reset instructions has been sent to your email address.");

        }
        return ResponseEntity.status(400).body("Not found");

    }
}*/
