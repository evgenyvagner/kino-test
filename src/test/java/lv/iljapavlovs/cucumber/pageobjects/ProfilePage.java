package lv.iljapavlovs.cucumber.pageobjects;


import lv.iljapavlovs.cucumber.core.WebElementHelper;
import lv.iljapavlovs.cucumber.pageobjects.base.Page;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProfilePage extends Page {

    @FindBy(css = "#firstname")
    private WebElement inptFirstName;

    @FindBy(css = "#lastname")
    private WebElement inptLastName;

    @FindBy(css = "[name='email']")
    private WebElement inptEmail;


    public ProfilePage() {
        wait.until(ExpectedConditions.visibilityOf(inptFirstName));
    }

    public String getFirstName() {
        return WebElementHelper.getValue(inptFirstName);
    }

    public String getLastName() {
        return WebElementHelper.getValue(inptLastName);
    }

    public String getEmail() {
        return WebElementHelper.getValue(inptEmail);
    }

    public boolean isPageDisplayed() {
        return WebElementHelper.isElementDisplayed(inptFirstName);
    }
}
