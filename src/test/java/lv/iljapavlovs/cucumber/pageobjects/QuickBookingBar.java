package lv.iljapavlovs.cucumber.pageobjects;

import lv.iljapavlovs.cucumber.core.WebElementHelper;
import lv.iljapavlovs.cucumber.enums.TheaterLocation;
import lv.iljapavlovs.cucumber.pageobjects.base.Page;
import lv.iljapavlovs.cucumber.util.RandomUtil;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;


public class QuickBookingBar extends Page {

    @FindBy(id = "quick-booking__container")
    private WebElement divQuickBookingSection;

    @FindBy(id = "quick-booking__cinema")
    private WebElement drpdwnTheaterLocation;

    @FindBy(id = "quick-booking__film")
    private WebElement drpdwnMovieTitle;

    @FindBy(id = "quick-booking__session")
    private WebElement drpdwnSession;

    @FindBy(id = "quick-booking__action")
    private WebElement btnBuy;

    @FindBy(css = "#quick-booking__film option:not([disabled])")
    private List<WebElement> optnMoviesList;

    @FindBy(css = "#quick-booking__session option:not([disabled])")
    private List<WebElement> optnMoviesSessionTime;

    @FindBy(xpath = "//*[@id='quick-booking__session']//option[@disabled][not(text()='Pick a session!')]")
    private List<WebElement> optnMoviesSessionDates;


    public QuickBookingBar() {
        wait.until(ExpectedConditions.visibilityOf(drpdwnTheaterLocation));
    }

    public boolean isPageDisplayed() {
        return WebElementHelper.isElementDisplayed(divQuickBookingSection);
    }

    public QuickBookingBar selectTheaterLocation(TheaterLocation theaterLocation) {
        WebElementHelper.selectByValue(drpdwnTheaterLocation, theaterLocation.toString());
        return this;
    }

    public QuickBookingBar selectMovieTitle(String movieTitle) {
        WebElementHelper.selectByVisiableText(drpdwnMovieTitle, movieTitle);
        return this;
    }

    public QuickBookingBar selectSession(String session) {
        WebElementHelper.waitForElementToBeClickable(drpdwnSession);
        WebElementHelper.selectByVisiableText(drpdwnSession, session);
        return this;
    }

    public QuickBookingBar selectAnySession() {
        WebElementHelper.waitForElementToBeClickable(drpdwnSession);
        int randomIndexInRange = RandomUtil.getRandomNumberInRange(optnMoviesSessionTime.size());
        WebElementHelper.selectByIndex(drpdwnSession, optnMoviesSessionTime, randomIndexInRange);
        return this;
    }


    public void buy() {
        WebElementHelper.click(btnBuy);
    }

    public QuickBookingBar selectAnyMovie() {
        int randomIndexInRange = RandomUtil.getRandomNumberInRange(optnMoviesList.size());
        WebElementHelper.selectByIndex(drpdwnMovieTitle, optnMoviesList, randomIndexInRange);
        return this;
    }

    public QuickBookingBar selectMovieByIndex(int index) {
        WebElementHelper.selectByIndex(drpdwnMovieTitle, optnMoviesList, index);
        return this;
    }
}



