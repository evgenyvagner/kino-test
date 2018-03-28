package lv.iljapavlovs.cucumber.stepdefs;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import lv.iljapavlovs.cucumber.pageobjects.ticketpurchase.TicketForm;
import lv.iljapavlovs.cucumber.util.TestDataContext;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public class TicketSelectionSteps {
    private TicketForm ticketForm;

    private TestDataContext testDataContext = TestDataContext.getInstance();

    @When("^Ticket Selection - member club user adds (\\d+) tickets$")
    public void ticketFormUserAddsTickets(int ticketCount) throws Throwable {
        for (int i = 0; i < ticketCount; i++) {
            ticketForm.addTicketForMember();
        }
    }

    @Then("^Ticket Selection - is displayed$")
    public void ticketFormIsDisplayed() throws Throwable {
        ticketForm = new TicketForm();
        assertThat(ticketForm.isPageDisplayed()).isTrue();
    }

    @Then("^store ticket sum in \"([^\"]*)\" variable$")
    public void storeTicketSumInVariable(String ticketSumVariable) throws Throwable {
        String ticketSum = ticketForm.getTicketSum();
        testDataContext.getTestDataMap().put(ticketSumVariable, () -> ticketSum);
    }

    @Then("^Ticket Selection - user proceeds to next step$")
    public void ticketFormUserProceedsToNextStep() throws Throwable {
        ticketForm.proceedToNextStep();
    }

    @When("^Ticket Selection - user submits \"([^\"]*)\" into coupon code$")
    public void ticketSelectionUserTypesIntoCouponCode(String invalidString) throws Throwable {
        ticketForm.submitCouponCode(invalidString);
    }

    @Then("^Ticket Selection - validation error is displayed$")
    public void ticketSelectionValidationErrorIsDisplayed() throws Throwable {
        assertThat(ticketForm.isCouponValidationErrorDisplayed()).isTrue();
    }
}
