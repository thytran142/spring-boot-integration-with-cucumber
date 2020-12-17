Feature: Sign up as a new user
  In order to sign up as a new user
  I need to be able to sign up a new account.
  Scenario: Sign up a new user with a new email address
    When I send a "POST" api request to "/users/sign-up" with body:
    """
    {
      "firstName": "Vanessa",
      "lastName": "Tran",
      "email": "vanessa@vanntechs.com",
      "password": "1234567"
    }
    """
    And print last JSON response
    Then the response status code should be 201
    Then print last JSON response
