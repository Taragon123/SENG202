Feature: Menu operations

  Scenario: Change the price of menu item
    Given The current price of a burger is $10
    When The user selects change price
    And Enters new price value ($12)
    And Selects save changes
    Then The current price of a burger is now the new price ($12)

