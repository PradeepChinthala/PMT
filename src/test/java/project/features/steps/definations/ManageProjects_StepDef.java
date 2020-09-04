package project.features.steps.definations;

import java.text.ParseException;
import java.util.Arrays;

import org.junit.Assert;

import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;
import project.pageobjects.AddProjectPage;
import project.pageobjects.AuditHistoryPage;
import project.pageobjects.ChangeInfo_Popup_Page;
import project.pageobjects.EditProjectPage;
import project.pageobjects.SearchProjectPage;
import project.utilities.DBQueries;
import project.utilities.DBUtils;
import project.utilities.SeleniumUtils;

public class ManageProjects_StepDef extends ScenarioSteps{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	AddProjectPage oAddProjectPage;
	SearchProjectPage oSearchProjectPage;
	AuditHistoryPage oAuditHistoryPage;
	DBUtils oDBUtils;
	EditProjectPage oEditProjectPage;
	SeleniumUtils oSeleniumUtils;
	ChangeInfo_Popup_Page  oChangeInfo_Popup_Page;
	

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
	public void user_enter_fields_in_Project_page(String field,String page) throws Exception {
		String str = "";
		if(page.toLowerCase().contains("search"))
			oSearchProjectPage.populateFields(field);
		else if(page.toLowerCase().contains("add"))
			str  = oAddProjectPage.populateFields(field);
		else if(page.toLowerCase().contains("edit"))
			str = oEditProjectPage.populateFields(field);		
		String[] sections = str.split(";");			
		for (int i = 0; i < sections.length; i++)
			Verify(sections[i],"PASSED");
	}

	@Step
	public void verify_created_in_UI(String arg1) {
		String msg = oSearchProjectPage.verifyProjectIdCreated();		
		SeleniumUtils.setSessionVaribale(arg1, msg.replaceAll("[^0-9]", ""));
		Verify(msg,"PASSED");
	}

	@Step
	public void verify_in_database(String arg1) throws Exception{
		String sqlQuery = DBQueries.getNewlyAddedProject(SeleniumUtils.getValueByName(arg1));		
		String result = oDBUtils.executeSQLQuery_GetValue_By_ColumnName(sqlQuery, arg1);	
		Assert.assertTrue(SeleniumUtils.getValueByName(arg1).contains(result));
		Verify(arg1+" '"+ SeleniumUtils.getValueByName(arg1)+"' is Displayed in Database","PASSED");
	}

	@Step
	public void verify_error_messages_for_fields_in_Add_Project_page(String arg1) {
		String[] messages = oAddProjectPage.getErroMessages();
		String[] eList = arg1.split(",");
		for(int i=0;i<messages.length;i++){
			boolean bln = false;
			for(int j=0;i<eList.length;j++){
				if(messages[i].contains(eList[j])){
					bln = true;
					break;
				}
			}
			Assert.assertTrue(messages[i]+" does not contains in "+Arrays.toString(messages),bln);
		}		
		Verify(Arrays.toString(messages)+" were displayed","PASSED");
	}

	@Step
	public void user_click_cancel_button_in_add_project_page() {
		oAddProjectPage.clickCancel();	
	}

	@Step
	public void user_should_verify_prompt_popup_and_click(String arg1) {
		Verify(oAddProjectPage.verifyPopup(arg1),"PASSED");		
	}
	
	@Step
	public void verify_field_in_project_search_page(String arg1) {
		oSearchProjectPage.verifyFields(arg1);
		Verify("'"+arg1+"' field is verified with all conditions","PASSED");
	}
	
	@Step
	public void verify_field_in_Add_Project_page(String arg1) {
		oAddProjectPage.verifyFields(arg1);
		Verify("'"+arg1+"' field is verified with all conditions","PASSED");
	}
	
	@Step
	public void user_enter_fields_in_search_page(String arg1) {
		oSearchProjectPage.populateFields(arg1);		
	}
	
	@Step
	public void user_should_see_sorted(String arg1) {
		oSearchProjectPage.validateSearchResultGrid(arg1);		
	}
	
	@Step
	public void user_should_be_able_search_with_given_criteria(String column) {
		String[] values = oSearchProjectPage.validateSearchFiledByResultedColumnData(column);	
		Verify("Resulted Data : "+Arrays.toString(values),"PASSED");
	}
	
	@Step
	public void validate_pagination() {
		oSearchProjectPage.validatePagination();
	}
	
	@Step
	public void user_should_see_rows_accordingly(String rows) {
		oSearchProjectPage.validateRows(rows);		
	}
	
	@Step("Verify Mandatory Messages")
	public void user_should_see_madatory_fields_with_error_message(String arg1) {
		oSearchProjectPage.verifymandatoryMessages(arg1);
		Verify(" Mandatory message displayed for => "+arg1,"PASSED");
	}
	
	@Step
	public void user_should_see_column_names(String arg1) {
		oAuditHistoryPage.verifyColumnNames(arg1);	
		Verify("["+arg1+"] columns dispalyed","PASSED");
	}
	
	@Step
	public void user_searh_with_audit_history(String arg1) throws ParseException {
		oAuditHistoryPage.populateFields(arg1);
	}
	
	@Step
	public void verify_field_in_Audit_history_page(String arg1) throws ParseException {
		oAuditHistoryPage.verifyFields(arg1);		
		Verify("'"+arg1+"' field is verified with all conditions","PASSED");
	}

	@Step
	public void user_should_click_in_Project_page(String button, String criteria, String page) {
		if(page.toLowerCase().contains("search"))
			oSearchProjectPage.clickButtonBasedOn(button,criteria);
		/*else if(page.toLowerCase().contains("add"))
			oAddCustomerPage.verifyFields(button);
		else if(page.toLowerCase().contains("edit"))
			oEditCustomerPage.verifyFields(button);*/		
		String cellText = SeleniumUtils.getValueByName(criteria);
		Verify(criteria+":"+cellText+" is exist in Web Table", "PASSED");
	}

	@Step
	public void verify_field_in_Project_page(String field, String page) {
		if(page.toLowerCase().contains("edit"))
			oEditProjectPage.verifyFields(field);
		else if(page.toLowerCase().contains("search"))
			oSearchProjectPage.verifyFields(field);
		/*else if(page.toLowerCase().contains("search"))
			oEditCustomerPage.verifyFields(field);*/		
	}

	@Step
	public void user_should_see_message_toaster(String arg1) {
		String text = oSeleniumUtils.get_Text_From_WebElement("//div[@id='toast-container']//div[normalize-space(text())='"+arg1+"']");	
		if(!arg1.contains(text))
			Assert.fail(arg1+" toaster message not displayed");
		Verify(arg1+" is dispalyed","PASSED");
	}

	@Step
	public void user_should_see_as_change_info_page(String arg1,String arg2) {
		// TODO Auto-generated method stub
		oAuditHistoryPage.validateChangeInfo(arg1, arg2);
		Verify(arg2+" is dispalyed in "+arg1,"PASSED");
	}

	@Step
	public void user_should_not_see_in_change_info(String arg1) {
		String[] labels = oChangeInfo_Popup_Page.getLables();
		String[] expected = arg1.split(",");
		for(String label : expected){
			boolean contains = Arrays.stream(labels).anyMatch(label::equals);
			Assert.assertFalse(contains);
		}
	}
}
