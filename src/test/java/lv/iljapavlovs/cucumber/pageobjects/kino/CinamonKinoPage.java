package lv.iljapavlovs.cucumber.pageobjects.kino;


import lv.iljapavlovs.cucumber.config.ApplicationProperties;
import lv.iljapavlovs.cucumber.core.WebElementHelper;
import lv.iljapavlovs.cucumber.pageobjects.base.Page;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static lv.iljapavlovs.cucumber.config.ApplicationProperties.ApplicationProperty.APP_URL;

public class CinamonKinoPage extends Page {

    @FindBy(id = "quick-booking__film")
    private WebElement inputField;


    public CinamonKinoPage() {
        wait.until(ExpectedConditions.visibilityOf(inputField));
    }

    public static CinamonKinoPage navigate() {
        WebElementHelper.navigateToPage(ApplicationProperties.getString(APP_URL));
        return new CinamonKinoPage();
    }

    public QuickBookingBar searchFor(String textToSearchFor) {
        WebElementHelper.sendKeys(inputField, textToSearchFor, Keys.ENTER);
        return new QuickBookingBar();
    }

}