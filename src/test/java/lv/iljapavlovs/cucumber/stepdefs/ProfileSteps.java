package lv.iljapavlovs.cucumber.stepdefs;

import cucumber.api.java.en.Then;
import lv.iljapavlovs.cucumber.model.User;
import lv.iljapavlovs.cucumber.pageobjects.ProfilePage;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/**
 * Created by ilya.pavlov on 3/28/2018.
 */
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
