#Author: pradeep.chinthala@cotiviti.com


@Regression @ManageCustomers @RAP-13711
Feature: ManageCustomers feature
  I want to use this template for ManageCustomers feature


@RAP-13715 @RAP-12880 @RAP-RAP-12881
Scenario: Validate 'Start Date and End Date' field- Audit History- Manage Customers- PMT Tool
	Given user opened PMT application
	When user login to pmt application
	And user click on "Manage Customers"
	And user click on "Audit History" "tab"	
		Then verify "Start Date,End Date" field Manage Customer in Audit History page


@RAP-12175 @RAP-12174
Scenario: Database tables Validation for Manage customers Tool on Home Screen- Manage Customers- PMT Tool
	Given user opened PMT application
	When user login to pmt application
		Then user should see tool tip for "Manage Customers" as "This tool allows the users to manage customers."
		And user should see "5" names in PMT Applicaiton database
		And user should see "5" Application Mapping for UserName in database
		
@RAP-12183 @RAP-12172 @RAP-12173
Scenario: Validate 'Manage Customers' screen is accessible to the user- Manage Customers- PMT Tool
	Given user opened PMT application
	When user login to pmt application
	And user click on "Manage Customers"
		Then user should see "Manage Customers" page
		
@RAP-12569 @RAP-12259
Scenario: Validate 'Manage Customers' screen is accessible to the user- Manage Customers- PMT Tool
	Given user opened PMT application
	When user login to pmt application
	And user click on "Manage Customers"
	And user click on "Add Customer" "button"	
		Then user should see "Add Customer" page
		
@RAP-12258 @RAP-12255
Scenario: Validate the columns- Customer Search- Manage Customers- PMT Tool
	Given user opened PMT application
	When user login to pmt application
	And user click on "Manage Customers"
	And user enter "Client ID:0 - Cotiviti - Healthcare" in Customer "Search" page
		Then user should see column names "Customer ID,Customer Name,Client ID - Name,Created Date,Modified By,Modified Date,Action"		
		And user should validate the result data for client "0" UI vs Database columns "CustomerID,Name,CreationDT,ModifiedDT,ModifiedBy"

@RAP-12573
Scenario: Validate 'Customer Name' field- Add Customer- Manage Customers- PMT Tool
	Given user opened PMT application
	When user login to pmt application
	And user click on "Manage Customers"
	And user click on "Add Customer" "button"	
		Then verify "Customer Name" field in "Add" Customer page	
		
@RAP-12184
Scenario: Validate the field names- Customer Search- Manage Customers- PMT Tool
	Given user opened PMT application
	When user login to pmt application
	And user click on "Manage Customers"
		Then verify "Customer Name,Client ID" field in "Search" Customer page	
		
@RAP-12666 @RAP-12261 @RAP-12582
Scenario: Validate navigation to other screens- Add Customer- Manage Customers- PMT Tool
	Given user opened PMT application
	When user login to pmt application
	And user click on "Manage Customers"
	And user click on "Add Customer" "button"
	And user enter "Client ID:random,Customer Name:random,cancel" in Customer "Add" page
	And user click on "Cancel" "button"
		Then user should verify prompt popup and click "Yes"
		And user should see "Manage Customers" page
	When user click on "Add Customer" "button"
	And user enter "Client ID:random,Customer Name:random,cancel" in Customer "Add" page
	And user click on "Cancel" "button"
		Then user should verify prompt popup and click "No"
		And verify "Header" field in "Add" Customer page	
		
@RAP-12571
Scenario: Validate the field names- Add Customer- Manage Customers- PMT Tool
	Given user opened PMT application
	When user login to pmt application
	And user click on "Manage Customers"
	And user click on "Add Customer" "button"
		Then verify "Customer Name,Client ID,Header,Button" field in "Add" Customer page	
		
@RAP-12576
Scenario: Validate 'Add' button functionality- Add Customer- Manage Customers- PMT Tool
	Given user opened PMT application
	When user login to pmt application
	And user click on "Manage Customers"
	And user click on "Add Customer" "button"
	And user enter "Client ID:random,Customer Name:random" in Customer "Add" page
		Then user should see newly added customer

@RAP-12570
Scenario: Validate 'Add Customer' screen is a popup window- Add Customer- Manage Customers- PMT Tool
	Given user opened PMT application
	When user login to pmt application
	And user click on "Manage Customers"
	And user click on "Add Customer" "button"	
		Then verify "Add" Customer popup with close	
		And user should see "Manage Customers" page
		
@RAP-12886 @RAP-12883
Scenario: Validate the columns in Manage Customer Audit History Grid- Audit History- Manage Customers- PMT Tool
	Given user opened PMT application
	When user login to pmt application
	And user click on "Manage Customers"
	And user click on "Audit History" "tab"	
	And user enter "StartDate:-60,EndDate:0" in Manage Customer-Audit History page
		Then user should see column names "Customer ID,Old Customer Name,New Customer Name,Client Name,Modified By,Modified Date,Action"

@RAP-12256
Scenario: Validate mandatory fields error messages- Customer Search- Manage Customers- PMT Tool
	Given user opened PMT application
	When user login to pmt application
	And user click on "Manage Customers"
	And user enter "" in Customer "Search" page
		Then user should see "Client ID / Name" madatory fields with error message

@RAP-12185
Scenario: Validate 'Client ID / Name' field- Customer Search- Manage Customers- PMT Tool
	Given user opened PMT application
	When user login to pmt application
	And user click on "Manage Customers"
		Then verify "Client ID / Name" field in "Search" Customer page	
		
@RAP-12572
Scenario: Validate the 'Client ID / Name' field- Add Customer- Manage Customers- PMT Tool
	Given user opened PMT application
	When user login to pmt application
	And user click on "Manage Customers"
	And user click on "Add Customer" "button"	
		Then verify "Client ID / Name" field in "Add" Customer page	
		
@RAP-12574
Scenario: Validate mandatory fields error messages- Add Customer- Manage Customers- PMT Tool
	Given user opened PMT application
	When user login to pmt application
	And user click on "Manage Customers"
	And user click on "Add Customer" "button"	
	And user enter "" in Customer "Add" page
		Then user should see "Client ID / Name,Customer Name" madatory fields with error message	

@RAP-12882
Scenario: Validate 'Action' field- Audit History- Manage Customers- PMT Tool
	Given user opened PMT application
	When user login to pmt application
	And user click on "Manage Customers"
	And user click on "Audit History" "tab"	
		Then verify "Action" field Manage Customer in Audit History page

@RAP-12575
Scenario: Validate Customer Name field does not accept spaces- Add Customer- Manage Customers- PMT Tool
	Given user opened PMT application
	When user login to pmt application
	And user click on "Manage Customers"
	And user click on "Audit History" "tab"	
		Then validate "Customer Name" filed does not accept spaces in "Add" Customer Page
	
@RAP-12884 @RAP-12883
Scenario: Validate Mandatory error messages- Audit History- Manage Customers- PMT Tool
	Given user opened PMT application
	When user login to pmt application
	And user click on "Manage Customers"
	And user click on "Audit History" "tab"	
	And user enter "" in Manage Customer-Audit History page
		Then user should see "Start Date,End Date" madatory fields with error message

@RAP-12186
Scenario: Validate 'Customer Name' field- Customer Search- Manage Customers- PMT Tool
	Given user opened PMT application
	When user login to pmt application
	And user click on "Manage Customers"
		Then verify "Customer Name" field in "Search" Customer page	


@RAP-12657
Scenario: Validate 'Customer Name' field- Edit Customer- Manage Customers- PMT Tool
	Given user opened PMT application
	When user login to pmt application
	And user click on "Manage Customers"
	And user click on "Add Customer" "button"
	And user enter "Client ID:random,Customer Name:random" in Customer "Add" page		
		Then user should see newly added customer		
	When user enter "Client ID:ClientName,Customer Name:CustomerName" in Customer "Search" page
	And user should click "Edit" for "CustomerID" in Customer "Search" page
		Then verify "Customer Name" field in "Edit" Customer page	
	When user enter "Customer Name:random" in Customer "Edit" page	
		Then user should see toaster message as "Success"

@RAP-12660 @RAP-12661 @RAP-12662 @RAP-12658
Scenario: Validate 'Customers,customerhistory' table- Add / Edit Customer- Manage Customers- PMT Tool
	Given user opened PMT application
	When user login to pmt application
	And user click on "Manage Customers"
	And user click on "Add Customer" "button"
	And user enter "Client ID:random,Customer Name:random" in Customer "Add" page		
		Then user should see newly added customer
		And user shoud see Customer History "ActionName" as "Add" in database 
	When user enter "Client ID:ClientName,Customer Name:CustomerName" in Customer "Search" page
	And user should click "Edit" for "CustomerID" in Customer "Search" page
	And user enter "Customer Name:random" in Customer "Edit" page	
		Then user should see toaster message as "Success"
		And user should see "CustomerID,NewCustomerName" for newly created customer in database 
		And user shoud see Customer History "ActionName" as "Edit" in database
	When user enter "Client ID:ClientName,Customer Name:NewCustomerName" in Customer "Search" page
	And user should click "Edit" for "NewCustomerName" in Customer "Search" page
	

@RAP-12585
Scenario: Validate the field names- Edit Customer- Manage Customers- PMT Tool
	Given user opened PMT application
	When user login to pmt application
	And user click on "Manage Customers"
	And user click on "Add Customer" "button"
	And user enter "Client ID:random,Customer Name:random" in Customer "Add" page		
		Then user should see newly added customer
		And user shoud see Customer History "ActionName" as "Add" in database 
	When user enter "Client ID:ClientName,Customer Name:CustomerName" in Customer "Search" page
	And user should click "Edit" for "CustomerID" in Customer "Search" page
		Then verify "Customer Name,Client ID,Header,Button" field in "Edit" Customer page	
	
	
@RAP-12659
Scenario: Validate 'Cancel' button functionality- Edit Customer- Manage Customers- PMT Tool
	Given user opened PMT application
	When user login to pmt application
	And user click on "Manage Customers"
	And user click on "Add Customer" "button"
	And user enter "Client ID:random,Customer Name:random" in Customer "Add" page		
		Then user should see newly added customer
	When user enter "Client ID:ClientName,Customer Name:CustomerName" in Customer "Search" page
	And user should click "Edit" for "CustomerID" in Customer "Search" page
	And user enter "Customer Name:random,cancel" in Customer "Edit" page		
	And user click on "Cancel" "button"
		Then user should verify prompt popup and click "Yes"
		And user should see "Manage Customers" page		
	And user should click "Edit" for "CustomerID" in Customer "Search" page	
	And user enter "Customer Name:random,cancel" in Customer "Edit" page
	And user click on "Cancel" "button"
		Then user should verify prompt popup and click "No"
		
@RAP-12665
Scenario: Validate navigation to other screens- Edit Customer- Manage Customers- PMT Tool
	Given user opened PMT application
	When user login to pmt application
	And user click on "Manage Customers"
	And user click on "Add Customer" "button"
	And user enter "Client ID:random,Customer Name:random" in Customer "Add" page		
		Then user should see newly added customer
	When user enter "Client ID:ClientName,Customer Name:CustomerName" in Customer "Search" page
	And user should click "Edit" for "CustomerID" in Customer "Search" page
	And user enter "Customer Name:random,cancel" in Customer "Edit" page
	And user navigate to "back" page
		Then popup should be displyed	

@RAP-12664
Scenario: Validate mandatory error messages- Edit Customer- Manage Customers- PMT Tool
	Given user opened PMT application
	When user login to pmt application
	And user click on "Manage Customers"
	And user click on "Add Customer" "button"
	And user enter "Client ID:random,Customer Name:random" in Customer "Add" page		
		Then user should see newly added customer
	When user enter "Client ID:ClientName,Customer Name:CustomerName" in Customer "Search" page
	And user should click "Edit" for "CustomerID" in Customer "Search" page
	When user enter "" in Customer "Edit" page
		Then user should see toaster message as "Error"
	When user enter "Customer Name:   " in Customer "Edit" page
		Then user should see "Customer Name:Enter Valid Customer Name" madatory fields with error message
	When user get "ExistingCustomerName" for "ClientID" from database
	And user enter "Customer Name:ExistingCustomerName" in Customer "Edit" page 
	Then user should see toaster message as "Error"
	

@RAP-12586 @RAP-12584 @RAP-12583
Scenario: Validate the 'Client ID / Name' field- Edit Customer- Manage Customers- PMT Tool
	Given user opened PMT application
	When user login to pmt application
	And user click on "Manage Customers"
	And user click on "Add Customer" "button"
	And user enter "Client ID:random,Customer Name:random" in Customer "Add" page		
		Then user should see newly added customer
	When user enter "Client ID:ClientName,Customer Name:CustomerName" in Customer "Search" page
	And user should click "Edit" for "CustomerID" in Customer "Search" page
		Then verify "Client ID" field in "Edit" Customer page
		Then verify "Edit" Customer popup with close
		
@RAP-12893
Scenario: Validate the column values Audit History Grid screen- Audit History- Manage Customers- PMT Tool	
	Given user opened PMT application
	When user login to pmt application
	And user click on "Manage Customers"
	And user click on "Add Customer" "button"
	And user enter "Client ID:random,Customer Name:random" in Customer "Add" page		
		Then user should see newly added customer
	When user enter "Client ID:ClientName,Customer Name:CustomerName" in Customer "Search" page
	And user should click "Edit" for "CustomerID" in Customer "Search" page
	And user enter "Customer Name:random" in Customer "Edit" page	
		Then user should see toaster message as "Success"
	When user enter "Client ID:ClientName,Customer Name:NewCustomerName" in Customer "Search" page
		And user shoud see Customer History "CustomerHistoryID" as "Add" in database 
		And user should see Columns "NewValue:CustomerName" in "PMT.TableAudit" table by "HistoryID:CustomerHistoryID$ORDER BY AUDITID DESC" in database with match condition "any"
		And user shoud see Customer History "CustomerHistoryID" as "Edit" in database 
		And user should see Columns "NewValue:NewCustomerName,OldValue:CustomerName" in "PMT.TableAudit" table by "HistoryID:CustomerHistoryID$ORDER BY AUDITID DESC" in database with match condition "any"
		
@RAP-12260
Scenario: Validate the Manage Customers Search grid is sortable, searchable , filtered and pagination- Customer Search- Manage Customers- PMT Tool	
	Given user opened PMT application
	When user login to pmt application
	And user click on "Manage Customers"	
	When user get "ClientName" from database	
	And user enter "Client ID:ClientName" in Customer "Search" page
		Then validate "all" columns are "sort,"
		And validate "Client ID - Name:ClientName" columns are "columnvalidation"
		

		