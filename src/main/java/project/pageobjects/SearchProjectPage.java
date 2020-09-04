package project.pageobjects;

import java.time.Duration;
import java.time.Year;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Stream;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import project.utilities.GenericUtils;
import project.utilities.ProjectVariables;
import project.utilities.SeleniumUtils;
import project.utilities.SharedPage;

import com.github.javafaker.Faker;

public class SearchProjectPage  extends PageObject{
	
	SeleniumUtils oSeleniumUtils;
	SharedPage oSharedPage;
	
	Faker faker = new Faker(new Locale("en-IND"));
	
	/*############################################ WebElements ###############################################*/
	
	@FindBy(xpath = "//button[text()='Add Project']")
	public WebElementFacade addProject;
	
	@FindBy(css = "button[class='btn btn-pmt']")
	public WebElementFacade search;
	
	@FindBy(xpath = "//div[text()='Project Search']")
	public WebElementFacade searchProjectHeader;
		
	String Suffix = "(//form[contains(@class,'ng-')][@style]/div/div)";
	
	String Prefix = "//ng-select[@formcontrolname='arg'][contains(@class,'font ng-select ng-select-single ng-select-searchable ng-select-clearable ng-')]";
		
	String Options ="//div[@class='ng-option ng-star-inserted' or @role='option']/div";
	
	String Mandatory_Symbol = "//label[text()='arg']/span[contains(text(),'*')]";
	
	String Mandatory_Msg = "//div[normalize-space(text())='arg is required']";
	
	String Headers = "(//table[@id='DataTables_Table_0']/thead/tr/th)";
	
	String TableData = "(//table[@id='DataTables_Table_0']/tbody/tr/td[index])";
	
	String SearchResult = "//div[@id='DataTables_Table_0_filter']/*/input";
	
	String rows = "//select[@name='DataTables_Table_0_length']/option";
	
	String successMsg = "//div[@class='toast-message ng-star-inserted']";
	
	String projectId = "//input[@id='number']";
	
	String projectName = "//input[@id='Name']";	
	
	String btn_Xpath = "(//td[text()='arg1']/../td/*[normalize-space(text())='arg2'])[1]";
	
	String edit_PopupHeader = "//h5[@id='exampleModalLabel']";
	
	/*############################################ methods ###################################################*/
	
	public void addProject(){
		oSeleniumUtils.click_given_WebElement(addProject);
		SeleniumUtils.defaultWait(5000);
	}

	public boolean verifySearchProjectpage(){
		return oSeleniumUtils.element_displayed(searchProjectHeader);
	}

	public boolean verifyFields(String arg1) {
		boolean bln = false;
		String[] sections = arg1.split(",");
		for (int i = 0; i < sections.length; i++){
			switch(sections[i]){
			case "Client ID":
				bln = verifyClientId();
				break;
			case "Season Year":
				bln = verifySeasonyear();
				break;
			case "Master Account ID":
				bln = verifyMasterAccountID();
				break;
			case "Project Wave":
				bln = verifyProjectWave();
				break;
			case "Service Line":
				bln = verifyServiceLine();
				break;
			case "Project Name":
			case "Project ID":
				bln = verifyProjectName(sections[i]);				
				break; 
				
			default:
				throw new IllegalArgumentException(sections[i]+" is does not exist, please provide the correct case");
			}
		}
		return bln; 
	}
		
	private boolean verifyProjectName(String fieldname){
		String str= fieldname.contains("Project Name")?"Project Name : ":"Project Id :";		
		Prefix = fieldname.contains("Project Name")?"//input[@id='Name']":"//input[@id='number']";
		String expectedLeght = fieldname.contains("Project Name")?"30":"4";
		boolean verify =fieldname.contains("Project Name")?true:false;
		
		// Verifying client id displayed along with mandatory
		Assert.assertTrue(str+" not displayed",oSeleniumUtils.element_displayed(Suffix+Prefix));
		Assert.assertFalse(str+" is not optional",oSeleniumUtils.element_displayed(Suffix+Mandatory_Symbol.replace("arg", str)));
									
		String length = $(Suffix+Prefix).getAttribute("maxlength");
		Assert.assertTrue(str+" max lenght not matched"+length,length.equals(expectedLeght));
		if(verify){
			oSeleniumUtils.enter_given_Text_Element(Suffix+Prefix,"12");
			oSeleniumUtils.click_given_WebElement(search);
			
			boolean bln = oSeleniumUtils.element_displayed("//div[normalize-space(text())='Minimum of 3 characters are required']");
			Assert.assertTrue("'Minimum of 3 characters are required' message not dispalyed",bln);
		}		
		return true;
	}
	
	private boolean verifyServiceLine(){
		String str= "Service Line : ";		
		Prefix = Prefix.replace("arg", "ProjectServiceLineID");
		
		// Verifying client id displayed along with mandatory
		Assert.assertTrue(str+" not displayed",oSeleniumUtils.element_displayed(Suffix+Prefix));
		Assert.assertFalse(str+" is not optional",oSeleniumUtils.element_displayed(Suffix+Mandatory_Symbol.replace("arg", str)));
		
		// validating default text
		String text = oSeleniumUtils.get_Text_From_WebElement(Suffix+Prefix+"/div//div[text()]");
		Assert.assertTrue("'Service Line' not populated by default",text.contains("Select Service Line"));
		
		// Validate drop down options
		oSeleniumUtils.click_given_WebElement(Suffix+Prefix);
		String[] values = oSeleniumUtils.get_All_Text_from_Locator(Options);				
		for(String v : values){			
			Assert.assertTrue("'Service Line' has empty"+v,v!="");
		}			
		// validate mandatory field 
		oSeleniumUtils.click_given_WebElement(search);
		boolean bln = oSeleniumUtils.element_displayed(Mandatory_Msg.replace("arg", str));
		Assert.assertFalse("'Service Line' is not optional",bln);	
		return bln;
	}
	
	private boolean verifyProjectWave(){
		String str= "Project Wave";		
		Prefix = Prefix.replace("arg", "ProjectWaveID");
		
		// Verifying client id displayed along with mandatory
		Assert.assertTrue(str+"' not displayed",oSeleniumUtils.element_displayed(Suffix+Prefix));
		Assert.assertFalse(str+" is not optional",oSeleniumUtils.element_displayed(Suffix+Mandatory_Symbol.replace("arg", str)));
		
		// validating default text
		String text = oSeleniumUtils.get_Text_From_WebElement(Suffix+Prefix+"/div/div/div");
		Assert.assertTrue("'Project Wave' not populated by default",text.contains("Select Project Wave"));
		
		// Validate drop down options
		oSeleniumUtils.click_given_WebElement(Suffix+Prefix);
		String[] values = oSeleniumUtils.get_All_Text_from_Locator(Options);				
		for(String v : values){			
			Assert.assertTrue("'Project Wave' has empty"+v,v!="");
		}
		
		// validate mandatory field 
		oSeleniumUtils.click_given_WebElement(search);
		boolean bln = oSeleniumUtils.element_displayed(Mandatory_Msg.replace("arg", str));
		Assert.assertFalse("'Project Wave 'is not optional",bln);
		return bln;
	}
	
	private boolean verifyMasterAccountID(){
		String str= "Master Account ID / Name";		
		Prefix = "//*[@id='ProjectMasterAccount']";
		
		// Verifying client id displayed along with mandatory
		Assert.assertTrue(str+"' not displayed",oSeleniumUtils.element_displayed(Suffix+Prefix));
		Assert.assertFalse(str+" is not mandatory",oSeleniumUtils.element_displayed(Suffix+Mandatory_Symbol.replace("arg", str)));
		
		// validating default text
		String text = oSeleniumUtils.get_Text_From_WebElement(Suffix+Prefix+"//span[text()]");
		Assert.assertTrue("'Select Master Account' not populated by default",text.contains("Select Master Account"));
		
		// Validate Drop Down along with search options
		oSeleniumUtils.click_given_WebElement(Suffix+Prefix);		
		oSeleniumUtils.enter_given_Text_Element("//input[@class='select2-search__field']", "1");
		oSeleniumUtils.dynamicWaitForLoadingIcon("//ul/li[text()='Searching…']");
		String[] values = oSeleniumUtils.get_All_Text_from_Locator("//span/ul/li[text()]");				
		for(String v : values){
			if(!v.contains("1"))
				Assert.assertFalse(v+" does not contains '1'",false);
			//Assert.assertTrue("Master Account ID does not have options in the form of <ID> - <Name> in "+v,GenericUtils.isInteger(v.split("-")[0].trim()));
		}
		
		// validate mandatory field 
		oSeleniumUtils.click_given_WebElement(search);
		boolean bln = oSeleniumUtils.element_displayed(Mandatory_Msg.replace("arg", str));
		Assert.assertFalse("'Master Account ID / Name is required' message not displayed",bln);
		return bln;
	}
	
	private boolean verifySeasonyear(){
		String str= "Season Year :";		
		Prefix = Prefix.replace("arg", "SeasonYear");
		
		// Verifying client id displayed along with mandatory
		Assert.assertTrue(str+" not displayed",oSeleniumUtils.element_displayed(Suffix+Prefix));
		//Assert.assertTrue(str+" is not mandatory field",oSeleniumUtils.element_displayed(Suffix+Mandatory_Symbol.replace("arg", str)));
		
		// validating default text
		String text = oSeleniumUtils.get_Text_From_WebElement(Suffix+Prefix+"/div/div/div");
		Assert.assertTrue("'Select Client' not populated by default",text.contains("Select Season Year"));
		
		// Validate options should be +5 and -5 years
		oSeleniumUtils.click_given_WebElement(Suffix+Prefix);		
		
		// validating options
		int year = Year.now().getValue();
		int past = year-5;
		int future = year+5;		
		int[] options = Stream.of(oSeleniumUtils.get_All_Text_from_Locator(Options)).mapToInt(Integer::parseInt).toArray();
		boolean bln = false;
		if(past==options[0]&&future==options[options.length-1])
			bln = true;
		Assert.assertTrue("Option were not in between "+past+" and "+future,bln);
		
		// validate mandatory field 
		oSeleniumUtils.click_given_WebElement(search);
		//bln = oSeleniumUtils.element_displayed(Mandatory_Msg.replace("arg", str));
		//Assert.assertTrue("Madatory message not displayed for "+str,bln);		
		return bln;
	}
	
	private boolean verifyClientId(){
		String str= "Client ID / Name";
		Prefix = Prefix.replace("arg", "ClientName");
		
		// Verifying client id displayed along with mandatory
		Assert.assertTrue(str+"' not displayed",oSeleniumUtils.element_displayed(Suffix+Prefix));
		Assert.assertTrue(str+" is not mandatory",oSeleniumUtils.element_displayed(Suffix+Mandatory_Symbol.replace("arg", str)));
		System.out.println(str+" is not mandatory");
		// validating default text
		String text = oSeleniumUtils.get_Text_From_WebElement(Suffix+Prefix+"/div/div/div");
		Assert.assertTrue("'Select Client' not populated by default",text.contains("Select Client"));
		System.out.println("'Select Client' populated by default");
		
		// Validate options are in the format of <Client id> - <client name>
		oSeleniumUtils.click_given_WebElement(Suffix+Prefix);		
		
		// validating options
		String[] values = oSeleniumUtils.get_All_Text_from_Locator(Options);				
		for(String v : values)
			Assert.assertTrue("'Client ID' does not have options in the form of <ID> - <Name> for "+v,GenericUtils.isInteger(v.split("-")[0].trim()));
		
		System.out.println("'Client ID' have options in the form of <ID> - <Name>");
		// validate mandatory field 
		oSeleniumUtils.click_given_WebElement(search);
		boolean bln = oSeleniumUtils.element_displayed(Mandatory_Msg.replace("arg", str));
		Assert.assertTrue("Madatory message not displayed for "+str,bln);
		System.out.println("Madatory message displayed for "+str);
		return bln;
	}

	public void populateFields(String options) {
		String[] sections = options.split(",");	
		for (int i = 0; i < sections.length; i++){			
			String[] field = sections[i].split(":");
			SeleniumUtils.defaultWait(300);
			field[1] = SeleniumUtils.getValueByName(field[1]).equals(null)?field[1]:SeleniumUtils.getValueByName(field[1]);
			switch(field[0].trim()){
			case "Client ID":
			case "ClientName":
			case "Client Name":
				oSeleniumUtils.click_given_WebElement(Suffix+Prefix.replace("arg", "ClientName"));
				oSeleniumUtils.enter_given_Text_Element(Suffix+Prefix.replace("arg", "ClientName")+"//input",field[1]);
				$("//ng-dropdown-panel/div").withTimeoutOf(Duration.ofMillis(ProjectVariables.MAX_THREAD_WAIT)).waitUntilVisible();
				SeleniumUtils.defaultWait(2000);
				$(Suffix+Prefix.replace("arg", "ClientName")+"//input").sendKeys(Keys.ENTER);
				break;
			case "Master Account ID":
			case "MasterAccountID":
				oSeleniumUtils.click_given_WebElement("//*[@id='ProjectMasterAccount']");
				oSeleniumUtils.enter_given_Text_Element("//input[@class='select2-search__field']",field[1]);
				oSeleniumUtils.dynamicWaitForLoadingIcon("//ul/li[text()='Searching…']");
				$("//input[@class='select2-search__field']").sendKeys(Keys.ENTER);
				break;
			case "Project ID":
			case "ProjectID":
				oSeleniumUtils.enter_given_Text_Element(Suffix+projectId,field[1]);
				break;
			case "Project Name":
			case "ProjectName":
				oSeleniumUtils.enter_given_Text_Element(Suffix+projectName,field[1]);
				break;
			case "Service Line":
				oSeleniumUtils.click_given_WebElement(Suffix+Prefix.replace("arg", "ProjectServiceLineID"));
				oSeleniumUtils.enter_given_Text_Element(Suffix+Prefix.replace("arg", "ProjectServiceLineID")+"//input",field[1]);				
				$(Suffix+Prefix.replace("arg", "ProjectServiceLineID")+"//input").sendKeys(Keys.ENTER);
				break;			
			case "Project Wave":
				oSeleniumUtils.click_given_WebElement(Suffix+Prefix.replace("arg", "ProjectWaveID"));
				oSeleniumUtils.enter_given_Text_Element(Suffix+Prefix.replace("arg", "ProjectWaveID")+"//input",field[1]);				
				$(Suffix+Prefix.replace("arg", "ProjectWaveID")+"//input").sendKeys(Keys.ENTER);
				break;
			case "Season Year":
			case "SeasonYear":
				oSeleniumUtils.click_given_WebElement(Suffix+Prefix.replace("arg", "SeasonYear"));
				oSeleniumUtils.enter_given_Text_Element(Suffix+Prefix.replace("arg", "SeasonYear")+"//input",field[1]);				
				$(Suffix+Prefix.replace("arg", "SeasonYear")+"//input").sendKeys(Keys.ENTER);
				break;
					
			default:
				throw new IllegalArgumentException(sections[i]+" is does not exist, please provide the correct case");					
			}			
		}
		search();
	}
	
	public String verifyProjectIdCreated(){
		String msg = oSeleniumUtils.get_Text_From_WebElement(successMsg);
		return msg;		
	}
	
	public void search(){
		SeleniumUtils.defaultWait(ProjectVariables.MID_THREAD_WAIT_5);
		oSeleniumUtils.click_given_WebElement(search);
		System.out.println("Search Clicked Successfully");
	}
		
	public void validateSearchResultGrid(String options){
		if(options.contains("All"))
				validateAllcolumnsSorted();
		else {
		String[] sections = options.split(",");	
		for (int i = 0; i < sections.length; i++){
			int index= get_ColumnIndex_By_ColumnName(sections[i],Headers);
			String[] values = oSeleniumUtils.get_All_Text_from_Locator(TableData.replace("index", String.valueOf(index)));
			Assert.assertTrue("Values are not in Ascending Order",isSorted(values));
		}}
	}
	
	public void validateAllcolumnsSorted(){
		oSeleniumUtils.click_given_WebElement(this.rows+"[last()]");
		SeleniumUtils.defaultWait(100);
		int len = getDriver().findElements(By.xpath(Headers)).size();
		for (int i = 1; i <= len; i++){	
			System.out.println(">>>>>>> Verify Column '"+$(Headers+"["+i+"]").getText()+"' Sorted <<<<<<<<");
			if(i!=1)
				oSeleniumUtils.click_given_WebElement(Headers+"["+i+"]");
			String[] values = oSeleniumUtils.get_All_Text_from_Locator(TableData.replace("index", String.valueOf(i)));
			String[] result  = sort(values);
			Assert.assertTrue($(Headers+"["+i+"]").getText()+" Values not sorted",Arrays.equals(values, result));
		}
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
	
	@SuppressWarnings("unused")
	public boolean isSorted(String[] list)
    {
        boolean sorted = true;        
        for (int i = 1; i < list.length; i++) {
            if (list[i-1].compareTo(list[i]) > 0)
            	sorted = false;break;
        }
        return sorted;
    }	
	
	public int get_ColumnIndex_By_ColumnName(String coumnName, String columnXpath) {
		int index = 0;
		List<WebElement> list = getDriver().findElements(By.xpath(columnXpath));
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getText().contains(coumnName) | list.get(i).getAttribute("innerHTML").contains(coumnName)) {				
				index = i + 1;
				break;
			}
		}
		if (index == 0)
			Assert.fail("coulumn name was wrong, please pass correct column name as shown in ui instead of : " + coumnName);
		return index;
	}
	
	public String[] validateSearchFiledByResultedColumnData(String columnS){
		int i;
		String text2 = "";
		String column2  = "";
		if(columnS.toLowerCase().contains("random")){
			oSeleniumUtils.dynamicWaitForLoadingIcon(HomePage.loading);	
			i = GenericUtils.getRandomNumberBetweenRange(1, getDriver().findElements(By.xpath(Headers)).size());
		}
		else
			i = get_ColumnIndex_By_ColumnName(columnS,Headers);
		String column = $("(//table[@id='DataTables_Table_0']/thead/tr/th)["+i+"]").getText();		
		String text = $(TableData.replace("index", String.valueOf(i))+"[1]").getText();
		if(columnS.toLowerCase().contains("and")){
			int i2 = GenericUtils.getRandomNumberBetweenRange(1, getDriver().findElements(By.xpath(Headers)).size());
			column2 = $("(//table[@id='DataTables_Table_0']/thead/tr/th)["+i2+"]").getText();	
			text2 = $(TableData.replace("index", String.valueOf(i2))+"[1]").getText();
		}	
		System.out.println("Verify Column Name :'"+column+" " +column2+"' with data '"+text+" "+text2+"'");
		oSeleniumUtils.enter_given_Text_Element(SearchResult,text+" "+text2);
		String[] values = oSeleniumUtils.get_All_Text_from_Locator(TableData.replace("index", String.valueOf(i)));
		Assert.assertTrue(Arrays.toString(values)+"' does not contains "+text,Arrays.asList(values).contains(text));
		return values;
	}

	public void validateRows(String rows) {
		String[] rowsText = oSeleniumUtils.get_All_Text_from_Locator(this.rows);
		String[] exp = rows.split(",");
		boolean bln = Arrays. equals(rowsText, exp);
		Assert.assertTrue(bln);
	}

	public void validatePagination() {
		if(getDriver().findElements(By.xpath("(//table[@id='DataTables_Table_0']/tbody/tr)")).size()>0)	{
			int count = getDriver().findElements(By.xpath("(//div[@id='DataTables_Table_0_paginate']/span)/child::*")).size();
			for(int i=0;i<count;i++){
				Assert.assertTrue($("(//div[@id='DataTables_Table_0_paginate']/span)/a["+String.valueOf(i+1)+"]").isDisplayed());
			}
			String[] values = oSeleniumUtils.get_All_Text_from_Locator("(//div[@id='DataTables_Table_0_paginate']/a)");
			Assert.assertTrue("Previous is not dispayed",Arrays.asList(values).contains("Previous"));
			System.out.println("Previous is dispayed");
			Assert.assertTrue("Next is not dispayed in pagination",Arrays.asList(values).contains("Next"));
			System.out.println("Next is dispayed");
		}
	}

	public void verifymandatoryMessages(String arg1) {
		String[] fields = arg1.split(",");	
		for(String field : fields){
			boolean bln = false;
			if(field.split(":")[1].length()==0)
				bln = oSeleniumUtils.element_displayed(Mandatory_Msg.replace("arg", field));
			else
				bln = oSeleniumUtils.element_displayed("//*[normalize-space(text())='"+field.split(":")[1]+"']");
			Assert.assertTrue(field+"'is not Mandatory",bln);			
		}
		
		
	}

	public void clickButtonBasedOn(String buttonName, String criteria) {
		oSeleniumUtils.dynamicWaitForLoadingIcon(SharedPage.loading);
		String cellText = SeleniumUtils.getValueByName(criteria);
		boolean bln = oSeleniumUtils.click_given_WebElement(btn_Xpath.replace("arg1",cellText).replace("arg2",buttonName));
		Assert.assertTrue(criteria+" Does not exist",bln);
	}

	public void verifyPopup(String popUpName) {
		SeleniumUtils.defaultWait(2000);
		oSeleniumUtils.dynamicWaitForLoadingIcon(SharedPage.loading);
		if(popUpName.toLowerCase().contains("edit")){
			boolean bln = $(edit_PopupHeader).withTimeoutOf(Duration.ofMillis(ProjectVariables.MID_TIME_OUT)).getText().contains("Edit");
			Assert.assertTrue("'Edit Project'Popup not dispalyed",bln);
			System.out.println("'Edit Project'Popup dispalyed");
			String[] labels = oSeleniumUtils.get_All_Text_from_Locator("//div[@class='modal-body']//label");
			System.out.println(Arrays.toString(labels));
		}
	}
}
