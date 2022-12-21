package rest.api;

import App.entity.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.List;
import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@RunWith(SpringRunner.class)
@WebMvcTest(value = PersonControllerTest.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("dev")
public class PersonControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void getAllPersons(){
        ResponseEntity<List> response = this.restTemplate.getForEntity("http://localhost:" + port + "/rest/api/investor", List.class);
        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
    }

    @Test public void findPersonById(){
        ResponseEntity<Person> response = this.restTemplate.getForEntity("http://localhost:" + port + "/rest/api/investor/1", Person.class);

        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
    }

    @Test public void personNotFounfById(){
        ResponseEntity<Person> response = this.restTemplate.getForEntity("http://localhost:" + port + "/rest/api/investor/999", Person.class);

        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
    }
}
