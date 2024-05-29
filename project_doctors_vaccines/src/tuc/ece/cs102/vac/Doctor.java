//Christos Kostadimas

package tuc.ece.cs102.vac;

public class Doctor {
	
	final int N = 5;
	
	private String am;
	private String doctorFullName;
	private Appointment[] listOfAppointments; /*the doctor's appointments*/
	private int numOfAppointments;
	
	/**constructors**/
	public Doctor(String am, String fullName) {
		this.am = am;
		this.doctorFullName = fullName;
		this.listOfAppointments = new Appointment[N];
		this.numOfAppointments = 0;
	}   
	
	public Doctor() {	
	
	}

	
	//setters and getters
	public String getAm() {
		return am;
	}
	
	public void setAm(String am) {
		this.am = am;
	}
	
	public String getDoctorFullName() {
		return doctorFullName;
	}
	
	public void setDoctorFullName(String doctorFullName) {
		this.doctorFullName = doctorFullName;
	}
	
	public Appointment[] getListOfAppointments() {
		return listOfAppointments;
	}
	
	public void setListOfAppointments(Appointment[] listOfAppointments) {
		this.listOfAppointments = listOfAppointments;
	} 
	
	public int getNumOfAppointments() {
		return numOfAppointments;
	}

	public void setNumOfAppointments(int numOfAppointments) {
		this.numOfAppointments = numOfAppointments;
	}

	
	/*method to add appointment for each doctor, also lets the user know when the list of appointments*/
	public void addDoctorsAppointment(Appointment ap) {
		if(ap!=null) {
		this.listOfAppointments[numOfAppointments] = ap;
		numOfAppointments++;
		System.out.println("The appointment has been added to the doctors appointment list!");
		}
	}
}
