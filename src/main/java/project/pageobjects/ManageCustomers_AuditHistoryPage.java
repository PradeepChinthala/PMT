package project.pageobjects;



import org.junit.Assert;
import org.openqa.selenium.Keys;

import net.serenitybdd.core.pages.PageObject;
import project.utilities.GenericUtils;
import project.utilities.SeleniumUtils;
import project.utilities.SharedPage;

public class ManageCustomers_AuditHistoryPage extends PageObject{

	SeleniumUtils oSeleniumUtils;
	SharedPage oSharedPage;
	
	/*############################################ WebElements ###############################################*/
			
		
	/*############################################ xpath ###############################################*/
	
	String Prefix = "//form[@id='frmCstmrAudit']";
	
	String startDate = "//input[@id='customerStartDate']";	
	
	String endDate = "//input[@id='customerEndDate']";
	
	String action = "//div[@class='ng-select-container ng-has-value']";
	
	String search = "//button[@ng-click='reset()']";
	
	/*############################################ methods ###################################################*/
	

	public boolean verifyFields(String arg1) {
		boolean bln = false;
		String[] sections = arg1.split(",");
		for (int i = 0; i < sections.length; i++){
			SeleniumUtils.defaultWait(500);
			switch(sections[i]){
			case "Start Date":								
				oSharedPage.datePicker_Validation(Prefix,startDate,search,true,"Select Start Date",sections[i]);
				oSharedPage.verifyInvalidDate(Prefix+startDate,search,"Invalid date","02/30/2020",sections[i]);
				break;
			case "End Date":
				oSharedPage.datePicker_Validation(Prefix,endDate,search,true,"Select End Date",sections[i]);
				// Validate The End Date is earlier than the Start Date	
				String currentDate = GenericUtils.get_Current_Date("MM/dd/yyyy");
				oSeleniumUtils.enter_given_Text_Element(startDate, currentDate);
				oSeleniumUtils.enter_given_Text_Element(endDate, GenericUtils.Add_days_to_given_date(currentDate, -3));
				$(endDate).sendKeys(Keys.TAB);
				oSeleniumUtils.click_given_WebElement(search);
				bln = oSeleniumUtils.element_displayed("//div[normalize-space(text())='The End Date is earlier than the Start Date']");
				Assert.assertTrue(bln);				
				System.out.println("'The End Date is earlier than the Start Date' error message displayed");
				
				//Validation The difference between Start Date and End Date should be 2 years
				oSeleniumUtils.enter_given_Text_Element(endDate, currentDate);
				oSeleniumUtils.enter_given_Text_Element(startDate, GenericUtils.Add_days_to_given_date(currentDate, -731));
				$(startDate).sendKeys(Keys.TAB);
				oSeleniumUtils.click_given_WebElement(search);
				bln = oSeleniumUtils.element_displayed("//div[normalize-space(text())='The difference between Start Date and End Date should be 2 years.']");
				Assert.assertTrue(bln);
				System.out.println("'The difference between Start Date and End Date should be 2 years' error message displayed");
				break;
			case "Action":
				oSharedPage.dropDownfieldValidation(Prefix,action,search,false,"All",sections[i]);	
				oSharedPage.dropDown_Options_Validation(Prefix+action,3,sections[i]);
				oSharedPage.select_Only_one_option_validation(Prefix+action,sections[i]);
				break;
			
			default:
				throw new IllegalArgumentException(sections[i]+" is does not exist, please provide the correct case");
			}
		}
		return bln; 
	}
	
	public boolean populateFields(String arg1) {
		boolean bln = false;
		String[] sections = arg1.split(",");
		for (int i = 0; i < sections.length; i++){
			String[] field = sections[i].split(":");
			SeleniumUtils.defaultWait(300);
			switch(field[0]){
			case "StartDate":
				String currentDate = GenericUtils.get_Current_Date("MM/dd/yyyy");
				String newDate = GenericUtils.Add_days_to_given_date(currentDate, Integer.valueOf(field[1]));
				oSeleniumUtils.enter_given_Text_Element(startDate, newDate);
				$(startDate).sendKeys(Keys.TAB);
				break;
			case "EndDate":
				currentDate = GenericUtils.get_Current_Date("MM/dd/yyyy");
				newDate = GenericUtils.Add_days_to_given_date(currentDate, Integer.valueOf(field[1]));
				oSeleniumUtils.enter_given_Text_Element(endDate, newDate);
				$(endDate).sendKeys(Keys.TAB);
				break;
			case "Action":
				
				break;
			case "":break;
			default:
				throw new IllegalArgumentException(sections[i]+" is does not exist, please provide the correct case");
			}			
		}
		oSeleniumUtils.click_given_WebElement(search);
		return bln; 
	}
}
