package myProject.web.dao;

import lombok.SneakyThrows;
import myProject.web.model.Comments;
import myProject.web.util.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class CommentsDao implements Dao<Integer, Comments> {
    private static final CommentsDao INSTANCE = new CommentsDao();
    private static final String CREATE_COMMENT = "insert into comments (text,author_id,news_id) values (?,?,?)";


    public static CommentsDao getInstance(){
        return INSTANCE;
    }
    @Override
    public List<Comments> findAll() throws SQLException {
        return null;
    }

    @Override
    public Comments create(Comments comments) throws SQLException {
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CREATE_COMMENT)) {
            preparedStatement.setObject(1,comments.getText());
            preparedStatement.setObject(2,comments.getAuthorId());
            preparedStatement.setObject(3,comments.getNewsId());
            preparedStatement.executeUpdate();
            return comments;
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
    public Optional<Comments> findById(Integer id) {
        return Optional.empty();
    }

    // метод comments приходит сущность сохраняем тескт id автора
}
