package daos;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import factory.DBConnectionFactory;
import models.Reservation;

public interface DAO<T> {
	void save(T t, DBConnectionFactory con);
	List<T> list(DBConnectionFactory conFactory);
	List<T> searchById(String id, DBConnectionFactory conFactory);
	void delete(Integer id, DBConnectionFactory conFactory);
}
