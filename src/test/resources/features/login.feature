Feature: SauceDemo Login Functionality

Scenario: Valid Login
Given User is on the SauceDemo login page
When User executes login test case "SD_TC_001"
Then User should be redirected to the Inventory page

@ErrorMessages
Scenario: Invalid Password 
Given User is on the SauceDemo login page
When User executes login test case "SD_TC_002"
Then User should see an error message for invalid credentials

@ErrorMessages
Scenario: Invalid Username 
Given User is on the SauceDemo login page
When User executes login test case "SD_TC_003"
Then User should see an error message for invalid credentials

@ErrorMessages
Scenario: Empty Fields
Given User is on the SauceDemo login page
When User executes login test case "SD_TC_004"
Then User should see a required field error message

@ErrorMessages
Scenario: Login with locked out user
Given User is on the SauceDemo login page
When User executes login test case "SD_TC_005"
Then User should see a locked out user error message
