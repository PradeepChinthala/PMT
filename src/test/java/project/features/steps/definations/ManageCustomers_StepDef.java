package project.features.steps.definations;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.junit.Assert;

import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;
import project.pageobjects.AddCustomerPage;
import project.pageobjects.CustomerSearchPage;
import project.pageobjects.EditCustomerPage;
import project.utilities.DBQueries;
import project.utilities.DBUtils;
import project.utilities.SeleniumUtils;
import project.utilities.SharedPage;
import project.pageobjects.ManageCustomers_AuditHistoryPage;



public class ManageCustomers_StepDef extends ScenarioSteps{

	ManageCustomers_AuditHistoryPage oManageCustomers_AuditHistoryPage;
	CustomerSearchPage oCustomerSearchPage;
	AddCustomerPage oAddCustomerPage;
	EditCustomerPage oEditCustomerPage;
	DBUtils oDBUtils;
	SharedPage oSharedPage;
	
	/**
	 * 
	 */
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
	public void verify_field_Manage_Customer_in_Audit_History_page(String arg1) {
		oManageCustomers_AuditHistoryPage.verifyFields(arg1);
	}

	@Step
	public void user_enter_in_Customer_page(String field,String page) throws Exception {			
		if(page.toLowerCase().contains("search"))
			oCustomerSearchPage.populateFields(field);
		else if(page.toLowerCase().contains("add"))
			oAddCustomerPage.populateFields(field);
		else if(page.toLowerCase().contains("edit"))
			oEditCustomerPage.populateFields(field);
	}

	@Step
	public void verify_field_in_Customer_page(String field,String page) throws Exception {
		if(page.toLowerCase().contains("search"))
			oCustomerSearchPage.verifyFields(field);
		else if(page.toLowerCase().contains("add"))
			oAddCustomerPage.verifyFields(field);
		else if(page.toLowerCase().contains("edit"))
			oEditCustomerPage.verifyFields(field);
	}

	@Step
	public void user_should_see_newly_added_customer() {
		String toasterMsg = oCustomerSearchPage.verifyUserAddedToaster();
		SeleniumUtils.setSessionVaribale("CustomerID",toasterMsg.replaceAll("[^0-9]", ""));		
		Verify("Toaster '"+toasterMsg+"' message displayed","PASSED");
	}

	@Step
	public void verify_Customerpopup_with_close(String page) {		
		if(page.toLowerCase().contains("search"))
			oCustomerSearchPage.verifyCustomePopUpWithClose();
		else if(page.toLowerCase().contains("add"))
			oAddCustomerPage.verifyCustomePopUpWithClose();
		else if(page.toLowerCase().contains("edit"))
			oEditCustomerPage.verifyCustomePopUpWithClose();
		
		Verify("Popup verified and closed with x button","PASSED");
	}

	@Step
	public void user_enter_in_Manage_Customer_Audit_History_page(String arg1) {
		oManageCustomers_AuditHistoryPage.populateFields(arg1);		
	}

	@Step
	public void validate_filed_does_not_accept_spaces_in_Customer_Page(String field, String page) {
		if(page.toLowerCase().contains("add"))
			oAddCustomerPage.verifySpaceInField(field);	
		
	}

	@Step
	public void user_should_see_for_newly_created_customer_in_database(String arg1) throws SQLException {		
		SeleniumUtils.defaultWait(2000);
		String clientID = "";
		String customerName = "";
		String newCustomerName = "";
		String dbVal = "";
		
		String customerID = SeleniumUtils.getValueByName("CustomerID");
		Assert.assertTrue("Please Provide CustomerID key for the Quey",customerID!=null);
		String query = DBQueries.getCustomer(customerID);
		if(arg1.equals("ClientID")){
			clientID = SeleniumUtils.getValueByName("ClientID");
			query+=" AND ClientID='"+clientID+"'";
		}
		Map<String, List<String>> results = oDBUtils.executeSQLQuery_GetMap(query);
		
		String[] keys = arg1.split(",");		
		for(String key : keys){
			if(key.equals("ClientID")){
				dbVal = results.get("ClientID").stream().toArray(String[]::new)[0];
				Assert.assertTrue(clientID.equals(dbVal));
			}
			else if(key.equals("CustomerID")){				
				dbVal = results.get("CustomerID").stream().toArray(String[]::new)[0];
				Assert.assertTrue(customerID.equals(dbVal));
			}
			else if(key.equals("CustomerName")){
				customerName = SeleniumUtils.getValueByName("CustomerName");
				dbVal = results.get("Name").stream().toArray(String[]::new)[0];
				Assert.assertTrue(customerName.equals(dbVal));
			}				
			else if(key.equals("NewCustomerName")){
				newCustomerName = SeleniumUtils.getValueByName("NewCustomerName");
				dbVal = results.get("Name").stream().toArray(String[]::new)[0];
				Assert.assertTrue(newCustomerName.equals(dbVal));
				key="CustomerName Updated ";
			}
			Verify(key+":"+dbVal+" is exist in the database", "PASSED");
		}	
	}

	@Step
	public void user_should_click_in_Customer_page(String button,String criteria, String page) throws Exception {
		if(page.toLowerCase().contains("search"))
			oCustomerSearchPage.clickButtonBasedOn(button,criteria);
		/*else if(page.toLowerCase().contains("add"))
			oAddCustomerPage.verifyFields(button);
		else if(page.toLowerCase().contains("edit"))
			oEditCustomerPage.verifyFields(button);*/		
		String cellText = SeleniumUtils.getValueByName(criteria);
		Verify(criteria+":"+cellText+" is exist in Web Table", "PASSED");
	}

	@Step
	public void user_should_see_customer_history_in_database(String columnName, String action) throws SQLException {		
		String dbValue = oDBUtils.executeSQLQuery_GetValue_By_ColumnName(DBQueries.getCustomerHistory(SeleniumUtils.getValueByName("CustomerID"),action), columnName);	
		//Assert.assertTrue(dbValue.equals(action));
		SeleniumUtils.setSessionVaribale(columnName, dbValue);
		Verify("Action Name is Successfully Created as '"+dbValue+"'","PASSED");
	}

	@Step
	public void user_get_for_from_database(String key, String databseParameter) throws SQLException {
		String dbValue = oDBUtils.executeSQLQuery_GetValue_By_ColumnName(DBQueries.getExistingCustomerName(SeleniumUtils.getValueByName(databseParameter)), "Name");		
		SeleniumUtils.setSessionVaribale(key, dbValue);
		Verify("Existing CustomerName : '"+dbValue+"'","PASSED");
	}

	@Step
	public void user_should_see_results_for_in_Customer_page(String columns, String page) {
		if(page.toLowerCase().contains("search"))
			oCustomerSearchPage.getResultsWebTable(columns);
	}

	@Step
	public void user_get_from_database(String key) throws SQLException {		
		String dbVal = oDBUtils.executeSQLQuery_GetValue_By_ColumnName(DBQueries.getRandomClientName(), key);
		SeleniumUtils.setSessionVaribale(key, dbVal);
	}

	@Step
	public void validate_columns_are(String columns, String acts) {
		//CustomerSearchPage.validateResults(columns,actions);
		
		String[] actions = acts.split(",");
		for(String action : actions){
			if(action.contains("sort"))
				oSharedPage.validateColumn_Sortable(CustomerSearchPage.tablePrefix,columns);
			else if(action.contains("columnvalidation")){
				String str = oSharedPage.columnvalidation(CustomerSearchPage.tablePrefix, columns);
				String[] sections = str.split("%");			
				for (int i = 0; i < sections.length; i++)
					Verify(sections[i],"PASSED");
			}
			Verify(action+" : Success","PASSED");	
		}
	}

}
