package project.pageobjects;

import org.junit.Assert;
import org.openqa.selenium.support.FindBy;

import net.serenitybdd.core.pages.WebElementFacade;
import project.utilities.SeleniumUtils;
import project.utilities.SharedPage;

public class EditProjectPage  extends AddProjectPage{
	SeleniumUtils oSeleniumUtils;
	SharedPage oSharedPage;
	
	/*############################################ WebElements ###############################################*/
	
	@FindBy(xpath = "(//div[@class='modal-content'])//button[text()='Add']")
	public WebElementFacade add;
	
	@FindBy(xpath = "//*[@id='AddProjectMasterAccount']")
	public WebElementFacade masterAccountID;
	
	@FindBy(css = "div.modal-content #Name")
	public WebElementFacade projectName;
	
	@FindBy(css = "div.modal-content #Description")
	public WebElementFacade projectDescription;
		
		
	/*############################################ xpath ###############################################*/
	
	String Prefix = "//div[@class='modal-content']";
	
	String clientID = "//input[@formcontrolname='ClientName']";	
	
	String save = Prefix+"//button[text()='Save']";
	
	String loading = "(//div[@class='block-ui-wrapper block-ui-main active'])[1]//div[@class='loader']";
	
	String Mandatory_Symbol = "//label[text()='arg']/span[contains(text(),'*')]";
	
	String headerTitle = Prefix+"//h5[@id='exampleModalLabel']";
	
	String cancel = Prefix+"//button[text()='Cancel']";
	
	String close_X = "//span[text()='×']/..";
	
	/*############################################ methods ###################################################*/
	
	public void VerifyAllFieldsEditable(){
		
	}
	
	@Override
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
			case "MasterAccountID":
			case "MasterAccountName":
			case "Master Account ID / Name":
				String str= "Master Account ID / Name";	
				
				// Verifying Master Account ID displayed along with mandatory
				Assert.assertTrue(str+"' not displayed",oSeleniumUtils.element_displayed(masterAccountID));
				Assert.assertTrue(str+" is not mandatory",oSeleniumUtils.element_displayed(Suffix+Mandatory_Symbol.replace("arg", str)));
				System.out.println("'"+str+"' * symbol displayed");
				
				/*// validating default text
				String text = oSeleniumUtils.get_Text_From_WebElement("("+masterAccountID+"//span[text()])[1]");
				Assert.assertTrue("'Select Master Account' is populated by default",text!="");
				System.out.println("'Select Master Account' is populated");*/
				bln = true;
				break;
			case "Project Name":
			case "ProjectName":
				str= "Project Name";		
				String expectedLeght = "30";
				
				Assert.assertTrue(str+"' not displayed",oSeleniumUtils.element_displayed(projectName));
				Assert.assertTrue(str+" is not mandatory",oSeleniumUtils.element_displayed(Suffix+Mandatory_Symbol.replace("arg", str)));
				System.out.println("'"+str+"' * symbol displayed");
				
				String length = $(projectName).getAttribute("maxlength");
				Assert.assertTrue(str+" max lenght not matched"+length,length.equals(expectedLeght));
				System.out.println("'"+str+"' Max lenght is "+length);
				bln = true;
				break;
			case "ProjectDescription":
			case "Project Description":
				str= "Project Description";		
				
				Assert.assertTrue(str+"' not displayed",oSeleniumUtils.element_displayed(projectDescription));
				System.out.println("'"+str+"' is textarea");
				Assert.assertTrue(str+" is not mandatory",oSeleniumUtils.element_displayed(Suffix+Mandatory_Symbol.replace("arg", str)));
				System.out.println("'"+str+"' * symbol displayed");
				
				length = $(projectDescription).getAttribute("maxlength");
				Assert.assertTrue(str+" max lenght not matched "+length,length.equals("255"));
				System.out.println("'"+str+"' Max lenght is "+length);
				bln = true;
				break;
			case "Project Wave":
			case "ProjectWave":
				str= "Project Wave";
				Prefix = super.Prefix.replace("arg", "ProjectWaveID");
				
				// Verifying Projectwave displayed along with mandatory
				Assert.assertTrue(str+"' not displayed",oSeleniumUtils.element_displayed(Suffix+Prefix));
				Assert.assertTrue(str+" is not mandatory",oSeleniumUtils.element_displayed(Suffix+Mandatory_Symbol.replace("arg", str)));
				System.out.println(str+" * symbol displayed");	
						
				bln = true;
				break;			
			case "SeasonYear":
				String labelName= "Season Year";
				Prefix = super.Prefix.replace("arg", "SeasonYear");		
				
				// Verifying Season Year displayed along with mandatory
				Assert.assertTrue(labelName+"' not displayed",oSeleniumUtils.element_displayed(Suffix+Prefix));
				Assert.assertTrue(labelName+" is not mandatory",oSeleniumUtils.element_displayed(Suffix+Mandatory_Symbol.replace("arg", labelName)));
				System.out.println(labelName+" * symbol displayed");				
				bln = true;
				break;
			case "ServiceLine":				
				bln = ServiceLine();
				break;
			case "OnOffShore":
				bln = onOffShore();
				break;
			case "clientServicesRep":
				bln = clientServicesRep();
				break;
			case "implementationContact":
				bln = implementationContact();
				break;
			case "targetPercentage":
				bln = targetPercentage();
				break;
			case "projectStartDate":
				bln = projectStartDate("projectStartDate");
				break;
			case "projectEndDate":
				bln = projectStartDate("projectEndDate");
				break;
			case "slaFirstContactInterval":
				bln = slaFirstContactInterval();
				break;
			case "additionalContactsSLAType":
				bln = additionalContactsSLAType();
				break;
			case "EveryXDays":
			case "XTimesDuringProject":
				bln = additionalContactsInput(arg1);
				break;
			case "FirstContactInterval":
			case "FirstContactIntervalWarning":
				bln = FirstContactInterval(arg1);
				break;
			case "estimatedEndDate":
			case "targetFaxDate":
				bln = estimatedEndDate(arg1);
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
	
	@Override
	public String populateFields(String arg1) {
		String clientName = $(Prefix+clientID).getAttribute("value");
		Assert.assertTrue(clientName.length()!=0);
		System.out.println(arg1+" is Pre-populated with :"+clientName);
		Assert.assertTrue($(Prefix+clientID).getAttribute("readonly").equals(String.valueOf(true)));
		System.out.println(arg1+" is Read Only");
		
		String str = "";
		try {
			str = super.populateFields(arg1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		/*
			case "clientServicesRep":
				break;
			case "implementationContact":
				break;
			case "targetPercentage":
				break;
			case "projectStartDate":
				break;
			case "projectEndDate":
				
				break;
			case "slaFirstContactInterval":
				
				break;
			case "additionalContactsSLAType":
				
				break;
			case "EveryXDays":
			case "XTimesDuringProject":
				
				break;
			case "FirstContactInterval":
			case "FirstContactIntervalWarning":
				
				break;
			case "estimatedEndDate":
			case "targetFaxDate":
				
				break;
			case "Header":
				
				break;
			case "Button":
				
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
			SeleniumUtils.setSessionVaribale(field[0].trim(), field[1]);
			str+=field[0].trim()+" => "+field[1]+";";
		}	*/	
		return str; 
	}	
	
	public void verifyCustomePopUpWithClose(){
		Assert.assertTrue(oSeleniumUtils.element_displayed(headerTitle));
		System.out.println("Popup Dispalyed with :"+$(headerTitle).getText());
		Assert.assertTrue("Popup x button not displayed",oSeleniumUtils.element_displayed(close_X));
		oSeleniumUtils.click_given_WebElement(close_X);
		Assert.assertFalse(oSeleniumUtils.element_displayed(headerTitle));
	}
}
