@UIValidation
Feature: SauceDemo UI Validation
 
Background:
Given User is logged into SauceDemo
 
Scenario: Verify SauceDemo logo
Then User should see SauceDemo logo
 
Scenario: Verify Login button alignment
Given User is on Login page
Then Login button should be properly aligned
 
Scenario: Verify Product page layout
Then Inventory page layout should be correct
 
Scenario: Verify Cart icon visibility
Then Cart icon should be visible
 
Scenario: Verify Footer links
Then Twitter, Facebook and LinkedIn links should be displayed
 