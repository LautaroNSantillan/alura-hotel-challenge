package controllers;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import daos.PatronDAO;
import daos.ReservationDAO;
import factory.DBConnectionFactory;
import models.Patron;
import models.Reservation;

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
	public List<Patron> listPatrons(){
		return this.patronDAO.list(con);
	}
	public List<Patron> searchById(String id){
		return this.patronDAO.searchById(id, con);
	}
	public void updatePatron(Integer id, String name, String lastName, Date birthdate, String nationality, String phoneNumber, int reservationId) {
		this.patronDAO.updatePatron(id, name, lastName, birthdate, nationality, phoneNumber, reservationId, con);
	}
	public void deletePatron(Integer id) {
		this.patronDAO.delete(id, con);
	}
}
