package myProject.web.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface Dao<I,E>{
    List<E> findAll() throws SQLException;

    E create(E e) throws SQLException;

    boolean update(String s);

    boolean delete(I i);

    Optional<E> findById(Integer id);
}
