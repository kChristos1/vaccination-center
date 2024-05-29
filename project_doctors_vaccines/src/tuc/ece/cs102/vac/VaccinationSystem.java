//Christos Kostadimas

package tuc.ece.cs102.vac;

import java.time.format.DateTimeFormatter;

public class VaccinationSystem {
	
	final int NUM_OF_INSURED = 100;
	final int NUM_OF_CENTERS = 10;
	final int MAX_DAYS = 7;
	
	private String departmentName;
	private String URL; 
	private int vaccinesPeriod[];
	private Insured[] insuredCatalog;
	private VaccineCenter[] vaccineCenter; 
	private int numOfInsured;
	private int numOfCenters;
	
	/**constructor**/
	public VaccinationSystem(String depName, String URL) {
		this.departmentName = depName;
		this.URL = URL; 
		this.insuredCatalog = new Insured[NUM_OF_INSURED];
		this.vaccineCenter = new VaccineCenter[NUM_OF_CENTERS];
		this.vaccinesPeriod = new int[MAX_DAYS];
		this.numOfInsured = 0;
		this.numOfCenters = 0;
	}

	//setters and getters
	public String getDepartmentName() {
		return departmentName;
	}
	
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	
	public String getURL() {
		return URL;
	}
	
	public void setURL(String uRL) {
		URL = uRL;
	}
	
	public VaccineCenter[] getVaccineCenter() {
		return vaccineCenter;
	}
	
	public Insured[] getInsuredCatalog() {
		return insuredCatalog;
	}
	
	public int getNumOfCenters() {
		return numOfCenters;
	}

	public int[] getVaccinesPeriod() {
		return vaccinesPeriod;
	}
	
	public void setVaccinesPeriod(int[] vaccinesPeriod) {
		this.vaccinesPeriod = vaccinesPeriod;
	}
	
	public int getNumOfInsured() {
		return numOfInsured;
	}
	
	public void setNumOfInsured(int numOfInsured) {
		this.numOfInsured = numOfInsured;
	}
	
	public void setInsuredCatalog(Insured[] insuredCatalog) {
		this.insuredCatalog = insuredCatalog;
	}
	
	public void setVaccineCenter(VaccineCenter[] vaccineCenter) {
		this.vaccineCenter = vaccineCenter;
	}
	
	public void setNumOfCenters(int numOfCenters) {
		this.numOfCenters = numOfCenters;
	}
	
	
	/*Method for adding insured people to the System*/
	public void addInsured(Insured insured) {
		insuredCatalog[numOfInsured]=insured;
		numOfInsured++;
		if(numOfInsured >= 100) {
			System.out.println("Maximum number of Insured is 10!");
		}
	}
	
	
	/*Method for adding centers to the System*/
	public void addVaccineCenter(VaccineCenter vacCent) {
		vaccineCenter[numOfCenters] = vacCent;
		numOfCenters++;
		if(numOfCenters >= 10) {
			System.out.println("Maximum number of centers is 10!");
		}
	}
	
	
	/*Method to find a specific insured by his AMKA*/          
	public Insured findInsuredByAmka(String amka) {
		int flag=0;
		for(Insured insured: this.insuredCatalog) {  
			if(insured != null) {
				if(amka != null) { 
					if(amka.equals(insured.getAmka())) {
						flag=1;
						return insured;
					}
				}
			}
		}
		if(flag==0) {
			System.out.println("The insured with amka "+ amka +" does not exist in the catalog");
			System.out.println("Please, first enter the insured by choosing 1");
		}
		return null;
	}
	
	
	/*Method that finds a specific center by it's city*/
	public VaccineCenter findCenterByCity(String city) {
		int flag=0;
		for(VaccineCenter cent: this.vaccineCenter) {
			if(cent != null) {
				if( city != null) {
					if(city.equals(cent.getCenterCity())){
						flag=1;
						return cent;
					}
				}
			}
		}
		if(flag==0) {
			System.out.println("The center in "+ city +" does not exist in the catalog");
			System.out.println("Please, first enter the insured by pressing 1");
			return null;
		}
		return null;
	}
	
	
	/*Method that find a center by its title*/
	public VaccineCenter findCenterByTitle(String title) {
		int flag=0;
		for(VaccineCenter cent: this.vaccineCenter){ 
			if(cent != null) {
				if(title != null) {
					if(title.equals(cent.getTitle())) {
						flag=1;
						return cent;
					}
				}
			}
		}
		if(flag==0) {
			System.out.println("The center with title "+ title +" does not exist in the catalog");
			System.out.println("Please, first enter the center by pressing 2");
			return null;
		}
		return null;
	}
	
	
	/*Method that finds center by doctors am*/
	public VaccineCenter findCenterByDoctorAm(String am) {
		int flag=0;
		for(VaccineCenter vac :this.vaccineCenter) {
			for(int i=0 ; i<vac.getNumOfDoctors() ; i++) {
				if(vac.getArrayOfDoc()[i].getAm().equals(am)) {
					flag=1;
					return vac;	
				}
			}
		}
		
		if(flag==0){
			System.out.println("Could not find a doctor with this am");
			return null;
		}
		return null;
	}

	
	/*Method that finds and prints appointment by the doctors am*/
	public void findAppointmentByDoctorAm(String am) {
		for(VaccineCenter vac :this.vaccineCenter) {
			if(vac!=null) {
				for(int a=0; a<vac.getNumOfDoctors(); a++) {
					if(vac.getArrayOfDoc()[a].getAm().equals(am)) {
						System.out.println("The appointment you searched: \n"+ vac.getAppointmentsOfCenter()[a].getStartingTime());	
					}
				}
			}
		}
	}
	

	/*Method that prints all the available appointments*/
	public void printAvailableApp(VaccineCenter cent) {
		int flag;
		System.out.println("The available appointments that you can choose are:");
		for(int a=0 ; a<7; a++) {
			System.out.println();
			System.out.println("Day "+ (a+1) +"." + cent.getFirstDay().plusDays(a).format(DateTimeFormatter.ofPattern("(dd/MM/yyyy)")));
			for(int b=0; b<4; b++) {
				for(int c=0; c<cent.getNumOfDoctors(); c++)  {
					flag=0;
					if(cent.getAppointmentTimeTable()[a][b][c] == null) {
						flag=1;
					}
					int n = c + 1;
					if(flag==1 && n==cent.getNumOfDoctors()) {
						System.out.println((b+1)+ ".    (" + cent.getFirstSlot().plusDays(a).plusMinutes(b*30).format(DateTimeFormatter.ofPattern("HH:mm")) +"): available");
					}
					if(flag==0 && n==cent.getNumOfDoctors()) {
						System.out.println((b+1)+ ".    (" + cent.getFirstSlot().plusDays(a).plusMinutes(b*30).format(DateTimeFormatter.ofPattern("HH:mm")) +"): ***full***");
					}
				}
			}	
		}
	}	
	
	
	/*Method that prints all the insured citiznes of the system*/
	public void printAllInsured() {
		for(Insured insured : insuredCatalog) {
			if(insured!=null) {
				System.out.println("AMKA: " + insured.getAmka());
				System.out.println("Fullname: " + insured.getFullName());
				System.out.println("City: " + insured.getCity());
			    System.out.println();
			}
		}
	}
	
	
	/*Method that prints all the centers*/
	public void printCenters() {
		for(VaccineCenter vac : vaccineCenter) {
			if(vac!=null) {
				System.out.println("Code of center: " + vac.getCode());
			    System.out.println("Title of center: " + vac.getTitle());
			    System.out.println("City of center: " + vac.getCenterCity());
			    System.out.println();
			}
		}
	}
	
	
	/*Method that prints all doctors of center*/
	public void printAllDoctors() {
		for(VaccineCenter vac: vaccineCenter) {
			if(vac!=null) {
				for(Doctor doctor: vac.getArrayOfDoc()) {
					if(doctor!=null) {
						System.out.println("Doctor fullname: " + doctor.getDoctorFullName());
					    System.out.println("Doctor am: " + doctor.getAm());
					    System.out.println();
					}
				}
			}
		}
	}
	
	/*Method that checks if an appointment is available or not*/
	//The return type is int because i use it in a do while(...) loop condition in main! 
	public int checkAppointment(int chosenDate, int chosenTime, VaccineCenter center) {
		int flag=0;
		for(VaccineCenter vac: this.vaccineCenter) {
			flag=0;
			if(vac!=null) {
				if(vac.getCenterCity().equals(center.getCenterCity())){
					for(int i=0;i<7;i++) {
						if(chosenDate==i) {
							for(int j=0;j<4;j++) {
								if(j==chosenTime) {
									for(int k=0; k<vac.getNumOfDoctors(); k++) {
										if(vac.getAppointmentTimeTable()[i][j][k] == null) {
											flag=1;
									}
								}
							}
						}
					}
				}
					if(flag==1) {
						return 1;
					}else
						return 0;
			}
		}
			
		}
		return 0;
}
	
	
	//END OF CLASS BODY
}
	
	
