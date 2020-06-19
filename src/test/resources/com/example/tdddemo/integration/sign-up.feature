Feature: Sign up
  Scenario: Successful sign-up
    New user can sign up when email and password valid
    When I sign up with "test@gmail.com", "0123456789" and "123456"
    Then I sign up success with successful response
  Scenario: Sign-up with registered email
  New user can sign up when email and password valid
    Given I sign up with "test@gmail.com", "0123456789" and "123456"
    When I sign up with "test@gmail.com", "0123456781" and "123456"
    Then I sign up fail with error response
   