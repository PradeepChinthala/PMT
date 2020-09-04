package project.testrunner;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions( plugin = {"pretty", "html:target/cucumber-html-report"},
features="Features",glue={"project.features.steps"},tags={"@RAP-10905"},monochrome = true,dryRun=false)
public class TestRunner {

}
