Feature: navigate in different window modes


  Scenario Outline:

    Given user on home page
    When  the browser size is as "<browserSize>"
    And   user select the following languages as "<language>"
    When  user navigate to page the following pages
      | SHOP      |
      | IMPRESSUM |
      | CONTACTUS |


    Examples: different window modes
      | language | browserSize |
      | DE       | half        |
      | DE       | max         |
      | EN       | quarter     |
      | EN       | big         |

