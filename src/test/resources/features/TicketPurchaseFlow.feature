@all
Feature: Ticket Purchase Flow

  Background:
    Given user navigates to Cinamon Kino page
    And Top Navigation - user ensures language is in ENG
    When Top Navigation - user navigates to Log In Page
    Then Login - is displayed
    And Login - default user logs in

  Scenario Outline: Ticket purchase flow - E2E

    Given Quick Booking - is displayed
    When Quick Booking - user selects ALFA_RIGA movie theater
    And Quick Booking - user selects any movie
#    And user selects any movie with a session in 5 days
#    And user selects session in 5 days
    And Quick Booking - user selects any session
    And Quick Booking - user clicks on Buy

    Then Ticket Selection - is displayed
    When Ticket Selection - member club user adds <numberOfTicketsFirstTry> tickets
    Then store ticket sum in "ticket_sum" variable

    When Ticket Selection - user proceeds to next step
    Then Seat Selection - is displayed

    When Seat Selection - user selects <numberOfTicketsFirstTry> tickets on 5th row OR any available seats

#    And store seat selection in "seat_selection" variable
    And store ticket row in "row" variable
    And store ticket seat in "seat" variable
    And Seat Selection - user proceeds to next step
    Then Ticket Confirmation - is displayed

    When Ticket Confirmation - ticket sum is "${ticket_sum}"
#    Then Ticket Confirmation - seat selection is "${seat_selection}"

    Then Ticket Confirmation - row is "${row}"
    Then Ticket Confirmation - seat is "${seat}"

    When Ticket Confirmation - user changes the order
    Then Ticket Selection - is displayed

    When Ticket Selection - member club user adds 1 tickets
    Then store ticket sum in "ticket_sum2" variable

    When Ticket Selection - user proceeds to next step
    And Seat Selection - user selects any available seat
    And Seat Selection - user proceeds to next step
    Then Ticket Confirmation - ticket sum is "${ticket_sum2}"

#    ONLY 4 payment methods displayed, not 5
    And Ticket Confirmation - 4 payment methods displayed

    Then Navigation - user logs out

    Examples:
      | numberOfTicketsFirstTry |
      | 2                       |