package myProject.web.service;

import myProject.dto.NewUserDto;
import myProject.exeception.ValidationException;
import myProject.validator.NewUserValidationResult;
import myProject.validator.NewUserValidator;
import myProject.web.dao.UserDao;
import myProject.web.model.User;

import java.sql.SQLException;
import java.util.Optional;

public class UserService {
    private static final UserService INSTANCE = new UserService();
    private final NewUserValidator newUserValidator = NewUserValidator.getInstance();
    private final UserDao userDao = UserDao.getInstance();
    public static UserService getInstance() {
        return INSTANCE;
    }

    public Optional<User> login(String email, String password) throws SQLException {
        return userDao.findByEmailAndPassword(email,password);
    }
    public User mapToEntity(NewUserDto newUserDto){
        return User.builder().name(newUserDto.getName()).country(new)
    }
    public void save(NewUserDto newUserDto) {
        NewUserValidationResult newUserValidationResult = newUserValidator.isValidUser(newUserDto);
        if(!newUserValidationResult.isValid()){
            throw new ValidationException(newUserValidationResult.getErrorList());
        }
        // валидация
        User user = newUserDtoMapper.mapToEntity(newUserDto);

        //маппинг
        //проверить что такого email и пароля нет в базе
        userDao.findByEmailAndPassword()// если есть то пробрасываем exception
        // сохраняем в базу
        userDao.create(user);
    }
}
