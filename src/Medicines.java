import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Medicines")
	@XmlAccessorType (XmlAccessType.FIELD)
	public class Medicines {
		
		@XmlElement(name = "Medicine")
		private List<Medicine> medicines_list = new ArrayList<Medicine>();
		
		public List<Medicine> getEmployees() {
	        return medicines_list;
	    }
	 
	    public void setEmployees(List<Medicine> medicines) {
	        this.medicines_list = medicines;
	    }
	    
	    public void addMedicine(Medicine medicine)
	    {
	    	this.medicines_list.add(medicine);	    	
	    }
	}