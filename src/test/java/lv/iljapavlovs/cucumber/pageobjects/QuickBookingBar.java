package lv.iljapavlovs.cucumber.pageobjects;

import lv.iljapavlovs.cucumber.core.WebElementHelper;
import lv.iljapavlovs.cucumber.enums.TheaterLocation;
import lv.iljapavlovs.cucumber.pageobjects.base.Page;
import lv.iljapavlovs.cucumber.util.RandomUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import static lv.iljapavlovs.cucumber.config.Constants.WAIT_NORMAL_SECONDS;
import static lv.iljapavlovs.cucumber.util.DateUtil.convertToLocalDate;


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
        int randomIndexInRange = ThreadLocalRandom.current().nextInt(0, optnMoviesSessionTime.size());
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

    public QuickBookingBar selectSessionInDays(int days) {

        WebElementHelper.waitForElementToBeClickable(drpdwnSession);

        boolean sessionAvailableInDays = false;
        while (!sessionAvailableInDays) {
            for (int i = 0; i < optnMoviesList.size(); i++) {
                sessionAvailableInDays = isSessionAvailableInDays(i, days);
            }
        }

        getSessionsElementInDays(days).get(0).findElement(By.xpath("following-sibling::option[not(@disabled)]")).click();

        return this;
    }

    private List<WebElement> getSessionsElementInDays(int days) {
        LocalDate inDays = LocalDate.now().plusDays(days);
        // waiting until "Looking for sessions..." will dissapear
        new WebDriverWait(driver, WAIT_NORMAL_SECONDS).until(drvr -> drvr.findElements(By.xpath("//*[@id='quick-booking__session']//option[text()='Looking for sessions...']")).size() == 0);

        List<WebElement> collect = null;
        try {
            collect = optnMoviesSessionDates.stream()
                    .filter(el -> !(el.getText().contains("Today") || el.getText().contains("Tomorrow")))
                    .filter(el -> convertToLocalDate(el.getText(), "EEEE dd'.' MMMM").isAfter(inDays)).collect(Collectors.toList());

        } catch (DateTimeParseException e) {
            collect = optnMoviesSessionDates.stream()
                    .filter(el -> !(el.getText().contains("Today") || el.getText().contains("Tomorrow")))
                    .filter(el -> convertToLocalDate(el.getText(), "EEEE d'.' MMMM").isAfter(inDays)).collect(Collectors.toList());
        }
        return collect;
    }


    public boolean isSessionAvailableInDays(int movieIndex, int inDays) {
        selectMovieByIndex(movieIndex);
        if (getSessionsElementInDays(inDays).isEmpty()) {
            return false;
        } else {
            return true;
        }
    }
}



