@ContactUs @Regression
Feature: Contact page functionality

  Scenario Outline: Contact Form functionality
    Given user on home page
    When  user select the following languages as "<language>"
    And   user navigate to contact page
    Then  the success of submission of the contact form should be as follows
      | name                | email            | subject      | message      | success |
      |                     |                  |              | message test | false   |
      |                     |                  | subject test | message test | false   |
      |                     | techno@study.com | subject test | message test | false   |
      | TechnoStudy Eu Test |                  | subject test | message test | false   |
      #| TechnoStudy Eu Test | techno@study.com | subject test | message test | true    |

    Examples:
      | language |
      | DE       |
      | EN       |
      | TR       |


