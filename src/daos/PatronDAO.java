package daos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.management.RuntimeErrorException;

import controllers.ControllerUtils;
import factory.DBConnectionFactory;
import models.Patron;
import models.Reservation;

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
		List<Patron> patrons = new ArrayList<Patron>();
		String sql = "SELECT * FROM patrons";

		try (Connection newCon = conFactory.connectToDb()) {
			this.con = newCon;
			try (PreparedStatement pstm = con.prepareStatement(sql)) {
				pstm.execute();
				ControllerUtils.toListPatrons(patrons, pstm);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			return patrons;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<Patron> searchById(String id, DBConnectionFactory conFactory) {
		List<Patron> patrons = new ArrayList<Patron>();
		String sql = "SELECT * FROM patrons WHERE id = ?";

		try (Connection newCon = conFactory.connectToDb()) {
			this.con = newCon;
			try (PreparedStatement pstm = con.prepareStatement(sql)) {
				pstm.setString(1, id);
				pstm.execute();
				ControllerUtils.toListPatrons(patrons, pstm);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			return patrons;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void delete(Integer id, DBConnectionFactory conFactory) {
		try(Connection newCon = conFactory.connectToDb()){
			this.con=newCon;
			Statement stm = con.createStatement();
			stm.execute("SET FOREIGN_KEY_CHECKS = 0");
			try(PreparedStatement pstm = con.prepareStatement("DELETE FROM patrons WHERE id = ?")){
				pstm.setInt(1, id);
				pstm.execute();
				stm.execute("SET FOREIGN_KEY_CHECKS = 1");
			}catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	
	public void updatePatron(Integer id, String name, String lastName, Date birthdate, String nationality, String phoneNumber, Integer reservationId, DBConnectionFactory conFactory) {
		String sql = "UPDATE patrons SET first_name= ?, "
				+ "last_name= ?, "
				+ "birthdate= ?, "
				+ "nationality= ?, "
				+ "phone_number= ?, "
				+ "reservation_id= ? WHERE id = ?";
		
			try (Connection newCon = conFactory.connectToDb()){	
				this.con=newCon;
				try(PreparedStatement pstm = con.prepareStatement(sql)){
					pstm.setString(1, name);
					pstm.setString(2, lastName);
					pstm.setObject(3, birthdate);
					pstm.setString(4, nationality);
					pstm.setString(5, phoneNumber);
					pstm.setInt(6, reservationId);
					pstm.setInt(7, id );
					
					pstm.executeUpdate();
				}catch (SQLException e) {
					throw new RuntimeException(e);
				}
			}catch (SQLException e) {
				throw new RuntimeException(e);
			}
	}

}
