package factory;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.management.RuntimeErrorException;
import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class DBConnectionFactory {
	
	private DataSource dataSource;
	
	public DBConnectionFactory(){
		ComboPooledDataSource cpd = new ComboPooledDataSource();
		cpd.setJdbcUrl("jdbc:mysql://localhost:3306/alura_hotel_db");
		cpd.setUser("root");
		cpd.setPassword("");
		
		this.dataSource=cpd;
	}

	public Connection connectToDb() {
		try {
			return this.dataSource.getConnection();
			}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
