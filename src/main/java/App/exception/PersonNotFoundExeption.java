package App.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@Setter
@ResponseStatus(HttpStatus.NOT_FOUND)
public class PersonNotFoundExeption extends RuntimeException{
    private Integer id;

    public PersonNotFoundExeption(Integer id) {
       this.id = id;
    }
}
