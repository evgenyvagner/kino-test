package lv.iljapavlovs.cucumber.pageobjects.ticketpurchase;


import lv.iljapavlovs.cucumber.core.WebElementHelper;
import lv.iljapavlovs.cucumber.pageobjects.base.Page;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class TicketForm extends Page {

    @FindBy(css = "#ticket_form")
    private WebElement frmTicketForm;

    @FindBy(css = "#mainticket")
    private WebElement inptAddTicketButton;

    @FindBy(xpath = "//tr[child::*[text()='Kluba biedra']]//input[@value='+']")
    private WebElement inptAddTicketButtonForMembers;

    @FindBy(css = "td.total")
    private WebElement tdTicketSum;

    @FindBy(css = "#next-step")
    private WebElement inptNextStepButton;

    @FindBy(css = "#ticket_form .voucher input")
    private WebElement inptCouponCodeInput;

    @FindBy(css = "small.error")
    private WebElement smlValidationError;


    public TicketForm() {
        wait.until(ExpectedConditions.visibilityOf(frmTicketForm));
    }

    public TicketForm addTicket() {
        WebElementHelper.click(inptAddTicketButton);
        return this;
    }

    public TicketForm addTicketForMember() {
        WebElementHelper.click(inptAddTicketButtonForMembers);
        return this;
    }

    public boolean isPageDisplayed() {
        return WebElementHelper.isElementDisplayed(frmTicketForm);
    }

    public String getTicketSum() {
        return WebElementHelper.getText(tdTicketSum);
    }

    public void proceedToNextStep() {
        WebElementHelper.scrollToCenterOfScreen(inptNextStepButton);
        WebElementHelper.click(inptNextStepButton);
    }

    public TicketForm submitCouponCode(String couponCode) {
        WebElementHelper.sendKeys(inptCouponCodeInput, couponCode);
        return this;
    }

    public boolean isCouponValidationErrorDisplayed() {
        return WebElementHelper.isElementDisplayed(smlValidationError);
    }
}
