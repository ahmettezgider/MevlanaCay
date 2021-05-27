@Regression
Feature: Shopping, Add Remove to Cart

  Scenario Outline: Add product to Cart
    Given user use "chrome" with the size "<size>"
    And   user on home page
    And   user select the following languages as "<language>"
    And   user navigate to shop page
    When  user select the <productNumber>.th product
    Then  the name and the price should be correct in product detail page
    When  user change quantity to <newQuantity>
    And   user goes to Cart
    Then  quantity should be changed
    And   the price in cart should be correct
    And   the total price in cart should be correct
    And   the product should be deleted

    Examples:
      | language | productNumber | newQuantity | size     |
      | TR       | 2             | 3           | 1360x740 |
      | EN       | 3             | 4           | 1360x740 |
      | DE       | 1             | 2           | 1360x740 |
