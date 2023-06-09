package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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

	public List<Reservation> listReservations(DBConnectionFactory conFactory) {
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

}
