@Navigation
Feature: Navigation Functionality
 
  Background:
 
    Given User is on the SauceDemo login page
    When User executes login test case "SD_TC_001"
    Then User should be redirected to the Inventory page
 
  # SD_TC_030
 
  Scenario: Verify Add to Cart from Product Details
 
    When User opens product details page for "Sauce Labs Backpack"
    And User clicks Add to Cart from product details page
    Then Remove button should be displayed
 
    And User captures navigation screenshot "ProductDetails_AddToCart"
 
  # SD_TC_031
 
  Scenario: Verify Remove button on Product Details page
 
    When User opens product details page for "Sauce Labs Backpack"
    And User clicks Add to Cart from product details page
    Then Remove button should be displayed
 
    When User clicks Remove button on product details page
    Then Add to Cart button should be displayed
 
    And User captures navigation screenshot "ProductDetails_Remove"
 
  # SD_TC_032
 
  Scenario: Verify Back to Products button
 
    When User opens product details page for "Sauce Labs Backpack"
    And User clicks Back to Products button
    Then User should be redirected to Inventory page
 
    And User captures navigation screenshot "BackToProducts"
 
  # SD_TC_033
 
  Scenario: Verify Inventory to Cart navigation
 
    When User clicks Cart icon
    Then Cart page should be displayed
 
    And User captures navigation screenshot "InventoryToCart"
 
  # SD_TC_034
 
  Scenario: Verify Cart to Inventory navigation
 
    When User clicks Cart icon
    Then Cart page should be displayed
 
    When User clicks Continue Shopping
    Then User should be redirected to Inventory page
 
    And User captures navigation screenshot "CartToInventory"
 
  # SD_TC_035
 
  Scenario: Verify Checkout navigation
 
    When User adds a single product to the cart
    And User opens the cart
    Then Cart page should be displayed
 
    When User clicks Checkout
    Then Checkout Information page should be displayed
 
    And User captures navigation screenshot "CheckoutNavigation"
 
  # SD_TC_036
 
  Scenario: Verify Back Home navigation
 
    When User adds a single product to the cart
    And User opens the cart
    And User clicks Checkout
 
    When User enters checkout details for test case "SD_TC_036"
      | John | Doe | 560001 |
 
    And User clicks Continue
    Then Checkout Overview page should be displayed
 
    When User clicks Finish
    Then Checkout Complete page should be displayed
 
    When User clicks Back Home button
    Then User should be redirected to Inventory page
 
    And User captures navigation screenshot "BackHome"
 
  # SD_TC_037
 
  Scenario: Verify Logout navigation
 
    When User clicks Menu
    And User clicks Logout
    Then User should be redirected to Login page
 
    And User captures logout screenshot "LogoutNavigation"