package controllers;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.management.RuntimeErrorException;

import daos.ReservationDAO;
import dtos.ReservationDTO;
import factory.DBConnectionFactory;
import models.Reservation;

public class ReservationsController {
	private DBConnectionFactory con;
	private ReservationDAO reservationDAO;

	public ReservationsController() {
		super();
		con = new DBConnectionFactory();
		reservationDAO = new ReservationDAO();
	}

	public void save(Reservation reservation) {
		ControllerUtils.saveEntity(reservation, reservationDAO, con);
	}
	
	public List<Reservation> list(){
		return this.reservationDAO.listReservations(con);
	}

}
