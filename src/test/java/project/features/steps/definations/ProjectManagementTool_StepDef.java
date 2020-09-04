package project.features.steps.definations;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.junit.Assert;

import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.environment.EnvironmentSpecificConfiguration;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;
import net.thucydides.core.util.EnvironmentVariables;
import net.thucydides.core.util.SystemEnvironmentVariables;
import project.pageobjects.AccountConfigurationPage;
import project.pageobjects.AddProjectPage;
import project.pageobjects.AuditHistoryPage;
import project.pageobjects.CustomerSearchPage;
import project.pageobjects.HomePage;
import project.pageobjects.LoginPage;
import project.pageobjects.SearchProjectPage;
import project.utilities.DBQueries;
import project.utilities.DBUtils;
import project.utilities.GenericUtils;
import project.utilities.SeleniumUtils;
import project.utilities.SharedPage;

public class ProjectManagementTool_StepDef extends ScenarioSteps{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	LoginPage ologinPage;
	HomePage oHomePage;
	SearchProjectPage oSearchProjectPage;
	AccountConfigurationPage oAccountConfigurationPage;
	AuditHistoryPage oAuditHistoryPage;
	AddProjectPage oAddProjectPage;
	SharedPage oSharedPage;
	DBUtils oDBUtils;
	CustomerSearchPage oCustomerSearchPage;
	
	
	EnvironmentVariables environmentVariables;
	
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
	public void user_login_to_pmt_application() throws Exception {
		ologinPage.logIn();	
	}
	
	@Step
	public void open_PMT_Application() throws Exception {
		ologinPage.open_PMT_Application();	
		EnvironmentVariables variables = SystemEnvironmentVariables.createEnvironmentVariables();
		Verify("PMT Application Launched in '"+variables.getProperty("environment")+"' environment","PASSED");
	}

	@Step
	public void user_click_on(String arg1) {
		oHomePage.gotoPage(arg1);		
	}

	@Step
	public void user_click_on_add_project() {
		oSearchProjectPage.addProject();		
	}

	@Step
	public void user_should_see_home_page() throws SQLException {
		oHomePage.validateHomePage();		
	}

	@Step
	public void user_should_see_login_page() {
		ologinPage.validate_loginPage();		
	}

	@Step
	public void username_should_be_in_dabase() throws Exception {
		String uName = EnvironmentSpecificConfiguration.from(environmentVariables).getProperty("username");
		String sqlQuery = DBQueries.getUserName(uName);		
		String userName = oDBUtils.executeSQLQuery(sqlQuery, 1);	
		Verify(userName+"' is Displayed in Database","PASSED");	
	}

	@Step
	public void validate_tool_tip_on_home_screen() {		
		Verify(Arrays.toString(oHomePage.validateToolTip())+"' Were Displayed in Home Page","PASSED");	
	}

	@Step
	public void verify_page_opened(String arg1) throws Exception {
		boolean bln ;
		switch(arg1){		
		case "Account Configuration":
			bln = oAccountConfigurationPage.veriyfAccountConfigurationPage();
			break;
		case "Manage Projects":
			bln = oSearchProjectPage.verifySearchProjectpage();
			break;
		default:			
			throw new IllegalArgumentException("Please Provide Correct Page Name In Gherkin");
		}
		Assert.assertTrue(arg1+ " is not displayed", bln);
		Verify(arg1+"is Displayed","PASSED");		
	}

	@Step
	public void user_goto_home_page() {
		boolean bln = oHomePage.gotoHomePage();	
		Assert.assertTrue("Unable to Navigate To Home Page", bln);
		Verify("Home page is displayed","PASSED");
	}	

	@Step
	public void user_opened_PMT_application_with_suffix_of(String arg1) throws Exception {
		ologinPage.open_PMT_Application(arg1);			
	}

	@Step
	public void user_should_see_in_page(String arg1,String arg2) {
		boolean bln = ologinPage.pageDisplayed(arg1,arg2);	
		Assert.assertTrue(arg1+" is Not displayed",bln);
		Verify(arg1+" is displayed","PASSED");
	}

	@Step
	public void user_click_on(String arg1, String arg2) {		
		ologinPage.click(arg1,arg2);
		Verify(arg1+" is displayed","PASSED");
	}
	
	@Step("Pop up displayed")
	public void popup_should_be_displyed() {
		oAuditHistoryPage.verifyPopup();		
	}

	@Step
	public void user_navigate_to_page(String arg1) {
		if(arg1.toLowerCase().contains("back"))
			getDriver().navigate().back();	
		else
			getDriver().navigate().forward();
	}

	@Step
	public void user_should_see_page(String arg1) {
		oHomePage.verifyPageOpened(arg1);
		Verify(arg1+" page is displayed","PASSED");
	}

	@Step
	public void verify_fields_displayed_in_Add_Project_page() {
		String[] fields = oAddProjectPage.verifyAllFieldsDispalyed(); 
		Verify(Arrays.toString(fields),"PASSED");
	}

	@Step
	public void user_should_see_tool_tip_for_as(String arg1, String arg2) {
		String toolTipMsg = oHomePage.verifyToolTip(arg2);
		Assert.assertTrue(toolTipMsg.equals(arg2));
		Verify("ToolTip message '"+toolTipMsg+"' displayed for '"+arg1,"PASSED");
	}

	@Step
	public void user_should_see_names_in_PMT_Applicaiton_database(String arg1) throws SQLException {
		String sqlQuery = DBQueries.getProectNames();	
		Map<String, List<String>> results = oDBUtils.executeSQLQuery_GetMap(sqlQuery);
		String[] projects = results.get("Name").stream().toArray(String[]::new);
		List<String> appID = results.get("ApplicationID");
		Assert.assertTrue(projects.length>=Integer.parseInt(arg1));
		for(int i=0;i<projects.length;i++)
			Verify("ApplicationID:"+appID.get(i).toString()+",Name:"+projects[i]+" were displayed in DB", "PASSED");
	}

	@Step
	public void user_should_validate_the_result_data_for_clinet_UI_vs_Database(String clientID,String dbColumns) throws Exception {		
		String query = DBQueries.getCustomers(clientID);
		Map<String, List<String>> dbResult = oDBUtils.executeSQLQuery_GetMap(query);
		Map<String, List<String>> uiResult = Serenity.sessionVariableCalled("UIResultData");
		String[] columns = dbColumns.split(",");
		for(int i=0;i<columns.length;i++){
			String[] dbValues = dbResult.get(columns[i]).stream().toArray(String[]::new);
			if(columns[i].equals("CustomerID")){
				String[] uivalues = uiResult.get("Customer ID").stream().toArray(String[]::new);
				boolean bln = Arrays.equals(uivalues,dbValues);
				Assert.assertTrue(bln);
			}
			else if(columns[i].equals("Name")){
				String[] uivalues = uiResult.get("Customer Name").stream().toArray(String[]::new);
				boolean bln = Arrays.equals(uivalues,dbValues);
				Assert.assertTrue(bln);
			}
			else if(columns[i].equals("CreationDT")){
				String[] uivalues = Arrays.stream(uiResult.get("Created Date").stream()
						.toArray(String[]::new)).map(s->s.split(" ")[0]).toArray(String[]::new);
				String[] db_updated = new String[dbValues.length];
				for(int j=0;j<dbValues.length;j++)
					db_updated[j] = GenericUtils.convertDateToUIFormat(dbValues[j].split(" ")[0]);
				boolean bln = Arrays.equals(uivalues,db_updated);
				Assert.assertTrue("'CreationDT' is not matched with 'Created Date'",bln);
			}
			else if(columns[i].equals("ModifiedDT")){
				String[] uivalues = Arrays.stream(uiResult.get("Modified Date").stream().toArray(String[]::new))
						.map(s->s.split(" ")[0]).toArray(String[]::new);
				String[] db_updated = new String[dbValues.length];
				for(int j=0;j<dbValues.length;j++)
					db_updated[j] = GenericUtils.convertDateToUIFormat(dbValues[j].split(" ")[0]);
				boolean bln = Arrays.equals(uivalues,db_updated);
				Assert.assertTrue("'ModifiedDT' is not matched with 'Modified Date'",bln);
			}
			else if(columns[i].equals("ModifiedBy")){
				String[] uivalues = Arrays.stream(uiResult.get("Modified By").stream().toArray(String[]::new)).
						map(s->s.replace(" ","").trim()).toArray(String[]::new);
				String[] db_updated = new String[dbValues.length];
				for(int j=0;j<dbValues.length;j++){
					String cquery = DBQueries.getUserNameByID(dbValues[j]);
					String res = oDBUtils.executeSQLQuery_GetValue_By_ColumnName(cquery, "FullName");
					db_updated[j] = res==null?"":res;					
				}					
				boolean bln = Arrays.equals(uivalues,db_updated);
				Assert.assertTrue("'ModifiedDT' is not matched with 'Modified Date'",bln);
			}
			Verify(columns[i].toString()+":"+Arrays.toString(dbValues)+" were matched with UI vs Database","PASSED");
		}		
	}

	@Step
	public void user_should_see_Application_Mapping_for_UserName_in_database(String appCount) throws SQLException {
		String userName = EnvironmentSpecificConfiguration.from(environmentVariables).getProperty("username");
		String userID = oDBUtils.executeSQLQuery_GetValue_By_ColumnName(DBQueries.getUserIDByUserName(userName), "UserID");
		List<String> mappingInfo = oDBUtils.executeSQLQuery_Get_Entire_ColumnData(DBQueries.getApplicationUserMappingBYUserID(userID), "MappingID");
		Assert.assertTrue("Mapping Applciation count not matched, Expected count is: "+appCount,Integer.valueOf(appCount)==mappingInfo.size());
		Verify("Mapping Id's are :"+mappingInfo.toString(), "PASSED");
	}

	@Step
	public void user_should_see_toaster_message(String arg1) {
		String toasterMsg = oCustomerSearchPage.verifyUserAddedToaster();
		Assert.assertTrue(toasterMsg.length()!=0);
		Verify(arg1+": '"+toasterMsg+"' toaster message displayed","PASSED");
	}

	@Step
	public void user_should_see_Columns_in_table_by_in_database(String columns, String table, String conditions,String match) throws SQLException {		
		String query = 	DBQueries.formQueryBy(table, conditions);		
		Map<String, List<String>> dbResult = oDBUtils.executeSQLQuery_GetMap(query);
		String[] columnsToVerify = columns.split(",");
		for(int i=0;i<columnsToVerify.length;i++){
			String[] col = columnsToVerify[i].split(":");
			List<String> dbValues = Arrays.asList(dbResult.get(col[0]).stream().toArray(String[]::new));
			String value = SeleniumUtils.getValueByName(col[1])==null?col[1]:SeleniumUtils.getValueByName(col[1]);			
			boolean bln = match.equals("any")?dbValues.stream().anyMatch(str->str.contains(value)):dbValues.stream().allMatch(str->str.contains(value));
			Assert.assertTrue(bln);
			Verify("Column["+col[0]+"] => Database Value:"+dbValues+" Expected Valued :"+value+" were matched","PASSED");
		}		
	}

	@Step
	public void user_should_see_pop_up_screen_for(String popUpName, String page) {
		if(page.toLowerCase().equals("project")){
			oSearchProjectPage.verifyPopup(popUpName);
		}
	}

	@Step
	public void verify_fields_in_page(String labelName, String pagename) throws Exception {
		switch(pagename){
		case "Account Configuration":
			oAccountConfigurationPage.verifyFields(labelName);
			break;
		default:
			throw new IllegalArgumentException(pagename+" is does not exist, please provide the correct pageName");
		}
	}
	
	
}
