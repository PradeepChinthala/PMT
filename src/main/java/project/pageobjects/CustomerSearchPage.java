package project.pageobjects;


import java.sql.SQLException;
import java.time.Duration;
import java.util.List;

import org.junit.Assert;

import net.serenitybdd.core.pages.PageObject;
import project.utilities.DBQueries;
import project.utilities.DBUtils;
import project.utilities.GenericUtils;
import project.utilities.ProjectVariables;
import project.utilities.SeleniumUtils;
import project.utilities.SharedPage;

public class CustomerSearchPage extends PageObject{

	SeleniumUtils oSeleniumUtils;
	SharedPage oSharedPage;
	DBUtils oDBUtils;
	
	/*############################################ WebElements ###############################################*/
	
			
		
	/*############################################ xpath ###############################################*/
	
	String Prefix = "//form[@id='CustomerSearchId']";
	
	String clientID = "//ng-select[@id='ClientId']";
	
	String customerName = "//input[@id='Name']";
	
	String search = "//form[@id='CustomerSearchId']//button[@ng-click='reset()']";
	
	String toaster = "//div[@id='toast-container']//*[text()]";
	
	String btn_Xpath = "(//td[text()='arg1']/../td/*[normalize-space(text())='arg2'])[1]";
	
	public static String loading = "(//div[@class='block-ui-wrapper block-ui-main active'])[1]//div[@class='loader']";
	
	public static String tablePrefix = "//table[contains(@id,'DataTables_Table')]";
	
	/*############################################ methods ###################################################*/
	

	public boolean verifyFields(String arg1) throws SQLException {
		boolean bln = false;
		String[] sections = arg1.split(",");
		for (int i = 0; i < sections.length; i++){
			switch(sections[i]){
			case "Client ID / Name":
			case "Client ID":
				oSharedPage.dropDownfieldValidation(Prefix, clientID, search, true, "Select Client", "Client ID / Name");				
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
				oSharedPage.inputfieldValidation(Prefix, customerName, search, false, 50, sections[i]);
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
			case "Client ID / Name":
			case "Client ID":
			case "ClientID":
				if(field[1].replace(" ", "").contains("ClientID") || field[1].replace(" ", "").contains("ClientName"))
					field[1] = SeleniumUtils.getValueByName(field[1]);
				oSeleniumUtils.select_Bootstrap_Dropdown(Prefix+clientID,field[1]);
				break;
			case "Customer Name":
			case "CustomerName":
				if(field[1].toLowerCase().equals("random"))
					field[1] = GenericUtils.randomString(10);
				if(field[1].replace(" ", "").equals("CustomerName"))
					field[1] = SeleniumUtils.getValueByName(field[1]);
				oSeleniumUtils.enter_given_Text_Element(Prefix+customerName, field[1]);
				break;
			case "":break;
			default:
				throw new IllegalArgumentException(sections[i]+" is does not exist, please provide the correct case");
			}
		}		
		oSeleniumUtils.click_given_WebElement(search);
		oSeleniumUtils.dynamicWaitForLoadingIcon(loading);
		return bln; 
	}

	public String verifyUserAddedToaster() {
		oSeleniumUtils.dynamicWaitForLoadingIcon(loading);
		return $(toaster).withTimeoutOf(Duration.ofMillis(ProjectVariables.MID_TIME_OUT)).waitUntilVisible().getText();
	}

	public void clickButtonBasedOn(String buttonName, String criteria) {
		oSeleniumUtils.dynamicWaitForLoadingIcon(loading);
		String cellText = SeleniumUtils.getValueByName(criteria);
		boolean bln = oSeleniumUtils.click_given_WebElement(btn_Xpath.replace("arg1",cellText).replace("arg2",buttonName));
		Assert.assertTrue(criteria+" Does not exist",bln);
	}

	public void verifyCustomePopUpWithClose() {}

	public void getResultsWebTable(String arg) {
		String[] columns = arg.split(",");
		oSeleniumUtils.dynamicWaitForLoadingIcon(loading);
		for(String c : columns){
			String result = SeleniumUtils.getValueByName(c);
			if(result!=null){				
				boolean bln = oSeleniumUtils.element_displayed("//td[text()='"+result+"']");
				Assert.assertTrue(bln);
			}
		}
	}	
	
}

