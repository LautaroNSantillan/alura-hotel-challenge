package services.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.mysql.cj.protocol.Resultset;

import factory.DBConnectionFactory;
import models.User;
import services.UserService;

public class UserServiceImpl implements UserService{
	
	private DBConnectionFactory conFactory=new DBConnectionFactory();
	
	@Override
	public boolean credentialsAreValid(String username, String password) {
		final Connection con = conFactory.connectToDb();
		
		try(con){
			final PreparedStatement stm = con.prepareStatement("SELECT * FROM users WHERE username = ? AND pwd = ?");
			stm.setString(1, username);
			stm.setString(2, password);
			try(stm){
				final ResultSet resultSet = stm.executeQuery(); 
				try(resultSet){
					return resultSet.next();
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();		}
			return false;
	}
	
}
