@all
Feature: Login

  Background:
    Given user navigates to Cinamon Kino page
    And Cinema Selection - user closes popup
    And Top Navigation - user ensures language is in ENG

  Scenario: Login form
    When Top Navigation - user navigates to Log In Page
    Then Login - is displayed

    When Login - default user logs in
    And Top Navigation - user goes to his profile
    Then Profile - is displayed
    And Profile - following data is shown
      | firstName | lastName | email                    |
      | Neo       | Tech     | DullCakes@mailinator.com |
