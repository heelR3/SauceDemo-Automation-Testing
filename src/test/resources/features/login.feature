Feature: SauceDemo Login Functionality

Scenario: Valid Login
Given User is on the SauceDemo login page
When User enters username "standard_user"
And User enters password "secret_sauce"
And User clicks the Login button
Then User should be redirected to the Inventory page

Scenario: Invalid Password 
Given User is on the SauceDemo login page
When User enters username "standard_user"
And User enters password "wrong_password"
And User clicks the Login button
Then User should see an error message for invalid credentials

Scenario: Invalid Username 
Given User is on the SauceDemo login page
When User enters username "wrong_user"
And User enters password "secret_sauce"
And User clicks the Login button
Then User should see an error message for invalid credentials

Scenario: Empty Fields
Given User is on the SauceDemo login page
When User leaves username and password fields empty
And User clicks the Login button
Then User should see a required field error message


Scenario: Login with locked out user
Given User is on the SauceDemo login page
When User enters username "locked_out_user"
And User enters password "secret_sauce"
And User clicks the Login button
Then User should see a locked out user error message
