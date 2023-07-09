package myProject.web.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Builder
public class User {
    private Long id;
    private String name;
    private LocalDate birthday;
    private String country;
    private String email;
    private String password;
    private Boolean isBanned;
    private LocalDateTime banDate;
    private LocalDateTime turnOffBanDate;
    private Boolean notice;
    private Role role;
    private Gender gender;


}
