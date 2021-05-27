@Regression
Feature: Checks all the links, images and videos of the shop pages

  Scenario Outline: Checks all links of the shop page
    Given user on home page
    And user choose the language "<language>"
    When user navigate to shop page
    Then all links should be properly defined

    Examples:
      | language |
      | DE       |
      | TR       |
      | EN       |

  Scenario Outline: Checks all images of the shop page
    Given user on home page
    And user choose the language "<language>"
    When user navigate to shop page
    Then all images should be properly defined

    Examples:
      | language |
      | DE       |
      | TR       |
      | EN       |


  Scenario Outline: Checks the videos of the shop page
    Given user on home page
    And user choose the language "<language>"
    When user navigate to tv spot page
    Then all videos should be properly defined

    Examples:
      | language |
      | DE       |
      | TR       |
      | EN       |
