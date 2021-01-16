package stepDefinition;

import io.cucumber.java.en.And;
import pages.Checkout;
import utilities.TestContext;

public class CheckoutStepDef {


    private TestContext context;
    private Checkout checkout;

    public CheckoutStepDef(TestContext testContext){
        context=testContext;
        checkout=context.getPageObjectManager().getCheckout();
    }
    @And("enter personal details on checkout page")
    public void enter_personal_details_on_checkout_page() {

        // Write code here that turns the phrase above into concrete actions

    }

    @And("select same delivery address")
    public void select_same_delivery_address() {

        // Write code here that turns the phrase above into concrete actions
    }

    @And("select payment method as {string} payment")
    public void select_payment_method_as_check_payment(String string) {

        // Write code here that turns the phrase above into concrete actions

    }
}
