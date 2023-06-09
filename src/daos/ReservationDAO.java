package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import factory.DBConnectionFactory;
import models.Reservation;

public class ReservationDAO implements DAO<Reservation>{
	
	private Connection con;

	@Override
	public void save(Reservation reservation, DBConnectionFactory conFactory) {
		
		String sql = "INSERT INTO reservations (from_date, to_date, price, payment_method)"
							+ "VALUES (?, ?, ?, ?)";
		
		try(Connection newCon = conFactory.connectToDb();){
			this.con=newCon;
			try (PreparedStatement stm= con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS) ){
				stm.setObject(1, reservation.getFromDate());
				stm.setObject(2, reservation.getToDate());
				stm.setString(3, reservation.getPrice());
				stm.setString(4, reservation.getPaymentMethod());
				
				stm.executeUpdate();
				
				try(ResultSet rst = stm.getGeneratedKeys()){
					while(rst.next()) {
						System.out.println(rst.getInt(1));
					}
				}
			}catch(SQLException e){
				throw new RuntimeException(e);
			}
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}
	
	
}
