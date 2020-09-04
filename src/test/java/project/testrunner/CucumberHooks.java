package project.testrunner;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import net.thucydides.core.annotations.ManagedPages;
import net.thucydides.core.pages.Pages;

public class CucumberHooks {

	@ManagedPages
	private Pages pages;
	
	@After 
    public void cleanUp(Scenario sScenario) throws Exception{
      pages.getDriver().quit();
    }
}
