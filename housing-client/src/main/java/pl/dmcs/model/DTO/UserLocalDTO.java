package pl.dmcs.model.DTO;

import lombok.Data;
import pl.dmcs.model.Local;
import pl.dmcs.model.User;

@Data
public class UserLocalDTO {

    private User user;
    private Local local;
}
