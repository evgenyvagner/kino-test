package lv.iljapavlovs.cucumber.stepdefs.kino;

import cucumber.api.java.en.Then;
import cucumber.runtime.java.guice.ScenarioScoped;
import lv.iljapavlovs.cucumber.model.User;
import lv.iljapavlovs.cucumber.pageobjects.kino.ProfilePage;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ScenarioScoped
public class ProfileSteps {
    private ProfilePage profilePage;

    @Then("^Profile - is displayed$")
    public void profileIsDisplayed() throws Throwable {
        profilePage = new ProfilePage();
        assertThat(profilePage.isPageDisplayed()).isTrue();
    }

    @Then("^Profile - following data is shown$")
    public void profileFollowingDataIsShown(List<User> userList) throws Throwable {
        User user = userList.get(0);
        assertThat(profilePage.getFirstName()).isEqualTo(user.getFirstName());
        assertThat(profilePage.getLastName()).isEqualTo(user.getLastName());
        assertThat(profilePage.getEmail()).isEqualTo(user.getEmail());
    }
}
