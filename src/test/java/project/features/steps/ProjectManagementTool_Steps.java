package project.features.steps;

import java.sql.SQLException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Steps;
import project.features.steps.definations.ProjectManagementTool_StepDef;

public class ProjectManagementTool_Steps {

	@Steps
	ProjectManagementTool_StepDef oProjectManagementTool_StepDef;	
	
	
	@Given("^user opened PMT application$")
	public void user_opened_PMT_application() throws Exception {
		oProjectManagementTool_StepDef.open_PMT_Application();		
	}
	
	@Given("^user opened PMT application with suffix of \"([^\"]*)\"$")
	public void user_opened_PMT_application_with_suffix_of(String arg1) throws Exception {
		oProjectManagementTool_StepDef.user_opened_PMT_application_with_suffix_of(arg1);
	}

	@When("^user login to pmt application$")
	public void user_login_to_pmt() throws Exception {
		oProjectManagementTool_StepDef.user_login_to_pmt_application();
	}
	
	@Then("^user should see tool tip for \"([^\"]*)\" as \"([^\"]*)\"$")
	public void user_should_see_tool_tip_for_as(String arg1, String arg2) {
		oProjectManagementTool_StepDef.user_should_see_tool_tip_for_as(arg1,arg2);
	}
	
	@Then("^user should see \"([^\"]*)\" names in PMT Applicaiton database$")
	public void user_should_see_names_in_PMT_Applicaiton_database(String arg1) throws Exception {
		oProjectManagementTool_StepDef.user_should_see_names_in_PMT_Applicaiton_database(arg1);
	}
	
	@Then("^user should see \"([^\"]*)\" Application Mapping for UserName in database$")
	public void user_should_see_Application_Mapping_for_UserName_in_database(String appCount) throws Exception {
		oProjectManagementTool_StepDef.user_should_see_Application_Mapping_for_UserName_in_database(appCount);
	}
	
	@When("^user goto home page$")
	public void user_goto_home_page() throws Exception {
		oProjectManagementTool_StepDef.user_goto_home_page();
	}
	
	@Then("^user should see login page$")
	public void user_should_see_login_page() {
		oProjectManagementTool_StepDef.user_should_see_login_page();
	}
	
	@Then("^username should be in dabase$")
	public void username_should_be_in_dabase() throws Exception {
		oProjectManagementTool_StepDef.username_should_be_in_dabase();
	}
	
	@Then("^validate toop tip on home screen$")
	public void validate_tool_tip_on_home_screen() throws Exception {
		oProjectManagementTool_StepDef.validate_tool_tip_on_home_screen();
	}
	
	
	@Then("^user should see home page$")
	public void user_should_see_home_page() throws SQLException {
		oProjectManagementTool_StepDef.user_should_see_home_page();
	}

	@Then("^verify \"([^\"]*)\" page opened$")
	public void verify_page_opened(String arg1) throws Exception {
		oProjectManagementTool_StepDef.verify_page_opened(arg1);
	}
	
	@When("^user click on \"([^\"]*)\"$")
	public void user_click_on(String arg1) {
		oProjectManagementTool_StepDef.user_click_on(arg1);
	}
	
	@Then("^user should see toaster message as \"([^\"]*)\"$")
	public void user_should_see_toaster_message(String arg1) {
		oProjectManagementTool_StepDef.user_should_see_toaster_message(arg1);
	}
	
	@When("^user click on add project$")
	public void user_click_on_add_project() {
		oProjectManagementTool_StepDef.user_click_on_add_project();
	}
		
	@Then("^user should see \"([^\"]*)\" as \"([^\"]*)\" in page$")
	public void user_should_see_in_page(String arg1,String arg2) {
		oProjectManagementTool_StepDef.user_should_see_in_page(arg1,arg2);
	}
	
	@When("^user click on \"([^\"]*)\" \"([^\"]*)\"$")
	public void user_click_on(String arg1,String arg2) {
		oProjectManagementTool_StepDef.user_click_on(arg1,arg2);
	}	
	
	@When("^user navigate to \"([^\"]*)\" page$")
	public void user_navigate_to_page(String arg1) {
		oProjectManagementTool_StepDef.user_navigate_to_page(arg1);
	}

	@Then("^popup should be displyed$")
	public void popup_should_be_displyed() {
		oProjectManagementTool_StepDef.popup_should_be_displyed();
	}
	
	@Then("^user should see \"([^\"]*)\" page$")
	public void user_should_see_page(String arg1) {
		oProjectManagementTool_StepDef.user_should_see_page(arg1);
	}
	
	@Then("^verify fields displayed in Add Project page$")
	public void verify_fields_displayed_in_Add_Project_page() {
		oProjectManagementTool_StepDef.verify_fields_displayed_in_Add_Project_page();
	}
	
	@Then("^user should validate the result data for client \"([^\"]*)\" UI vs Database columns \"([^\"]*)\"$")
	public void user_should_validate_the_result_data_for_clinet_UI_vs_Database(String clientID,String dbColumns) throws Exception  {
		oProjectManagementTool_StepDef.user_should_validate_the_result_data_for_clinet_UI_vs_Database(clientID,dbColumns);
	}
	
	@Then("^user should see Columns \"([^\"]*)\" in \"([^\"]*)\" table by \"([^\"]*)\" in database with match condition \"([^\"]*)\"$")
	public void user_should_see_Columns_in_table_by_in_database(String columns, String table, String condtions,String match) throws SQLException {
		oProjectManagementTool_StepDef.user_should_see_Columns_in_table_by_in_database(columns,table,condtions,match);
	}
	
	@Then("^user should see \"([^\"]*)\" pop up screen for \"([^\"]*)\"$")
	public void user_should_see_pop_up_screen_for(String popUp, String page) {
		oProjectManagementTool_StepDef.user_should_see_pop_up_screen_for(popUp,page);
	}
	
	@Then("^verify \"([^\"]*)\" fields in \"([^\"]*)\" page$")
	public void verify_fields_in_page(String labelName, String pagename) throws Exception {
		oProjectManagementTool_StepDef.verify_fields_in_page(labelName,pagename);
	}
			
}
