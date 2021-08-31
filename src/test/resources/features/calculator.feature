Feature: Calculator - Addition

  Scenario: Adding positive values
    Given Caluclator object is created
    When adding 20 with 25
    Then method returns 45

  Scenario Outline: Adding positive values (parameterized)
    Given Caluclator object is created
    When adding <argument1> with <argument2>
    Then method returns <result>
    Then method returns some <result>

    Examples:
    | argument1 | argument2 | result |
    | 20        | 25        | 45     |
    | 8         | 17        | 25     |
    | 8         | 18        | 26     |
    | 8         | 17        | 25     |