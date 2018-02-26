package pl.dominisz;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class UserDetails {

    private String username;
    private LocalDateTime loginDateTime;

}
