@Android
Feature: Check Android sample app behaviours

  @WiFiPreferences
  Scenario: WiFi preferences
    Given I navigate to Preference dependencies
    When Turn ON WiFi option
    And Select WiFi Settings and write MySettings and click OK to close the dialog
    Then Wifi should be enable
    Then Wifi settings should has the text MySettings

  @ContextualAction
  Scenario: Contextual action
    Given I navigate to Views -> Expandable Lists -> Custom Adapter
    When I Perform a long tap on People Names row
    And Select Sample action in the Sample menu
    Then A Toast appears with some text

  @DragAndDrop
  Scenario: Drag and Drop
    Given I navigate through Views -> Drag and Drop
    When I Drag the top left circle to the top right circle
    Then I should see a text appears at the bottom saying "Dropped!"

  @Search
  Scenario: Search
    Given I navigate through Views -> Search View -> Filter
    When I write "Colby" as text filter
    Then The first result should be Colby

  @WebView
  Scenario: WebView
    Given navigate through Views -> WebView
    When I tap on the 'I am a link' link
    Then I should see a web with the text "I am one other page content" appears