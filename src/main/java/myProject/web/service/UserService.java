package myProject.web.service;

import myProject.dto.NewUserDto;
import myProject.exeception.DuplicateException;
import myProject.exeception.ValidationException;
import myProject.mapper.NewUserDtoMapper;
import myProject.validator.NewUserValidationResult;
import myProject.validator.NewUserValidator;
import myProject.web.dao.UserDao;
import myProject.web.model.User;

import java.sql.SQLException;
import java.util.Optional;

public class UserService {
    private static final UserService INSTANCE = new UserService();
    private final NewUserValidator newUserValidator = NewUserValidator.getInstance();
    private final NewUserDtoMapper newUserDtoMapper = NewUserDtoMapper.getInstance();
    private final UserDao userDao = UserDao.getInstance();
    public static UserService getInstance() {
        return INSTANCE;
    }

    public Optional<User> login(String email, String password) throws SQLException {
        return userDao.findByEmailAndPassword(email,password);
    }

    public void save(NewUserDto newUserDto) throws SQLException {
        NewUserValidationResult newUserValidationResult = newUserValidator.isValidUser(newUserDto);
        if(!newUserValidationResult.isValid()){
            throw new ValidationException(newUserValidationResult.getErrorList());
        }
        // маппинг в сущность
        User user = newUserDtoMapper.mapToEntity(newUserDto);

        //проверить что такого email и пароля нет в базе
        if(userDao.findByEmailAndPassword(user.getEmail(), user.getPassword()).isPresent()){ // если есть то пробрасываем exception
           throw new DuplicateException();
        }
        // сохраняем в базу
        userDao.create(user);
    }
}
