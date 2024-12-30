Feature: User's Management - Change Name

  Scenario Outline: Change a user's name in the User Management section
    Given I am on the login page
    When I log in with email and password
    And I navigate to 'User Management'
    And I click on user with name "Kanis Student"
    And I click 'Options'
    And I type as the new full name "Kanis Student3"
    Then the user's full name should be updated to "Kanis Student3"

    Examples:
      | email             | password | oldName            | newName           |
      | qa.sofi@gmail.com | ABC123   | Kanis Student      | Kanis Student3    |
