Feature: Authorization feature

  Scenario: Checking we can not login with wrong login/password credentials.
    Given user is on main page
    When user enter username 'vasya' and password 'secure1234'
    And clicks on login button
    Then user gets authentication error message