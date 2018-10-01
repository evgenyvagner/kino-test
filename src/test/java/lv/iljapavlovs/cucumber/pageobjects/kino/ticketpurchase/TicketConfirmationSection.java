package lv.iljapavlovs.cucumber.pageobjects.kino.ticketpurchase;


import lv.iljapavlovs.cucumber.core.WebElementHelper;
import lv.iljapavlovs.cucumber.pageobjects.base.Page;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class TicketConfirmationSection extends Page {

    @FindBy(css = ".entry-header")
    private WebElement hdrSummarySelectionSection;

    @FindBy(css = "tfoot .total")
    private WebElement tdTicketSum;

    @FindBy(css = ".ticket-row:nth-child(5) td")
    private WebElement tdSeatSelection;

    @FindBy(css = "a.change-btn")
    private WebElement lnkChangeOrderButton;

    @FindBy(css = "#submit_order_form .payment-methods li")
    private List<WebElement> liPaymentMethods;

    public TicketConfirmationSection() {
        wait.until(ExpectedConditions.visibilityOf(hdrSummarySelectionSection));
    }

    public boolean isPageDisplayed() {
        return WebElementHelper.isElementDisplayed(hdrSummarySelectionSection);
    }

    public String getTicketSum() {
        return WebElementHelper.getText(tdTicketSum);
    }

    public String getSelectionSeat() {
        return WebElementHelper.getText(tdSeatSelection);
    }

    public void changeOrder() {
        WebElementHelper.click(lnkChangeOrderButton);
    }

    public int getPaymentMethodCount() {
        return liPaymentMethods.size();
    }
}

