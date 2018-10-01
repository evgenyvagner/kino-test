package lv.iljapavlovs.cucumber.stepdefs.kino;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.runtime.java.guice.ScenarioScoped;
import lv.iljapavlovs.cucumber.pageobjects.kino.CinamonKinoPage;
import lv.iljapavlovs.cucumber.pageobjects.kino.CinemaSelectionPopup;
import lv.iljapavlovs.cucumber.pageobjects.kino.TopBar;

@ScenarioScoped
public class CommonSteps {
    private CinamonKinoPage cinamonKinoPage;
    private TopBar topBar;
    private CinemaSelectionPopup cinemaSelectionPopup;

    @Given("^user navigates to Cinamon Kino page$")
    public void userNavigatesToCinamonKinoPage() throws Throwable {
        cinamonKinoPage = CinamonKinoPage.navigate();
        topBar = new TopBar();
    }


    @When("^Top Navigation - user navigates to Log In Page$")
    public void userNavigatesToLogInPage() throws Throwable {
        topBar = new TopBar();
        topBar.goToLogin();
    }

    @Given("^Top Navigation - user ensures language is in (ENG|LAT|RUS)$")
    public void userEnsuresLanguageIsIn(String language) throws Throwable {
        topBar.switchLanguage(language);
    }

    @Then("^Navigation - user logs out$")
    public void navigationUserLogsOut() throws Throwable {
        topBar.logOut();
    }

    @When("^Top Navigation - user goes to his profile$")
    public void topNavigationUserGoesToHisProfile() throws Throwable {
        topBar.goToProfile();
    }

    @When("^Cinema Selection - user closes popup$")
    public void cinemaSelectionUserClosesPopup() throws Throwable {
        cinemaSelectionPopup = new CinemaSelectionPopup();
        cinemaSelectionPopup.closePopup();
    }
}
