Feature: Ranking crimes
  Scenario: Ranking crimes by crime type
    Given the crime type "Theft"
    When I rank crimes
    Then the most common crime should be "Theft" with frequency value 1