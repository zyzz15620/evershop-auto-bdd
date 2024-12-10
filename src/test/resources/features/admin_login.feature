Feature: Admin - Login
  Background:
    Given User navigate to admin login page


  Scenario: Verify login successful
    When User input "Email" field with value "total650@gmail.com"
    And User input "Password" field with value "12345678"
    And User click on button "SIGN IN"
    Then User should see Dashboard page


  Scenario Outline: Verify login form invalid
    When User input "Email" field with value <email>
    And User input "Password" field with value <password>
    And User click on button "SIGN IN"
    Then User should see error message for "Email" is <emailError>
    And User should see error message for "Password" is <passwordError>
    Examples:
      | email                | password   | emailError                    | passwordError                 |
      | ""                   | "12345678" | "This field can not be empty" | ""                            |
      | "total650@gmail.com" | ""         | ""                            | "This field can not be empty" |
      | "abc.xyz"            | "12345678" | "Invalid email"               | ""                            |
      | ""                   | ""         | "This field can not be empty" | "This field can not be empty" |