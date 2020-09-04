package project.pageobjects;

import java.time.Duration;
import java.util.Locale;

import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.FindBy;

import com.github.javafaker.Faker;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import project.utilities.DBQueries;
import project.utilities.DBUtils;
import project.utilities.GenericUtils;
import project.utilities.ProjectVariables;
import project.utilities.SeleniumUtils;

public class AddProjectPage extends PageObject{

	SeleniumUtils oSeleniumUtils;
	HomePage oHomePage;	
	DBUtils oDBUtils;
	
	Faker faker = new Faker(new Locale("en-IND"));
	/*############################################ WebElements ###############################################*/
	
	@FindBy(css = "#ClientId input")
	public WebElementFacade clientId;
	
	@FindBy(xpath = "(//div[@class='modal-content'])//button[text()='Save'] | (//div[@class='modal-content'])//button[text()='Add']")
	public WebElementFacade add;
	
	@FindBy(xpath = "//*[@id='AddProjectMasterAccount']")
	public WebElementFacade masterAccountID;
	
	@FindBy(css = "div.modal-content #Name")
	public WebElementFacade projectName;
	
	@FindBy(css = "div.modal-content #Description")
	public WebElementFacade projectDescription;
	
	@FindBy(xpath = "//button[text()='Cancel']")
	public WebElementFacade cancel;
	
	/*############################################ xpath ###############################################*/
	
	String modal = "(//div[@class='modal-content'])";
	
	String fieldLabel = "("+modal+"//label) | ("+modal+"//button)";
	
	String Suffix = "(//div[@class='modal-content'])//form[@class]";
	
	String Prefix = "//ng-select[@formcontrolname='arg'][contains(@class,'font ng-select ng-select-single ng-select-')]";
		
	String Options ="//div[@class='ng-option ng-star-inserted' or @role='option']/div";
	
	String Mandatory_Symbol = "//label[text()='arg']/span[contains(text(),'*')]";
	
	String Mandatory_Msg = "//div[normalize-space(text())='arg is required']";
	
	String Headers = "(//table[@id='DataTables_Table_0']/thead/tr/th)";
	
	String TableData = "(//table[@id='DataTables_Table_0']/tbody/tr/td[index])";
	
	String SearchResult = "//div[@id='DataTables_Table_0_filter']/*/input";
	
	String rows = "//select[@name='DataTables_Table_0_length']/option";
	
	String datePicker_Prefix = "//div[@class='bs-datepicker-container ng-trigger ng-trigger-datepickerAnimation']";
	
	String promptMsg = "//div[@class='modal-dialog']//p";
	
	String clientName = Suffix+Prefix.replace("arg", "ClientName");
	String projectWave = Suffix+Prefix.replace("arg", "ProjectWaveID");
	String seasonYear = Suffix+Prefix.replace("arg", "SeasonYear");
	String serviceLine = Suffix+Prefix.replace("arg", "ProjectServiceLineID");
	String onOffShore = Suffix+Prefix.replace("arg", "OnOffShore");
	
	
	/*String noOfDays = "//label[text()='No. of Days :']/../following-sibling::div[1]/input";
	
	String noOfContacts = "//label[text()='No. of Contacts :']/../following-sibling::div[1]/input";*/
	
	
	/*############################################ methods ###################################################*/
	
	public String[] verifyAllFieldsDispalyed() {
		$(modal+"//label[1]").withTimeoutOf(Duration.ofMillis(ProjectVariables.MID_TIME_OUT)).waitUntilVisible();
		return oSeleniumUtils.get_All_Text_from_Locator(fieldLabel);
	}
	
	public String populateFields(String options) throws Exception {
		String[] sections = options.split(",");	
		String str = "";
		for (int i = 0; i < sections.length; i++){			
			String[] field = sections[i].split(":");
			SeleniumUtils.defaultWait(300);
			switch(field[0].trim()){
			case "Client ID / Name":
			case "ClientID":
			case "ClientName":
				SeleniumUtils.defaultWait(300);
				oSeleniumUtils.click_given_WebElement(clientName);
				field[1] = field[1].toLowerCase().contains("random")?oSeleniumUtils.getRandomOptionFromBootStrap(clientName):field[1];				
				oSeleniumUtils.enter_given_Text_Element(clientName+"//input",field[1]);
				$("//ng-dropdown-panel/div").withTimeoutOf(Duration.ofMillis(ProjectVariables.MAX_THREAD_WAIT)).waitUntilVisible();
				SeleniumUtils.defaultWait(1000);
				$(clientName+"//input").sendKeys(Keys.ENTER);
				SeleniumUtils.setSessionVaribale("ClientID", field[1].replaceAll("[^0-9]", ""));
				break;
			case "MasterAccountID":
			case "MasterAccountName":
			case "Master Account ID / Name":				
				field[1] = field[1].toLowerCase().contains("random")?MasterAccountIDFromDB():field[1];
				oSeleniumUtils.click_given_WebElement(masterAccountID);
				oSeleniumUtils.enter_given_Text_Element("//input[@class='select2-search__field']",field[1]);
				oSeleniumUtils.dynamicWaitForLoadingIcon("//ul/li[text()='Searching…']");
				$("//input[@class='select2-search__field']").sendKeys(Keys.ENTER);
				SeleniumUtils.setSessionVaribale(field[0].trim(), field[1]);
				break;
			case "Project Name":
			case "ProjectName":
				if(field[1].toLowerCase().contains("random"))
					field[1] = "Automation_"+GenericUtils.faker().number().digits(6);
				else
					field[1] = SeleniumUtils.getValueByName(field[1])!=null? SeleniumUtils.getValueByName(field[1]):field[1];				
				oSeleniumUtils.enter_given_Text_Element(projectName,field[1]);
				SeleniumUtils.setSessionVaribale(field[0],field[1]);
				break;
			case "ProjectDescription":
			case "Project Description":
				field[1] = field[1].toLowerCase().contains("random")?"Automation Project Description "+faker.letterify("????? ?????", true):field[1];
				oSeleniumUtils.enter_given_Text_Element(projectDescription,field[1]);
				break;
			case "Project Wave":
			case "ProjectWave":
				oSeleniumUtils.click_given_WebElement(projectWave);
				field[1] = field[1].toLowerCase().contains("random")?oSeleniumUtils.getRandomOptionFromBootStrap(projectWave):field[1];
				$(projectWave+"//input").sendKeys(field[1]);
				SeleniumUtils.defaultWait(500);
				$(projectWave+"//input").sendKeys(Keys.ENTER);				
				break;
			case "SeasonYear":				
				oSeleniumUtils.click_given_WebElement(seasonYear);
				field[1] = field[1].toLowerCase().contains("random")?oSeleniumUtils.getRandomOptionFromBootStrap(seasonYear):field[1];
				$(seasonYear+"//input").sendKeys(field[1]);
				SeleniumUtils.defaultWait(500);
				$(seasonYear+"//input").sendKeys(Keys.ENTER);				
				break;
			case "ServiceLine":				
				oSeleniumUtils.click_given_WebElement(serviceLine);
				field[1] = field[1].toLowerCase().contains("random")?oSeleniumUtils.getRandomOptionFromBootStrap(serviceLine):field[1];
				$(serviceLine+"//input").sendKeys(field[1]);
				SeleniumUtils.defaultWait(500);
				$(serviceLine+"//input").sendKeys(Keys.ENTER);
				break;
			case "OnOffShore":
				oSeleniumUtils.click_given_WebElement(onOffShore);
				field[1] = field[1].toLowerCase().contains("random")?oSeleniumUtils.getRandomOptionFromBootStrap(onOffShore):field[1];
				$(onOffShore+"//input").sendKeys(field[1]);
				SeleniumUtils.defaultWait(500);
				$(onOffShore+"//input").sendKeys(Keys.ENTER);
				break;
			case "Addbutton":break;
			default:
				throw new IllegalArgumentException(sections[i]+" is does not exist, please provide the correct case");					
			}
			SeleniumUtils.setSessionVaribale(field[0].trim(), field[1]);
			str+=field[0].trim()+" => "+field[1]+";";
		}
		if(!options.contains("Addbutton:No"))
			this.add();
		return str;
	}
	
	public boolean verifyFields(String arg1) {
		boolean bln = false;
		String[] sections = arg1.split(",");
		for (int i = 0; i < sections.length; i++){
			switch(sections[i]){
			case "Client ID / Name":
			case "ClientID":
			case "ClientName":
				bln = ClientID();
				break;			
			case "MasterAccountID":
			case "MasterAccountName":
			case "Master Account ID / Name":
				bln = MasterAccountID();
				break;				
			case "Project Name":
			case "ProjectName":
				bln = ProjectName("Project Name");
				break;
			case "ProjectDescription":
			case "Project Description":
				bln = ProjectDescription();
				break;
			case "Project Wave":
			case "ProjectWave":
				bln = ProjectWave();
				break;			
			case "SeasonYear":
				bln = SeasonYear();
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
				//bln = targetPercentage();
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
			default:
				throw new IllegalArgumentException(sections[i]+" is does not exist, please provide the correct case");
			}
		}
		return bln; 
	}	

	protected boolean ClientID(){
		String str= "Client ID / Name";
		Prefix = Prefix.replace("arg", "ClientName");
		
		// Verifying client id displayed along with mandatory
		Assert.assertTrue(str+"' not displayed",oSeleniumUtils.element_displayed(Suffix+Prefix));
		Assert.assertTrue(str+" is not mandatory",oSeleniumUtils.element_displayed(Suffix+Mandatory_Symbol.replace("arg", str)));
		System.out.println(str+" is mandatory");
		
		// validating default text
		String text = oSeleniumUtils.get_Text_From_WebElement(Suffix+Prefix+"/div/div/div");
		Assert.assertTrue("'Select Client' not populated by default",text.contains("Select Client"));
		System.out.println("'Select Client' populated by default");		
		
		// validate mandatory field 
		oSeleniumUtils.click_given_WebElement(add);
		boolean bln = oSeleniumUtils.element_displayed(Mandatory_Msg.replace("arg", str));
		Assert.assertTrue("'"+str+" is required' message not displayed",bln);
		System.out.println("'"+str+" is required' message  displayed");
		
		oSeleniumUtils.click_given_WebElement(Suffix+Prefix);		
		
		// Validate options are in the format of <Client id> - <client name>
		String[] values = oSeleniumUtils.get_All_Text_from_Locator(Options);				
		for(String v : values)
			Assert.assertTrue("'Client ID' does not have options in the form of <ID> - <Name> for "+v,GenericUtils.isInteger(v.split("-")[0].trim()));		
		System.out.println("'Client ID' have options in the form of <ID> - <Name>");
		
		//should be able to search with Id/name/both
		String[] list = "0-Hu".split("-");
		for(String l : list){
			oSeleniumUtils.enter_given_Text_Element(Suffix+Prefix+"//input",l);
			values = oSeleniumUtils.get_All_Text_from_Locator(Options);
			for(String v : values){
				Assert.assertTrue("",v.contains(l));
				System.out.println("User is able to search with => "+l);
			}
		}
		return true;
	}
	
	protected boolean MasterAccountID(){
		String str= "Master Account ID / Name";	
		
		// Verifying Master Account ID displayed along with mandatory
		Assert.assertTrue(str+"' not displayed",oSeleniumUtils.element_displayed(masterAccountID));
		Assert.assertTrue(str+" is not mandatory",oSeleniumUtils.element_displayed(Suffix+Mandatory_Symbol.replace("arg", str)));
		System.out.println("'"+str+"' * symbol displayed");
		
		// validating default text
		String text = oSeleniumUtils.get_Text_From_WebElement(masterAccountID+"//span[text()]");
		Assert.assertTrue("'Select Master Account' not populated by default",text.contains("Select Master Account"));
		System.out.println("'Select Master Account' not populated by default");
		
		// validate mandatory field 
		oSeleniumUtils.click_given_WebElement(add);
		boolean bln = oSeleniumUtils.element_displayed(Mandatory_Msg.replace("arg", str));
		Assert.assertTrue("'Master Account ID / Name is required' message not displayed",bln);
		System.out.println("'Master Account ID / Name is required' message  displayed");
		
		// Select Client Name first then only Master Id values will be displayed
		String xpath = "//ng-select[@formcontrolname='ClientName'][contains(@class,'font ng-select ng-select-single ng-select-searchable ng-select-clearable ng-')]";
		oSeleniumUtils.click_given_WebElement(Suffix+xpath);
		oSeleniumUtils.enter_given_Text_Element(Suffix+xpath+"//input","0");
		$(Suffix+xpath+"//input").sendKeys(Keys.ENTER);
		// Validate options are in the format of <Master id> - <client name>
		oSeleniumUtils.click_given_WebElement(Suffix+Prefix);	
		oSeleniumUtils.enter_given_Text_Element("//input[@class='select2-search__field']", "1");
		oSeleniumUtils.dynamicWaitForLoadingIcon("//ul/li[text()='Searching…']");
		String[] values = oSeleniumUtils.get_All_Text_from_Locator("//span/ul/li[text()]");									
		for(String v : values){
			if(!v.contains("Loading"))
				Assert.assertTrue("'"+str+"' does not have options in the form of <ID> - <Name> for "+v,v.split("-").length>1);
		}					
		System.out.println("'"+str+"' have options in the form of <ID> - <Name>");		
		return bln;
	}
	
	protected boolean ProjectName(String fieldname){
		String str= "Project Name";		
		String expectedLeght = fieldname.contains("Project Name")?"30":"4";
		
		Assert.assertTrue(str+"' not displayed",oSeleniumUtils.element_displayed(projectName));
		Assert.assertTrue(str+" is not mandatory",oSeleniumUtils.element_displayed(Suffix+Mandatory_Symbol.replace("arg", str)));
		System.out.println("'"+str+"' * symbol displayed");
		
		String length = $(projectName).getAttribute("maxlength");
		Assert.assertTrue(str+" max lenght not matched"+length,length.equals(expectedLeght));
		System.out.println("'"+str+"' Max lenght is "+length);		
		
		// validate mandatory field 
		oSeleniumUtils.click_given_WebElement(add);
		boolean bln = oSeleniumUtils.element_displayed(Mandatory_Msg.replace("arg", str));
		Assert.assertTrue("'"+str+"'  is required' message not displayed",bln);
		System.out.println("'"+str+"' is required' message  displayed");
		
		oSeleniumUtils.enter_given_Text_Element(projectName, "Sample Test Name");
		Assert.assertTrue($(projectName).getAttribute("value").equals("Sample Test Name"));
		System.out.println("User is able accept characters in '"+str+"'");		
		return true;
	}
	
	protected boolean ProjectDescription(){
		String str= "Project Description";		
		
		Assert.assertTrue(str+"' not displayed",oSeleniumUtils.element_displayed(projectDescription));
		System.out.println("'"+str+"' is textarea");
		Assert.assertTrue(str+" is not mandatory",oSeleniumUtils.element_displayed(Suffix+Mandatory_Symbol.replace("arg", str)));
		System.out.println("'"+str+"' * symbol displayed");
		
		String length = $(projectDescription).getAttribute("maxlength");
		Assert.assertTrue(str+" max lenght not matched "+length,length.equals("255"));
		System.out.println("'"+str+"' Max lenght is "+length);
		
		// validate mandatory field 
		oSeleniumUtils.click_given_WebElement(add);
		boolean bln = oSeleniumUtils.element_displayed(Mandatory_Msg.replace("arg", str));
		Assert.assertTrue("'"+str+"'  is required' message not displayed",bln);
		System.out.println("'"+str+"' is required' message  displayed");
		
		oSeleniumUtils.enter_given_Text_Element(projectDescription, "Sample Test Description");
		Assert.assertTrue($(projectDescription).getAttribute("value").equals("Sample Test Description"));
		System.out.println("User is able accept characters in '"+str+"'");		
		
		// Remaining lenght of characters not displaying 
		return true;
	}
	
	protected boolean ProjectWave(){		
		String str= "Project Wave";
		Prefix = Prefix.replace("arg", "ProjectWaveID");
		
		// Verifying Projectwave displayed along with mandatory
		Assert.assertTrue(str+"' not displayed",oSeleniumUtils.element_displayed(Suffix+Prefix));
		Assert.assertTrue(str+" is not mandatory",oSeleniumUtils.element_displayed(Suffix+Mandatory_Symbol.replace("arg", str)));
		System.out.println(str+" * symbol displayed");			
		
		// validating default text
		String text = oSeleniumUtils.get_Text_From_WebElement(Suffix+Prefix+"/div/div/div");
		Assert.assertTrue("'Select Project Wave' not populated by default",text.contains("Select Project Wave"));
		System.out.println("'Select Project Wave' populated by default");
		
		// validate mandatory field 
		oSeleniumUtils.click_given_WebElement(add);
		boolean bln = oSeleniumUtils.element_displayed(Mandatory_Msg.replace("arg", str));
		Assert.assertTrue("'"+str+"'  is required' message not displayed",bln);
		System.out.println("'"+str+"' is required' message  displayed");
		
		// Validate options 
		oSeleniumUtils.click_given_WebElement(Suffix+Prefix);		
				
		// user should be able to select 1 option at a time
		String[] values = oSeleniumUtils.get_All_Text_from_Locator(Options);
		for(String v : values){
			$(Suffix+Prefix+"//input").sendKeys(v);
			$(Suffix+Prefix+"//input").sendKeys(Keys.ENTER);
			SeleniumUtils.defaultWait(1000);
			bln = oSeleniumUtils.element_displayed(Suffix+Prefix+"//span[normalize-space(text())='"+v+"']");
			Assert.assertTrue("'"+v+"' not selected",bln);
			System.out.println("'"+v+"' is selected");
		}
		System.out.println("'"+str+"' User can select only 1 value from the dropdown");
		$(Suffix+Prefix+"//span[@class='ng-clear']").withTimeoutOf(Duration.ofMillis(ProjectVariables.MID_TIME_OUT)).waitUntilClickable();
		oSeleniumUtils.moveToElement_and_click(Suffix+Prefix+"//span[@class='ng-clear']");
		
		// Validate with unknown option 
		oSeleniumUtils.enter_given_Text_Element(Suffix+Prefix+"//input","Sample Option");
		bln = oSeleniumUtils.element_displayed(Suffix+Prefix+"//div[text()='No items found']");
		Assert.assertTrue("'"+str+"' ",bln);
		System.out.println("'No items found' when I type 'Sample Option'");		
		return true;
	}
	
	protected boolean SeasonYear(){
		String labelName= "Season Year";
		Prefix = Prefix.replace("arg", "SeasonYear");		
		
		// Verifying Season Year displayed along with mandatory
		Assert.assertTrue(labelName+"' not displayed",oSeleniumUtils.element_displayed(Suffix+Prefix));
		Assert.assertTrue(labelName+" is not mandatory",oSeleniumUtils.element_displayed(Suffix+Mandatory_Symbol.replace("arg", labelName)));
		System.out.println(labelName+" * symbol displayed");
		
		//by default no value should be selected
		Assert.assertFalse(oSeleniumUtils.element_displayed(Suffix+Prefix+"//div/span[contains(@class,'ng-value')][2]"));
		
		// validating default text
		String text = oSeleniumUtils.get_Text_From_WebElement(Suffix+Prefix+"/div/div/div");
		Assert.assertTrue("'Select Season Year' not populated by default",text.contains("Select Season Year"));
		System.out.println("'Select Season Year' populated by default");
		
		// validate mandatory field 
		oSeleniumUtils.click_given_WebElement(add);
		boolean bln = oSeleniumUtils.element_displayed(Mandatory_Msg.replace("arg", labelName));
		Assert.assertTrue("'"+labelName+"'  is required' message not displayed",bln);
		System.out.println("'"+labelName+"' is required' message  displayed");
		
		// Validate options 
		oSeleniumUtils.click_given_WebElement(Suffix+Prefix);		
				
		int currentyear = Integer.parseInt(GenericUtils.get_Current_Date("yyyy"));		
		String[] values = oSeleniumUtils.get_All_Text_from_Locator(Options);
		for(String v : values){
			int val = Integer.parseInt(v);	
			if(!(val >= currentyear-5 && val <= currentyear+5))
				Assert.assertFalse(v+" is not in between +5/-5 years",false);			
		}
		System.out.println(labelName+" values in b/w "+String.valueOf(currentyear-5)+" and "+String.valueOf(currentyear+5)+" years");		
		return true;
	}
	
	protected boolean ServiceLine(){
		String labelName= "Service Line";
		Prefix = Prefix.replace("arg", "ProjectServiceLineID");
		
		//by default no value should be selected
		Assert.assertFalse("By default value selected",oSeleniumUtils.element_displayed(Suffix+Prefix+"//div/span[contains(@class,'ng-value')][2]"));
		System.out.println("By default no value selected");
		this.dropDownfieldValidation(labelName,Prefix,"Select Service Line",true);		
		
		// Validate options 
		oSeleniumUtils.click_given_WebElement(Suffix+Prefix);
		System.out.println("options are : ");
		oSeleniumUtils.get_All_Text_from_Locator(Options);
		return true;
	}
	
	protected boolean onOffShore() {
		String labelName= "On / Offshore";
		Prefix = Prefix.replace("arg", "OnOffShore");
		boolean bln = false;
		//by default no value should be selected
		Assert.assertFalse("By default value selected",oSeleniumUtils.element_displayed(Suffix+Prefix+"//div/span[contains(@class,'ng-value')][2]"));
		System.out.println("By default no value selected");
		this.dropDownfieldValidation(labelName,Prefix,"Select On / Offshore",true);		
		
		// Validate options 
		oSeleniumUtils.click_given_WebElement(Suffix+Prefix);
		System.out.println("options are : ");
		String[] values = oSeleniumUtils.get_All_Text_from_Locator(Options);
		for(String v : values){
			oSeleniumUtils.moveToElement_and_click(Options+"[text()='"+v+"']");
			SeleniumUtils.defaultWait(1000);
			bln = oSeleniumUtils.element_displayed(Suffix+Prefix+"//span[normalize-space(text())='"+v+"']");
			Assert.assertTrue("'"+v+"' not selected",bln);
			System.out.println("'"+v+"' is selected");
			oSeleniumUtils.click_given_WebElement(Suffix+Prefix);
		}
		System.out.println("'"+labelName+"' User can select only 1 value from the dropdown");
		return true;
	}
	
	protected boolean clientServicesRep(){
		String labelName= "Client Services Rep. :";
		Prefix = "//input[@id='clientServicesRep']";
		
		this.inputfieldValidation(labelName, Prefix, "100", false);
		
		oSeleniumUtils.enter_given_Text_Element(Suffix+Prefix, "Sample Test 123");
		Assert.assertTrue($(Suffix+Prefix).getAttribute("value").equals("Sample Test 123"));
		System.out.println("User is able accept characters and nubers in '"+labelName+"'");
		return true;
	}
	
	protected boolean slaFirstContactInterval(){
		String labelName= "First Contact Interval (SLA in Days) : ";
		Prefix = "//input[@id='slaFirstContactInterval']";
		
		this.inputfieldValidation(labelName, Prefix, "4", false);
		
		oSeleniumUtils.enter_given_Text_Element(Suffix+Prefix, "Sample Test 123");
		Assert.assertTrue($(Suffix+Prefix).getAttribute("value").equals("Sample Test 123"));
		System.out.println("User is able accept characters and nubers in '"+labelName+"'");
		return true;
	}
	
	protected boolean targetPercentage(){
		String labelName= "Target Percentage :";
		Prefix = "//input[@id='number']";		
		this.inputfieldValidation(labelName, Prefix, "5", false);		
		Assert.assertTrue($(Suffix+Prefix).getAttribute("placeholder").equals("Percentage (0.00 to 100)"));		
		oSeleniumUtils.enter_given_Text_Element(Suffix+Prefix, "101");
		this.add();
		boolean bln = oSeleniumUtils.element_displayed(HomePage.Div_With_Exact_Text.replace("arg", "Target % must be between 0.00 and 100 (upto two decimals)"));
		Assert.assertTrue("Target % must be between 0.00 and 100 (upto two decimals) is not displayed" , bln);
		System.out.println("'Target % must be between 0.00 and 100 (upto two decimals)' is displayed for "+labelName+"'");
		oSeleniumUtils.enter_given_Text_Element(Suffix+Prefix, "0.01");
		this.add();
		bln = oSeleniumUtils.element_displayed(HomePage.Div_With_Exact_Text.replace("arg", "Target % must be between 0.00 and 100 (upto two decimals)"));
		Assert.assertFalse("Target % must be between 0.00 and 100 (upto two decimals) is dispalyed when I enter 0.01",bln);
		//pedning db validation	because we are not adding project
		return true;
	}
	
	protected boolean implementationContact(){
		String labelName= "Implementation Contact :";
		Prefix = "//input[@id='implementationContact']";		
		this.inputfieldValidation(labelName, Prefix, "100", false);
		
		oSeleniumUtils.enter_given_Text_Element(Suffix+Prefix, "Sample Test 123");
		Assert.assertTrue($(Suffix+Prefix).getAttribute("value").equals("Sample Test 123"));
		System.out.println("User is able accept characters and nubers in '"+labelName+"'");
		return true;
	}
	
	public boolean projectStartDate(String dateType){
		String labelName= "";		
		if(dateType=="projectStartDate"){
			labelName= "Project Start Date (SLA) :";
			Prefix = "//input[@id='projectStartDate']";
		}
		else{
			labelName= "Project End Date (SLA) :";
			Prefix = "//input[@id='projectEndDate']";
		}
		String placeHolder = $(Suffix+Prefix).getAttribute("placeholder");
		Assert.assertTrue(placeHolder.equals("Select Date"));	
		System.out.println(labelName+" Place holder is '"+placeHolder+"'");
		this.inputfieldValidation(labelName,Prefix,"10",false);
		oSeleniumUtils.click_given_WebElement(Suffix+Prefix);
		Assert.assertTrue("Date Picker Not displayed",oSeleniumUtils.element_displayed(datePicker_Prefix));
		System.out.println(labelName+"is a date picker");
		
		// Verify Invalid date
		oSeleniumUtils.enter_given_Text_Element(Suffix+Prefix, "02/30/2020");
		Assert.assertTrue("Invalid Date Enter message Not displayed",oSeleniumUtils.element_displayed(Prefix+"/../..//div[text()='Invalid date entered']"));
		System.out.println(labelName+" 'invalid date' message is dispayed");
		System.out.println(labelName+" is able to enter date");
		
		// Verify end date is greater than start date 
		String currentDate = GenericUtils.get_Current_Date("MM/dd/yyyy");
		oSeleniumUtils.enter_given_Text_Element("//input[@id='projectStartDate']", currentDate);
		oSeleniumUtils.enter_given_Text_Element("//input[@id='projectEndDate']", GenericUtils.Add_days_to_given_date(currentDate,-1));
		this.add();
		String txt = $("//input[@id='projectEndDate']/../div/div").getText();
		Assert.assertTrue(txt+" displayed",txt.contains("Project End Date is earlier than the Project Start Date"));
		System.out.println(txt + " is displayed");
		return true;
	}
	
	protected boolean additionalContactsSLAType() {
		String labelName= "Additional Contacts SLA Type : ";
		Prefix = Prefix.replace("arg", "AdditionalContactsSLAType");
		boolean bln = false;
		//by default no value should be selected
		Assert.assertFalse("By default value selected",oSeleniumUtils.element_displayed(Suffix+Prefix+"//div/span[contains(@class,'ng-value')][2]"));
		System.out.println("By default no value selected");
		this.dropDownfieldValidation(labelName,Prefix,"Select SLA Type",false);		
		
		// Validate options 
		oSeleniumUtils.click_given_WebElement(Suffix+Prefix);
		System.out.println("options are : ");
		String[] values = oSeleniumUtils.get_All_Text_from_Locator(Options);
		for(String v : values){
			oSeleniumUtils.moveToElement_and_click(Options+"[text()='"+v+"']");
			SeleniumUtils.defaultWait(1000);
			bln = oSeleniumUtils.element_displayed(Suffix+Prefix+"//span[normalize-space(text())='"+v+"']");				
			Assert.assertTrue("'"+v+"' not selected",bln);
			System.out.println("'"+v+"' is selected");
			oSeleniumUtils.click_given_WebElement(Suffix+Prefix);
		}
		System.out.println("'"+labelName+"' User can select only 1 value from the dropdown");
		return true;
	}
	
	protected boolean additionalContactsInput(String option) {
		String labelName = "";
		Prefix = Prefix.replace("arg", "AdditionalContactsSLAType");
		SeleniumUtils.defaultWait(1000);
		oSeleniumUtils.click_given_WebElement(Suffix+Prefix);
		oSeleniumUtils.moveToElement_and_click(Options+"[text()='"+option+"']");
		System.out.println("Validation for Option '"+option+"'");
		if(option.contains("EveryXDays")){			
			labelName= "Number of Days";
			Prefix = "//input[@formcontrolname='XDays' and @id='number']";		
		}
		else if(option.contains("XTimesDuringProject")){
			labelName= "Number of Contacts";
			Prefix = "//input[@formcontrolname='XNumContacts' and @id='number']";			
		}
		Assert.assertTrue(labelName+"' not displayed",oSeleniumUtils.element_displayed(Suffix+Prefix));
		String length = $(Suffix+Prefix).getAttribute("maxlength");
		Assert.assertTrue(labelName+" max lenght not matched"+length,length.equals("4"));
		System.out.println("'"+labelName+"' Max lenght is "+length);
		oSeleniumUtils.click_given_WebElement(add);
		boolean bln = oSeleniumUtils.element_displayed(Mandatory_Msg.replace("arg", labelName));
		Assert.assertTrue("'"+labelName+"'  is required' message not displayed",bln);
		System.out.println("'"+labelName+"' is required' message  displayed");
		oSeleniumUtils.enter_given_Text_Element(Prefix, "123Test");
		Assert.assertTrue($(Prefix).getAttribute("value").equals("123"));
		System.out.println("User is able accept numbers in '"+labelName+"'");		
		return true;
	}
	
	protected boolean FirstContactInterval(String lable) {
		String labelName= lable.equals("FirstContactInterval")?"First Contact Interval (SLA in Days) :":"First Contact Warning Interval (SLA in Days) :";
		Prefix = lable.equals("FirstContactInterval")?"//input[@id='slaFirstContactInterval']":"//input[@id='slaFirstContactWarningInterval']";
		
		this.inputfieldValidation(labelName, Prefix, "4", false);
		
		oSeleniumUtils.enter_given_Text_Element(Suffix+Prefix, "@ 123");
		Assert.assertTrue($(Suffix+Prefix).getAttribute("value").equals("123"));
		System.out.println("User is able accept only nubers in '"+labelName+"'");
		return true;
	}
	
	protected boolean estimatedEndDate(String lable){
		String labelName= lable.equals("estimatedEndDate")?"Estimated End Date :":"Target Fax Date :";
		Prefix = lable.equals("estimatedEndDate")?"//input[@id='estimatedEndDate']":"//input[@id='targetFaxDate']";
		
		String placeHolder = $(Suffix+Prefix).getAttribute("placeholder");
		Assert.assertTrue(placeHolder.equals("Select Date"));	
		System.out.println(labelName+" Place holder is '"+placeHolder+"'");
		this.inputfieldValidation(labelName,Prefix,"10",false);
		oSeleniumUtils.click_given_WebElement(Suffix+Prefix);
		Assert.assertTrue("Date Picker Not displayed",oSeleniumUtils.element_displayed(datePicker_Prefix));
		System.out.println(labelName+"is a date picker");
		
		// Verify Invalid date
		oSeleniumUtils.enter_given_Text_Element(Suffix+Prefix, "02/30/2020");
		this.add();
		Assert.assertTrue("Invalid Date Enter message Not displayed",oSeleniumUtils.element_displayed(Prefix+"/../..//div[text()='Invalid date entered']"));
		System.out.println(labelName+" 'invalid date' message is dispayed");
		System.out.println(labelName+" is able to enter date");		
		
		return true;
	}
	
	protected void dropDownfieldValidation(String str,String Prefix,String defaultValue,boolean mandatory){			
		// Displayed
		Assert.assertTrue(str+"' not displayed",oSeleniumUtils.element_displayed(Suffix+Prefix));				
		
		// validating default text
		if(defaultValue!=null){
			String text = oSeleniumUtils.get_Text_From_WebElement(Suffix+Prefix+"/div/div/div");
			Assert.assertTrue("'"+defaultValue+"' not populated by default",text.contains(defaultValue));
			System.out.println("'"+defaultValue+"' populated by default");
		}		
		
		// validate mandatory field 
		if(mandatory){
			Assert.assertTrue(str+" is not mandatory",oSeleniumUtils.element_displayed(Suffix+Mandatory_Symbol.replace("arg", str)));
			System.out.println(str+" * symbol displayed");
			oSeleniumUtils.click_given_WebElement(add);
			boolean bln = oSeleniumUtils.element_displayed(Mandatory_Msg.replace("arg", str));
			Assert.assertTrue("'"+str+"'  is required' message not displayed",bln);
			System.out.println("'"+str+"' is required' message  displayed");
		}
		else{
			Assert.assertFalse(str+" is optional",oSeleniumUtils.element_displayed(Suffix+Mandatory_Symbol.replace("arg", str)));
			System.out.println(str+" is optional");
			oSeleniumUtils.click_given_WebElement(add);
			boolean bln = oSeleniumUtils.element_displayed(Mandatory_Msg.replace("arg", str));
			Assert.assertFalse("'"+str+"'  is required' message displayed",bln);
			System.out.println("'"+str+"' is optional' message  displayed");
		}
	}
	
	protected void inputfieldValidation(String str,String Prefix,String maxlength,boolean mandatory ){
		// Displayed
		Assert.assertTrue(str+"' not displayed",oSeleniumUtils.element_displayed(Suffix+Prefix));
		
		// Max length
		String length = $(Suffix+Prefix).getAttribute("maxlength");
		Assert.assertTrue(str+" max lenght not matched"+length,length.equals(maxlength));
		System.out.println("'"+str+"' Max lenght is "+length);	
		
		// validate mandatory/optional field 
		if(mandatory){
			Assert.assertTrue(str+" is not mandatory",oSeleniumUtils.element_displayed(Suffix+Mandatory_Symbol.replace("arg", str)));
			System.out.println(str+" * symbol displayed");
			oSeleniumUtils.click_given_WebElement(add);
			boolean bln = oSeleniumUtils.element_displayed(Mandatory_Msg.replace("arg", str));
			Assert.assertTrue("'"+str+"'  is required' message not displayed",bln);
			System.out.println("'"+str+"' is required' message  displayed");
		}
		else{
			Assert.assertFalse(str+" is optional",oSeleniumUtils.element_displayed(Suffix+Mandatory_Symbol.replace("arg", str)));
			System.out.println(str+" is optional");
			oSeleniumUtils.click_given_WebElement(add);
			boolean bln = oSeleniumUtils.element_displayed(Mandatory_Msg.replace("arg", str));
			Assert.assertFalse("'"+str+"'  is required' message displayed",bln);
			System.out.println("'"+str+"' is required' message  not displayed");
		}
	}
	
	public void add(){
		oSeleniumUtils.click_given_WebElement(add);
		System.out.println("Add Clicked Successfully");
	}

	public String MasterAccountIDFromDB() throws Exception{		
		String sqlQuery = DBQueries.getRandomMasterAccountID();
		String[] values = oDBUtils.executeSQLQuery_GetMap(sqlQuery).get("MasterAccountName").stream().toArray(String[]::new);
		String MasterAccountID = values[GenericUtils.getRandomNumberBetweenRange(0, values.length-1)];
		return MasterAccountID;
	}

	public String[] getErroMessages() {		
		return oSeleniumUtils.get_All_Text_from_Locator("//div[contains(text(),'is required')]");
	}

	public void clickCancel() {
		SeleniumUtils.defaultWait(1000);
		oSeleniumUtils.click_given_WebElement(cancel);		
	}
	
	public String verifyPopup(String arg1){
		String message = oSeleniumUtils.get_Text_From_WebElement(promptMsg);		
		oSeleniumUtils.click_given_WebElement("//div[@class='modal-dialog']//div[3]//button[text()='"+arg1+"']");
		return message;
	}
}
