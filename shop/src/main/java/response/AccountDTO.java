package response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountDTO {
    private Integer id;
    private String firstName;

    private String lastName;

    private String mobile;

    private String email;

    private String photo;

    private String userRole;
}
