package lv.iljapavlovs.cucumber.pageobjects.ticketpurchase;


import lv.iljapavlovs.cucumber.core.WebElementHelper;
import lv.iljapavlovs.cucumber.pageobjects.base.Page;
import lv.iljapavlovs.cucumber.util.RandomUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

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
        WebElement element = availableSeatsFromRow.get(0);
        WebElementHelper.scrollToCenterOfScreen(element);
        WebElementHelper.click(element);
        return this;
    }

    public boolean isPageDisplayed() {
        return WebElementHelper.isElementDisplayed(divSeatSelectionSection);
    }

    public boolean isSeatAvailableInRow(int rowNumber) {
        return getAvailableSeatsFromRow(rowNumber).size() > 0;
    }

    public SeatSelectionForm selectAnyAvailableSeat() {
        int randomIndexInRange = RandomUtil.getRandomNumberInRange(getAvailableSeats().size());
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
