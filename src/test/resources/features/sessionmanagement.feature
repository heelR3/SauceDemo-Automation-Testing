Feature: Session Management

  Background:
    Given User is on the SauceDemo login page
    When User executes login test case "SD_TC_001"
    Then User should be redirected to the Inventory page

  @SessionRefresh
  Scenario: Verify session after browser refresh

    When User refreshes the browser
    Then User session should remain active

  @SessionNavigation
  Scenario: Verify session after navigating pages

    When User navigates between pages
    Then User should remain logged in

  @SessionLogoutAccess
  Scenario: Verify direct access after logout

    When User clicks Menu
    And User clicks Logout
    And User directly enters inventory URL
    Then User should not access inventory page after logout
