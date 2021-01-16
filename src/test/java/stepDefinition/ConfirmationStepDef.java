package stepDefinition;

import io.cucumber.java.en.And;
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
        // Write code here that turns the phrase above into concrete actions

    }
}
