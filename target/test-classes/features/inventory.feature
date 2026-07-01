Feature: SauceDemo Inventory Feature
 
  As a user
  I want to validate the Inventory page
  So that I can verify products and sorting functionality
 
  Background:
    Given User logs in using test case "SD_TC_001"
 
  @InventoryValidation
  Scenario: Verify Inventory Page
 
    Then Inventory page should be displayed
    And Inventory title should be "Products"
    And Inventory page should contain 6 products
 
  @ProductExtraction
  Scenario: Extract Product Details
 
    When User extracts all inventory products
    Then Product details should be written into Inventory Excel sheet
 
  @SortNameAZ
  Scenario: Verify Product Sorting Name A to Z
 
    When User sorts products by "Name (A to Z)"
    Then Sorted products should be written under "A-Z"
 
  @SortNameZA
  Scenario: Verify Product Sorting Name Z to A
 
    When User sorts products by "Name (Z to A)"
    Then Sorted products should be written under "Z-A"
 
  @SortPriceLowHigh
  Scenario: Verify Product Sorting Price Low to High
 
    When User sorts products by "Price (low to high)"
    Then Sorted products should be written under "Low-High"
 
  @SortPriceHighLow
  Scenario: Verify Product Sorting Price High to Low
 
    When User sorts products by "Price (high to low)"
    Then Sorted products should be written under "High-Low"