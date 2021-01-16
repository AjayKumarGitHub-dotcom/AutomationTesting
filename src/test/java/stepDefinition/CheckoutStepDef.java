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


    @And("select same delivery address")
    public void select_same_delivery_address() {
        checkout.selectDefaultAddress();
        checkout.agreeToTnCAndConfirmShipping();
    }


    @And("select payment method as {string} payment")
    public void select_payment_method_as_check_payment(String string) {
        checkout.payByCheck();

    }

}
