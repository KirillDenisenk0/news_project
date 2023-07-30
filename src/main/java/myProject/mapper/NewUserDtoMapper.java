package myProject.mapper;

import myProject.dto.NewUserDto;
import myProject.validator.NewUserValidator;
import myProject.web.model.Gender;
import myProject.web.model.Role;
import myProject.web.model.User;
import myProject.web.util.BirthdayFormatter;

public class NewUserDtoMapper implements Mapper<NewUserDto, User>{
    private static final NewUserDtoMapper INSTANCE = new NewUserDtoMapper();
    @Override
    public User mapToEntity(NewUserDto userDto) {
        return User.builder()
                .name(userDto.getName())
                .birthday(BirthdayFormatter.parse(userDto.getBirthday()))
                .country(userDto.getCountry())
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .role(Role.valueOf(userDto.getRole()))
                .gender(Gender.valueOf(userDto.getGender()))
                .isBanned(false)
                .notice(false)
                .build();
    }

    public static NewUserDtoMapper getInstance(){
        return INSTANCE;
    }
}
