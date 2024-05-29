//Christos Kostadimas

package tuc.ece.cs102.vac;

import java.time.LocalDate;
import java.time.LocalDateTime;



public class VaccineCenter {
	
	final int N = 5, M=5; 
	final int Dim1 = 7, Dim2 = 4, Dim3 = 5; //first , second , and third dimension of the 3-d array
	final int MAX_APPOINTMENTS = 28;
	
	private String centerCity;
	private String code;
	private String title;
	private Doctor[] arrayOfDoc;
	private int numOfDoctors;
	private Appointment[][][] appointmentTimeTable; 
	private Appointment[] appointmentsOfCenter;
	private int numOfappointments;
	
	private LocalDate firstDay = LocalDate.now().plusDays(1);
	private LocalDateTime firstSlot = LocalDateTime.parse(LocalDate.now().plusDays(1).toString()+"T09:00"); 

	
	/**constructors**/
	public VaccineCenter(String code, String title, String centerCity) {
		this.code = code;
		this.title = title; 
		this.centerCity = centerCity;
		this.arrayOfDoc = new Doctor[N];
		this.numOfDoctors = 0;
		this.appointmentTimeTable = new Appointment[Dim1][Dim2][Dim3];   
		this.appointmentsOfCenter = new Appointment[MAX_APPOINTMENTS];
	}
	
	public VaccineCenter() {
		
	}
	
	
	//setters and getters
	public String getCode() {
		return code;
	}
	
	public int getNumOfappointments() {
		return numOfappointments;
	}
	
	public void setNumOfappointments(int numOfappointments) {
		this.numOfappointments = numOfappointments;
	}
	
	public Appointment[] getAppointmentsOfCenter() {
		return appointmentsOfCenter;
	}
	
	public void setAppointmentsOfCenter(Appointment[] appointmentsOfCenter) {
		this.appointmentsOfCenter = appointmentsOfCenter;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getCenterCity() {
		return centerCity;
	}
	
	public void setCenterCity(String centerCity) {
		this.centerCity = centerCity;
	}
	
	public Doctor[] getArrayOfDoc() {
		return arrayOfDoc;
	}
	
	public void setArrayOfDoc(Doctor[] arrayOfDoc) {
		this.arrayOfDoc = arrayOfDoc;
	}

	public int getNumOfDoctors() {
		return numOfDoctors;
	}

	public void setNumOfDoctors(int numOfDoctors) {
		this.numOfDoctors = numOfDoctors;
	}

	public Appointment[][][] getAppointmentTimeTable() {
		return appointmentTimeTable;
	}

	public void setAppointmentTimeTable(Appointment[][][] appointmentTimeTable) {
		this.appointmentTimeTable = appointmentTimeTable;
	}
	
	public LocalDate getFirstDay() {
		return firstDay;
	}
	
	public void setFirstDay(LocalDate firstDay) {
		this.firstDay = firstDay;
	}
	
	public LocalDateTime getFirstSlot() {
		return firstSlot;
	}
	
	public void setFirstSlot(LocalDateTime firstSlot) {
		this.firstSlot = firstSlot;
	}
	
	
	/*Method that adds doctor to the vaccination center*/
	public void addDoctor(Doctor doc) {
		if(doc!=null) {
		this.arrayOfDoc[numOfDoctors] = doc;
		numOfDoctors++;
		}
		if(numOfDoctors >= 5) {
			System.out.println("Maximum number of centers is 5!");
		}
	}

	
	/*Method that finds the available doctor with the minimum num of appointments that day*/
	public Doctor findDoctor(int dayChoice, int timeChoice) {
		Doctor[] available = new Doctor[N];
		Doctor temp = new Doctor();
		int numOfD=0;
		int flag;
		flag=0;
		for(Doctor doc: this.arrayOfDoc) {
			if(doc!=null) {
				for(Appointment ap: doc.getListOfAppointments()) {	
					if(ap!=null) {
						if(ap.getDate() == firstDay.plusDays(dayChoice)) {
							if(ap.getStartingTime() == firstSlot.plusMinutes(timeChoice*30)) {
								flag=1;
							}
						}
					}
				}			
			}
			if(flag==1) {
				available[numOfD] = doc;
				numOfD++;
			}
		}
		/*finding the one with minimum*/
		int min=140;
		for(Doctor doct: available) {
			if(doct!=null) {
			if(doct.getNumOfAppointments() <= min) {
				min = doct.getNumOfAppointments();
				temp = doct; 
				}
			}
		}
		return temp;
	}
	
	
	/*Method that adds a new appointment to the list with a specific KAP*/
	/*used by the programmer*/
	public void addNewAppointment2(String kAP, int dayChoice, int timeChoice, Insured ins, Doctor doct1, VaccineCenter vac) {
		int docIndex=0;
		Appointment appointment;
		//filling the array with appointments!
		for(int a =0; a<numOfDoctors; a++) {
			if(this.arrayOfDoc[a] != null) {
				if(this.arrayOfDoc[a].getAm().equals(doct1.getAm())) {
					 docIndex = a;
				}
			}
		}
		appointmentTimeTable[dayChoice][timeChoice][docIndex] = new Appointment(kAP, firstDay.plusDays(dayChoice), firstSlot.plusDays(dayChoice).plusMinutes(timeChoice*30) , ins, vac, doct1);
		
		appointment = appointmentTimeTable[dayChoice][timeChoice][docIndex];
		
		this.arrayOfDoc[docIndex].addDoctorsAppointment(appointmentTimeTable[dayChoice][timeChoice][docIndex]);  //Adding the appointment to the doctor that has the minimum rantevouz
		
		if(appointment!=null)
			this.appointmentsOfCenter[numOfappointments] = appointment;
		if(numOfappointments>=MAX_APPOINTMENTS) {
			System.out.println("Number of appointments surpassed!");
		}
		numOfappointments++;
	}
	
	
	/*method to add new appointment with RANDOM(!) KAP */
	public void addNewAppointment(int dayChoice, int timeChoice, Insured ins, VaccineCenter vac) {	
		/*filling the array with appointments!*/
		int docIndex=0;
		Appointment appointment;
		Doctor d = findDoctor(dayChoice,timeChoice);
		if( ins != null) {
			for(int j =0; j<numOfDoctors; j++) {
				if(this.arrayOfDoc[j] != null) {
					if(this.arrayOfDoc[j].getAm().equals(d.getAm())) {
						 docIndex = j;
					}
				}
			}
			appointmentTimeTable[dayChoice][timeChoice][docIndex] = new Appointment(firstDay.plusDays(dayChoice), firstSlot.plusDays(dayChoice).plusMinutes(timeChoice*30) , ins, vac, d);//Adding the appoinmtent to the list of appoinments of the CENTER
			
			appointment = appointmentTimeTable[dayChoice][timeChoice][docIndex];
			
			this.arrayOfDoc[docIndex].addDoctorsAppointment(appointmentTimeTable[dayChoice][timeChoice][docIndex]);  //Adding the appointment to the doctor that has the minimum rantevouz
			System.out.println("The new appointment that has just been aranged is at "+appointmentTimeTable[dayChoice][timeChoice][docIndex].getStartingTime()+ "!");

			//ALSO adding the appointment to the 1-d list of the center
			if(appointment!=null)
				this.appointmentsOfCenter[numOfappointments] = appointment;
			if(numOfappointments>=28) {
				System.out.println("Number of appointments surpassed!");
			}
			numOfappointments++;
		}
	}

	
	/*Method that finds + prints appointments by insured's amka*/
	public void findAppointmentByAmka(String amka) {
		int flag = 0;
		for(int a=0 ; a<Dim1; a++) {
 			for(int b=0; b<Dim2; b++) {
				for(int c=0; c<Dim3; c++) {
					if(appointmentTimeTable[a][b][c] !=null){
						if(amka.equals(appointmentTimeTable[a][b][c].getInsured().getAmka())) {
							flag=1;
							System.out.println(appointmentTimeTable[a][b][c].getStartingTime());
						}
					}
				}
			}
		}
		if(flag==0) {
			System.out.println("The insured with this amka has no appointment");
		}
	}
	
	
	/*Method that finds + prints appointment by centers title*/
	public void findAppointmentByCentersTitle(VaccineCenter vac) {
		
		for(int i=0; i< vac.getNumOfappointments();i++) {
			if(vac.getAppointmentsOfCenter()[i] != null) {
			System.out.println("Appointment time:" +vac.getAppointmentsOfCenter()[i].getStartingTime());
			}
			else
				System.out.println("No appointment...!");
		}
	}

}
