//POJO Class for converting each CSV line to an Object (Except Header)
public class Prescriber
{
	String lastName;
	String firstName;
	String drugName;
	long drugCost;
	boolean valid;
	
	public Prescriber(String lastName, String firstName, String drugName, long drugCost) {
		super();
		this.lastName = lastName;
		this.firstName = firstName;
		this.drugName = drugName;
		this.drugCost = drugCost;
		this.valid = isValid();
	}

	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getDrugName() {
		return drugName;
	}
	public void setDrugName(String drugName) {
		this.drugName = drugName;
	}
	public long getDrugCost() {
		return drugCost;
	}
	public void setDrugCost(long drugCost) {
		this.drugCost = drugCost;
	}
	
	boolean isValid()
	{
		if(lastName==null || lastName.length()==0)
			return false;

		if(firstName==null || firstName.length()==0)
			return false;
		
		if(drugName==null || drugName.length()==0)
			return false;
		
		if(drugCost<0)
			return false;
		
		return true;
			
	}
	
}