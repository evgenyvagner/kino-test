package lv.iljapavlovs.cucumber.pageobjects.ticketpurchase;


import lv.iljapavlovs.cucumber.core.WebElementHelper;
import lv.iljapavlovs.cucumber.pageobjects.base.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class SeatSelectionForm extends Page {

    @FindBy(css = "div.pick-tickets")
    private WebElement divSeatSelectionSection;

    @FindBy(css = "#seatplan g")
    private List<WebElement> gTheaterSeats;

    @FindBy(css = "td.total")
    private WebElement tdTicketSum;

    @FindBy(css = "#seats_picked_form")
    private WebElement inptNextStepButton;

    @FindBy(css = "div.picked-summary")
    private WebElement divSeatSelection;


    public SeatSelectionForm() {
        wait.until(ExpectedConditions.visibilityOf(divSeatSelectionSection));
    }

    public List<WebElement> getSeatsFromRow(int row) {
        return driver.findElements(By.cssSelector(String.format("#seatplan g[seatmeta*='RowId\\\":\\\"%n\\\"']", row)));
    }

    public List<WebElement> getAvailableSeats() {
        return driver.findElements(By.cssSelector("#seatplan g:not(.broken)"));
    }

    public List<WebElement> getAvailableSeatsFromRow(int row) {
        return driver.findElements(By.cssSelector("#seatplan g[seatmeta*='RowId\\\":\\\"" + row + "\\\"']:not(.broken):not(.reserved)"));
    }

    public SeatSelectionForm selectAvailableSeatFromRow(int rowNumber) {

        List<WebElement> availableSeatsFromRow = getAvailableSeatsFromRow(rowNumber);
//        int randomIndexInRange = ThreadLocalRandom.current().nextInt(0, availableSeatsFromRow.size());

        WebElement element = availableSeatsFromRow.get(0);
        WebElementHelper.scrollToCenterOfScreen(element);
        WebElementHelper.click(element);
        return this;
    }

    public boolean isPageDisplayed() {
        return WebElementHelper.isElementDisplayed(divSeatSelectionSection);
    }


//    private int getReversedRowNumber(int rowNumber) {
//        return gTheaterSeats.size() - rowNumber;
//    }
//
//    private int getParsedRowNumber(String idAttribute) {
//        String s = idAttribute.split("-")[1];
//        return Integer.parseInt(s.replaceAll("\\D+", ""));
//    }

    public boolean isSeatAvailableInRow(int rowNumber) {
//        return getSeatsFromRow(rowNumber).stream()
////                .filter(el -> getReversedRowNumber(getParsedRowNumber(el.getAttribute("id"))) == rowNumber)
//                .anyMatch(el -> !el.getAttribute("class").contains("broken"));

        return getAvailableSeatsFromRow(rowNumber).size() > 0;
    }


    public SeatSelectionForm selectAnyAvailableSeat() {
//        gTheaterSeats.stream()
//                .filter(el -> !el.getAttribute("class").contains("broken"))
//                .findFirst()
//                .orElseThrow(() -> new AssertionError("All seats are booked!"))
//                .click();

        int randomIndexInRange = ThreadLocalRandom.current().nextInt(0, getAvailableSeats().size());
        WebElement element = getAvailableSeats().get(randomIndexInRange);
        WebElementHelper.scrollToCenterOfScreen(element);
        WebElementHelper.click(element);
        return this;
    }

    public void proceedToNextStep() {
        WebElementHelper.scrollToCenterOfScreen(inptNextStepButton);
        WebElementHelper.click(inptNextStepButton);
    }

    public String getSeatSelection() {
        return WebElementHelper.getText(divSeatSelection);
    }
}
