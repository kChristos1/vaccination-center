//Christos Kostadimas
package tuc.ece.cs102.vac;

import java.time.LocalDate;
import java.time.LocalDateTime; 
import java.util.Random; //to use the Random() method in order to generate a random appointmentCode

public class Appointment {
	
	
	
	private Random appointmentCode;      /*not given by user! it is given by the system, using Random() method*/
	private String appointmentCode2;     /*the one given by the programmer!, used for the initializations*/
	private LocalDateTime startingTime;  /*the time the appointment starts*/
	private LocalDate date;
	private Insured insured;
	private VaccineCenter vacCenter;
	private Doctor doctor;
	
	/**constructors**/
	public Appointment(LocalDate date, LocalDateTime startingTime ,Insured insured, VaccineCenter vacCenter, Doctor doctor) {
		this.appointmentCode = new Random();	//random KAP
		this.startingTime = startingTime;
		this.insured = insured;
		this.vacCenter = vacCenter; 
		this.doctor = doctor;
	}
	
	public Appointment(LocalDate date, LocalDateTime startingTime ,Insured insured, VaccineCenter vacCenter) {
		this.appointmentCode = new Random();
		this.startingTime = startingTime;
		this.insured = insured;
		this.vacCenter = vacCenter; 
	}

	public Appointment(String code,LocalDate date, LocalDateTime startingTime, Insured insured, VaccineCenter vacCenter, Doctor doctor) {
		this.appointmentCode2 = code;	//KAP chosen by the programmer for initializing appointments
		this.startingTime = startingTime;
		this.insured = insured;
		this.vacCenter = vacCenter; 
		this.doctor = doctor;
	}
	
	public Appointment(String code,LocalDate date, LocalDateTime startingTime ,Insured insured, Doctor doctor) {
		this.appointmentCode2 = code;
		this.startingTime = startingTime;
		this.insured = insured;
		this.doctor = doctor;
	}
	
	public Appointment(LocalDate date, LocalDateTime startingTime ,Insured insured, Doctor doctor) {
		this.appointmentCode = new Random();
		this.startingTime = startingTime;
		this.date = date;
		this.insured = insured;
		this.doctor = doctor;
	}


	//setters and getters
	public Random getAppointmentCode() {
		return appointmentCode;
	}

	public void setAppointmentCode(Random appointmentCode) {
		this.appointmentCode = appointmentCode;
	}

	public Insured getInsured() {
		return insured;
	}

	public void setInsured(Insured insured) {
		this.insured = insured;
	}

	public VaccineCenter getVacCenter() {
		return vacCenter;
	}

	public void setVacCenter(VaccineCenter vacCenter) {
		this.vacCenter = vacCenter;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}
	
	public LocalDateTime getStartingTime() {
		return startingTime;
	}

	public void setStartingTime(LocalDateTime startingTime) {
		this.startingTime = startingTime;
	}
	
	public String getAppointmentCode2() {
		return appointmentCode2;
	}
	
	public void setAppointmentCode2(String appointmentCode2) {
		this.appointmentCode2 = appointmentCode2;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}
}
