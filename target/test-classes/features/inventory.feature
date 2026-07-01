Feature: SauceDemo Inventory Feature

  Background:
    Given User is logged into SauceDemo

  @InventoryValidation
  Scenario: Verify Inventory Page
    Then Inventory page should be displayed
    And Inventory title should be "Products"
    And Inventory page should contain 6 products

  @InventoryExtraction
  Scenario: Extract Product Details
    When User extracts all inventory products
    Then Product details should be written into Inventory Excel sheet

  @SortAZ
  Scenario: Sort Name A to Z
    When User sorts products by "Name (A to Z)"
    Then Sorted products should be written under "A-Z"

  @SortZA
  Scenario: Sort Name Z to A
    When User sorts products by "Name (Z to A)"
    Then Sorted products should be written under "Z-A"

  @SortLowHigh
  Scenario: Sort Price Low to High
    When User sorts products by "Price (low to high)"
    Then Sorted products should be written under "Low-High"

  @SortHighLow
  Scenario: Sort Price High to Low
    When User sorts products by "Price (high to low)"
    Then Sorted products should be written under "High-Low"