package project.features.steps;

import java.text.ParseException;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Steps;
import project.features.steps.definations.ManageProjects_StepDef;

public class ManageProjects_Steps {
	
	
	@Steps
	ManageProjects_StepDef oManageProjects_Steps;
		
	@Then("^verify \"([^\"]*)\" field in project search page$")
	public void verify_filed_in_project_search_page(String arg1) {
		oManageProjects_Steps.verify_field_in_project_search_page(arg1);
	}
	
	@Then("^verify \"([^\"]*)\" field in Add Project page$")
	public void verify_field_in_Add_Project_page(String arg1) throws ParseException {
		oManageProjects_Steps.verify_field_in_Add_Project_page(arg1);
	}
	
	@When("^user enter fields \"([^\"]*)\" in search page$")
	public void user_enter_fields_in_search_page(String arg1) {
		oManageProjects_Steps.user_enter_fields_in_search_page(arg1);
	}
	
	@Then("^verify \"([^\"]*)\" field in \"([^\"]*)\" Project page$")
	public void verify_field_in_Project_page(String field,String page) throws Exception {
		oManageProjects_Steps.verify_field_in_Project_page(field,page);
	}
	
	@Then("^user should see \"([^\"]*)\" sorted$")
	public void user_should_see_sorted(String arg1) {
		oManageProjects_Steps.user_should_see_sorted(arg1);
	}	

	@Then("^user should be able to search \"([^\"]*)\" column data in resulted grid$")
	public void user_should_be_able_to_search_column_data_in_resulted_grid(String column) {
		oManageProjects_Steps.user_should_be_able_search_with_given_criteria(column);
	}	

	@Then("^validate pagination$")
	public void validate_pagination(){
		oManageProjects_Steps.validate_pagination();
	}	
	
	@Then("^user should see rows \"([^\"]*)\" accordingly$")
	public void user_should_see_rows_accordingly(String rows) {
		oManageProjects_Steps.user_should_see_rows_accordingly(rows);
	}	

	@Then("^user should see \"([^\"]*)\" madatory fields with error message$")
	public void user_should_see_madatory_fields_with_error_message(String arg1) {
		oManageProjects_Steps.user_should_see_madatory_fields_with_error_message(arg1);
	}
	
	@Then("^user should see column names \"([^\"]*)\"$")
	public void user_should_see_column_names(String arg1) {
		oManageProjects_Steps.user_should_see_column_names(arg1);
	}
	
	@When("^user search with \"([^\"]*)\" Audit history$")
	public void user_searh_with_audit_history(String arg1) throws ParseException {
		oManageProjects_Steps.user_searh_with_audit_history(arg1);
	}
	
	@Then("^user should not see \"([^\"]*)\" in Change Info$")
	public void user_should_not_see_in_change_info(String arg1) throws ParseException {
		oManageProjects_Steps.user_should_not_see_in_change_info(arg1);
	}
	
	@Then("^verify \"([^\"]*)\" field in Audit history page$")
	public void verify_field_in_Audit_history_page(String arg1) throws ParseException {
		oManageProjects_Steps.verify_field_in_Audit_history_page(arg1);
	}
	
	@When("^user enter \"([^\"]*)\" fields in \"([^\"]*)\" Project page$")
	public void user_enter_fields_in_Project_page(String arg1,String arg2) throws Exception {
		oManageProjects_Steps.user_enter_fields_in_Project_page(arg1,arg2);
	}

	@Then("^verify \"([^\"]*)\" created in UI$")
	public void verify_created_in_UI(String arg1) {
		oManageProjects_Steps.verify_created_in_UI(arg1);
	}

	@Then("^user should see \"([^\"]*)\" as \"([^\"]*)\" change info page$")
	public void user_should_see_as_change_info_page(String column,String expected) {
		oManageProjects_Steps.user_should_see_as_change_info_page(column,expected);
	}
	
	@Then("^user should see \"([^\"]*)\" message toaster$")
	public void user_should_see_message_toaster(String arg1) {
		oManageProjects_Steps.user_should_see_message_toaster(arg1);
	}
	
	@Then("^verify \"([^\"]*)\" in database$")
	public void verify_in_database(String arg1) throws Exception {
		oManageProjects_Steps.verify_in_database(arg1);
	}
	
	@Then("^user click cancel button in Add Project page$")
	public void user_click_cancel_button_in_add_project_page(){
		oManageProjects_Steps.user_click_cancel_button_in_add_project_page();
	}
	
	@Then("^verify error messages for \"([^\"]*)\" fields in Add Project page$")
	public void verify_error_messages_for_fields_in_Add_Project_page(String arg1){
		oManageProjects_Steps.verify_error_messages_for_fields_in_Add_Project_page(arg1);
	}
	
	@Then("^user should verify prompt popup and click \"([^\"]*)\"$")
	public void user_should_verify_prompt_popup_and_click(String arg1){
		oManageProjects_Steps.user_should_verify_prompt_popup_and_click(arg1);
	}
	
	@When("^user should click \"([^\"]*)\" for \"([^\"]*)\" in Project \"([^\"]*)\" page$")
	public void user_should_click_in_Project_page(String button,String criteria,String page) throws Exception {
		oManageProjects_Steps.user_should_click_in_Project_page(button,criteria,page);
	}
}
