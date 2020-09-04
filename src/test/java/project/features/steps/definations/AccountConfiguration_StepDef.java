package project.features.steps.definations;

import java.util.Arrays;

import org.junit.Assert;

import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;
import project.pageobjects.AccountConfig_AuditHistoryPage;

public class AccountConfiguration_StepDef extends ScenarioSteps{


	/**
	 * 
	 */
	AccountConfig_AuditHistoryPage oAccountConfig_AuditHistoryPage;
	
	private static final long serialVersionUID = 1L;

	public static void Verify(String StepDetails, String sStatus) {
        if (sStatus.equalsIgnoreCase("PASSED")) {
            System.out.println(StepDetails);
            Serenity.recordReportData().withTitle(StepDetails).andContents(sStatus);
            Assert.assertTrue(StepDetails, true);
        } else {
            Serenity.recordReportData().withTitle(StepDetails).andContents(sStatus);
            Serenity.takeScreenshot();
            System.out.println(StepDetails);
            Assert.assertTrue(StepDetails, false);
        }
    }
	
	@Step
	public void user_enter_fields_in_accountconfig_audit_history_page(String arg1) {
		oAccountConfig_AuditHistoryPage.populateFields(arg1);
	}

	@Step
	public void user_click_project_details_in_audit_history() {
		oAccountConfig_AuditHistoryPage.clickDetails();		
	}

	@Step
	public void user_should_see_in_change_info_popup(String arg1) {
		String[] names = oAccountConfig_AuditHistoryPage.verifyPopupDetails(arg1);
		Verify(Arrays.toString(names) +" were displayed","PASSED");
	}
	@Step
	public void user_click_project_details_in_audit_history_for_action(String action) {
		oAccountConfig_AuditHistoryPage.clickDetails(action);
	}
	@Step
	public void user_should_see_information_change_info_popup(String action) {
		oAccountConfig_AuditHistoryPage.verifyChangeInfoTable(action);		
	}
	@Step
	public void user_close_change_info_popup() {
		oAccountConfig_AuditHistoryPage.closeChangeInfo();
	}

}
