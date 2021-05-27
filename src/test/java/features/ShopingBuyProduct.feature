@Regression
Feature: Shopping, Add Remove to Cart

  Scenario Outline: Add product to Cart
    Given user use "chrome" with the size "<size>"
    And   user on home page
    And   user select the following languages as "<language>"
    And   user navigate to shop page
    And   user select the <productNumber>.th product
    And   user goes to Cart
    And   user click to Checkout
    When  user fill the order form as follows
      | FirstName   | LastName | Company | Country | Address1 | Address2 | Postcode | City | Phone | Email           | AboneCheckBox | OrderComments | Terms | Result |
      | TechnoStudy | Test     | company | 2       | Address1 | Address2 | 12345    | City | 12345 | email@email.com | false         | OrderComments | true  | false  |
      |             | Test     | company | 6       | Address1 | Address2 | 12345    | City | 12345 | email@email.com | false         | OrderComments | true  | false  |
      #| TechnoStudy | Test     | company | 2       | Address1 | Address2 | 12345    | City | 12345 | email@email.com | false         | OrderComments | true  | true   |

    Examples:
      | language | productNumber | size     |
      | TR       | 2             | 1360x740 |
      | EN       | 3             | 1360x740 |
      | DE       | 1             | 1360x740 |


