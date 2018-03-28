package lv.iljapavlovs.cucumber.pageobjects.ticketpurchase;


import lv.iljapavlovs.cucumber.pageobjects.base.Page;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MovieInfoSection extends Page {

    @FindBy(id = "content")
    private WebElement frmTicketForm;

    public MovieInfoSection() {
        wait.until(ExpectedConditions.visibilityOf(frmTicketForm));
    }

}
