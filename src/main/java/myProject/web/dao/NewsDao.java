package myProject.web.dao;

import lombok.SneakyThrows;
import myProject.web.model.Gender;
import myProject.web.model.News;
import myProject.web.model.Role;
import myProject.web.model.User;
import myProject.web.util.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class NewsDao implements Dao<Integer, News>{
    private static final NewsDao INSTANCE = new NewsDao();
    private static final String FIND_ALL = "select * from news";
    private static final String FIND_BY_ID = "select * from news where id = ?";
    @Override
    public List<News> findAll() throws SQLException {
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            List<News> resultList = new ArrayList<>();
            while (resultSet.next()){
                resultList.add(buildEntity(resultSet));
            }
            return resultList;
        }
    }

    @Override
    public News create(News news) throws SQLException {
        return null;
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
    public Optional<News> findById(Integer id) {
        return Optional.empty();
    }

    @SneakyThrows
    public Optional<News> findById(String id){
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID)) {
            preparedStatement.setObject(1,Integer.parseInt(id));
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return Optional.ofNullable(buildEntity(resultSet));
            }
            return Optional.empty();
        }
    }
    @SneakyThrows
    private News buildEntity(ResultSet resultSet) {
        return News.builder()
                .id(resultSet.getObject(1,Integer.class))
                .name(resultSet.getObject(2,String.class))
                .createDate(resultSet.getObject(3, LocalDate.class))
                .text(resultSet.getObject(4,String.class))
                .authorId(resultSet.getObject(5,Integer.class))
                .build();
    }

    public static NewsDao getInstance(){
        return INSTANCE;
    }
}
