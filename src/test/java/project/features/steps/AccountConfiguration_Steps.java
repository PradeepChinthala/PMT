package project.features.steps;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Steps;
import project.features.steps.definations.AccountConfiguration_StepDef;

public class AccountConfiguration_Steps {
	
	@Steps
	AccountConfiguration_StepDef oAccountConfiguration_StepDef;
	
	
	@When("^user enter fields in account configuration \"([^\"]*)\" in audit history page$")
	public void user_enter_fields_in_accountconfig_audit_history_page(String arg1) {
		oAccountConfiguration_StepDef.user_enter_fields_in_accountconfig_audit_history_page(arg1);
	}
	
	@When("^user click project details in audit history$")
	public void user_click_project_details_in_audit_history() {
		oAccountConfiguration_StepDef.user_click_project_details_in_audit_history();
	}
	
	@Then("^user should see \"([^\"]*)\" in change info popup$")
	public void user_should_see_in_change_info_popup(String arg1) {
		oAccountConfiguration_StepDef.user_should_see_in_change_info_popup(arg1);
	}
	
	@When("^user click project details in audit history for \"([^\"]*)\" action$")
	public void user_click_project_details_in_audit_history_for_action(String action) {
		oAccountConfiguration_StepDef.user_click_project_details_in_audit_history_for_action(action);
	}


	@Then("^user should see \"([^\"]*)\" information change info popup$")
	public void user_should_see_information_change_info_popup(String action) {
		oAccountConfiguration_StepDef.user_should_see_information_change_info_popup(action);
	}

	@Then("^user close change info popup$")
	public void user_close_change_info_popup() {
		oAccountConfiguration_StepDef.user_close_change_info_popup();
	}

}
