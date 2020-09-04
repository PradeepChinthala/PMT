package project.pageobjects;

import org.junit.Assert;

import net.serenitybdd.core.pages.PageObject;
import project.utilities.GenericUtils;
import project.utilities.SeleniumUtils;
import project.utilities.SharedPage;

public class EditCustomerPage extends PageObject{

	
	SeleniumUtils oSeleniumUtils;
	SharedPage oSharedPage;
	
	/*############################################ WebElements ###############################################*/
	
			
		
	/*############################################ xpath ###############################################*/
	
	String Prefix = "//div[@class='modal-content']";
	
	String clientID = "//input[@formcontrolname='CustomerClientName']";
	
	String customerName = "//input[@id='CustomerName']";
	
	String save = Prefix+"//button[text()='Save']";
	
	String loading = "(//div[@class='block-ui-wrapper block-ui-main active'])[1]//div[@class='loader']";
	
	String Mandatory_Symbol = "//label[text()='arg']/span[contains(text(),'*')]";
	
	String headerTitle = Prefix+"//h5[@id='exampleModalLabel']";
	
	String cancel = Prefix+"//button[text()='Cancel']";
	
	String close_X = "//span[text()='×']/..";
	
	/*############################################ methods ###################################################*/
	

	public boolean verifyFields(String arg1) {
		boolean bln = false;
		String[] sections = arg1.split(",");
		oSeleniumUtils.dynamicWaitForLoadingIcon(loading);
		for (int i = 0; i < sections.length; i++){
			switch(sections[i]){
			case "Client ID / Name":
			case "Client ID":
				//oSharedPage.dropDownfieldValidation(Prefix, clientID, save, true, "Select Client", sections[i]);
				String clientName = $(Prefix+clientID).getAttribute("value");
				Assert.assertTrue(clientName.length()!=0);
				System.out.println(arg1+" is Pre-populated with :"+clientName);
				Assert.assertTrue($(Prefix+clientID).getAttribute("readonly").equals(String.valueOf(true)));
				System.out.println(arg1+" is Read Only");
				break;
			case "Customer Name":
				String length = $(Prefix+customerName).getAttribute("maxlength");
				Assert.assertTrue(sections[i]+" max lenght not matched "+length,length.equals(String.valueOf(50)));
				System.out.println("'"+sections[i]+"' Max lenght is "+length);	
				Assert.assertTrue(sections[i]+" is not mandatory",oSeleniumUtils.element_displayed(Prefix+Mandatory_Symbol.replace("arg", sections[i])));
				System.out.println(sections[i]+" * symbol displayed");
				
				String existing = $(Prefix+customerName).getAttribute("value");
				Assert.assertTrue(existing.equals(SeleniumUtils.getValueByName("CustomerName")));
				oSeleniumUtils.enter_given_Text_Element(Prefix+customerName, "      ");	
				oSeleniumUtils.click_given_WebElement(save);
				Assert.assertTrue(sections[i]+" : Error Message not Dispalyed",oSeleniumUtils.element_displayed("//div[text()='Enter Valid Customer Name']"));
				System.out.println(sections[i]+" is not accepting spaces");
				break;
			case "Header":
				Assert.assertTrue(oSeleniumUtils.element_displayed(headerTitle));
				System.out.println("Header Title '"+$(headerTitle).getText()+"' displayed");
				break;
			case "Button":
				Assert.assertTrue(oSeleniumUtils.element_displayed(save));
				Assert.assertTrue(oSeleniumUtils.element_displayed(cancel));
				System.out.println("Add and Cancel buttons displayed");
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
		oSeleniumUtils.dynamicWaitForLoadingIcon(loading);
		for (int i = 0; i < sections.length; i++){
			String[] field = sections[i].split(":");
			SeleniumUtils.defaultWait(300);
			switch(field[0]){
			case "Client ID / Name":
			case "Client ID":
				//oSeleniumUtils.select_Bootstrap_Dropdown(Prefix+clientID,field[1]);
				break;
			case "Customer Name":
				if(field[1].toLowerCase().contains("random"))
					field[1] = "AutomationTest_New_"+GenericUtils.randomString(7);
				if(field[1].replace(" ", "").contains("CustomerName"))
					field[1] = SeleniumUtils.getValueByName(field[1]);
				oSeleniumUtils.enter_given_Text_Element(Prefix+customerName, field[1]);
				SeleniumUtils.setSessionVaribale("NewCustomerName", field[1]);
				break;
			case "Cancel":
			case "cancel":
			case "":break;			
			default:
				throw new IllegalArgumentException(sections[i]+" is does not exist, please provide the correct case");
			}
			if(!arg1.toLowerCase().contains("cancel"))
				oSeleniumUtils.click_given_WebElement(save);			
			oSeleniumUtils.dynamicWaitForLoadingIcon(loading);
		}
		return bln; 
	}
	
	public void verifyCustomePopUpWithClose(){
		Assert.assertTrue(oSeleniumUtils.element_displayed(headerTitle));
		System.out.println("Popup Dispalyed with :"+$(headerTitle).getText());
		Assert.assertTrue("Popup x button not displayed",oSeleniumUtils.element_displayed(close_X));
		oSeleniumUtils.click_given_WebElement(close_X);
		Assert.assertFalse(oSeleniumUtils.element_displayed(headerTitle));
	}
}
