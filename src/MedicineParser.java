import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.*;

public class MedicineParser {

	/**
	 * @param args
	 */

	public static WebDriver driver = null;
	public static List<WebElement> elements;
	public static String alphabet = "рстуфхцчшщэю€1234567890";
	//public static String alphabet = "абвгдежзийклмнопрстуфхцчшщэю€1234567890";
	//public static String alphabet = "й";
	public static List<Medicine> allMedicine = null;
	public static Medicines medicines = null;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.get("http://www.rceth.by/Refbank/reestr_lekarstvennih_sredstv/");

		/* строка поиска */

		// element.sendKeys("а");

		/* опции фильтра */
		WebElement dropDownListBox = driver.findElement(By
				.id("FProps_0__CritElems_0__Crit"));
		Select clickThis = new Select(dropDownListBox);
		clickThis.selectByValue("Start");

		/* отображать формы выпуска */
		driver.findElement(By.id("FOpt_VEField1")).click();
		medicines = new Medicines();
		for (int i = 1; i <= alphabet.length(); i++) {
			WebElement element = driver.findElement(By
					.id("FProps_0__CritElems_0__Val"));
			element.clear();
			element.sendKeys(alphabet.substring(i - 1, i));
			element.submit();
			parseAllPages();
		}
		// element.submit();

		// parseAllPages();

	}

	public static void parseAllPages() {

		int numberOfPages = 0;
		List<WebElement> listOfPages = null;

		try {
			listOfPages = driver.findElements(By.id("FOpt_PageN"));
			numberOfPages = listOfPages.size();
			System.out.println("number of pages: " + numberOfPages);
		} catch (Exception e) {
			numberOfPages = -1;
		}

		if (numberOfPages >= 0) {
			parsePage();
			for (int pageNmbr = 1; pageNmbr < numberOfPages; pageNmbr++) {
				listOfPages.get(pageNmbr).click();
				listOfPages = driver.findElements(By.id("FOpt_PageN"));
				parsePage();
			}
		}
	}

	public static void parsePage() {
		int rowCount = driver
				.findElements(
						By.xpath("//*[@id='content']/div/div[4]/div[1]/table/tbody/tr"))
				.size();
		System.out.println("row count = " + rowCount);

		WebElement instruction_link = null;
		String linkGuideUtilize = null;
		String linkGuidePatient = null;
		String linkGuideSpecialist = null;
		String cellText = null;
		
		
		for (int i = 1; i <= rowCount; i++) {

			Medicine medicine = new Medicine();

			for (int j = 1; j <= 10; j++) {

				System.out.print("  | ");
				WebElement cell = driver
						.findElement(By
								.xpath("//*[@id='content']/div/div[4]/div[1]/table/tbody/tr["
										+ i + "]/td[" + j + "]"));

				if (j != 2) {
					cellText = cell.getText();
					cellText = cellText.replaceAll("\\r?\\n"," ");
					System.out.print(cellText);
				}

				if (j == 2) {

					cellText = getMedicineName(cell.getText());
					System.out.print(cellText);
					System.out.print("  | ");

					if (cell.getText().contains("по применению")) {

						try {
							instruction_link = cell.findElement(By
									.linkText("по применению"));
							linkGuideUtilize = instruction_link
									.getAttribute("href");
							System.out.print("link: " + linkGuideUtilize);
							medicine.setLinkGuideUtilize(linkGuideUtilize);
						} catch (Exception e) {
						}
					}
					if (cell.getText().contains("пациента")) {
						try {
							instruction_link = cell.findElement(By
									.linkText("пациента"));
							linkGuidePatient = instruction_link
									.getAttribute("href");
							System.out.print("link: " + linkGuidePatient);
							medicine.setLinkGuidePatient(linkGuidePatient);
						} catch (Exception e1) {
						}
					}
					if (cell.getText().contains("специалиста")) {
						try {
							instruction_link = cell.findElement(By
									.linkText("специалиста"));
							linkGuideSpecialist = instruction_link
									.getAttribute("href");
							System.out.print("link: " + linkGuideSpecialist);
							medicine.setLinkGuideSpecialist(linkGuideSpecialist);
						} catch (Exception e1) {
						}
					}

				}
				medicine.setCurrentAttribute(j, cellText);
			}
			
			System.out.println("");
			System.out
					.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
			System.out.println(medicine.toString());
			System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
			medicines.addMedicine(medicine);
		}
		
		try {
			marshalingMedicines();
			System.out.println("Completed!");
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static String getMedicineName(String cellText) {
		String medicineName = cellText;
		medicineName = medicineName.replaceAll("инструкци€: ", "");
		medicineName = medicineName.replaceAll("пациента", "");
		medicineName = medicineName.replaceAll("специалиста", "");
		medicineName = medicineName.replaceAll("по применению", "");
		medicineName = medicineName.trim();
		return medicineName;

	}
	
	public static void marshalingMedicines() throws JAXBException
	{
	    JAXBContext jaxbContext = JAXBContext.newInstance(Medicines.class);
	    Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
	 
	    jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
	     
	    //Marshal the employees list in console
	   // jaxbMarshaller.marshal(medicines, System.out);
	     
	    //Marshal the employees list in file
	    jaxbMarshaller.marshal(medicines, new File("c:/temp/medicines.xml"));
	}
	
	
	
	
}
