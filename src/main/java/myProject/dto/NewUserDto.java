package myProject.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class NewUserDto {
    String name;
    String birthday;
    String country;
    String email;
    String password;
    String role;
    String gender;
}
