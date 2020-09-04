#Author: pradeep.chinthala@cotiviti.com


@Regression @AccountConfiguration
Feature: AccountConfiguration feature
  I want to use this template for my feature file
  
  
 		
@RAP-9826
Scenario: Validate the Project ID in Audit Details Popup- Audit History- Account Configuration- PMT Tool
	Given user opened PMT application
	When user login to pmt application
	And user click on "Account Configuration"
	And user click on "Audit History" "tab"	
	And user enter fields in account configuration "StartDate:-90,EndDate:0,Client ID:0" in audit history page
	And user click project details in audit history
		Then user should see "Account ID" in change info popup

@RAP-9825
Scenario: Validate Details link on Audit History Grid-Audit History- Account Configuration -PMT Tool
	Given user opened PMT application
	When user login to pmt application
	And user click on "Account Configuration"
	And user click on "Audit History" "tab"	
	And user enter fields in account configuration "StartDate:-90,EndDate:0,Client ID:0" in audit history page
	And user click project details in audit history
		Then user should see "Old Info,New Info" in change info popup

@RAP-9832
Scenario: Validate All Labels Details link on Audit History Grid-Audit History- Account Configuration -PMT Tool
	Given user opened PMT application
	When user login to pmt application
	And user click on "Account Configuration"
	And user click on "Audit History" "tab"	
	And user enter fields in account configuration "StartDate:-90,EndDate:0,Client ID:0" in audit history page
	And user click project details in audit history
		Then user should see "All Labels" in change info popup
		
		
@RAP-9830
Scenario: Validate the columns in Changed Info Table in Audit Details Popup- Audit History- Account Configuration- PMT Tool
	Given user opened PMT application
	When user login to pmt application
	And user click on "Account Configuration"
	And user click on "Audit History" "tab"	
	And user enter fields in account configuration "StartDate:-90,EndDate:0,Client ID:0" in audit history page
	And user click project details in audit history
		Then user should see "Label,Old Info,New Info" in change info popup
		
@RAP-9827
Scenario: Validate the 'Changed Info' title on Audit Details Popup- Audit History- Account Configuration- PMT Tool
	Given user opened PMT application
	When user login to pmt application
	And user click on "Account Configuration"
	And user click on "Audit History" "tab"	
	And user enter fields in account configuration "StartDate:-90,EndDate:0,Client ID:0" in audit history page
	And user click project details in audit history
		Then user should see "Changed Info" in change info popup
		
#@RAP-9838
#Scenario: Validate Old Info and New Info columns in Changed Info table Audit Details popup- Audit History- Account Configuration- PMT Tool
#	Given user opened PMT application
#	When user login to pmt application
#	And user click on "Account Configuration"
#	And user click on "Audit History" "tab"	
#	And user enter fields in account configuration "StartDate:-90,EndDate:0,Client ID:0" in audit history page
#	And user click project details in audit history for "Edit" action
#		Then user should see "Edit" information change info popup
#		And user close change info popup
#	When user click project details in audit history for "Add" action
#		Then user should see "Add" information change info popup
		
@RAP-9839
Scenario: Validate Browser Back button or X button on Audit Details Popup - Audit History- Account Configuration- PMT Tool
	Given user opened PMT application
	When user login to pmt application
	And user click on "Account Configuration"
	And user click on "Audit History" "tab"	
	And user enter fields in account configuration "StartDate:-90,EndDate:0,Client ID:0" in audit history page
	And user click project details in audit history		
		Then user close change info popup
		And user click on "Audit History" "tab"	
		
@RAP-9847
Scenario: Validate tabs on 'Account Configuration Tool' screen in PMT Tool
	Given user opened PMT application
	When user login to pmt application
	And user click on "Account Configuration"
		Then user click on "Audit History" "tab"	
		And user click on "Account Search" "tab"
		And user click on "Add / Edit Account Configurations" "tab"
		And user click on "Link / Unlink Accounts" "tab"	
		
		
@RAP-12124 @RAP-12125 @RAP-12127
Scenario: Validate 'Project Type' field- Add / Edit Account configurations- Account Configuration- PMT Tool
	Given user opened PMT application
	When user login to pmt application
	And user click on "Account Configuration"
	And user click on "Add / Edit Account Configurations" "tab"
		Then verify "Project Type" fields in "Account Configuration" page
		
		
	
		
		