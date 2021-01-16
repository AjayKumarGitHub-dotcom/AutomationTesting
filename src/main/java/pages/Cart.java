package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import utilities.CommonUtilities;

public class Cart {
    private WebDriver driver;
    public Cart(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(this.driver,this);
    }
    @FindBy(how = How.XPATH,using ="//span[text()='Proceed to checkout']" )
    public WebElement proceedToPaybutton;

    public void clickCheckOutButton(){
        CommonUtilities.clickWebElement(proceedToPaybutton);

    }
}
