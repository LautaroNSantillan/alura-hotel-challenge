package models;

import java.lang.reflect.Constructor;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class Reservation {

	private int id;
	private LocalDate fromDate;
	private LocalDate toDate;
	private String price;
	private String paymentMethod;
	
	public Reservation(LocalDate fromDate, LocalDate toDate, String price, String paymentMethod) {
		super();
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.price = price;
		this.paymentMethod = paymentMethod;
	}

	public Reservation(int id, LocalDate fromDate, LocalDate toDate, String price, String paymentMethod) {
		super();
		this.id = id;
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.price = price;
		this.paymentMethod = paymentMethod;
	}
	
	

}
