package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import factory.DBConnectionFactory;
import models.Patron;

public class PatronDAO implements DAO<Patron> {
	
	private Connection con;

	@Override
	public void save(Patron t, DBConnectionFactory conFactory) {
	
		String sql = "INSERT INTO patrons (first_name, last_name, birthdate, nationality, phone_number, reservation_id)"+
				"VALUES (?, ?, ?, ?, ?, ?)";
		try(Connection newCon=conFactory.connectToDb()){
			this.con=newCon;
			try {
				try(PreparedStatement pstm = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
					pstm.setString(1, t.getName());
					pstm.setString(2, t.getLastName());
					pstm.setObject(3, t.getBirthdate());
					pstm.setString(4, t.getNationality());
					pstm.setString(5, t.getPhoneNumber());
					pstm.setInt(6, t.getReservationId());
					
					pstm.execute();
					
					try (ResultSet result = pstm.getGeneratedKeys()) {
				        if (result.next()) { // Move the cursor to the first row
				            t.setId(result.getInt(1));
				            System.out.println("Patron : " + result.getInt(1));
				        } else {
				            throw new RuntimeException("No generated keys found.");
				        }
				    } 
				}
			}catch(SQLException e){
				throw new RuntimeException(e);
			}
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<Patron> list(DBConnectionFactory conFactory) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Patron> searchById(String id, DBConnectionFactory conFactory) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Integer id, DBConnectionFactory connectionFactory) {
		// TODO Auto-generated method stub
	}

}
