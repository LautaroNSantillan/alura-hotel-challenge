package dtos;

import java.time.LocalDate;

import models.Reservation;

public class ReservationDTO {
	private LocalDate fromDate;
	private LocalDate toDate;
	private String price;
	private String paymentMethod;
	
	public ReservationDTO(Reservation reservation) {
		this.fromDate=reservation.getFromDate();
		this.toDate=reservation.getToDate();
		this.price=reservation.getPrice();
		this.paymentMethod=reservation.getPaymentMethod();		
	}

	public ReservationDTO(LocalDate fromDate, LocalDate toDate, String price, String paymentMethod) {
		super();
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.price = price;
		this.paymentMethod = paymentMethod;
	}
	
	
}
