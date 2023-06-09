package models;

import java.time.LocalDate;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class Patron {
	private int id;
	private String name;
	private String lastName;
	private LocalDate birthdate;
	private String nationality;
	private String phoneNumber;
	private Integer reservationId;
	
	public Patron(String name, String lastName, LocalDate birthdate, String nationality, String phoneNumber, int reservationId) {
		super();
		this.name = name;
		this.lastName = lastName;
		this.birthdate = birthdate;
		this.nationality = nationality;
		this.phoneNumber=phoneNumber;
		this.reservationId = reservationId;
	}
}
