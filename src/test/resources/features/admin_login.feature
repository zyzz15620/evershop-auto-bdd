Feature: Admin - Login
  Scenario: Verify login successful
    Given User navigate to admin login page
    When User input "Email" field with value "total650@gmail.com"
    And User input "Password" field with value "12345678"
    And User click on button "SIGN IN"
    Then I should see Dashboard page
