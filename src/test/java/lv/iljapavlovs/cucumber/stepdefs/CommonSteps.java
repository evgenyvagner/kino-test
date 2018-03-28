package lv.iljapavlovs.cucumber.stepdefs;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import lv.iljapavlovs.cucumber.pageobjects.CinamonKinoPage;
import lv.iljapavlovs.cucumber.pageobjects.TopBar;

public class CommonSteps {
    private CinamonKinoPage cinamonKinoPage;
    private TopBar topBar;

    @Given("^I navigate to Google page")
    public void iNavigateToGoogleCom() throws Throwable {
        cinamonKinoPage = CinamonKinoPage.navigate();
    }

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
}
