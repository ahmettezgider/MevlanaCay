@Regression
Feature: Newsletter Registration

  Scenario Outline: Newsletter Registration
    Given user on home page
    And   user select the following languages as "<language>"
    When  the submissions of the following eMails should be as follows
      | email               | result |
      | aa.gmail            | not    |
      | asd@gmail.com       | fake   |
      | atezgider@gmail.com | real   |
      # not: not an email, fake: a fake email not used, real: a used email

    Examples:
      | language |
      | DE       |
      | EN       |
      | TR       |