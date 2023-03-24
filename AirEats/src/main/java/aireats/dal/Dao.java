package aireats.dal;

import java.sql.SQLException;

public interface Dao<T> {
    T create(T obj) throws SQLException;

    T delete(T obj) throws SQLException;
}
