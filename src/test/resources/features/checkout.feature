@Checkout
Feature: Checkout Functionality
 
  Background:
 
    Given User is on the SauceDemo login page
    When User executes login test case "SD_TC_001"
    Then User should be redirected to the Inventory page
 
    When User adds a single product to the cart
    And User opens the cart
    Then Cart page should be displayed
 
  # SD_TC_019
 
  Scenario: Checkout with valid details
 
    When User clicks Checkout
 
    Then Checkout Information page should be displayed
 
    When User enters checkout details for test case "SD_TC_019"
      | John | Doe | 560001 |
 
    And User clicks Continue
 
    Then Checkout Overview page should be displayed
 
    # And Order summary should be written into Checkout Excel sheet
 
    And User captures checkout screenshot "Checkout_Overview"
 
    When User clicks Finish
 
    Then Checkout Complete page should be displayed
    
    And Checkout result should be written into Checkout Excel sheet with status "PASS"
 
    And User captures checkout screenshot "Checkout_Complete"
 
  # SD_TC_020
 
  Scenario: Checkout without First Name
 
    When User clicks Checkout
 
    Then Checkout Information page should be displayed
 
    When User enters checkout details for test case "SD_TC_020"
      |      | Doe | 560001 |
 
    And User clicks Continue
 
    Then Checkout error message should be "Error: First Name is required"
    
    And Checkout result should be written into Checkout Excel sheet with status "FAIL"
 
    And User captures checkout screenshot "Checkout_FirstName"
 
  # SD_TC_021
 
  Scenario: Checkout without Last Name
 
    When User clicks Checkout
 
    Then Checkout Information page should be displayed
 
    When User enters checkout details for test case "SD_TC_021"
      | John |      | 560001 |
 
    And User clicks Continue
 
    Then Checkout error message should be "Error: Last Name is required"
    
    And Checkout result should be written into Checkout Excel sheet with status "FAIL"
 
    And User captures checkout screenshot "Checkout_LastName"
 
  # SD_TC_022
 
  Scenario: Checkout without Postal Code
 
    When User clicks Checkout
 
    Then Checkout Information page should be displayed
 
    When User enters checkout details for test case "SD_TC_022"
      | John | Doe |      |
 
    And User clicks Continue
 
    Then Checkout error message should be "Error: Postal Code is required"
    
    And Checkout result should be written into Checkout Excel sheet with status "FAIL"
 
    And User captures checkout screenshot "Checkout_PostalCode"