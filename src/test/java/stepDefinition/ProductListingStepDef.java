package stepDefinition;

import enums.Context;
import io.cucumber.java.en.And;
import org.testng.asserts.SoftAssert;
import pages.ProductListing;
import utilities.TestContext;

import java.util.Map;

public class ProductListingStepDef {
    private TestContext context;
    private ProductListing productListing;

    public ProductListingStepDef(TestContext testContext){
        context=testContext;
        productListing= context.getPageObjectManager().getProductListing();
    }

    @And("choose to buy the first item")
    public void choose_to_buy_the_first_item() {

        productListing.selectProduct(((Map<String,String>)context.getScenarioContextInstance().getContext(Context.TESTDATA)).get("dress_name"));
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(productListing.getDressSelected(),((Map<String,String>)context.getScenarioContextInstance().getContext(Context.TESTDATA)).get("dress_name"));
        softAssert.assertAll();
        productListing.addTocart();

    }
}
