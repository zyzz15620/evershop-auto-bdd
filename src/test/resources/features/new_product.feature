Feature: New product
  Background:
    Given User navigate to admin login page

  Scenario: Verify admin is able to create new product
    When User input "Email" field with value "total650@gmail.com"
    And User input "Password" field with value "12345678"
    And User click on button "SIGN IN"
    Then User should see "Dashboard" page
    When User select menu item "New Product"
    Then User should see "New Product" page with title "Create A New Product"
    When User input "Name" field with value "Bitis"
    And User input "SKU" field with value "123456"
    And User input "Price" field with value "100"
    And User input "Weight" field with value "0.5"
    And User select category "Men" on New Product page
    And User select "None" on dropdown Tax class
    And User input product description on New Product page with layout "1"
    """
      My new product description 0
    """
    And User input product description on New Product page with layout "3"
    """
      My new product description 1
                <-->
      My new product description 2
    """
    And User push image "sweater.webp, image1.png" on New Product page
    And User input "Url key" field with value "new_brand"
    And User input "Meta title" field with value "new bitis"
    And User input "Meta keywords" field with value "Bitis, new bitis"
    And User input "Meta description" textarea with value "Bitis, new bitis"
    And User select option "Enabled" of radio "Status"
    And User select option "Visible" of radio "Visibility"
    And User select option "Yes" of radio "Manage stock?"
    And User select option "Yes" of radio "Stock availability"
    And User input "Quantity" field with value "10"
    And User select "Default" on dropdown Attribute Group
    And User select "Black" on dropdown Attributes "Color"
    And User select "XXL" on dropdown Attributes "Size"
    And User click button "Save"
    Then User see alert notification "Product saved successfully!"