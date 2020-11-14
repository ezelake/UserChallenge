package ceq.challenge.UserCreator.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

public class JwtCreator {

    public String createJWT(String email) {
 		return Jwts.builder()
    		    .setSubject(email) 
    		    .signWith(Keys.secretKeyFor(SignatureAlgorithm.HS256))
    		    .compact();             
    }

}
