package App;

import lombok.*;
import org.apache.commons.lang3.StringUtils;

@NoArgsConstructor
@Setter
@Getter
@ToString
public class Error {
    private String code;
    private String message;

    public boolean isEmpty() {
        return StringUtils.isEmpty(code) && StringUtils.isEmpty(message);
    }
}
