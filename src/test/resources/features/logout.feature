@Logout
Feature: Logout Functionality
 
  Background:
    Given User is on the SauceDemo login page
    When User executes login test case "SD_TC_001"
    Then User should be redirected to the Inventory page
 
  Scenario: Verify Logout Functionality
 
    When User clicks Menu
    And User clicks Logout
 
    Then User should be redirected to Login page
 
    And Logout result should be written into Logout Excel sheet with status "PASS"
 
    And User captures logout screenshot "Logout"