package lv.iljapavlovs.cucumber.stepdefs.kino;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.runtime.java.guice.ScenarioScoped;
import lv.iljapavlovs.cucumber.pageobjects.kino.ticketpurchase.TicketConfirmationSection;
import lv.iljapavlovs.cucumber.util.StringUtil;
import lv.iljapavlovs.cucumber.util.DataHolder;

import java.util.Arrays;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ScenarioScoped
public class TicketConfirmationSteps {
    TicketConfirmationSection ticketConfirmationSection;
    private DataHolder dataHolder = DataHolder.getInstance();

    @Then("^Ticket Confirmation - is displayed$")
    public void summarySelectionIsDisplayed() throws Throwable {
        ticketConfirmationSection = new TicketConfirmationSection();
        assertThat(ticketConfirmationSection.isPageDisplayed()).isTrue();
    }

    @When("^Ticket Confirmation - ticket sum is \"([^\"]*)\"$")
    public void summarySelectionTicketSumIs(String expectedTicketSumVariable) throws Throwable {
        String expected = StringUtil.findAndReplaceContextVariables(expectedTicketSumVariable);
        assertThat(ticketConfirmationSection.getTicketSum()).isEqualTo(expected);
    }

    @When("^Ticket Confirmation - (seat|row) is \"([^\"]*)\"$")
    public void summarySelectionSeatSelectionIs(String rowSeat, String expectedSeatSelectionVariable) throws Throwable {

        String replace = ticketConfirmationSection.getSelectionSeat().replaceAll("[A-Za-z]", "");
        String[] split = replace.replace("-", ",").split(",");
        String actualRow = split[0].trim();
        String[] actualSeats = Arrays.copyOfRange(split, 1, split.length);
        switch (rowSeat) {
            case "row":
                String expected = StringUtil.findAndReplaceContextVariables(expectedSeatSelectionVariable);
                assertThat(actualRow).isEqualToIgnoringCase(expected);
                break;
            case "seat":
                String[] expectedSeats = dataHolder.getSeatArr();
                assertThat(actualSeats).containsExactlyInAnyOrder(expectedSeats);
                break;
        }
    }

    @When("^Ticket Confirmation - seat selection is \"([^\"]*)\"$")
    public void summarySelectionSeatSelectionIs(String expectedVariable) throws Throwable {
        String expected = StringUtil.findAndReplaceContextVariables(expectedVariable);
        //TODO - need to parse properly
        String actual = ticketConfirmationSection.getSelectionSeat().replaceAll("\\s-", ",");

        assertThat(actual).isEqualToIgnoringCase(expected);
    }

    @Then("^Ticket Confirmation - user changes the order$")
    public void summarySelectionUserChangesTheOrder() throws Throwable {
        ticketConfirmationSection.changeOrder();
    }

    @Then("^Ticket Confirmation - (\\d+) payment methods displayed$")
    public void summarySelectionPaymentMethodsDisplayed(int expectedPaymentMethodCount) throws Throwable {
        assertThat(ticketConfirmationSection.getPaymentMethodCount()).isEqualTo(expectedPaymentMethodCount);
    }
}