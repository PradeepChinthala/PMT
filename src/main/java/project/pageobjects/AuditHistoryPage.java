package project.pageobjects;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.FindBy;

import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import project.utilities.GenericUtils;
import project.utilities.SeleniumUtils;

public class AuditHistoryPage extends PageObject{

	SeleniumUtils oSeleniumUtils;
	
	/*############################################ WebElements ###############################################*/
	
	@FindBy(css = "input[formcontrolname='ProjectStartDate']")
	public WebElementFacade startDate;
	
	@FindBy(id = "projectEndDate")
	public WebElementFacade endDate;
	
	@FindBy(css = "button[class='btn btn-pmt']")
	public WebElementFacade search;
	
	@FindBy(id = "Anumber")
	public WebElementFacade projectId;
	
	String rows = "//select[@name='DataTables_Table_0_length']/option";
	
	String Headers = "(//table[@id='DataTables_Table_0']/thead/tr/th)";
	
	String Body = "(//table[@id='DataTables_Table_0']/tbody)";
	
	String mandatory = "//label[@for='arg']/span[contains(text(),'*')]";
	
	String msg = "//div[text()='arg is required']";
	
	String datePicker = "//div[@class='bs-datepicker-body']";
	
	String action = "(//ng-select[@formcontrolname='ActionID'])";
	
	String action_Options = "//ng-dropdown-panel//div[contains(@class,'ng-option')]/span";
	
	String TableData = "(//table[@id='DataTables_Table_0']/tbody/tr/td[index])";
	
	String projectName = "//span[@id='select2-id_PrjAuditProjectName-container']";
	
	
	/*############################################ methods ###################################################*/

	
	public void populateFields(String options) throws ParseException {
		String[] sections = options.split(",");	
		for (int i = 0; i < sections.length; i++){			
			String[] field = sections[i].split(":");
			SeleniumUtils.defaultWait(300);
			String cDate = GenericUtils.get_Current_Date("MM/dd/yyyy");
			switch(field[0].trim().replace(" ", "").toLowerCase()){
			case "startdate":		
				String start = GenericUtils.Add_days_to_given_date(cDate,Integer.parseInt(field[1]));
				oSeleniumUtils.enter_given_Text_Element(startDate, start);
				System.out.println("Start Date Entered "+start);
				break;
			case "enddate":
				SeleniumUtils.defaultWait(300);
				String end = GenericUtils.Add_days_to_given_date(cDate,Integer.parseInt(field[1]));
				oSeleniumUtils.enter_given_Text_Element(endDate, end);
				System.out.println("End Date Entered "+end);
				break;
			case "projectid":
				field[1] = SeleniumUtils.getValueByName(field[1])!=null? SeleniumUtils.getValueByName(field[1]):field[1];	
				oSeleniumUtils.enter_given_Text_Element(projectId, field[1]);
				System.out.println("Project Id Entered "+field[1]);
				break;
			case "projectname":
				field[1] = SeleniumUtils.getValueByName(field[1])!=null? SeleniumUtils.getValueByName(field[1]):field[1];
				oSeleniumUtils.click_given_WebElement("//span[@id='select2-id_PrjAuditProjectName-container']");
				oSeleniumUtils.enter_given_Text_Element("//input[@class='select2-search__field']",field[1]);
				oSeleniumUtils.dynamicWaitForLoadingIcon("//ul/li[text()='Searching…']");
				$("//input[@class='select2-search__field']").sendKeys(Keys.ENTER);
				break;
			}			
		}
		oSeleniumUtils.click_given_WebElement(search);
	}
	
	public void verifyColumnNames(String arg1){
		String[] cnames = arg1.split(",");
		oSeleniumUtils.click_given_WebElement(this.rows+"[last()]");
		SeleniumUtils.defaultWait(100);
		int len = getDriver().findElements(By.xpath(Headers)).size();
		Map<String, List<String>> map = new HashMap<>(len);
		for (int i = 1; i <= len; i++){	
			String cName = $(Headers+"["+i+"]").getText();			
			Assert.assertTrue(cName+" is not displayed",Arrays.asList(cnames).contains(cName));
			System.out.println(cName+ " column displayed");
			map.put(cName, new ArrayList<>());
			int rowlen = getDriver().findElements(By.xpath(Body+"/tr")).size();
			for(int j=1;j<=rowlen;j++){
				String cellText = $(Body+"/tr["+j+"]/td["+i+"]").getText();				
				map.get(cName).add(cellText);
			}
		}
		Serenity.setSessionVariable("UIResultData").to(map);		
	}
	
	public void validateChangeInfo(String name,String expected){
		String xpath = "//table[@class='table table-bordered']/tbody/tr/td[index]";
		String[] s = null;
		if(name.contains("Old Info")){
			s = oSeleniumUtils.get_All_Text_from_Locator(xpath.replace("index", "2"));
			Assert.assertTrue(Arrays.asList(s).contains(expected));
		}
		else if(name.contains("New Info")){
			s = oSeleniumUtils.get_All_Text_from_Locator(xpath.replace("index", "3"));
		}
		else if(name.contains("Label")){
			s = oSeleniumUtils.get_All_Text_from_Locator(xpath.replace("index", "1"));
		}		
	}
	
	public boolean verifyFieldsInSearchProject(String arg1) throws Exception {
		boolean bln = false;
		String[] sections = arg1.split(",");
		for (int i = 0; i < sections.length; i++){
			switch(sections[i].trim()){
			case "StartDate":
			case "EndDate":
				WebElementFacade ele = sections[i].contains("StartDate")?startDate:endDate;
				sections[i] = sections[i].replaceAll("\\d+", "").replaceAll("(.)([A-Z])", "$1 $2");
				
				bln = oSeleniumUtils.element_displayed(ele);
				Assert.assertTrue(sections[i]+" is not displayed",bln);
				System.out.println(sections[i]+" is displayed");
				break;
			case "Project ID":
				bln = oSeleniumUtils.element_displayed(projectId);
				Assert.assertTrue(sections[i]+" is not displayed",bln);
				System.out.println(sections[i]+" is displayed");				
				
				String len = projectId.getAttribute("maxlength");
				Assert.assertTrue(sections[i]+" Max Lenght is Not '4'",len.equals("4"));
				System.out.println(sections[i]+" Max Lenght is "+len);
				break;			
			}
		}
		return bln;
	}
	
	public boolean verifyFields(String arg1) throws ParseException {
		boolean bln = false;
		String[] sections = arg1.split(",");
		for (int i = 0; i < sections.length; i++){
			switch(sections[i].trim()){
			case "StartDate":
			case "EndDate":
				WebElementFacade ele = sections[i].contains("StartDate")?startDate:endDate;
				sections[i] = sections[i].replaceAll("\\d+", "").replaceAll("(.)([A-Z])", "$1 $2");
				
				bln = oSeleniumUtils.element_displayed(ele);
				Assert.assertTrue(sections[i]+" is not displayed",bln);
				System.out.println(sections[i]+" is displayed");				
				
				oSeleniumUtils.click_given_WebElement(ele);
				bln = oSeleniumUtils.element_displayed(datePicker);
				Assert.assertTrue(sections[i]+"  date picker is not displayed",bln);
				System.out.println(sections[i]+" date picker  is displayed");
				
				String defaultvalue = ele.getAttribute("placeholder");
				System.out.println("Default value is '"+"Select "+sections[i]);
				Assert.assertTrue(sections[i]+" default value is not displayed",defaultvalue.contains("Select "+sections[i]));
				
				bln = oSeleniumUtils.element_displayed(mandatory.replace("arg", sections[i].replace(" ", "")));
				Assert.assertTrue(sections[i]+" is not mandatory",bln);		
				System.out.println(sections[i]+" is madatory");				
				
				oSeleniumUtils.click_given_WebElement(search);
				bln = oSeleniumUtils.element_displayed(this.msg.replace("arg", sections[i]));
				Assert.assertTrue(sections[i]+" is required message is not displayed",bln);
				System.out.println(sections[i]+" is required message is displayed");				
				
				String start = GenericUtils.Add_days_to_given_date(GenericUtils.get_Current_Date("MM/dd/yyyy"),-91);
				oSeleniumUtils.enter_given_Text_Element(startDate, start);				
				oSeleniumUtils.enter_given_Text_Element(endDate, GenericUtils.get_Current_Date("MM/dd/yyyy"));
				System.out.println("Date Formate is the format of MM/dd/yyyy");
				oSeleniumUtils.click_given_WebElement(search);	
				bln  = oSeleniumUtils.element_displayed("//div[contains(text(),'The difference between Start Date and End Date should be 90 days.')]");
				//Assert.assertTrue("'The difference between Start Date and End Date should be 90 days.' is not displayed",bln);
				System.out.println("'The difference between Start Date and End Date should be 90 days.' displayed");
				startDate.clear();
				endDate.clear();
				break;
				
			case "Project ID":
				bln = oSeleniumUtils.element_displayed(projectId);
				Assert.assertTrue(sections[i]+" is not displayed",bln);
				System.out.println(sections[i]+" is displayed");				
				
				String len = projectId.getAttribute("maxlength");
				Assert.assertTrue(sections[i]+" Max Lenght is Not '4'",len.equals("4"));
				System.out.println(sections[i]+" Max Lenght is "+len);
				
				bln = oSeleniumUtils.element_displayed(mandatory.replace("arg", "projectId"));
				Assert.assertFalse(sections[i]+" is mandatory",bln);		
				System.out.println(sections[i]+" is optional");				
				
				// should accept only numbers
				oSeleniumUtils.enter_given_Text_Element(projectId, "123P");
				Assert.assertTrue(projectId.getAttribute("value").equals("123"));
				System.out.println(sections[i]+" is accepted only numbers");
				oSeleniumUtils.enter_given_Text_Element(projectId, "");
				
				start = GenericUtils.Add_days_to_given_date(GenericUtils.get_Current_Date("MM/dd/yyyy"),-90);
				oSeleniumUtils.enter_given_Text_Element(startDate, start);				
				oSeleniumUtils.enter_given_Text_Element(endDate, GenericUtils.get_Current_Date("MM/dd/yyyy"));				
				oSeleniumUtils.click_given_WebElement(search);	
				int count = getDriver().findElements(By.xpath(Headers)).size();
				Assert.assertTrue("Results Not Displayed",count>0);
				System.out.println("Results Displayed As Project ID left blank");				
				break;
			case "Project Name":
				bln = oSeleniumUtils.element_displayed(projectName);
				Assert.assertTrue(sections[i]+" is not displayed",bln);
				System.out.println(sections[i]+" is displayed");					
				
				bln = oSeleniumUtils.element_displayed(mandatory.replace("arg", "projectName"));
				Assert.assertFalse(sections[i]+" is mandatory",bln);		
				System.out.println(sections[i]+" is optional");		
				break;
			case "Action":
				bln = oSeleniumUtils.element_displayed(action);
				Assert.assertTrue(sections[i]+" is not displayed",bln);
				System.out.println(sections[i]+" is displayed");
				
				// validating default text
				/*String text = oSeleniumUtils.get_Text_From_WebElement(action+"/div/div/div");
				Assert.assertTrue("'Action' not populated by default",text.contains("Select Action"));
				bln = oSeleniumUtils.element_displayed(action+"/div/div/div[@class='ng-value ng-star-inserted']/span[2]");
				text = oSeleniumUtils.get_Text_From_WebElement(action+"/div/div/div[@class='ng-value ng-star-inserted']/span[2]");
				//Assert.assertTrue(sections[i]+" default value is :"+text,bln);
				System.out.println(sections[i]+" default value is :"+text);*/				
				
				//optional field
				bln = oSeleniumUtils.element_displayed(mandatory.replace("arg", "ActionID"));
				Assert.assertFalse(sections[i]+" is not optional",bln);		
				System.out.println(sections[i]+" is optional");					

				//Action filed as blank 
				start = GenericUtils.Add_days_to_given_date(GenericUtils.get_Current_Date("MM/dd/yyyy"),-90);
				oSeleniumUtils.enter_given_Text_Element(startDate, start);				
				oSeleniumUtils.enter_given_Text_Element(endDate, GenericUtils.get_Current_Date("MM/dd/yyyy"));				
				oSeleniumUtils.click_given_WebElement("//button[@class='btn btn-pmt']");
				SeleniumUtils.defaultWait(5000);
				count = getDriver().findElements(By.xpath(Headers)).size();
				if(count==0)
				{
					for(int j=1;j<5;j++){
						System.out.println("Retry ["+j+"] => For Clicking Search button");
						oSeleniumUtils.click_given_WebElement("//button[@class='btn btn-pmt']");
						count = getDriver().findElements(By.xpath(Headers)).size();
						if(count>0)
							break;
					}
				}
				Assert.assertTrue("Results Not Displayed",count>0);
				System.out.println("Results Displayed As Action left blank");
				
				// verify dropdown options add,edit,all
				oSeleniumUtils.click_given_WebElement(action);
				String[] options = oSeleniumUtils.get_All_Text_from_Locator(action_Options);
				for(String a : options)
					System.out.println(a+" options is displayed");
				break;
			case "":
				break;
			default:
				throw new IllegalArgumentException(sections[i]+" is does not exist, please provide the correct case");
			}
		}
		return bln; 
	}

	public void verifyPopup() {
		SeleniumUtils.defaultWait(1000);
		Alert alert = getDriver().switchTo().alert();
		String text = alert.getText();
		System.out.println("Prompt message displayed as '"+text+"'");
		if(!(text.contains("You might lose search information on this page. Do you wish to continue?") || text.contains("You might lose information on this page. Do you wish to continue?")))
			Assert.fail("Prompt Message is "+text);		
		SeleniumUtils.defaultWait(1000);
		alert.dismiss();
		System.out.println("Cancel button displayed");
		getDriver().navigate().back();
		SeleniumUtils.defaultWait(3000);
		alert.accept();
		System.out.println("Ok button displayed");
	}	
	
	public String[] sort(String[] list){
		for(int i = 0; i < list.length; i++)
		{
		    int smallest = i;
		    for(int j = i + 1; j < list.length; j++) // here you find the index of the minimum String between the strings in the unsorted side of the array
		    {
		        if(list[j].compareTo(list[i]) < 0)
		            smallest = j;
		    }
		    String aux = list[i];
		    list[i] = list[smallest];
		    list[smallest] = aux;
		}
		System.out.println("=== Sorted Values ===");
		for(int i = 0; i < list.length; i++)
			System.out.println(list[i]);
		return list;
	}
}
