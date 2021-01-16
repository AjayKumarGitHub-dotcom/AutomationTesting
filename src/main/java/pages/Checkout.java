package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import utilities.CommonUtilities;
import utilities.Waits;

public class Checkout {
    private WebDriver driver;
    public Checkout(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(this.driver,this);
    }


    @FindBy(how = How.XPATH,using = "//button[@name='processAddress']//span[contains(text(),'Proceed to checkout')]")
    private WebElement confirmAddress;

    @FindBy(how = How.XPATH,using = "//input[@id='cgv']")
    private WebElement TnCheckbox;

    @FindBy(how = How.XPATH,using = "//button[@name='processCarrier']//span[contains(text(),'Proceed to checkout')]")
    private WebElement confirmShipping;

    @FindBy(how = How.XPATH,using = "//a[@title='Pay by check.']")
    private WebElement payByCheckBtn;

    @FindBy(how = How.XPATH,using = "//a[@title='Pay by bank wire']")
    private WebElement payBybankWire;


    public void selectDefaultAddress(){
        CommonUtilities.clickWebElement(confirmAddress);
        Waits.waitAjax(driver);
    }

    public void agreeToTnCAndConfirmShipping(){
        CommonUtilities.selectCheckBoxOrRadioButton(TnCheckbox);
        CommonUtilities.clickWebElement(confirmShipping);
        Waits.waitAjax(driver);
    }

    public void payByCheck(){
        CommonUtilities.clickWebElement(payByCheckBtn);
        Waits.waitAjax(driver);
    }


    public void payByBankWire() {
        CommonUtilities.clickWebElement(payBybankWire);
        Waits.waitAjax(driver);
    }
}
