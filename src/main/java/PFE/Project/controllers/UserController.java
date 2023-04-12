package PFE.Project.controllers;


import PFE.Project.models.User;
import PFE.Project.data.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController

public class UserController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    @PostMapping("/register")
    public User Register(@RequestBody User user) {

        return userRepository.save(user);
    }

    @PostMapping("/login")
    public User Login(@RequestBody User user) {
        User oldUSer = userRepository.findByEmailAndPassword(user.getEmail(), user.getPassword());
        return oldUSer;
    }
}