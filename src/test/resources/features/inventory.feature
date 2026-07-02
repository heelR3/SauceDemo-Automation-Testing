Feature: SauceDemo Inventory Feature

Scenario: Complete Inventory Flow

Given User is logged into SauceDemo using "SD_TC_001"

Then Inventory page should be displayed
And Inventory title should be "Products"
And Inventory page should contain 6 products

When User extracts all inventory products
Then Product details should be written into Inventory Excel sheet

When User sorts products by "Name (A to Z)"
Then Sorted products should be written under "A-Z"

When User sorts products by "Name (Z to A)"
Then Sorted products should be written under "Z-A"

When User sorts products by "Price (low to high)"
Then Sorted products should be written under "Low-High"

When User sorts products by "Price (high to low)"
Then Sorted products should be written under "High-Low"