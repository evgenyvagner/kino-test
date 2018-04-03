@all
Feature: Validation

  Background:
    Given user navigates to Cinamon Kino page
    And Cinema Selection - user closes popup
    Then Quick Booking - is displayed
    When Quick Booking - user selects ALFA_RIGA movie theater
    And Quick Booking - user selects any movie
    And Quick Booking - user selects any session
    And Quick Booking - user clicks on Buy
    Then Ticket Selection - is displayed

  Scenario: Coupon Validation
    When Ticket Selection - user submits "asadafg" into coupon code
    Then Ticket Selection - validation error is displayed