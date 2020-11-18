package ceq.challenge.UserCreator.util;

import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import ceq.challenge.UserCreator.model.User;
import ceq.challenge.UserCreator.repository.UserRepository;

@Service
public class UserValidator {

    @Autowired
    private UserRepository userRepository;

    private Pattern emailPattern = Pattern.compile("^(.+)@(.+)\\.(.+)$");
    private Pattern passwordUpperPattern = Pattern.compile("^(.*)([A-Z]+)(.*)$");
    private Pattern passwordLowerPattern = Pattern.compile("^(.*)([a-z]+)(.*)$");
    private Pattern passwordNumberPattern = Pattern.compile("^(.*)([0-9]+)(.*)([0-9]+)(.*)$");

	public void validateUser(User user) {
    	// Control de existencia del usuario
    	if(userRepository.findByEmail(user.getEmail()).size() > 0) {
    		throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El correo ya registrado");
    	}
    	
    	// Validacion del usuario a generar
    	if (!emailPattern.matcher(user.getEmail()).matches()) {
    		throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Formato de correo incorrecto");
    	}
    	
    	if (!passwordUpperPattern.matcher(user.getPassword()).matches()
    			|| !passwordLowerPattern.matcher(user.getPassword()).matches()
    			|| !passwordNumberPattern.matcher(user.getPassword()).matches()) {
    		throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Formato de password incorrecto");
    	}
	}

}
