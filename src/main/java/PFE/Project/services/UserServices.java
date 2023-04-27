package PFE.Project.services;


import PFE.Project.data.ResetPasswordRequestRepository;
import PFE.Project.data.RoleRepository;
import PFE.Project.data.UserRepository;
import PFE.Project.dto.UserDto;
import PFE.Project.dto_convertor.UserConvertor;
import PFE.Project.enumerate.Results;
import PFE.Project.models.ResetPasswordRequest;
import PFE.Project.models.Role;
import PFE.Project.models.User;
import PFE.Project.requests.ChangePasswordRequest;
import PFE.Project.requests.LoginRequest;
import PFE.Project.requests.RegistrationRequest;
import PFE.Project.responses.AuthenticationResponse;
import org.apache.tomcat.util.buf.UEncoder;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServices implements UserDetailsService {

    private final static String USER_NOT_FOUND_MSG = "user with email %s not found";


    private final UserRepository userRepository;
    private final ResetPasswordRequestRepository resetPasswordRequestRepository;
    private JavaMailSender mailSender;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserServices(ResetPasswordRequestRepository resetPasswordRequestRepository, JavaMailSender mailSender, RoleRepository roleRepository, UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.mailSender = mailSender;
        this.resetPasswordRequestRepository = resetPasswordRequestRepository;
    }


    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, email)));
    }

    public ResponseEntity registerService(RegistrationRequest request) {
        boolean checkUser = this.userRepository.findByEmail(request.getEmail()).isPresent();

        if (checkUser) {

            Map<String, String> responseMap = new HashMap<>();
            responseMap.put("error", "L'e-mail est pris");
            return ResponseEntity.status(404).body(responseMap);
        }

        User user = new User();
        Role role = roleRepository.getById(request.getRole_id());
        String encodedPassword;
        if (request.getPassword() != null) {
            encodedPassword = bCryptPasswordEncoder.encode(request.getPassword());
        } else {
            encodedPassword = request.getPassword();
        }
        user.setPassword(encodedPassword);
        user.setFull_name(request.getFull_name());
        user.setPhone_number(request.getPhone_number());
        user.setCompany(request.getCompany());
        user.setEmail(request.getEmail());
        user.setRole(role);
        userRepository.save(user);

        return ResponseEntity.status(200).body(UserConvertor.userToDto(user));

    }

    public ResponseEntity resetPasswordService(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent()) {

            if (user.get().getPassword() != null) {
                ResetPasswordRequest resetPasswordRequest = new ResetPasswordRequest();
                resetPasswordRequest.setUser(user.get());
                Random rand = new Random();
                Long code = rand.nextLong(9000) + 1000;
                resetPasswordRequest.setCode(code);
                resetPasswordRequestRepository.save(resetPasswordRequest);
                Map<String, String> responseMap = new HashMap<>();
                responseMap.put("message", "done");

                sendEmail(email, "Réinitialiser le mot de passe", "Bonjour , voici le code pour réinitialiser votre mot de passe " + code);
                return ResponseEntity.status(200).body(responseMap);
            } else {
                Map<String, String> responseMap = new HashMap<>();
                responseMap.put("error", "facebook no");
                return ResponseEntity.status(404).body(responseMap);
            }
        }

        Map<String, String> responseMap = new HashMap<>();
        responseMap.put("error", "user not found");
        return ResponseEntity.status(404).body(responseMap);
    }


    public  ResponseEntity checkOtp(Long code){
        Optional<ResetPasswordRequest> resetPasswordRequest=resetPasswordRequestRepository.getResetPasswordRequestByCode(code);
     if(resetPasswordRequest.isPresent()&& Objects.equals(resetPasswordRequest.get().getCode(), code))   {
         Map<String, String> responseMap = new HashMap<>();
         responseMap.put("message", "done");
         return ResponseEntity.status(200).body(responseMap);
     }else{
         Map<String, String> responseMap = new HashMap<>();
         responseMap.put("message", "not found");
         return ResponseEntity.status(404).body(responseMap);
     }
    }

    public  ResponseEntity changePassword(ChangePasswordRequest changePasswordRequest){
        Optional<User> user=userRepository.findByEmail(changePasswordRequest.getEmail());
        String encodedPassword;
        encodedPassword = bCryptPasswordEncoder.encode(changePasswordRequest.getPassword());

        user.get().setPassword(encodedPassword);
        userRepository.save(user.get());
        resetPasswordRequestRepository.deleteByCode(changePasswordRequest.getCode());
        Map<String, String> responseMap = new HashMap<>();
        responseMap.put("message", "done");
        return ResponseEntity.status(200).body(responseMap);


    }

    public ResponseEntity loginService(LoginRequest loginRequest) {
        Optional<User> user = userRepository.findByEmail(loginRequest.getEmail());
        if (user.isPresent()) {

            if (user.get().getPassword() != null) {
                if (bCryptPasswordEncoder.matches(loginRequest.getPassword(), user.get().getPassword())) {
                    return ResponseEntity.status(200).body(UserConvertor.userToDto(user.get()));

                } else {
                    Map<String, String> responseMap = new HashMap<>();
                    responseMap.put("error", "Mot de passe incorrecte");
                    return ResponseEntity.status(404).body(responseMap);

                }

            }

            Map<String, String> responseMap = new HashMap<>();
            responseMap.put("error", "Aucun utilisateur avec cet email");
            return ResponseEntity.status(404).body(responseMap);
        }
        Map<String, String> responseMap = new HashMap<>();
        responseMap.put("error", "Aucun utilisateur avec cet email");
        return ResponseEntity.status(404).body(responseMap);
    }

    public void sendEmail(String toEmail,
                          String subject,
                          String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("walatlili@gmail.com");
        message.setTo(toEmail);
        message.setText(body);
        message.setSubject(subject);
        mailSender.send(message);
        System.out.println("Mail Sent successfully...");
    }
}
