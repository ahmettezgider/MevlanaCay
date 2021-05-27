@MainMenu @Smoke @Regression
Feature: Main Menu functionality

  @MainMenuWorkJustify
  Scenario Outline: Main manu should work properly at Desktop and Mobile mode
    Given user use "<browser>" with the size "<size>"
    And   user on home page
    When  user select the following languages
      | DE |
      | EN |
      | TR |
    Then  main menu should work properly
    And   close the browser

    Examples:
      | browser | size     |
      | chrome  | 600x740  |
      | chrome  | 1300x740 |


  @MainMenuLinkJustify
  Scenario: Main and footer menu links should have destination
    Given user on home page
    When  user select the following languages
      | DE |
      | EN |
      | TR |
    Then  main menu links should work properly
