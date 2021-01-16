package stepDefinition;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.testng.Assert;
import pages.Confirmation;
import utilities.TestContext;

public class ConfirmationStepDef {

    private Confirmation pagesConfirmation;
    private TestContext testContext;
    public  ConfirmationStepDef(TestContext testContext){
        this.testContext=testContext;
        pagesConfirmation = this.testContext.getPageObjectManager().getConfirmation();
    }

    @And("place the order")
    public void place_the_order() {
        pagesConfirmation.placeOrder();

    }

    @Then("gets order placed message")
    public void gets_order_placed_message_with_order_id() {
        Assert.assertTrue(pagesConfirmation.isSuccessMsg());
    }
}
