Feature: Acceptance testing for our project

  Scenario: Add cash to get total
    Given $100 current total cash
    When $50 is add to the current total cash
    Then The current total cash is now $150