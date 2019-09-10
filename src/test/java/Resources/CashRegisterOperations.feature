Feature: Adding cash to the cash register

  Scenario: Add cash to get total
    Given $100 current total cash
    When $50 is add to the current total cash
    Then The current total cash is now $150
    When $50 is removed from the current total cash
    Then Current total cash is now $100