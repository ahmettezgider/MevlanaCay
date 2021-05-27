@Regression
Feature: Write review for a product


  Scenario Outline: Writing a review for a product
    Given user on home page
    And   user select the following languages as "<language>"
    And   user navigate to shop page
    When  user select the <prod>.th product
    Then  the name and the price should be correct in product detail page

    When user click to the review tab in product detail

    Then  the success of submission of the review form should be as follows
      | stars | comment             | name         | email            | success |
      |       |                     |              |                  | false   |
      | 1     |                     |              |                  | false   |
      | 2     | TechnoStudy Eu Test |              |                  | false   |
      | 3     |                     |              | techno@study.com | false   |
      | 4     |                     | Ahmet        |                  | false   |
      | 5     | TechnoStudy Eu Test |              | techno@study.com | false   |
      | 5     | TechnoStudy Eu Test | subject test | techno@study.com | true    |

    Examples:
      | language | prod |
      | DE       | 1    |
      | EN       | 2    |
      | TR       | 3    |

