package lv.iljapavlovs.cucumber.pageobjects;


import lv.iljapavlovs.cucumber.core.WebElementHelper;
import lv.iljapavlovs.cucumber.pageobjects.base.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static lv.iljapavlovs.cucumber.config.Constants.WAIT_NORMAL_SECONDS;

public class LoginPage extends Page {

    @FindBy(css = "[name='username']")
    private WebElement inptUserName;

    @FindBy(css = "[name='password']")
    private WebElement inptPassword;

    @FindBy(xpath = "//button[text()='Log in' or text()='Ienākt' or text()='Войти']")
    private WebElement btnLogin;

    public LoginPage() {
        wait.until(ExpectedConditions.visibilityOf(inptUserName));
    }

    public void login(String userName, String password) {
        WebElementHelper.sendKeys(inptUserName, userName);
        WebElementHelper.sendKeys(inptPassword, password);
        WebElementHelper.scrollToCenterOfScreen(btnLogin);
        WebElementHelper.click(btnLogin);
        // wait until you will be logged in
        WebElementHelper.waitForVisibility(By.cssSelector("#banner-area"), WAIT_NORMAL_SECONDS);
    }

    public boolean isPageDisplayed() {
        return WebElementHelper.isElementDisplayed(inptUserName);
    }

}
