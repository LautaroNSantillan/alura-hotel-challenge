package daos;

import java.sql.SQLException;

import factory.DBConnectionFactory;
import models.Reservation;

public interface DAO<T> {
	void save(T t, DBConnectionFactory con);
}
