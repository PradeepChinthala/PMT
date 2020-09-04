package project.pageobjects;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.openqa.selenium.support.FindBy;

import net.serenitybdd.core.environment.EnvironmentSpecificConfiguration;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.util.EnvironmentVariables;
import project.utilities.DBQueries;
import project.utilities.DBUtils;
import project.utilities.SeleniumUtils;

public class HomePage  extends PageObject{
	
	SeleniumUtils oSeleniumUtils;
	DBUtils oDBUtils;
	
	private EnvironmentVariables environmentVariables;
	
	/*############################################ WebElements ###############################################*/
		
	@FindBy(css = "a[href='/home']")
	private WebElementFacade home;
			
	@FindBy(css = "i[class*='fa fa-fw fa-angle-double']")
	private WebElementFacade arrow;
	
	@FindBy(xpath="//span[text()='Search Projects']/..")
	private WebElementFacade searchProject;
	
	@FindBy(xpath="//span[text()='Account Configuration']/..")
	private WebElementFacade accountConfiguration;
	
	@FindBy(css="div[class='navbar-welcome']")
	private WebElementFacade welcome;
	
	@FindBy(css="img[class='navbar-favicon']")
	private WebElementFacade favicon;
	
	@FindBy(css="div[class='avatar-circle nav-link dropdown-toggle']")
	private WebElementFacade initialBtn;
	
	@FindBy(css="div[class='dropdown-menu-right icon-div dropdown-menu show'] a")
	private List<WebElementFacade> initialOptions;
	
	@FindBy(css="div[class='ico-header']")
	private List<WebElementFacade> toolTips;
	
	String xpath = "//div[text()='arg']/ancestor::div[@class='ico-header'][1]";
	
	/*############################################ xpath ###################################################*/	
	
	public static String Div_With_Exact_Text = "//div[text()='arg']";	
	
	public static String Prefix  = "(//form[contains(@class,'ng-pristine')][@style]/div/div)";
	
	public static String Suffix = "//ng-select[@formcontrolname='arg'][contains(@class,'font ng-select ng-select-single ng-select-searchable ng-select-clearable ng-')]";
		
	public static String Options ="//div[@class='ng-option ng-star-inserted' or @role='option']/div";
	
	public static String Mandatory_Symbol = "//label[text()='arg']/span[contains(text(),'*')]";
	
	public static String Mandatory_Msg = "//div[normalize-space(text())='arg is required']";
	
	public static String Headers = "(//table[@id='DataTables_Table_0']/thead/tr/th)";
	
	public static String TableData = "(//table[@id='DataTables_Table_0']/tbody/tr/td[index])";
	
	public static String SearchResult = "//div[@id='DataTables_Table_0_filter']/*/input";
	
	public static String rows = "//select[@name='DataTables_Table_0_length']/option";
	
	public static String toolTip = "//div[@class='d-block huge' and text()='arg']/ancestor::div[@class='ico-header']";
	
	public static String loading = "(//div[@class='block-ui-wrapper block-ui-main active'])[1]//div[@class='loader']";
	
	/*############################################ methods ###################################################*/
	
	

	public void gotoPage(String type){		
		SeleniumUtils.defaultWait(500);
		oSeleniumUtils.dynamicWaitForLoadingIcon(loading);
		oSeleniumUtils.click_given_WebElement(xpath.replace("arg", type));
	}

	public void validateHomePage() throws SQLException {
		oSeleniumUtils.dynamicWaitForLoadingIcon(loading);	
		String userName = EnvironmentSpecificConfiguration.from(environmentVariables).getProperty("username");
		String sqlQuery = DBQueries.getUserName(userName);	
		Map<String, List<String>> results = oDBUtils.executeSQLQuery_GetMap(sqlQuery);
		String firstName = results.get("FirstName").stream().toArray(String[]::new)[0];
		String lastName = results.get("LastName").stream().toArray(String[]::new)[0];
		Assert.assertTrue("Title Not matched with first name '"+firstName,welcome.getText().contains(firstName));
		Assert.assertTrue("Title Not matched with Last name '"+lastName,welcome.getText().contains(lastName));
		Assert.assertTrue("favicon not displayed",oSeleniumUtils.element_displayed(favicon));
		
		oSeleniumUtils.click_given_WebElement(initialBtn);
		for(WebElementFacade e : initialOptions){
			Assert.assertTrue(e.isDisplayed());
			System.out.println("'"+e.getText()+"' is dispalyed");
		}
	}

	public String[] validateToolTip() {
		String[] text= new String[toolTips.size()];
		for(int i =0;i<toolTips.size();i++)
			text[i]=toolTips.get(i).getAttribute("title");		
		return text;
		 //return oSeleniumUtils.get_All_Text_from_Locator(toolTips);	
	}
	
	public boolean gotoHomePage(){
		oSeleniumUtils.click_given_WebElement(home);		
		return oSeleniumUtils.element_displayed("//h3[@class='home-header']");
	}

	public void verifyPageOpened(String arg1) {
		boolean bln = false;
		switch(arg1.trim()){
		case"AddProject":
		case"Add Project":
			bln = oSeleniumUtils.element_displayed("//div[@class='modal-content']/div/h5[normalize-space(text())='Add Project']");
			break;
		case "Manage Customers":
			bln = oSeleniumUtils.element_displayed("//div[text()='Customer Search']");
			break;
		case "Add Customer":
			bln = oSeleniumUtils.element_displayed("//h5[@id='exampleModalLabel'][normalize-space(text()='"+arg1+"')]");
			break;
		}
		Assert.assertTrue(arg1+" Not displayed",bln);
	}

	public String verifyToolTip(String arg1) {
		WebElementFacade element = oSeleniumUtils.getElement("//div[@title='"+arg1+"']");
		withAction().moveToElement(element).build().perform();
		//boolean bln = oSeleniumUtils.element_displayed(toolTip.replace("arg", arg1));	
		boolean bln = oSeleniumUtils.element_displayed(element);
		Assert.assertTrue("Tool tip not displayed for "+arg1,bln);
		return element.getAttribute("title");
		//return $(toolTip.replace("arg", arg1)).getAttribute("title");
	}
	
}
