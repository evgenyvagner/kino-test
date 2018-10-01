package lv.iljapavlovs.cucumber.pageobjects.kino;


import lv.iljapavlovs.cucumber.core.WebElementHelper;
import lv.iljapavlovs.cucumber.pageobjects.base.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static lv.iljapavlovs.cucumber.core.DriverBase.getDriver;

public class TopBar extends Page {

    @FindBy(css = "#header .login-area .username")
    private WebElement lnkLogin;

    @FindBy(xpath = "//*[@id='langmenu']//a[text()='ENG']")
    private WebElement lnkEnglishLanguage;

    @FindBy(css = ".login-area .logout")
    private WebElement lnkLogoutButton;

    @FindBy(css = ".login-area .username")
    private WebElement lnkProfileButton;

    public TopBar() {
        wait.until(ExpectedConditions.visibilityOf(lnkLogin));
    }

    private WebElement getLanguageElement(String language) {
        return getDriver().findElement(By.xpath("//*[@id='langmenu']//a[text()='" + language + "']"));
    }

    public void goToLogin() {
        WebElementHelper.click(lnkLogin);
    }

    public TopBar switchLanguage(String language) {
        WebElement element = getLanguageElement(language);

        String elementState = WebElementHelper.getAttribute(element, "class");
        if (!elementState.equals("active")) {
            WebElementHelper.click(element);
        }

        return this;
    }

    public void logOut() {
        WebElementHelper.click(lnkLogoutButton);
    }

    public void goToProfile() {
        WebElementHelper.click(lnkProfileButton);
    }
}
