package project.pageobjects;

import java.time.Duration;

import org.openqa.selenium.support.FindBy;

import net.serenitybdd.core.environment.EnvironmentSpecificConfiguration;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.util.EnvironmentVariables;
import project.utilities.ProjectVariables;
import project.utilities.SeleniumUtils;
import org.junit.Assert;

public class LoginPage extends PageObject{

	SeleniumUtils oSeleniumUtils;
	
	private EnvironmentVariables environmentVariables;
	
	String APP_URL;	
	
	public String loading = "//div[@class='block-ui-spinner ng-star-inserted']/div";
	
	public String Loading = "//div[@class='loader']";
	
	/*############################################ WebElements ###############################################*/
	
	@FindBy(id = "inputUsername")
	public WebElementFacade userName;
	
	@FindBy(id = "inputPassword")
	public WebElementFacade password;
	
	@FindBy(css = "button[type='submit']")
	public WebElementFacade submit;
	
	@FindBy(css="img[class='navbar-favicon']")
	private WebElementFacade cotivitiLogo;
	
	/*############################################ methods ###################################################*/
	
	
	public void validate_loginPage(){
		//Assert.assertTrue("Cotiviti Logo not displayed",cotivitiLogo.isDisplayed());
		Assert.assertTrue("App Center Logo displayed",$("//h4[text()='App Center']").isDisplayed());
	}
	
	public boolean logIn() throws Exception{	
		boolean bln = false;
		try{	
			String userName = EnvironmentSpecificConfiguration.from(environmentVariables).getProperty("username");
			String password = EnvironmentSpecificConfiguration.from(environmentVariables).getProperty("password");
			for(int i=0;i<5;i++){
				try{
					this.userName.withTimeoutOf(Duration.ofMillis(ProjectVariables.MAX_THREAD_WAIT)).waitUntilVisible();
					break;
				}catch(Exception e){
					oSeleniumUtils.refresh();
					System.out.println("Retry");
					SeleniumUtils.defaultWait(5000);}
			}			
			oSeleniumUtils.enter_given_Text_Element(this.userName, userName);			
			oSeleniumUtils.enter_given_Text_Element(this.password,password);
			
			//oSeleniumUtils.enter_given_Text_Element(password,new String(Base64.decodeBase64(ProjectVariables.PASSWORD), "UTF-8"));
			oSeleniumUtils.click_given_WebElement(submit);
			bln=true;
			
		}catch(Exception e){
			throw new Exception("[Failed] Login Failed Due to '"+e.getMessage()+"'");
		}
		return bln;
	}

	public void open_PMT_Application(String urlSuffix) throws Exception {
		APP_URL = EnvironmentSpecificConfiguration.from(environmentVariables).getProperty("webdriver.base.url");	
		getDriver().get(APP_URL+urlSuffix);
		try{	
			if(getDriver().getWindowHandles().size()>1) {
				oSeleniumUtils.switchWindowUsingWindowCount(1,2);
				if(getDriver().getTitle().equals(""))
					getDriver().close();		
				oSeleniumUtils.switchWindowUsingWindowCount(1,1);
			}		
			getDriver().manage().window().maximize();			
		}catch(Exception e){
			throw new Exception("Browser Not Initialized due to '"+e.getMessage()+"'");
		}
	}
	
	public void open_PMT_Application() throws Exception {
		APP_URL = EnvironmentSpecificConfiguration.from(environmentVariables).getProperty("webdriver.base.url");	
		getDriver().get(APP_URL);
		try{	
			if(getDriver().getWindowHandles().size()>1) {
				oSeleniumUtils.switchWindowUsingWindowCount(1,2);
				if(getDriver().getTitle().equals(""))
					getDriver().close();		
				oSeleniumUtils.switchWindowUsingWindowCount(1,1);
			}		
			getDriver().manage().window().maximize();			
		}catch(Exception e){
			throw new Exception("Browser Not Initialized due to '"+e.getMessage()+"'");
		}
	}
	
	public void dynamicWaitForLoadingIcon() {		
		  for(int i=0; i<=100;i++) { 
			  try { 
				  SeleniumUtils.defaultWait(ProjectVariables.MID_TIME_OUT);
				  waitForAbsenceOf(Loading);
				  break;
		  } catch(Exception e) { 
		  if(i==30)
			  Assert.assertTrue("Loading is taking more time....", false); 
		  }
		}		 
	}

	public boolean pageDisplayed(String content,String type) {
		boolean bln = false;
		switch(type.toLowerCase()){
		case "header":
			bln = oSeleniumUtils.element_displayed("//h2[contains(text(),'"+content+"')]");
		case "text":
			bln = oSeleniumUtils.element_displayed("//*[contains(text(),'"+content+"')]");
			break;
		case "link":
			bln = oSeleniumUtils.element_displayed("//a[contains(text(),'"+content+"')]");
			break;
		}		
		return bln;	
	}

	public void click(String arg1,String type) {
		switch(type.toLowerCase()){
		case "tab":
		case "text":
			boolean bln = oSeleniumUtils.element_displayed("//div[text()='"+arg1+"']");
			Assert.assertTrue(bln);
			oSeleniumUtils.click_given_WebElement("//div[text()='"+arg1+"']");
			break;
		case "link":
			oSeleniumUtils.click_given_WebElement("//a[text()='"+arg1+"']");
			break;
		case "button":
			oSeleniumUtils.click_given_WebElement("//button[normalize-space(text())='"+arg1+"']");
			break;
		}
	}	
}
