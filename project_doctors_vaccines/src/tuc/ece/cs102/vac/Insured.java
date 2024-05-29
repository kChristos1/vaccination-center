//Christos Kostadimas

package tuc.ece.cs102.vac;
public class Insured {
	
	private String amka;
	private String fullName;
	private String city;
	
	/**constructors**/
	public Insured(String amka, String fullName, String city) {
		this.amka = amka; 
		this.fullName = fullName; 
		this.city = city; 
	}
	
	public Insured(){
		
	}
	
	//setters and getters
	public String getAmka() {
		return amka;
	}
	
	public void setAmka(String amka) {
		this.amka = amka;
	}
	
	public String getFullName() {
		return fullName;
	}
	
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	
	public String getCity() {
		return city;
	}
	
	public void setCity(String city) {
		this.city = city;
	} 		
}
