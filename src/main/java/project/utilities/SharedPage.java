package project.utilities;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import com.github.javafaker.Faker;

import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.core.pages.PageObject;

public class SharedPage extends PageObject {

	SeleniumUtils oSeleniumUtils;
	
	Faker faker = new Faker(new Locale("en-IND"));
	
	/*############################################ Shared Xpath's ###############################################*/
	
	String Mandatory_Symbol = "//label[text()='arg']/span[contains(text(),'*')]";
	
	String Mandatory_Msg = "//div[normalize-space(text())='arg is required']";
	
	String datePicker_Prefix = "//div[@class='bs-datepicker-container ng-trigger ng-trigger-datepickerAnimation']";
	
	String columnSuffix = "//thead/tr/th";
	
	public static String loading = "(//div[@class='block-ui-wrapper block-ui-main active'])[1]//div[@class='loader']";
	
	/*############################################ Shared Methods ###############################################*/
	
	public void inputfieldValidation(String Prefix,String fieldXpath,String submitXpath,boolean mandatory,int maxlength,String fieldName){
		System.out.println("*****************  " +fieldName+": Filed Validation ********************");
		// Displayed
		Assert.assertTrue(fieldName+"' not displayed",oSeleniumUtils.element_displayed(Prefix+fieldXpath));
		
		// Max length
		String length = $(Prefix+fieldXpath).getAttribute("maxlength");
		Assert.assertTrue(fieldName+" max lenght not matched "+length,length.equals(String.valueOf(maxlength)));
		System.out.println("'"+fieldName+"' Max lenght is "+length);	
		
		// validate mandatory/optional field 
		if(mandatory){
			Assert.assertTrue(fieldName+" is not mandatory",oSeleniumUtils.element_displayed(Prefix+Mandatory_Symbol.replace("arg", fieldName)));
			System.out.println(fieldName+" * symbol displayed");
			oSeleniumUtils.click_given_WebElement(submitXpath);
			boolean bln = oSeleniumUtils.element_displayed(Mandatory_Msg.replace("arg", fieldName));
			Assert.assertTrue("'"+fieldName+"'  is required' message not displayed",bln);
			System.out.println("'"+fieldName+" is required' message  displayed");
		}
		else{
			Assert.assertFalse(fieldName+" is optional",oSeleniumUtils.element_displayed(Prefix+Mandatory_Symbol.replace("arg", fieldName)));
			System.out.println(fieldName+" is optional");
			oSeleniumUtils.click_given_WebElement(submitXpath);
			boolean bln = oSeleniumUtils.element_displayed(Mandatory_Msg.replace("arg", fieldName));
			Assert.assertFalse("'"+fieldName+"'  is required' message displayed",bln);
			System.out.println("'"+fieldName+"' is required' message  not displayed");
		}
	}
	
	public void dropDownfieldValidation(String Prefix,String fieldXpath,String submitXpath,boolean mandatory,String defaultValue,String fieldName){			
		System.out.println("*****************  " +fieldName+": Filed Validation ********************");
		// Displayed
		Assert.assertTrue(fieldName+"' not displayed",oSeleniumUtils.element_displayed(Prefix+fieldXpath));				
		
		// validating default text
		if(defaultValue!=null){
			boolean bln = oSeleniumUtils.element_displayed(Prefix+fieldXpath+"/div//*[text()='"+defaultValue+"']");
			Assert.assertTrue(bln);
			String text = oSeleniumUtils.get_Text_From_WebElement(Prefix+fieldXpath+"/div//*[text()='"+defaultValue+"']");
			Assert.assertTrue("'"+defaultValue+"' not populated by default",text.contains(defaultValue));
			System.out.println("'"+defaultValue+"' populated by default");
		}			
		// validate mandatory field 
		if(mandatory){
			Assert.assertTrue(fieldName+" is not mandatory",oSeleniumUtils.element_displayed(Prefix+Mandatory_Symbol.replace("arg", fieldName)));
			System.out.println(fieldName+" * symbol displayed");
			oSeleniumUtils.click_given_WebElement(submitXpath);
			boolean bln = oSeleniumUtils.element_displayed(Mandatory_Msg.replace("arg", fieldName));
			Assert.assertTrue("'"+fieldName+"'  is required' message not displayed",bln);
			System.out.println("'"+fieldName+"' is required' message  displayed");
		}
		else{
			Assert.assertFalse(fieldName+" is optional",oSeleniumUtils.element_displayed(Prefix+Mandatory_Symbol.replace("arg", fieldName)));
			System.out.println("'"+fieldName+"' is an optional field & '*' Symbol not displayed");
			oSeleniumUtils.click_given_WebElement(submitXpath);
			boolean bln = oSeleniumUtils.element_displayed(Mandatory_Msg.replace("arg", fieldName));
			Assert.assertFalse("'"+fieldName+"  is required' message displayed",bln);
			System.out.println("'"+fieldName+" is required' message not displayed");
		}
	}
	
	public void dropDown_Options_Validation(String xpath,int optionsCount,String fieldName){
		// Validate drop down options
		List<String> values = oSeleniumUtils.get_All_Options_From_Brootstarp_Dropdown_With_click(xpath);				
		for(String v : values)
			Assert.assertTrue("'"+fieldName+"' has empty"+v,v!="");
		System.out.println(values+" options were displayed for '"+fieldName+"' field");
		Assert.assertTrue(values.size()==optionsCount);
	}
	
	public void select_Only_one_option_validation(String xpath,String filedName){
		// user should be able to select 1 option at a time
		List<String> values = oSeleniumUtils.get_All_Options_From_Brootstarp_Dropdown_With_click(xpath);	
		for(String v : values){
			$(xpath+"//input").sendKeys(v);
			SeleniumUtils.defaultWait(500);
			$(xpath+"//input").sendKeys(Keys.ENTER);
			SeleniumUtils.defaultWait(1000);
			boolean bln = oSeleniumUtils.element_displayed(xpath+"//span[normalize-space(text())='"+v+"']");
			Assert.assertTrue("'"+v+"' not selected",bln);
			System.out.println("'"+v+"' is selected");
		}
		System.out.println("'"+filedName+"' User can select only 1 value from the dropdown");
	}
	
	//String Prefix,String fieldXpath,String maxlength,boolean mandatory,String submitXpath,String fieldName
	public void datePicker_Validation(String Prefix,String fieldXpath,String submitXpath,boolean mandatory,String defaultValue,String fieldName){		
		String placeHolder = $(Prefix+fieldXpath).getAttribute("placeholder");
		Assert.assertTrue(placeHolder.equals(defaultValue));	
		System.out.println("'"+fieldName+"' Place holder is '"+placeHolder+"'");
		this.inputfieldValidation(Prefix,fieldXpath,submitXpath,mandatory,10,fieldName);
		oSeleniumUtils.click_given_WebElement(Prefix+fieldXpath);
		Assert.assertTrue("Date Picker Not displayed",oSeleniumUtils.element_displayed(datePicker_Prefix));
		System.out.println(fieldName+"' is a date picker");		
	}
	
	public void verifyInvalidDate(String xpath,String submitXpath,String expectedText,String invalidDate,String fieldName){
		// Sample Parameters :   Prefix+startDate,search,"Invalid date","02/30/2020","Start Date"
		//Verify Invalid date
		oSeleniumUtils.enter_given_Text_Element(xpath, invalidDate);
		oSeleniumUtils.click_given_WebElement(submitXpath);
		Assert.assertTrue(expectedText+" message Not displayed",$(xpath).getAttribute("value").contains(expectedText));
		System.out.println(fieldName+" '"+expectedText+"' message is dispayed");
		System.out.println(fieldName+" is able to enter date in the format MM/dd/yyyy");	
	}
	
	public Map<String, String[]> getTableData(String tablePrefix){
		Map<String, String[]> map = null;
		
		String headerXpath = tablePrefix+columnSuffix;
		String rowXpath = tablePrefix+"//tbody/tr";
		
		int cCount = getDriver().findElements(By.xpath(headerXpath)).size();
		int rowCount = getDriver().findElements(By.xpath(rowXpath)).size();
		map = new HashMap<>(cCount);
		for(int i=0;i<cCount;i++){			
			String cIndex = String.valueOf(i+1);
			String cName = $(headerXpath+"["+cIndex+"]").getText();			
			String[] list = new String[rowCount];			
			for(int j=0;j<rowCount;j++){
				String rIndex = String.valueOf(j+1);
				list[j] = $(rowXpath+"["+rIndex+"]"+"/td["+cIndex+"][text()]").getText();			
			}
			map.put(cName, list);
		}
		return map;
	}
	
	public String[] getColumnNames(String tablePrefix){
		String headerXpath = tablePrefix+columnSuffix;
		List<String> columnNames = getDriver().findElements(By.xpath(headerXpath)).stream()
				  .map(WebElement::getText).collect(Collectors.toList());
		return columnNames.toArray(new String[columnNames.size()]);
	}
	
	public String[] getColumnData(String tablePrefix,String columnName){
		String rowXpath = tablePrefix+"//tbody/tr";
		String cIndex = this.getColumnIndex_By_ColumnName(tablePrefix,columnName);		
		
		int rowCount = getDriver().findElements(By.xpath(rowXpath)).size();
		String[] list = new String[rowCount];
		for(int i=0;i<rowCount;i++){
			String rIndex = String.valueOf(i+1);
			list[i] = $(rowXpath+"["+rIndex+"]"+"/td["+cIndex+"][text()]").getText();				
		}
		return list;
	}
	
	public String getColumnIndex_By_ColumnName(String tablePrefix,String columnName){
		String headerXpath = tablePrefix+columnSuffix;
		String cIndex = "";
		int cCount = getDriver().findElements(By.xpath(headerXpath)).size();
		for(int i=0;i<cCount;i++){	
			cIndex = String.valueOf(i+1);
			String cName = $(headerXpath+"["+cIndex+"]").getText();	
			if(cName.equals(columnName)){
				break;
			}
		}
		return cIndex;
	}
	
	public void validateColumn_Sortable(String tablePrefix,String columns){	
		String[] columnNames = null;
		columnNames = (columns=="" || columns.contains("all"))? this.getColumnNames(tablePrefix):columns.split(",");		
		for(String columnName : columnNames){
			String colXpath = tablePrefix+"//thead/tr/th[normalize-space(text())='"+columnName+"']";
			clickUntil(colXpath,"ascending");
			String[] actual = null;
			String[] expected  = null;
			SeleniumUtils.defaultWait(10);
			actual = getColumnData(tablePrefix, columnName);
			expected = GenericUtils.getAscendingOrder(actual,columnName);
			GenericUtils.compareArrays(actual,expected);
			clickUntil(colXpath,"descending");
			actual = getColumnData(tablePrefix, columnName);
			expected = GenericUtils.descendingOrder(actual,columnName);
			GenericUtils.compareArrays(actual,expected);
		}		
	}
	
	public String columnvalidation(String tablePrefix, String columns) {
		String[] columnNames = (columns=="" || columns.contains("all"))? this.getColumnNames(tablePrefix):columns.split(",");		
		String result = "";
		for(String columnName : columnNames){
			String[] cName = columnName.split(":");
			String[] cData = getColumnData(tablePrefix,cName[0]);
			String value = SeleniumUtils.getValueByName(cName[1])==null?cName[1]:SeleniumUtils.getValueByName(cName[1]);	
			boolean bln =Arrays.asList(cData).stream().allMatch(str->str.contains(value));
			Assert.assertTrue(bln);
			result+=cName[0]+" Actual:"+Arrays.toString(cData)+ " Expected:"+value+"%";
		}
		return result;
	}
	
	private void clickUntil(String colXpath,String order){
		for(int i=0;i<5;i++){
			oSeleniumUtils.click_given_WebElement(colXpath);
			String actOrder = $(colXpath).getAttribute("aria-sort");
			if(actOrder.contains(order))
				break;
		}
	}
	
}
