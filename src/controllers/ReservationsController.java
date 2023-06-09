package controllers;

import java.sql.Connection;
import java.sql.SQLException;

import javax.management.RuntimeErrorException;

import daos.ReservationDAO;
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

}
