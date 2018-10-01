package lv.iljapavlovs.cucumber.stepdefs.kino;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.runtime.java.guice.ScenarioScoped;
import lv.iljapavlovs.cucumber.pageobjects.kino.ticketpurchase.SeatSelectionForm;
import lv.iljapavlovs.cucumber.util.DataHolder;

import java.util.Arrays;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ScenarioScoped
public class SeatSelection {
    SeatSelectionForm seatSelectionForm;
    private DataHolder dataHolder = DataHolder.getInstance();

    @Then("^Seat Selection - is displayed$")
    public void seatSelectionIsDisplayed() throws Throwable {
        seatSelectionForm = new SeatSelectionForm();
        assertThat(seatSelectionForm.isPageDisplayed()).isTrue();
    }

    @When("^Seat Selection - user proceeds to next step$")
    public void seatSelectionUserProceedsToNextStep() throws Throwable {
        seatSelectionForm.proceedToNextStep();
    }

    @And("^store ticket (row|seat) in \"([^\"]*)\" variable$")
    public void storeTicketRowInVariable(String rowSeat, String variable) throws Throwable {
        String seatSelection = seatSelectionForm.getSeatSelection();
        String replace = seatSelection.replaceAll("[A-Za-z]", "");
        String[] split = replace.split(",");
        switch (rowSeat) {
            case "row":
                String row = split[0].trim();
                dataHolder.getTestDataMap().put(variable, () -> row);
                break;
            case "seat":
                String[] array = Arrays.copyOfRange(split, 1, split.length);
                dataHolder.setSeatArr(array);
                break;
        }
    }

    @When("^Seat Selection - user selects any available seat$")
    public void seatSelectionUserSelectsAnyAvailableSeat() throws Throwable {
        seatSelectionForm.selectAnyAvailableSeat();
    }

    @When("^Seat Selection - user selects (\\d+) tickets on (\\d+)th row OR any available seats$")
    public void seatSelectionUserSelectsTicketsOnThORAnyAvailableSeats(int numberOfSeats, int firstRow) throws Throwable {
        for (int i = 0; i < numberOfSeats; i++) {
            if (seatSelectionForm.isSeatAvailableInRow(firstRow)) {
                seatSelectionForm.selectAvailableSeatFromRow(firstRow);
            } else {
                seatSelectionForm.selectAnyAvailableSeat();
            }
        }
    }
}
