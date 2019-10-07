Feature: Order operations

  Scenario: Add chips to current order
    Given Current order contains only one burger
    When Chips are added to the current order
    Then The current order consists of one burger and one chips

  Scenario: Remove chips from current order
    Given Current order contains one burger and one chips
    When Chips are removed from the current order
    Then The current order consists of one burger

  Scenario: Trying to remove an item from an empty order
    Given Current order is empty
    When Burger is removed from current order
    Then An error is raised notifying the user that this isn't possible

  Scenario: Cancelling an order
    Given The current order contains a burger and chips
    When The customer asks to cancel the order
    Then Current order is terminated and new order is started (empty)

  Scenario: Confirming an order
    Given The customer has ordered all that they desire
    When The order is confirmed by the user
    Then The customer is asked to pay the full price which is then added to the cash register

  Scenario: Customise a menu item to suit a customers needs
    Given A customer requests no tomatoes on their burger
    When Edit item is selected
    And Remove tomatoes is selected
    Then Burger no longer has the tomatoes tag, receipt reflects this

  Scenario: A customer returns an item for a refund
    Given A customer returns items bought from the food truck
    When Total price of order is found and cash returned
    Then New cash value in the register