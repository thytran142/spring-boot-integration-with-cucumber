Feature: Get the version of CRM app.
  In order to get the version of this app
  As the Angular-Front application
  I need to be able to get the current version.
  Scenario: Get the latest version of this running application.
    When I send a "GET" api request to "/version"
    Then the response status code should be 200
    And the response should be in JSON