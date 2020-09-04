package project.pageobjects;


import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import project.utilities.GenericUtils;
import project.utilities.ProjectVariables;
import project.utilities.SeleniumUtils;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;

public class AccountConfig_AuditHistoryPage extends PageObject{

	SeleniumUtils oSeleniumUtils;
	
	/*############################################ WebElements ###############################################*/
	
	@FindBy(xpath = "//button[@ng-click='reset()']")
	private WebElementFacade search;
	
	@FindBy(css = "#ClientId input")
	private WebElementFacade clientId;
	
	@FindBy(id = "select2-id_AuditMasterAccountName-container")
	private WebElementFacade masterAccountid;
	
	
	@FindBy(xpath = "(//button[text()='Details'])[1]")
	private WebElementFacade firstDetailsLink;
	
	/*############################################ xpath ###############################################*/
	
	String startDate = "//input[@formcontrolname='AccountStartDate']";
	
	String endDate = "//input[@id='AccountEndDate']";
	
	String headersXpath = "(//table[@id='DataTables_Table_0']/thead/tr/th)";
	
	String close = "//button[@class='close pull-right']";
	
	String hyphenXpath = "//div[@role='dialog']//tr[not(td[text()='Office ID' or (text()='Append Mode')])]/td[2]";
	
	/*############################################ methods ###################################################*/
	
	
	public void populateFields(String options) {		
		String[] sections = options.split(",");		
		SeleniumUtils.defaultWait(300);
		String cDate = GenericUtils.get_Current_Date("MM/dd/yyyy");
		for (int i = 0; i < sections.length; i++){			
			String[] field = sections[i].split(":");
			SeleniumUtils.defaultWait(300);
			switch(field[0].trim()){
			case "Start Date":
			case "StartDate":
				String start = GenericUtils.Add_days_to_given_date(cDate,Integer.parseInt(field[1]));
				oSeleniumUtils.enter_given_Text_Element(startDate, start);
				System.out.println("Start Date Entered "+start);
				break;
			case "End Date":
			case "EndDate":
				String end = GenericUtils.Add_days_to_given_date(cDate,Integer.parseInt(field[1]));
				oSeleniumUtils.enter_given_Text_Element(endDate, end);
				System.out.println("End Date Entered "+end);
				break;
			case "Client ID":
			case "Client Name":
				oSeleniumUtils.click_given_WebElement(clientId);
				oSeleniumUtils.enter_given_Text_Element(clientId,field[1]);
				$("//ng-dropdown-panel/div").withTimeoutOf(Duration.ofMillis(ProjectVariables.MAX_THREAD_WAIT)).waitUntilVisible();
				SeleniumUtils.defaultWait(2000);
				clientId.sendKeys(Keys.ENTER);
				break;			
			case "Master Account ID":
				oSeleniumUtils.click_given_WebElement("//*[@id='ProjectMasterAccount']");
				oSeleniumUtils.enter_given_Text_Element("//input[@class='select2-search__field']",field[1]);
				oSeleniumUtils.dynamicWaitForLoadingIcon("//ul/li[text()='Searching…']");
				$("//input[@class='select2-search__field']").sendKeys(Keys.ENTER);
				break;
			default:
				throw new IllegalArgumentException(sections[i]+" is does not exist, please provide the correct case");					
			}			
		}
		search();
	}
	
	public void search(){
		oSeleniumUtils.click_given_WebElement(search);
	}
	
	public void clickDetails(String action){
		int index = oSeleniumUtils.getColumnIndexByColumnName(headersXpath, "Action");
		List<WebElement> elements = getDriver().findElements(By.xpath("//tbody/tr/td["+index+"]"));		
		for (int i = 0; i < elements.size(); i++){				
			if(action.trim()==elements.get(i).getText().trim())
				index = i+1;
		}
		oSeleniumUtils.click_given_WebElement("//tbody/tr["+index+"]/td["+oSeleniumUtils.getColumnIndexByColumnName(headersXpath, "Changed Info")+"]/button");
	}
	
	public void clickDetails(){
		oSeleniumUtils.click_given_WebElement(firstDetailsLink);
	}

	public String[] verifyPopupDetails(String arg1) {
		String[] names = arg1.split(",");
		String xpath = "";
		boolean verify = true;
		boolean found = false;
		for(String name : names){
			switch(name.trim()){
			case "Account ID":
				xpath = "//div[@class='PopUpInfoHeader']";
				break;
			case "Old Info":
				xpath = "//th[text()='Old Info']";
				break;
			case "New Info":
				xpath = "//th[text()='New Info']";
				break;
			case "Label":
				xpath = "//th[text()='Label']";
				break;
			case "Changed Info":
				xpath = "//h5[@id='exampleModalLabel']";
				break;
			case "All Labels":
				List<WebElement> elements = getDriver().findElements(By.xpath("//div[@class='modal-dialog modal-lg']//tbody/tr/td[1]"));
				List<String> actual = new ArrayList<String>();
				List<String> expectedList = Arrays.asList("Client ID / Name", "Account ID / Name", "Request Status","Account Mgr. Name"
						,"Phone Number","Email","Address Line 1","Address Line 2","State","City","Zip Code","Date of Service","Request Type",
						"Project Type","Work Flow State ID","Office ID","Append Mode","Instructions","DDOS");
				for(WebElement e : elements)
					actual.add(e.getText().trim());	
				found = actual.equals(expectedList);
				Assert.assertTrue(name+ " is not displayed",found);
				
				String[] temp = actual.stream().toArray(String[]::new);
				names = Arrays.copyOf(temp,temp.length,String[].class);				
				verify = false;
				break;
			}
			if(verify){
				found = oSeleniumUtils.element_displayed(xpath);
				Assert.assertTrue(name+ " is not displayed",found);
			}	
		}		
		return names;
	}
	
	public void closeChangeInfo(){
		SeleniumUtils.defaultWait(3000);
		oSeleniumUtils.click_given_WebElement(close);
	}

	public void verifyChangeInfoTable(String action) {
		switch(action){
		case "Edit":
			
			break;
		case "Add":
			SeleniumUtils.defaultWait(2000);
			List<WebElement> elements = getDriver().findElements(By.xpath("hyphenXpath"));
			for(WebElement e : elements)
				Assert.assertTrue(e.getText().contains("-"));
			System.out.println("excluding 'Office ID' and 'Append Mode' every section have hyphen");
			break;
		}
	}
	
	
}
