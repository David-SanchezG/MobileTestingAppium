@iOS
Feature: Check iOS sample app behaviours

  @ColorPicker
  Scenario: Color picker
    Given I navigate to Picker View
    When I set the colors to 70, 210, 125
    Then I should see the color numbers 70, 210, 125

  @Stacks
  Scenario: Stacks
    Given I navigate to Stack Views
    When I add further details
    And I add a view
    Then I should see further detail
    And I should see a new view

  @DatePicker
  Scenario: Date picker
    Given I navigate to Date Picker
    When I set the time to tomorrow at 15 hours and 30 minutes
    Then I should see a text with tomorrow at 15 hours and 30 minutes