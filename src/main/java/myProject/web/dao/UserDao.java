package myProject.web.dao;

import myProject.web.model.Gender;
import myProject.web.model.Role;
import myProject.web.model.User;
import myProject.web.util.ConnectionManager;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class UserDao implements Dao<Integer,User> {
    private static final String FIND_BY_EMAIL_AND_PASSWORD = "select * from users where email = ? and password = ?";
    private static final String CREATE_USER = "insert into users (name, birthday, country, email, password, role, gender,isbanned,notice) " +
            " values(?,?,?,?,?,?,?,?,?)";
    private static final UserDao INSTANCE = new UserDao();
    public static UserDao getInstance() {
        return INSTANCE;
    }

    public Optional<User> findByEmailAndPassword(String email, String password) throws SQLException {
        try(Connection connection = ConnectionManager.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_EMAIL_AND_PASSWORD);
            preparedStatement.setObject(1,email);
            preparedStatement.setObject(2,password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return Optional.of(buildEntity(resultSet));
            }
            return Optional.empty();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    private User buildEntity(ResultSet resultSet) throws SQLException {
        return User.builder()
                .id(resultSet.getObject("id",Integer.class))
                .name(resultSet.getObject("name",String.class))
                .birthday(resultSet.getObject("birthday", LocalDate.class))
                .isBanned(resultSet.getObject("isbanned",Boolean.class))
                .banDate(resultSet.getObject("ban_date", LocalDateTime.class))
                .notice(resultSet.getObject("notice",Boolean.class))
                .country(resultSet.getObject("country",String.class))
                .email(resultSet.getObject("email",String.class))
                .password(resultSet.getObject("password",String.class))
                .role(Role.valueOf(resultSet.getObject("role", String.class)))
                .gender(Gender.valueOf(resultSet.getObject("gender",String.class)))
                .build();
    }

    @Override
    public List<User> findAll() {
        return null;
    }
    @Override
    public User create(User entity) throws SQLException {
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CREATE_USER)) {
            preparedStatement.setObject(1,entity.getName());
            preparedStatement.setObject(2,entity.getBirthday());
            preparedStatement.setObject(3,entity.getCountry());
            preparedStatement.setObject(4,entity.getEmail());
            preparedStatement.setObject(5,entity.getPassword());
            preparedStatement.setObject(6,entity.getRole().name());
            preparedStatement.setObject(7,entity.getGender().name());
            preparedStatement.setObject(8,entity.getIsBanned());
            preparedStatement.setObject(9,entity.getNotice());
            preparedStatement.executeUpdate();
            return entity;
        }
    }

    @Override
    public boolean update(String s) {
        return false;
    }

    @Override
    public boolean delete(Integer integer) {
        return false;
    }

    @Override
    public Optional<User> findById(Integer id) {
        return Optional.empty();
    }
}
