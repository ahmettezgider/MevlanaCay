Feature: Select a product and check the information

  @Smoke @Regression
  Scenario Outline: Select product scenario outline
    Given user on home page
    And   user navigate to shop page
    When  user select the <productNumber>.th product
    Then  the name and the price should be correct in product detail page

    Examples:
      | productNumber |
      | 1             |
      | 3             |
      | 4             |
