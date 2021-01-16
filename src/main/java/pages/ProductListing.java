package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import utilities.CommonUtilities;
import utilities.Waits;

import java.util.List;

public class ProductListing {
    private WebDriver driver;
    public ProductListing(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(this.driver,this);
    }


    @FindBy(how = How.XPATH,using="//h5/a[starts-with(@href,'http://automationpractice.com/index.php?id_product')]")
    private List<WebElement> products;

    @FindBy(how = How.XPATH,using = "//button[@type='submit' and @name='Submit']")
    private WebElement addToCartButton;

    @FindBy(how = How.XPATH,using = "//*[@id='layer_cart']//span[@title='Close window']")
    private WebElement crossIcon;

    @FindBy(how = How.XPATH,using = "//a[@title='View my shopping cart']")
    private  WebElement miniCart;



    public void selectProduct(String productName){
        try {
            WebElement product=products.stream().filter(p ->p.getAttribute("title").equalsIgnoreCase(productName)).findFirst().orElseThrow(Exception::new);
            CommonUtilities.clickWebElement(product);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void addTocart(){
        CommonUtilities.clickWebElement(addToCartButton);
        Waits.waitAjax(driver);
        CommonUtilities.clickWebElement(crossIcon);
        Waits.waitAjax(driver);
        CommonUtilities.clickWebElement(miniCart);
    }
}
