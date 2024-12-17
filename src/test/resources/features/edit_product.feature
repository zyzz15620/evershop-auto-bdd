Feature: Edit product
  Background:
    Given User navigate to admin login page
    When User input "Email" field with value "$ADMIN_EMAIL$"
    And User input "Password" field with value "$ADMIN_PASSWORD$"
    And User click on button "SIGN IN"
    Then User should see "Dashboard" page

  Scenario: Verify admin is able to edit new product
    When User select menu item "Products"
    And User now in page with title "Products"
    And User create a product
    And User search for product that just created
    And User click on the product that just created
    #    And User now in page with title "Editing Backpack"
    #    Then User see should see input field "Name" is "Backpack"
    #    Then User see should see input field "SKU" is "111"
    #    Then User see should see input field "Price" is "300"
    And User input "Name" field with value "Bitis"
    And User input "SKU" field with value "222"
    And User input "Price" field with value "500"
    And User click button "Save"
    Then User see alert notification "Product saved successfully!"