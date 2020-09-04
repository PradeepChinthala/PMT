package project.pageobjects;


import java.util.List;

import org.openqa.selenium.support.FindBy;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import project.utilities.DBUtils;
import project.utilities.SeleniumUtils;

public class AccountConfigurationPage  extends PageObject{

	SeleniumUtils oSeleniumUtils;
	DBUtils oDBUtils;
	
	/*############################################ WebElements ###############################################*/
	
	@FindBy(css = "#ClientId input")
	public WebElementFacade clientId;
	
	@FindBy(id="select2-MasterAccountId-container")
	public WebElementFacade masterAccountId;
	
	@FindBy(css = "#RequestStatusID inputt")
	public WebElementFacade requestStatus;
	
	@FindBy(css = "input[class='select2-search__field']")
	public WebElementFacade subAccountId;
	
	@FindBy(id = "AccountMgrId")
	public WebElementFacade accountManagerName;
	
	@FindBy(id = "PhoneId")
	public WebElementFacade phoneNumber;
	
	@FindBy(id = "EmailId")
	public WebElementFacade emailId;
	
	@FindBy(id = "AddressLineId1")
	public WebElementFacade addressLineId1;
	
	@FindBy(xpath = "//div[@class='mat-tab-label-content'][text()='Account Search']")
	public WebElementFacade accountSearch;


	String projectType = "//ng-select[@id='ProjectTypeId']";
	
	String editAccountConfiguration_Tab = "//div[@class='mat-tab-labels']//div[text()='Add / Edit Account Configurations']";
	
	String link_Unlink_tab = "//div[@class='mat-tab-labels']//div[text()='Link / Unlink Accounts']";
	
	String auditHistory_Tab = "//div[@class='mat-tab-labels']//div[text()='Audit History']";	
	
	
	/*############################################ methods ###################################################*/
	

	public boolean veriyfAccountConfigurationPage(){
		return oSeleniumUtils.element_displayed(accountSearch);
	}
	
	//Add Edit Account Configurations
	public boolean verifyFields(String arg1) throws Exception {
		boolean bln = false;
		String[] sections = arg1.split(",");
		for (int i = 0; i < sections.length; i++){
			switch(sections[i].trim()){
			case "Date of Service":
				
				break;
			case "Request Type":
				
				break;
			case "Project Type":
				verifyProjectType();
				break;
			case "Work Flow State ID":
				
				break;
			case "Office ID":
				
				break;
			case "Append Mode":
				
				break;
			case "Instructions":
				
				break;			
			case "DDOS":
				
				break;			
			default:
				throw new IllegalArgumentException(sections[i]+" is does not exist, please provide the correct case");
			}
		}
		return bln; 
	}
	
	public void verifyProjectType(){
		oSeleniumUtils.scrollToElement(projectType);
		oSeleniumUtils.moveToElement_and_click(projectType);
		//oSeleniumUtils.click_given_WebElement(projectType);					
		// Validate options are in the format of <Project Type> - <Project Description>
		List<String> values = oSeleniumUtils.get_All_Options_From_Brootstarp_Dropdown(projectType);					
		for(String v : values){
			System.out.println(v);	
		}
		//Assert.assertTrue("'Project Type' does not have options in the form of <Project Type> - <Project Description> for "+v,GenericUtils.isInteger(v.split("-")[0].trim()));		
		//System.out.println("'projectType' have options in the form of <Project Type> - <Project Description>");
		
		//validating Clients in database
		/*System.out.println(sections[i]+" validation in Databse");
		values = oDBUtils.executeSQLQuery_Get_Entire_ColumnData(DBQueries.getClients(), "Project Description");
		for(String v : values)
			Assert.assertTrue("'Project Type' does not have options in the form of <Project Type> - <Project Description> for "+v,GenericUtils.isInteger(v.split("-")[0].trim()));		
		System.out.println("'Project Type' have options in the form of <Project Type> - <Project Description> in the Database");*/
	}
		
}
