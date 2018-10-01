package lv.iljapavlovs.cucumber.stepdefs.google;

import com.google.inject.Inject;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.runtime.java.guice.ScenarioScoped;
import lv.iljapavlovs.cucumber.pageobjects.google.GooglePage;
import lv.iljapavlovs.cucumber.util.DataHolder;
import lv.iljapavlovs.cucumber.util.DataHolderDI;

@ScenarioScoped
public class GoogleSearchSteps {

  private GooglePage googlePage;

  @Inject
  DataHolderDI dataHolder;

  @Given("^I navigate to Google page")
  public void iNavigateToGoogleCom() throws Throwable {
    googlePage = GooglePage.navigate();
  }

  @When("^I search for \"([^\"]*)\"$")
  public void iSearchFor(String searchItem) throws Throwable {
    googlePage.searchFor(searchItem);
  }

  @When("^I set variable in one class$")
  public void iSetVariableInOneClass() throws Throwable {
    dataHolder.setSharedVariable("SHARED VARIABLE");
  }
}
