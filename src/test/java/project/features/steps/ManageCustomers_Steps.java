package project.features.steps;

import java.sql.SQLException;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Steps;
import project.features.steps.definations.ManageCustomers_StepDef;

public class ManageCustomers_Steps {

	@Steps
	ManageCustomers_StepDef oManageCustomers_StepDef;		
	
	@Then("^verify \"([^\"]*)\" field Manage Customer in Audit History page$")
	public void verify_field_Manage_Customer_in_Audit_History_page(String arg1) {
		oManageCustomers_StepDef.verify_field_Manage_Customer_in_Audit_History_page(arg1);
	}
	
	@When("^user enter \"([^\"]*)\" in Customer \"([^\"]*)\" page$")
	public void user_enter_in_Customer_page(String field,String page) throws Exception {
		oManageCustomers_StepDef.user_enter_in_Customer_page(field,page);
	}
	
	@Then("^user should see newly added customer$")
	public void user_should_see_newly_added_customer() {
		oManageCustomers_StepDef.user_should_see_newly_added_customer();
	}
	
	@Then("^user should see \"([^\"]*)\" for newly created customer in database$")
	public void user_should_see_for_newly_created_customer_in_database(String arg1) throws SQLException {
		oManageCustomers_StepDef.user_should_see_for_newly_created_customer_in_database(arg1);
	}
	
	@Then("^verify \"([^\"]*)\" Customer popup with close$")
	public void verify_Customerpopup_with_close(String page) {
		oManageCustomers_StepDef.verify_Customerpopup_with_close(page);
	}
	
	@Then("^verify \"([^\"]*)\" field in \"([^\"]*)\" Customer page$")
	public void verify_field_in_Customer_page(String field,String page) throws Exception {
		oManageCustomers_StepDef.verify_field_in_Customer_page(field,page);
	}
	
	@When("^user enter \"([^\"]*)\" in Manage Customer-Audit History page$")
	public void user_enter_in_Manage_Customer_Audit_History_page(String arg1) {
		oManageCustomers_StepDef.user_enter_in_Manage_Customer_Audit_History_page(arg1);
	}
	
	@Then("^validate \"([^\"]*)\" filed does not accept spaces in \"([^\"]*)\" Customer Page$")
	public void validate_filed_does_not_accept_spaces_in_Customer_Page(String filedName, String page) {
		oManageCustomers_StepDef.validate_filed_does_not_accept_spaces_in_Customer_Page(filedName,page);
	}
	
	@When("^user should click \"([^\"]*)\" for \"([^\"]*)\" in Customer \"([^\"]*)\" page$")
	public void user_should_click_in_Customer_page(String button,String criteria,String page) throws Exception {
		oManageCustomers_StepDef.user_should_click_in_Customer_page(button,criteria,page);
	}
	
	@Then("^user shoud see Customer History \"([^\"]*)\" as \"([^\"]*)\" in database$")
	public void user_should_see_customer_history_in_database(String columnName, String value) throws SQLException {
		oManageCustomers_StepDef.user_should_see_customer_history_in_database(columnName,value);
	}
	
	@When("^user get \"([^\"]*)\" for \"([^\"]*)\" from database$")
	public void user_get_for_from_database(String key, String databseParameter) throws SQLException {
		oManageCustomers_StepDef.user_get_for_from_database(key,databseParameter);
	}
	
	@Then("^user should see results for \"([^\"]*)\" in Customer \"([^\"]*)\" page$")
	public void user_should_see_results_for_in_Customer_page(String columns, String page) {
		oManageCustomers_StepDef.user_should_see_results_for_in_Customer_page(columns,page);
	}
	
	@When("^user get \"([^\"]*)\" from database$")
	public void user_get_from_database(String key) throws Exception {
		oManageCustomers_StepDef.user_get_from_database(key);
	}
	
	@When("^validate \"([^\"]*)\" columns are \"([^\"]*)\"$")
	public void validate_columns_are(String columns,String action) throws Exception {
		oManageCustomers_StepDef.validate_columns_are(columns,action);
	}
}
