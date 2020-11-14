package ceq.challenge.UserCreator.controller;

import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import ceq.challenge.UserCreator.model.User;
import ceq.challenge.UserCreator.repository.UserRepository;
import ceq.challenge.UserCreator.util.JwtCreator;

@RestController
public class UserController {
	
    @Autowired
    private UserRepository userRepository;
    
    private JwtCreator jwtCreator = new JwtCreator();
    
    private Pattern emailPattern = Pattern.compile("^(.+)@(.+)\\.(.+)$");
    private Pattern passwordUpperPattern = Pattern.compile("^(.*)([A-Z]+)(.*)$");
    private Pattern passwordLowerPattern = Pattern.compile("^(.*)([a-z]+)(.*)$");
    private Pattern passwordNumberPattern = Pattern.compile("^(.*)([0-9]+)(.*)([0-9]+)(.*)$");

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
	public User createUser(@RequestBody User user) {
    	if(userRepository.findByEmail(user.getEmail()).size() > 0) {
    		throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El correo ya registrado");
    	}
    	
    	if (!emailPattern.matcher(user.getEmail()).matches()) {
    		throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Formato de correo incorrecto");
    	}
    	
    	if (!passwordUpperPattern.matcher(user.getPassword()).matches()
    			|| !passwordLowerPattern.matcher(user.getPassword()).matches()
    			|| !passwordNumberPattern.matcher(user.getPassword()).matches()) {
    		throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Formato de password incorrecto");
    	}

    	Date date = new Date();
    	user.setCreated(date);
    	user.setModified(date);
    	user.setLast_login(date);
    	user.setIsactive(true);
    	user.setToken(jwtCreator.createJWT(user.getEmail()));
    	
		return userRepository.save(user);
	}

    @GetMapping("/")
	public List<User> listUsers() {
		return userRepository.findAll();
	}

}
