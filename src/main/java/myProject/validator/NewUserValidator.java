package myProject.validator;

import myProject.dto.NewUserDto;
import myProject.web.util.BirthdayFormatter;

import java.time.LocalDate;
import java.time.Period;

public class NewUserValidator implements ValidatorForUser<NewUserDto, NewUserValidationResult> {
    private static final NewUserValidator INSTANCE = new NewUserValidator();

    public static NewUserValidator getInstance(){
        return INSTANCE;
    }


    @Override
    public NewUserValidationResult isValidUser(NewUserDto newUserDto) {
        NewUserValidationResult newUserValidationResult = new NewUserValidationResult();
        String email = newUserDto.getEmail();
        if(!email.matches("^\\w+([-+.']\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$")){
            newUserValidationResult.addError(Error.of("Почта введена некорректно"));
        }
        if(!BirthdayFormatter.isValidDate(newUserDto.getBirthday())){
            newUserValidationResult.addError(Error.of("Дата введена некорректно"));
        }
        LocalDate birthday = BirthdayFormatter.parse(newUserDto.getBirthday());
        Period period = Period.between(birthday, LocalDate.now());
        if(period.getYears() < 18){
            newUserValidationResult.addError(Error.of("Вам меньше 18 лет"));
        }
        String regex = "^(?=.*[0-9])"
                + "(?=.*[a-z])(?=.*[A-Z])"
                + "(?=.*[@#$%^&+=])"
                + "(?=\\S+$).{6,20}$";
        if(!newUserDto.getPassword().matches(regex)){
            newUserValidationResult.addError(Error.of("Пароль должен содержать цифры, заглавные буквы, спец.символы и быть не короче 6 символов"));
        }
        return newUserValidationResult;
    }
}
