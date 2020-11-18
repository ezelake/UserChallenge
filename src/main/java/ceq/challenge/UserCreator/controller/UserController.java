package ceq.challenge.UserCreator.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import ceq.challenge.UserCreator.model.User;
import ceq.challenge.UserCreator.repository.UserRepository;
import ceq.challenge.UserCreator.util.UserGenerator;

@RestController
public class UserController {
	
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private UserGenerator userGenerator;
    
    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
	public User createUser(@RequestBody User user) {
    	User newUser = userGenerator.generateUser(user);
		return userRepository.save(newUser);
	}

    @GetMapping("/")
	public List<User> listUsers() {
		return userRepository.findAll();
	}

}
