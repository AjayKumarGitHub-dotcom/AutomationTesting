package stepDefinition;

import io.cucumber.java.en.And;
import pages.Cart;
import utilities.TestContext;

public class CartStepDef {

        private TestContext context;
        private Cart cart;
        public CartStepDef(TestContext testContext){
            context=testContext;
            cart= context.getPageObjectManager().getCart();
        }

        @And("And moves to checkout from mini cart")
        public void and_moves_to_checkout_from_mini_cart() {
            cart.clickCheckOutButton();
        }


}
