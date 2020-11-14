package ceq.challenge.UserCreator;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class UserCreatorApplicationTests {

    @Autowired    
    private MockMvc mockMvc;

	@Test
	void contextLoads() throws Exception {
        String json = "{\"name\": \"usuario\", \"email\": \"user@gmail.com\", \"password\": \"A1a2\"}";
        mockMvc.perform(post("/").contentType(MediaType.APPLICATION_JSON).content(json))
        	.andExpect(status().isCreated());
	}

	@Test
	void emailFails() throws Exception {
        String json = "{\"name\": \"usuario\", \"email\": \"badgmail.com\", \"password\": \"A1a2\"}";
        mockMvc.perform(post("/").contentType(MediaType.APPLICATION_JSON).content(json))
    		.andExpect(status().isBadRequest());
	}

}
