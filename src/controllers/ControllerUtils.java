package controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import daos.DAO;
import dtos.ReservationDTO;
import factory.DBConnectionFactory;
import models.Reservation;

public class ControllerUtils {
	 public static <T> void saveEntity(T entity, DAO<T> dao, DBConnectionFactory con) {
	        try (Connection connection = con.connectToDb()) {
	            dao.save(entity, con);
	        } catch (SQLException e) {
	            throw new RuntimeException(e);
	        }
	    }
	 
	 public static  void toListReservationDTO (List<ReservationDTO> list, PreparedStatement pstm) throws SQLException{
		 try(ResultSet result = pstm.getResultSet()){
			 while(result.next()) {
				 int id = result.getInt("id");
				 LocalDate fromDate = result.getDate("from_date").toLocalDate().plusDays(1);
				 LocalDate toDate = result.getDate("to_date").toLocalDate().plusDays(1);
				 String price = result.getString("price");
				 String paymentMethod = result.getString("payment_method");
				 
				 ReservationDTO reservationDTO = new ReservationDTO(fromDate, toDate, price, paymentMethod);
				 list.add(reservationDTO);
			 }
		 }
	 }
	 public static  void toListReservation (List<Reservation> list, PreparedStatement pstm) throws SQLException{
		 try(ResultSet result = pstm.getResultSet()){
			 while(result.next()) {
				 int id = result.getInt("id");
				 LocalDate fromDate = result.getDate("from_date").toLocalDate().plusDays(1);
				 LocalDate toDate = result.getDate("to_date").toLocalDate().plusDays(1);
				 String price = result.getString("price");
				 String paymentMethod = result.getString("payment_method");
				 
				 Reservation reservation = new Reservation(id, fromDate, toDate, price, paymentMethod);
				 list.add(reservation);
			 }
		 }
	 }
}
