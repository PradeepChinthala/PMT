#Author: pradeep.chinthala@cotiviti.com

@Smoke @PMT
Feature: Smoke feature


Scenario: Validate the columns are sortable in Search Grid-Search Project-PMT Tool
	Given user opened PMT application
	When user login to pmt application
	And user click on "Manage Projects"
	And user enter madatory fields "Client ID:0,Master Account ID:001M" in search page
		Then user should see "All" sorted