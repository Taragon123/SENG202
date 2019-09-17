Feature: Manager options

  Scenario: Checking the number of sales made on a given day
    Given The manager wants to check the sales for a given day
    When Date selected
    Then Sales made on selected date displayed

  Scenario: Generate sales report
    Given Sales report needed for investors etc
    When Generate sales report selected
    Then Sales report is generated