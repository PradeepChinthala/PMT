package project.utilities;


import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

public class SeleniumUtils extends PageObject{	
	
	org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(SeleniumUtils.class);
	
	public void waitUntillGivenWindowPresent(int Time, int NoofWindows) {	
		Set<String> windows = getDriver().getWindowHandles();
		int windowCount = windows.size();			
		for (int i = 0; i < Time; i++) {	
			if (windowCount > NoofWindows) {
				break;
			} else {
				try {						
					defaultWait(ProjectVariables.MIN_THREAD_WAIT);
				} catch (Exception e1) {
					System.out.println("Waiting for webelement in DOM");
				}	
			}
		}	
	}
	
	public <T> void switchToFrame(T nameOrIndex) {
		try {
			defaultWait(500);
			if(nameOrIndex.getClass().equals(String.class))
				getDriver().switchTo().frame(nameOrIndex.toString());		
			else if(nameOrIndex.getClass().equals(Integer.class))		
				getDriver().switchTo().frame(Integer.parseInt(nameOrIndex.toString()));
		}
		catch(Exception e) {
			logger.info("Unable to Switch To Frame :"+nameOrIndex);
		}
	}
	
	public void switchDefaultContent() {
		try {
			defaultWait(500);
			getDriver().switchTo().defaultContent();				
		}
		catch(Exception e) {
			logger.info("Unable to Switch To Default");
		}
	}
	
	public static void defaultWait(int i) {
		try {
			Thread.sleep(i);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void defaultWait(long i) {
		try {
			Thread.sleep(i);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static String getValueByName(String key) {
		try {
			return Serenity.sessionVariableCalled(key).toString();
		}catch(NullPointerException e) {
			return null;
		}
	}
	
	public int switchWindowUsingWindowCount(int waitTimer, int windowNum) {
		waitUntillGivenWindowPresent(waitTimer, windowNum);
		ArrayList<String> windowHandles = new ArrayList<String>(getDriver().getWindowHandles());		
		getDriver().switchTo().window(windowHandles.get(windowNum - 1).toString());
		return windowHandles.size();
	}
	
	public static void setSessionVaribale(String key,String value) {
		Serenity.setSessionVariable(key).to(value);
	}
	// Parameter can be xpath or element which returns WebElementFacade
	public <T> WebElementFacade getElement(T xpathOrElementFacade) {
		WebElementFacade element=null;
		if(xpathOrElementFacade.getClass().equals(String.class))
			element = $(xpathOrElementFacade.toString()).withTimeoutOf(Duration.ofMillis(ProjectVariables.MIN_TIME_OUT));
		else
			element = (WebElementFacade)xpathOrElementFacade;
		return element;
	}
		
	public <T> WebElementFacade highlightElement(T xpathOrElementFacade){
		WebElementFacade element = this.getElement(xpathOrElementFacade);
		if(true) {
			for (int i = 0; i < ProjectVariables.HIGHLIGHT_COUNT; i++) {
				WebDriver driver = getDriver();
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("arguments[0].style.border='5px solid blue'", element);			
				js.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "");
			}
		}
		return element;
	}
	
	
	public void refresh() {
		((JavascriptExecutor)getDriver()).executeScript("document.location.reload()");		
	}
	
	
	public <T> boolean element_displayed(T xpathOrElementFacade){
		boolean flag =false;
		try{
			WebElementFacade element = this.highlightElement(xpathOrElementFacade);
			flag = element.withTimeoutOf(Duration.ofMillis(ProjectVariables.MID_TIME_OUT)).waitUntilVisible().isDisplayed();
		}catch(Exception ignored){
			flag =false;
		}
		return flag;
	}
	
	public <T> boolean click_given_WebElement(T xpathOrElementFacade) {
		boolean flag =false;
		try{
			WebElementFacade element = this.highlightElement(xpathOrElementFacade);
			element.withTimeoutOf(Duration.ofMillis(ProjectVariables.MIN_TIME_OUT)).waitUntilClickable().click();
			flag =true;
		}
		catch(Exception ignored){						
			Assert.fail("Element not found to click the given Webelemnet,"+xpathOrElementFacade+ "failed due to exception "+ignored );
			flag =false;
		}
		return flag;	
	}
	
	public boolean click_given_WebElement(WebElement element) {
		boolean flag =false;
		try{
			element.click();
			flag =true;
		}
		catch(Exception ignored){						
			Assert.fail("Element not found to click the given Webelemnet,"+element+ "failed due to exception "+ignored );
			flag =false;
		}
		return flag;	
	}
	
	public <T> boolean enter_given_Text_Element(T xpathOrElementFacade, String text) {
		try {
			WebElementFacade element = this.highlightElement(xpathOrElementFacade);
			element.withTimeoutOf(Duration.ofMillis(ProjectVariables.MIN_TIME_OUT)).waitUntilVisible().clear();
			element.withTimeoutOf(Duration.ofMillis(ProjectVariables.MIN_TIME_OUT)).waitUntilVisible().sendKeys(text);
			return true;		
		} catch (Exception e) {
			Assert.fail("Element not found to Enter the given Webelemnet,"+xpathOrElementFacade+ " failed due to exception "+e );
			return false;
		}
	}


	public void dynamicWaitForLoadingIcon(String loading) {
		for(int i=0; i<=100;i++) { 
			  try { 
				  SeleniumUtils.defaultWait(500);				 
				  waitForAbsenceOf(loading);
				  break;
		  } catch(Exception e) { 
		  if(i==50)
			  Assert.assertTrue("Loading is taking more time....", false); } }		
	}
	
	public String[] get_All_Text_from_Locator(List<WebElementFacade> elements) {
		try {		
		String[] text= new String[elements.size()];
		for(int i =0;i<elements.size();i++)
		{			
			text[i]=elements.get(i).getText();
			System.out.println(text[i]); 
		}   return text;		
	    }catch (Exception e) {
	    	return null;
	    }
	}
	
	public String[] get_All_Text_from_Locator(String Xpath) {
		try {
		List <WebElement> elements = getDriver().findElements(By.xpath(Xpath));
		String[] text= new String[elements.size()];
		for(int i =0;i<elements.size();i++)
		{			
			text[i]=elements.get(i).getText();
			System.out.println(text[i]); 
		}   return text;
		
	    }catch (Exception e) {
	    	return null;
	    }
	}

	public <T> String get_Text_From_WebElement(T xpathOrElementFacade) {
		WebElementFacade element = this.highlightElement(xpathOrElementFacade);
		return element.withTimeoutOf(Duration.ofMillis(ProjectVariables.MID_TIME_OUT)).waitUntilVisible().getTextValue();
	}
	
	public <T> void moveToElement_and_click(T obj) {
		try{
			Actions builder = new Actions(getDriver());
			builder.moveToElement(getElement(obj)).build().perform();
			builder.click().build().perform();
			//System.out.println("Move To Element Succesful");
		}
		catch(Exception e){
			Assert.fail(e.getMessage());
		}		
	}
	
	public void moveToElement_and_click(WebElement element) {
		try{
			Actions builder = new Actions(getDriver());
			builder.moveToElement(element).build().perform();
			builder.click().build().perform();
		}
		catch(Exception e){
			Assert.fail(e.getMessage());
		}		
	}	
	
	public int getColumnIndexByColumnName(String HeadersXpath,String columnName){
		defaultWait(2000);
		int len = getDriver().findElements(By.xpath(HeadersXpath)).size();
		int index = 0;
		for (int i = 1; i <= len; i++){	
			String cName = $(HeadersXpath+"["+i+"]").getText();
			if(columnName.trim()==cName.trim()){index+=1;break;}			
		}
		if(index==0)Assert.fail(columnName + " not found");
		return index;
	}
	
	public List<String> get_All_Options_From_Brootstarp_Dropdown(String xpath) {
		try {
			return getDriver().findElements(By.xpath("//div[@role='option']//*[text()]")).stream().map(WebElement::getText).collect(Collectors.toList());						
		}catch(Exception ignored){
			Assert.fail("Unable to get the option from select,"+xpath+ "failed due to exception "+ignored );
			return null;
		}
	}
	
	public String select_Bootstrap_Dropdown(String xpath,String option){
		String selectedOption = null;
		try {
			this.click_given_WebElement(xpath);
			SeleniumUtils.defaultWait(100);
			List<String> options =  getDriver().findElements(By.xpath("//div[@role='option']//*[text()]")).stream().map(WebElement::getText).collect(Collectors.toList());
			if(option.contains("random")){
				if(options.size()>1){
					int index = GenericUtils.getRandomNumberBetweenRange(0, options.size()-1);
					option = options.get(index);
				}
				else{option = options.get(0);}			
			}				
			this.enter_given_Text_Element(xpath+"//input[@aria-expanded!='false']", option.trim());
			SeleniumUtils.defaultWait(100);
			$(xpath+"//input[@aria-expanded!='false']").sendKeys(Keys.ENTER);
			SeleniumUtils.defaultWait(100);
			selectedOption = get_Text_From_WebElement(xpath+"//span[@class='ng-value-label ng-star-inserted']");
			Assert.assertTrue(selectedOption.trim().equals(option.trim()));
			System.out.println(selectedOption+" Option selected");
		}catch(Exception ignored){
			Assert.fail("Unable to select option,"+option+" for "+xpath+" , failed due to exception "+ignored );
		}
		return selectedOption;
	}
	
	public List<String> get_All_Options_From_Brootstarp_Dropdown_With_click(String xpath) {
		try {		
			this.click_given_WebElement(xpath);
			if(!element_displayed("//div[@role='option']//*[text()]"))
				this.click_given_WebElement(xpath);
			return getDriver().findElements(By.xpath("//div[@role='option']//*[text()]")).stream().map(WebElement::getText).collect(Collectors.toList());						
		}catch(Exception ignored){
			Assert.fail("Unable to get the option from select,"+xpath+ "failed due to exception "+ignored );
			return null;
		}
	}
	
	public <T> String getRandomOptionFromBootStrap(String xpath) {
		Random r = new Random();
		List<String> options = this.get_All_Options_From_Brootstarp_Dropdown(xpath);
		Optional<String> option = options.stream().filter(opt -> !(opt.trim().equals("")))
				.skip(r.nextInt(options.size() - 1)).findFirst();
		option = options.stream().filter(opt -> !(opt.trim().equals(""))).skip(r.nextInt(options.size() - 1)).findFirst();
		return option.get().toString();
	}
	
	public void click_JS(String xpath){		
		JavascriptExecutor js = (JavascriptExecutor) getDriver();
		js.executeScript("arguments[0].click();", $(xpath));	
	}
	
	public void scrollToElement(String xpath){		
		JavascriptExecutor js = (JavascriptExecutor) getDriver();
		js.executeScript("arguments[0].scrollIntoView(true);", $(xpath));	
	}
}
