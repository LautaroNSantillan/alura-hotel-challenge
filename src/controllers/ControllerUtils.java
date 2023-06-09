package controllers;

import java.sql.Connection;
import java.sql.SQLException;

import daos.DAO;
import factory.DBConnectionFactory;

public class ControllerUtils {
	 public static <T> void saveEntity(T entity, DAO<T> dao, DBConnectionFactory con) {
	        try (Connection connection = con.connectToDb()) {
	            dao.save(entity, con);
	        } catch (SQLException e) {
	            throw new RuntimeException(e);
	        }
	    }
}
