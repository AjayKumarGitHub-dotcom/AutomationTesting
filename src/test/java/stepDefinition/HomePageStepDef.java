package stepDefinition;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.junit.Assert;
import pages.HomePage;
import utilities.TestContext;

public class HomePageStepDef {

    private TestContext testContext;
    private HomePage pagesHomePage;
    public HomePageStepDef(TestContext context){
        testContext=context;
        pagesHomePage = testContext.getPageObjectManager().getHomePage();
    }

    @Given("user is on home page")
    public void user_is_on_home_page() {
        Assert.assertEquals(testContext.getWDriverManager().getDriver().getCurrentUrl(),"http://automationpractice.com/index.php");
    }

    @When("user searches for {string}")
    public void user_searches_for(String string) {
        pagesHomePage.searchItem(string);
    }
}
