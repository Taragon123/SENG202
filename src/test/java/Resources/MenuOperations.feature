Feature: Menu operations

  Scenario: Change the price of menu item
    Given The current price of a burger is $10
    When The user selects change price
    And Enters new price value ($12)
    And Selects save changes
    Then The current price of a burger is now the new price ($12)

  Scenario: Upload a new menu to the system
    Given A new menu has been created
    When The user uploads to the new menu to the system
    Then The menu is now available for use within the system

  Scenario: Checking the recipe for a given menu item
    Given Menu is open and the user needs to check the recipe for a burger
    When A burger is selected
    Then The recipe for a burger is displayed

  Scenario: Exporting menus to an external file
    Given Menus need to be exported for review etc
    When Save to external file is selected
    Then Menu.csv saved in the same directory as program

  Scenario: Load menus from an external file
    Given Files that are not in the system need to be opened
    When User selects load new menu
    And Selects the file they wish to open
    And Selects open
    Then The selected file is open in the system

  Scenario: Add item to existing menu
    Given A new burger is created
    When Edit menu is selected
    And Burger information added
    Then New burger now in menu

  Scenario: Remove item from existing menu
    Given A burger is no longer being sold
    When Edit menu is selected
    And Burger is removed
    Then Burger no longer in menu

  Scenario: Add recipe to accompany a existing menu item
    Given A new recipe for an item is created
    When Add recipe is selected
    And Recipe is selected
    And Add is selected
    Then Recipe is now in the menu

