package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import controllers.ControllerUtils;
import dtos.ReservationDTO;
import factory.DBConnectionFactory;
import models.Reservation;

public class ReservationDAO implements DAO<Reservation> {

	private Connection con;

	@Override
	public void save(Reservation reservation, DBConnectionFactory conFactory) {

		String sql = "INSERT INTO reservations (from_date, to_date, price, payment_method)" + "VALUES (?, ?, ?, ?)";

		try (Connection newCon = conFactory.connectToDb();) {
			this.con = newCon;
			try (PreparedStatement stm = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
				stm.setObject(1, reservation.getFromDate());
				stm.setObject(2, reservation.getToDate());
				stm.setString(3, reservation.getPrice());
				stm.setString(4, reservation.getPaymentMethod());

				stm.executeUpdate();

				try (ResultSet rst = stm.getGeneratedKeys()) {
					while (rst.next()) {
						reservation.setId(rst.getInt(1));
						System.out.println("Reservation " + rst.getInt(1));
					}
				}
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<Reservation> list(DBConnectionFactory conFactory) {
		List<Reservation> reservations = new ArrayList<Reservation>();
		String sql = "SELECT * FROM reservations";

		try (Connection newCon = conFactory.connectToDb()) {
			this.con = newCon;
			try (PreparedStatement pstm = con.prepareStatement(sql)) {
				pstm.execute();
				ControllerUtils.toListReservation(reservations, pstm);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			return reservations;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<Reservation> searchById(String id, DBConnectionFactory conFactory) {
		List<Reservation> reservations = new ArrayList<Reservation>();
		String sql = "SELECT * FROM reservations WHERE id = ?";

		try (Connection newCon = conFactory.connectToDb()) {
			this.con = newCon;
			try (PreparedStatement pstm = con.prepareStatement(sql)) {
				pstm.setString(1, id);
				pstm.execute();
				ControllerUtils.toListReservation(reservations, pstm);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			return reservations;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}


	public void update(Integer id, LocalDate fromDate, LocalDate toDate, String price, String paymentMethod,
			DBConnectionFactory conFactory) {

		String sql = "UPDATE reservations SET from_date = ? , " + "to_date = ?, " + "price = ?, "
				+ "payment_method = ?  WHERE id = ?";
		try (Connection newCon = conFactory.connectToDb()) {
			this.con = conFactory.connectToDb();
			System.out.println("Connect" + con);
			try (PreparedStatement pstm = con.prepareStatement(sql)) {
				pstm.setObject(1, java.sql.Date.valueOf(fromDate));
				pstm.setObject(2, java.sql.Date.valueOf(toDate));
				pstm.setString(3, price);
				pstm.setString(4, paymentMethod);
				pstm.setInt(5, id);
				
				pstm.executeUpdate();
				System.out.println(pstm.toString());
			} catch (SQLException e) {
				System.out.println("Error executing update statement: " + e.getMessage());
			    e.printStackTrace();
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	//too lazy to make a logic delete
	@Override
	public void delete(Integer id, DBConnectionFactory conFactory) {
		try(Connection newCon = conFactory.connectToDb()){
			this.con=newCon;
			Statement stm = con.createStatement();
			stm.execute("SET FOREIGN_KEY_CHECKS = 0");
			try(PreparedStatement pstm = con.prepareStatement("DELETE FROM reservations WHERE id = ?")){
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

}
