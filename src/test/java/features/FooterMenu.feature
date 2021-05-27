@FooterMenu @Regression @Smoke
Feature: Footer Menu functionality

  @FooterMenuWorkJustify
  Scenario Outline: Footer manu should work properly at Desktop and Mobile mode
    Given user use "<browser>" with the size "<size>"
    And   user on home page
    When  user select the following languages
      | DE |
      | EN |
      | TR |
    Then  footer menu should work properly
    And   close the browser

    Examples:
      | browser | size     |
      | chrome  | 600x740  |
      | chrome  | 1300x740 |


  @FooterMenuLinkJustify
  Scenario: Main and footer menu links should have destination
    Given user on home page
    Then  footer menu links should work properly
