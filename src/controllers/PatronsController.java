package controllers;

import java.sql.Connection;
import java.sql.SQLException;

import daos.PatronDAO;
import daos.ReservationDAO;
import factory.DBConnectionFactory;
import models.Patron;

public class PatronsController {
	
	private DBConnectionFactory con;
	private PatronDAO patronDAO;

	
	public PatronsController() {
		super();
		this.con = new DBConnectionFactory();
		this.patronDAO = new PatronDAO();
	}

	public void save(Patron patron) {
		ControllerUtils.saveEntity(patron, patronDAO, con);
	}
}
