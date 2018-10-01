package lv.iljapavlovs.cucumber.pageobjects.kino;


import lv.iljapavlovs.cucumber.core.WebElementHelper;
import lv.iljapavlovs.cucumber.pageobjects.base.Page;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CinemaSelectionPopup extends Page {

    @FindBy(css = "#cinema-selector")
    private WebElement divCinemaSelectionPopup;

    @FindBy(css = ".close-reveal-modal")
    private WebElement lnkClosePopup;

    public CinemaSelectionPopup() {
        wait.until(ExpectedConditions.visibilityOf(divCinemaSelectionPopup));
    }

    public void closePopup() {
        WebElementHelper.click(lnkClosePopup);
    }

}
