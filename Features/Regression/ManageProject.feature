#Author: pradeep.chinthala@cotiviti.com


@Regression @RAP-13551 @ManageProject
Feature: Manage Project feature

@RAP-9388 
Scenario: Validate the user is able to access the PMT - App Center Tool- Search Project URL
	Given user opened PMT application
	When user login to pmt application
	And user click on "Manage Projects"
		Then verify "Manage Projects" page opened 
		
@RAP-9390
Scenario: Validate Client ID / Name field in Search Project - PMT Tool
	Given user opened PMT application
	When user login to pmt application
	And user click on "Manage Projects"
		Then verify "Client ID" field in project search page

@RAP-9414
Scenario: Validate Season Year field in Search Project - PMT Tool
	Given user opened PMT application
	When user login to pmt application
	And user click on "Manage Projects"
		Then verify "Season Year" field in project search page

@RAP-9408
Scenario: Validate Master Acct ID/ Name field in Search Project - PMT Tool
	Given user opened PMT application
	When user login to pmt application
	And user click on "Manage Projects"
		Then verify "Master Account ID" field in project search page
		# pending with data base validation
		
@RAP-9413
Scenario: Validate Project Wave field in Search Project - PMT Tool
	Given user opened PMT application
	When user login to pmt application
	And user click on "Manage Projects"
		Then verify "Project Wave" field in project search page
		
@RAP-9412
Scenario: Validate Service Line field in Search Project - PMT Tool
	Given user opened PMT application
	When user login to pmt application
	And user click on "Manage Projects"
		Then verify "Service Line" field in project search page
		
@RAP-9410 @RAP-11887 @RAP-11888
Scenario: Validate Project Name field in Search Project - PMT Tool
	Given user opened PMT application
	When user login to pmt application
	And user click on "Manage Projects"
		Then verify "Project Name" field in project search page
		
@RAP-9409
Scenario: Validate Project Name field in Search Project - PMT Tool
	Given user opened PMT application
	When user login to pmt application
	And user click on "Manage Projects"
		Then verify "Project ID" field in project search page
		
@RAP-9418
Scenario: Validate the Search grid is in ascending order of Project Id in Search Project-PMT Tool
	Given user opened PMT application
	When user login to pmt application
	And user click on "Manage Projects"
	And user enter fields "Client ID:0,Master Account ID:001M" in search page
		Then user should see "Project ID" sorted

@RAP-9420
Scenario: Validate the results in Search grid is a combination of details entered in Search Project- PMT Tool
	Given user opened PMT application
	When user login to pmt application
	And user click on "Manage Projects"
	And user enter fields "Client ID:0,Master Account ID:001M" in search page
		Then user should be able to search "randomAND" column data in resulted grid

@RAP-9421 @RAP-9417
Scenario: Validate the Search Textbox functionality in Search Project- PMT tool
	Given user opened PMT application
	When user login to pmt application
	And user click on "Manage Projects"
	And user enter fields "Client ID:0,Master Account ID:001M" in search page
		Then user should be able to search "random" column data in resulted grid
		
@RAP-9422 @RAP-9423
Scenario: Validate the number of rows in Search Grid -Search Project-PMT Tool
	Given user opened PMT application
	When user login to pmt application
	And user click on "Manage Projects"
	And user enter fields "Client ID:0,Master Account ID:001M" in search page
		Then user should see rows "10,25,50,100" accordingly
		And validate pagination
		
@RAP-9424
Scenario: Validate the columns are sortable in Search Grid-Search Project-PMT Tool
	Given user opened PMT application
	When user login to pmt application
	And user click on "Manage Projects"
	And user enter fields "Client ID:0,Master Account ID:001M" in search page
		Then user should see "All" sorted

@RAP-9416
Scenario: Validate Search button when all the mandatory fields are not entered on Search Project-PMT Tool
	Given user opened PMT application
	When user login to pmt application
	And user click on "Manage Projects"
	And user enter fields "" in search page
		Then user should see "Client ID / Name,Master Account ID / Name" madatory fields with error message

@RAP-9887 @RAP-9885 @RAP-9891
Scenario: Validate the Login link on 404 Page Not Found'-PMT Tool
	Given user opened PMT application with suffix of "login1"
		Then user should see "404" as "header" in page
		And user should see "login" as "link" in page
	When user click on "login" "link"
		Then user should see login page	
	
@RAP-9777
Scenario: Validate the columns in Audit History tab- PMT Tool
	Given user opened PMT application
	When user login to pmt application
	And user click on "Manage Projects"
	And user click on "Audit History" "tab"
	And user search with "StartDate:-40,EndDate:0" Audit history
		Then user should see column names "Project ID,Project Name,Modified By,Modified Date,Action,Changed Info"
	
#@RAP-9786
#Scenario: Validate the Start Date Column in Search Audit History-PMT Tool
#	Given user opened PMT application
#	When user login to pmt application
#	And user click on "Manage Projects"
#	And user click on "Audit History" "tab"
#		Then verify "StartDate" field in Audit history page	
	
@RAP-9787
Scenario: Validate End Date Column in Search Audit History Screen-PMT Tool
	Given user opened PMT application
	When user login to pmt application
	And user click on "Manage Projects"
	And user click on "Audit History" "tab"
		Then verify "EndDate" field in Audit history page	
		
@RAP-9788
Scenario: Validate Project ID field in Search Audit History- PMT Tool
	Given user opened PMT application
	When user login to pmt application
	And user click on "Manage Projects"
	And user click on "Audit History" "tab"
		Then verify "Project ID" field in Audit history page	

@RAP-9789
Scenario: Validate Action column in Audit History-PMT Tool
	Given user opened PMT application
	When user login to pmt application
	And user click on "Manage Projects"
	And user click on "Audit History" "tab"
		Then verify "Action" field in Audit history page
		
		
@RAP-9798
Scenario: Validate a popup window is displayed when user navigates after entering details in Audit History Screen-PMT Tool
	Given user opened PMT application
	When user login to pmt application
	And user click on "Manage Projects"
	And user click on "Audit History" "tab"
	And user search with "StartDate:-40,EndDate:0" Audit history
	And user navigate to "back" page
		Then popup should be displyed	
		And user should see login page
	
@RAP-9797
Scenario: Validate the columns are sortable in Audit History Grid-Audit History-PMT Tool
	Given user opened PMT application
	When user login to pmt application
	And user click on "Manage Projects"
	And user click on "Audit History" "tab"
	And user search with "StartDate:-40,EndDate:0" Audit history
		Then user should see "All" sorted

@RAP-9073
Scenario: Validate the user is able to access the PMT - App Center Tool- Add Project URL
	Given user opened PMT application
	When user login to pmt application
	And user click on "Manage Projects"
	And user click on "Add Project" "button"	
		Then user should see "Add Project" page
		
		
@RAP-9795 @RAP-9794
Scenario: Validate the number of rows and pagination in Audit History Grid -Audit History-PMT Tool
	Given user opened PMT application
	When user login to pmt application
	And user click on "Manage Projects"
	And user click on "Audit History" "tab"	
	And user search with "StartDate:-40,EndDate:0" Audit history		
		Then user should see rows "10,25,50,100" accordingly
		And validate pagination
		
@RAP-9796
Scenario: Validate the Search Textbox functionality in Audit History- PMT tool
	Given user opened PMT application
	When user login to pmt application
	And user click on "Manage Projects"
	And user click on "Audit History" "tab"	
	And user search with "StartDate:-40,EndDate:0" Audit history
		Then user should be able to search "random" column data in resulted grid
	
@RAP-9792
Scenario: Validate 'Search button' functionality on Search Audit History Screen-PMT Tool
	Given user opened PMT application
	When user login to pmt application
	And user click on "Manage Projects"
	And user click on "Audit History" "tab"
	And user search with "" Audit history	
	And user click on "Search" "button"	
		Then user should see "Start Date,End Date" madatory fields with error message
	When user search with "StartDate:-40,EndDate:0" Audit history
	And user click on "Search" "button"	
		Then user should see column names "Project ID,Project Name,Modified By,Modified Date,Action,Changed Info"

@RAP-9793
Scenario: Validate the results in Audit History grid is a combination of details entered in Audit History Search- PMT Tool
	Given user opened PMT application
	When user login to pmt application
	And user click on "Manage Projects"
	And user click on "Audit History" "tab"	
	And user search with "StartDate:-40,EndDate:0" Audit history
		Then user should see results based on "StartDate:-40,EndDate:0"


@RAP-9100
Scenario: Validate the fields in Add Project Screen - PMT Tool
	Given user opened PMT application
	When user login to pmt application
	And user click on "Manage Projects"
	And user click on "Add Project" "button"
		Then verify fields displayed in Add Project page	
		
@RAP-9101 @RAP-9102
Scenario: Validate Client ID / Name field in Add Project - PMT Tool
	Given user opened PMT application
	When user login to pmt application
	And user click on "Manage Projects"
	And user click on "Add Project" "button"	
		Then verify "ClientID" field in Add Project page
		
@RAP-9104 @RAP-9103
Scenario: Validate Master Acct ID/ Name is a Mandatory field in Add Project - PMT Tool
	Given user opened PMT application
	When user login to pmt application
	And user click on "Manage Projects"
	And user click on "Add Project" "button"	
		Then verify "MasterAccountID" field in Add Project page
	
@RAP-9105 @RAP-9106
Scenario: Validate Project Name field in Add Project - PMT Tool
	Given user opened PMT application
	When user login to pmt application
	And user click on "Manage Projects"
	And user click on "Add Project" "button"	
		Then verify "ProjectName" field in Add Project page
		
@RAP-9107 @RAP-9108
Scenario: Validate Project Description field in Add Project - PMT Tool
	Given user opened PMT application
	When user login to pmt application
	And user click on "Manage Projects"
	And user click on "Add Project" "button"	
		Then verify "ProjectDescription" field in Add Project page
	
@RAP-9109 @RAP-9110
Scenario: Validate Project Wave field in Add Project - PMT Tool
	Given user opened PMT application
	When user login to pmt application
	And user click on "Manage Projects"
	And user click on "Add Project" "button"	
		Then verify "ProjectWave" field in Add Project page

@RAP-9111 @RAP-9112
Scenario: Validate Season Year field in Add Project - PMT Tool
	Given user opened PMT application
	When user login to pmt application
	And user click on "Manage Projects"
	And user click on "Add Project" "button"	
		Then verify "SeasonYear" field in Add Project page	
		
@RAP-9113 @RAP-9114
Scenario: Validate Service Line field in Add Project - PMT Tool
	Given user opened PMT application
	When user login to pmt application
	And user click on "Manage Projects"
	And user click on "Add Project" "button"	
		Then verify "ServiceLine" field in Add Project page	
		
@RAP-9115 @RAP-9116 
Scenario: Validate OnOffShore field in Add Project - PMT Tool
	Given user opened PMT application
	When user login to pmt application
	And user click on "Manage Projects"
	And user click on "Add Project" "button"	
		Then verify "OnOffShore" field in Add Project page	
		
@RAP-9117 @RAP-9118 
Scenario: Validate Client Services Rep field in Add Project - PMT Tool
	Given user opened PMT application
	When user login to pmt application
	And user click on "Manage Projects"
	And user click on "Add Project" "button"	
		Then verify "clientServicesRep" field in Add Project page
		
@RAP-9119 @RAP-9120
Scenario: Validate Implementation Contact field in Add Project - PMT Tool
	Given user opened PMT application
	When user login to pmt application
	And user click on "Manage Projects"
	And user click on "Add Project" "button"	
		Then verify "implementationContact" field in Add Project page
		
@RAP-9121 @RAP-9122
Scenario: Validate Target percentage field in Add Project - PMT Tool
	Given user opened PMT application
	When user login to pmt application
	And user click on "Manage Projects"
	And user click on "Add Project" "button"	
		Then verify "targetPercentage" field in Add Project page
	
@RAP-9124
Scenario: Validate Project Start Date (SLA) field in Add Project - PMT Tool
	Given user opened PMT application
	When user login to pmt application
	And user click on "Manage Projects"
	And user click on "Add Project" "button"	
		Then verify "projectStartDate" field in Add Project page	
		
@RAP-9125
Scenario: Validate Project End Date (SLA) field in Add Project - PMT Tool
	Given user opened PMT application
	When user login to pmt application
	And user click on "Manage Projects"
	And user click on "Add Project" "button"	
		Then verify "projectEndDate" field in Add Project page		
		
@RAP-9869
Scenario: Validate Additional Contacts SLA Type field in Add Project - PMT Tool
	Given user opened PMT application
	When user login to pmt application
	And user click on "Manage Projects"
	And user click on "Add Project" "button"	
		Then verify "additionalContactsSLAType" field in Add Project page
		
@RAP-9870
Scenario: Validate 'XDays' field in Add Project - PMT Tool
	Given user opened PMT application
	When user login to pmt application
	And user click on "Manage Projects"
	And user click on "Add Project" "button"	
		Then verify "EveryXDays" field in Add Project page
		
@RAP-9871
Scenario: Validate 'XNumContacts' field in Add Project - PMT Tool
	Given user opened PMT application
	When user login to pmt application
	And user click on "Manage Projects"
	And user click on "Add Project" "button"	
		Then verify "XTimesDuringProject" field in Add Project page
		
@RAP-9126
Scenario: Validate First Contact Interval (SLA in Days) field in Add Project - PMT Tool
	Given user opened PMT application
	When user login to pmt application
	And user click on "Manage Projects"
	And user click on "Add Project" "button"	
		Then verify "FirstContactInterval" field in Add Project page

@RAP-9127
Scenario: Validate First Contact Warning Date field in Add Project - PMT Tool
	Given user opened PMT application
	When user login to pmt application
	And user click on "Manage Projects"
	And user click on "Add Project" "button"	
		Then verify "FirstContactIntervalWarning" field in Add Project page
		
@RAP-9128
Scenario: Validate Estimated End Date field in Add Project - PMT Tool
	Given user opened PMT application
	When user login to pmt application
	And user click on "Manage Projects"
	And user click on "Add Project" "button"	
		Then verify "estimatedEndDate" field in Add Project page
		
@RAP-9129
Scenario: Validate Target Fax Date field in Add Project - PMT Tool
	Given user opened PMT application
	When user login to pmt application
	And user click on "Manage Projects"
	And user click on "Add Project" "button"	
		Then verify "targetFaxDate" field in Add Project page	

@RAP-9130
Scenario: Validate Add button when all the mandatory fields have a value on Add Project popup-PMT Tool
	Given user opened PMT application
	When user login to pmt application
	And user click on "Manage Projects"
	And user click on "Add Project" "button"
	And user enter "ClientName:random,MasterAccountID:random,ProjectName:random,ProjectDescription:Automation Test,ProjectWave:random,SeasonYear:random,ServiceLine:random,OnOffShore:random" fields in "Add" Project page
		Then verify "ProjectID" created in UI
		And verify "ProjectID" in database
			
@RAP-9131
Scenario: Validate Add Button when all the mandatory fields are not entered on Add Project popup-PMT Tool
	Given user opened PMT application
	When user login to pmt application
	And user click on "Manage Projects"
	And user click on "Add Project" "button"
	And user enter "ClientName:random" fields in "Add" Project page	
		Then verify error messages for "Master Account ID / Name,Project Name,Project Description,Project Wave,Season Year,Service Line,On / Offshore" fields in Add Project page
	
@RAP-9132
Scenario: Validate Cancel Button and X sign on Add Project popup-PMT Tool
	Given user opened PMT application
	When user login to pmt application
	And user click on "Manage Projects"
	And user click on "Add Project" "button"	
	And user enter "ClientName:random,Addbutton:No" fields in "Add" Project page	
		Then user click cancel button in Add Project page
		Then user should verify prompt popup and click "Yes"
		And user click on "Add Project" "button"
	When user enter "ClientName:random,Addbutton:No" fields in "Add" Project page	
		Then user click cancel button in Add Project page	
		And user should verify prompt popup and click "No"
	When user enter "MasterAccountID:random" fields in "Add" Project page	
	
@RAP-9074 @RAP-11087 @RAP-10270
Scenario: Project ID search field should also be Partially searchable in Search Projects and Audit History-PMT Tool
	Given user opened PMT application
	When user login to pmt application
	And user click on "Manage Projects"
	And user click on "Add Project" "button"
	And user enter "ClientName:random,MasterAccountID:random,ProjectName:random,ProjectDescription:random,ProjectWave:random,SeasonYear:random,ServiceLine:random,OnOffShore:random" fields in "Add" Project page
		Then verify "ProjectID" created in UI		
	When user enter fields "ClientName:ClientName,ProjectID:ProjectID" in search page
		Then validate "Project ID:ProjectID" columns are "columnvalidation"
	When user should click "Edit" for "ProjectID" in Project "Search" page
		Then user should see "Edit" pop up screen for "Project" 

@RAP-10271
Scenario: Validate the Edit Project details are enabled to be updated by the user-Edit Project-PMT Tool
	Given user opened PMT application
	When user login to pmt application
	And user click on "Manage Projects"
	And user click on "Add Project" "button"
	And user enter "ClientName:random,MasterAccountID:random,ProjectName:random,ProjectDescription:random,ProjectWave:random,SeasonYear:random,ServiceLine:random,OnOffShore:random" fields in "Add" Project page
		Then verify "ProjectID" created in UI		
	When user enter fields "ClientName:ClientName,ProjectID:ProjectID" in search page
	And user should click "Edit" for "ProjectID" in Customer "Search" page			
	When user enter "MasterAccountID:random,ProjectName:random,ProjectDescription:random,ProjectWave:random,SeasonYear:random,ServiceLine:random,OnOffShore:random,Addbutton:No" fields in "Edit" Project page		
		#Then verify "Client ID,MasterAccountID,ProjectName,ProjectDescription,ProjectWave,SeasonYear,ServiceLine,OnOffShore,clientServicesRep,implementationContact,targetPercentage" field in "Edit" Project page	
		
@RAP-10276 @RAP-9421 @RAP-9539 @RAP-10272
Scenario: Validate same Client ID and Project name behavior on Edit Screen-PMT Tool
	Given user opened PMT application
	When user login to pmt application
	And user click on "Manage Projects"
	And user click on "Add Project" "button"
	And user enter "ClientName:random,MasterAccountID:random,ProjectName:random,ProjectDescription:random,ProjectWave:random,SeasonYear:random,ServiceLine:random,OnOffShore:random" fields in "Add" Project page
		Then verify "ProjectID" created in UI	
		And user should see Columns "Name:ProjectName" in "dbo.projects" table by "Name:ProjectName,ClientID:ClientID" in database with match condition "any"	
	When user enter fields "ClientName:ClientName,ProjectID:ProjectID" in search page
	And user should click "Edit" for "ProjectID" in Customer "Search" page
	And user enter "ProjectName:ProjectName" fields in "Edit" Project page
		Then user should see "No changes were made." message toaster
			
@RAP-9389
Scenario: Validate the 2 tabs on the top-Manage Projects- PMT Tool
	Given user opened PMT application
	When user login to pmt application
	And user click on "Manage Projects"
	And user click on "Audit History" "tab"
	And user click on "Project Search" "tab"

@RAP-15307
Scenario: Validate 'Tool Tip' for Manage Projects application- Manage Projects- PMT Tool
	Given user opened PMT application
	When user login to pmt application	
	Then user should see tool tip for "Manage Projects" as "This tool allows the users to manage projects."
	
@RAP-13614
Scenario: Validate 'Master Account ID / Name' is an optional field- Project Search- Manage Projects- PMT Tool
	Given user opened PMT application
	When user login to pmt application
	And user click on "Manage Projects"	
		Then verify "Master Account ID" field in "search" Project page
		
@RAP-9838 @RAP-9793 @RAP-10224 @RAP-9776 @RAP-9790 @RAP-10265 
Scenario: Validate Old Info and New Info columns in Changed Info table Audit Details popup- Audit History- Account Configuration- PMT Tool
	Given user opened PMT application
	When user login to pmt application
	And user click on "Manage Projects"
	And user click on "Add Project" "button"
	And user enter "ClientName:random,MasterAccountID:random,ProjectName:random,ProjectDescription:random,ProjectWave:random,SeasonYear:random,ServiceLine:random,OnOffShore:random" fields in "Add" Project page
		Then verify "ProjectID" created in UI	
	When user click on "Audit History" "tab"
	And user search with "StartDate:-5,EndDate:0,ProjectId:ProjectID,ProjectName:ProjectName" Audit history
	And user should click "Details" for "ProjectID" in Customer "Search" page
		Then user should see "Old Info" as "-" change info page
		Then user should see "New Info" as "-" change info page
	
@RAP-9786 @RAP-13627
Scenario: Validate the Start Date Column- Audit History- Manage Projects- PMT Tool
	Given user opened PMT application
	When user login to pmt application
	And user click on "Manage Projects"
	When user click on "Audit History" "tab"
		Then verify "StartDate,EndDate" field in Audit history page
	When user click on "Project Search" "tab"
		Then verify "Season Year" field in "search" Project page
		
@RAP-10903
Scenario: Validate "Season Year" is optional in Search Project-PMT Tool
	Given user opened PMT application
	When user login to pmt application
	And user click on "Manage Projects"
		Then verify "Season Year" field in "search" Project page
		
@RAP-9411 
Scenario: Validate Service Line field in Search Project - PMT Tool
	Given user opened PMT application
	When user login to pmt application
	And user click on "Manage Projects"
		Then verify "Service Line" field in "search" Project page
		
@RAP-9419 @RAP-10904
Scenario: Validate the columns in Search Grid- Project Search- Manage Projects- PMT Tool
	Given user opened PMT application
	When user login to pmt application
	And user click on "Manage Projects"
	And user click on "Add Project" "button"
	And user enter "ClientName:random,MasterAccountID:random,ProjectName:random,ProjectDescription:random,ProjectWave:random,SeasonYear:random,ServiceLine:random,OnOffShore:random" fields in "Add" Project page
		Then verify "ProjectID" created in UI	
	When user enter fields "ClientName:ClientName,ProjectID:ProjectID,MasterAccountID:MasterAccountID" in search page
		Then user should see column names "Project ID,Project Name,Project Wave,Service Line,Master Account,Client,Season Year,Implementation contact,Created By,Created Date,Modified By,Modified Date,Action"
		

@RAP-10905
Scenario: Validate "Audit History" screen for new modifications Audit History-PMT Tool		
	Given user opened PMT application
	When user login to pmt application
	And user click on "Manage Projects"
	And user click on "Add Project" "button"
	And user enter "ClientName:random,MasterAccountID:random,ProjectName:random,ProjectDescription:random,ProjectWave:random,SeasonYear:random,ServiceLine:random,OnOffShore:random" fields in "Add" Project page
		Then verify "ProjectID" created in UI	
	When user click on "Audit History" "tab"
		Then verify "StartDate,EndDate,Project Name,Action" field in Audit history page
	When user search with "StartDate:-5,EndDate:0,ProjectId:ProjectID,ProjectName:ProjectName" Audit history
	And user should click "Details" for "ProjectID" in Customer "Search" page
		Then user should not see "Production Plan" in Change Info