Feature: SauceDemo Cart Feature
 
@CartFeature
Scenario: Verify Complete Cart Functionality
 
Given User logs in using test case "SD_TC_001"
 
# Add Single Product
When User adds a single product to the cart
Then Cart badge count should be "1"
And Cart details should be written into Cart Excel sheet
And User captures screenshot "Add_Single_Product"
 
# Add Multiple Products
When User adds 2 more products to the cart
Then Cart badge count should be "3"
And Cart details should be written into Cart Excel sheet
And User captures screenshot "Add_Multiple_Products"
 
# Open Cart
When User opens the cart
Then Cart page should be displayed
And Cart title should be "Your Cart"
And Cart should contain 3 products
And User captures screenshot "Cart_Page"
 
# Continue Shopping
When User clicks Continue Shopping
Then Inventory page should be displayed
And User captures screenshot "Continue_Shopping"
 
# Remove Product
When User opens the cart
And User removes the product
Then Cart badge count should be "2"
And Cart details should be written into Cart Excel sheet
And User captures screenshot "Remove_Product"
 