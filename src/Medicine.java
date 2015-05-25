import java.util.Arrays;

public class Medicine {
		private String name;
		private String internationalName;
		private String[] forms;

		private String manufacturer;
		private String applicant;
		private String serialNumber;
		private String regDate;
		private String validityDate;
		private String original;
		private String linkGuideUtilize;
		private String linkGuidePatient;
		private String linkGuideSpecialist;

		public Medicine() {

		}
				
		public Medicine setCurrentAttribute(int j, String attribute) {
			switch (j) {
			case 1:
				break;
			case 2:
				this.setName(attribute);
				break;
			case 3:
				this.setInternationalName(attribute);
				break;
			case 4:
				this.setForms(attribute.split(";"));
				break;
			case 5:
				this.setManufacturer(attribute);
				break;
			case 6:
				this.setApplicant(attribute);
				break;
			case 7:
				this.setSerialNumber(attribute);
				break;

			case 8:
				this.setRegDate(attribute);
				break;
			case 9:
				this.setValidityDate(attribute);
				break;
			case 10:
				this.setOriginal(attribute);
				break;								
				
			}
			return this;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getInternationalName() {
			return internationalName;
		}

		public void setInternationalName(String internationalName) {
			this.internationalName = internationalName;
		}

		public String[] getForms() {
			return forms;			
		}

		public void setForms(String[] forms) {
			this.forms = forms;
		}

		public String getManufacturer() {
			return manufacturer;
		}

		public void setManufacturer(String manufacturer) {
			this.manufacturer = manufacturer;
		}

		public String getApplicant() {
			return applicant;
		}

		public void setApplicant(String applicant) {
			this.applicant = applicant;
		}

		public String getSerialNumber() {
			return serialNumber;
		}

		public void setSerialNumber(String serialNumber) {
			this.serialNumber = serialNumber;
		}

		public String getRegDate() {
			return regDate;
		}

		public void setRegDate(String regDate) {
			this.regDate = regDate;
		}

		public String getValidityDate() {
			return validityDate;
		}

		public void setValidityDate(String validityDate) {
			this.validityDate = validityDate;
		}

		public String getOriginal() {
			return original;
		}

		public void setOriginal(String original) {
			this.original = original;
		}

		public String getLinkGuideUtilize() {
			return linkGuideUtilize;
		}

		public void setLinkGuideUtilize(String linkGuideUtilize) {
			this.linkGuideUtilize = linkGuideUtilize;
		}

		public String getLinkGuidePatient() {
			return linkGuidePatient;
		}

		public void setLinkGuidePatient(String linkGuidePatient) {
			this.linkGuidePatient = linkGuidePatient;
		}

		public String getLinkGuideSpecialist() {
			return linkGuideSpecialist;
		}

		public void setLinkGuideSpecialist(String linkGuideSpecialist) {
			this.linkGuideSpecialist = linkGuideSpecialist;
		}


		@Override
		public String toString() {
			return "Medicine [name=" + name + ", internationalName="
					+ internationalName + ", forms=" + Arrays.toString(forms)
					+ ", manufacturer=" + manufacturer + ", applicant="
					+ applicant + ", serialNumber=" + serialNumber
					+ ", regDate=" + regDate + ", validityDate=" + validityDate
					+ ", original=" + original + ", linkGuideUtilize="
					+ linkGuideUtilize + ", linkGuidePatient="
					+ linkGuidePatient + ", linkGuideSpecialist="
					+ linkGuideSpecialist + "]";
		}
	}