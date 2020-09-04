#Author: pradeep.chinthala@cotiviti.com

@Regression @RAP-9049 @Login
Feature: Login feature

@RAP-8966 @RAP-8967 @RAP-8982   
Scenario: Check Login Funcationality
	Given user opened PMT application
	When user login to pmt application

@RAP-9355 @RAP-9356 @RAP-9357
Scenario: Validate Home Screen
	Given user opened PMT application
	When user login to pmt application
		Then user should see home page
		And username should be in dabase

@RAP-9359
Scenario: Validate the Applications Tool Tip on Home Screen
	Given user opened PMT application
	When user login to pmt application
		Then validate toop tip on home screen	
	
@RAP-8974 @RAP-9365
Scenario: Validate LoginPage
	Given user opened PMT application
	Then user should see login page	
	
@RAP-9361
Scenario: Validate the functionality of applications list on Home Screen- PMT Tool
	Given user opened PMT application
	When user login to pmt application
	When user click on "Account Configuration"
		Then verify "Account Configuration" page opened 
	When user goto home page
	

		
#@RAP-8982 
#Scenario: Configuration Search 
#	Given user opened PMT application
#	When user login to pmt application
#		Then user should see home page
#	When user click on "Configuration"
#
#@RAP-9073
#Scenario: Search Project
#	Given user opened PMT application
#	When user login to pmt application
#		Then user should see home page
#	When user go to "Search Projects"
#	And user click on add project
