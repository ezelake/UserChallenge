package ceq.challenge.UserCreator.util;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ceq.challenge.UserCreator.model.User;

@Service
public class UserGenerator {

    private JwtCreator jwtCreator = new JwtCreator();
    
    @Autowired
    private UserValidator userValidator;

	public User generateUser(User user) {
    	
		userValidator.validateUser(user);

    	Date date = new Date();
    	user.setCreated(date);
    	user.setModified(date);
    	user.setLast_login(date);
    	user.setIsactive(true);
    	user.setToken(jwtCreator.createJWT(user.getEmail()));
    	
		return user;
	}

}
