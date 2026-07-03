@Cart
Feature: Cart Functionality
 
  Scenario: Verify Complete Cart Functionality
 
    Given User is on the SauceDemo login page
	When User executes login test case "SD_TC_001"
 
    # Add Single Product
    When User adds a single product to the cart
    Then Cart badge count should be "1"
 
    When User opens the cart
    Then Cart page should be displayed
    And Cart title should be "Your Cart"
    And Cart should contain 1 products
    And Cart details should be written into Cart Excel sheet with action "Added"
    And User captures screenshot "Cart_One_Product"
 
    # Continue Shopping
    When User clicks Continue Shopping
 
    # Add Multiple Products
    When User adds 2 more products to the cart
    Then Cart badge count should be "3"
 
    When User opens the cart
    Then Cart should contain 3 products
    And Newly added products should be written into Cart Excel sheet with action "Added"
    And User captures screenshot "Cart_Three_Products"
 
    # Remove Product
    When User removes the first product
    Then Cart badge count should be "2"
    And Removed product should be written into Cart Excel sheet with action "Removed"
    And User captures screenshot "Cart_After_Remove"
 
    # Continue Shopping
    When User clicks Continue Shopping