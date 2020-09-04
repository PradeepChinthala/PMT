package project.pageobjects;

import java.util.List;

import org.junit.Assert;

import net.serenitybdd.core.pages.PageObject;
import project.utilities.DBQueries;
import project.utilities.DBUtils;
import project.utilities.GenericUtils;
import project.utilities.SeleniumUtils;
import project.utilities.SharedPage;

public class AddCustomerPage extends PageObject{
	
	SeleniumUtils oSeleniumUtils;
	SharedPage oSharedPage;
	DBUtils oDBUtils;
	
	/*############################################ WebElements ###############################################*/
	
			
		
	/*############################################ xpath ###############################################*/
	
	String Prefix = "//div[@class='modal-content']";
	
	String clientID = "//ng-select[@id='ClientId']";
	
	String customerName = "//input[@id='CustomerName']";
	
	String add = Prefix+"//button[@ng-click='reset()']";
	
	String cancel = Prefix+"//button[text()='Cancel']";
	
	String headerTitle = Prefix+"//h5[@id='exampleModalLabel']";
	
	String close_X = "//span[text()='×']/..";
	
	/*############################################ methods ###################################################*/
	
	
	
	public boolean verifyFields(String arg1) throws Exception {
		boolean bln = false;
		String[] sections = arg1.split(",");
		for (int i = 0; i < sections.length; i++){
			switch(sections[i]){
			case "Client ID / Name":
			case "Client ID":				
				oSharedPage.dropDownfieldValidation(Prefix, clientID, add, true, "Select Client", "Client ID / Name");				
				oSeleniumUtils.click_given_WebElement(Prefix+clientID);					
				// Validate options are in the format of <Client id> - <client name>
				List<String> values = oSeleniumUtils.get_All_Options_From_Brootstarp_Dropdown(Prefix+clientID);					
				for(String v : values)
					Assert.assertTrue("'Client ID' does not have options in the form of <ID> - <Name> for "+v,GenericUtils.isInteger(v.split("-")[0].trim()));		
				System.out.println("'Client ID' have options in the form of <ID> - <Name>");
				//should be able to search with Id/name/both
				String[] list = "0-Hu".split("-");
				for(String l : list){
					oSeleniumUtils.enter_given_Text_Element(Prefix+clientID+"//input",l);
					values = oSeleniumUtils.get_All_Options_From_Brootstarp_Dropdown(Prefix+clientID);
					for(String v : values){
						Assert.assertTrue("",v.contains(l));
						System.out.println("User is able to search with => "+l+" and resulted option is '"+v+"'");
					}
				}
				//validating Clients in database
				System.out.println(sections[i]+" validation in Databse");
				values = oDBUtils.executeSQLQuery_Get_Entire_ColumnData(DBQueries.getClients(), "ClientName");
				for(String v : values)
					Assert.assertTrue("'Client ID' does not have options in the form of <ID> - <Name> for "+v,GenericUtils.isInteger(v.split("-")[0].trim()));		
				System.out.println("'Client ID' have options in the form of <ID> - <Name> in the Database");
				
				break;
			case "Customer Name":
				oSharedPage.inputfieldValidation(Prefix, customerName, add, true, 50, sections[i]);
				oSeleniumUtils.enter_given_Text_Element(Prefix+customerName, "Sample Test Name 123");
				Assert.assertTrue($(Prefix+customerName).getAttribute("value").equals("Sample Test Name 123"));
				System.out.println("User is able accept characters in '"+ sections[i]+"'");	
				break;
			case "Header":
				Assert.assertTrue(oSeleniumUtils.element_displayed(headerTitle));
				System.out.println("Header Title '"+$(headerTitle).getText()+"' displayed");
				break;
			case "Button":
				Assert.assertTrue(oSeleniumUtils.element_displayed(add));
				Assert.assertTrue(oSeleniumUtils.element_displayed(cancel));
				System.out.println("Add and Cancel buttons displayed");
				break;
			default:
				throw new IllegalArgumentException(sections[i]+" is does not exist, please provide the correct case");
			}
		}
		return bln; 
	}
	
	public boolean populateFields(String arg1) throws Exception {
		boolean bln = false;
		String[] sections = arg1.split(",");
		for (int i = 0; i < sections.length; i++){
			String[] field = sections[i].split(":");
			SeleniumUtils.defaultWait(300);
			switch(field[0]){
			case "Client ID / Name":
			case "Client ID":
			case "ClientID":
				field[1] = oSeleniumUtils.select_Bootstrap_Dropdown(Prefix+clientID,field[1]);
				SeleniumUtils.setSessionVaribale("ClientName", field[1]);				
				SeleniumUtils.setSessionVaribale("ClientID", field[1].replaceAll("[^0-9]", ""));
				break;
			case "Customer Name":
				if(field[1].toLowerCase().contains("random"))
					field[1] = "AutomationTest_"+GenericUtils.randomString(7);
				oSeleniumUtils.enter_given_Text_Element(Prefix+customerName, field[1]);
				SeleniumUtils.setSessionVaribale("CustomerName", field[1]);
				break;			
			case "Cancel":
			case "cancel":
			case "":break;
			default:
				throw new IllegalArgumentException(sections[i]+" is does not exist, please provide the correct case");
			}
		}
		if(!arg1.toLowerCase().contains("cancel"))
			oSeleniumUtils.click_given_WebElement(add);
		return bln; 
	}
	
	public void verifyCustomePopUpWithClose(){
		Assert.assertTrue("Popup x button not displayed",oSeleniumUtils.element_displayed(close_X));
		oSeleniumUtils.click_given_WebElement(close_X);
	}

	public void verifySpaceInField(String field) {
		oSeleniumUtils.enter_given_Text_Element(Prefix+customerName, "      ");	
		oSeleniumUtils.click_given_WebElement(add);
		Assert.assertTrue(field+" : Error Message not Dispalyed",oSeleniumUtils.element_displayed("//div[text()='Enter Valid Customer Name']"));
		oSeleniumUtils.enter_given_Text_Element(Prefix+customerName, "    Automation Test");
		oSeleniumUtils.click_given_WebElement(add);
		Assert.assertFalse(field+" : Error Message Dispalyed",oSeleniumUtils.element_displayed("//div[text()='Enter Valid Customer Name']"));
		System.out.println(field+" : is not accepting space");
	}

}
