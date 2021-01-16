package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import utilities.CommonUtilities;
import utilities.Waits;

public class Confirmation {
    private WebDriver driver;
    public Confirmation(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(this.driver,this);
    }


    @FindBy(how = How.XPATH,using = "//span[normalize-space()='I confirm my order']")
    private WebElement confirmOrder;


    @FindBy(how = How.XPATH,using = "//p[@class='alert alert-success']")
    private WebElement successMsg;

    public void placeOrder(){
        CommonUtilities.clickWebElement(confirmOrder);
        Waits.waitAjax(driver);
    }

    public Boolean isSuccessMsg(){
        return successMsg.isDisplayed();
    }

}
