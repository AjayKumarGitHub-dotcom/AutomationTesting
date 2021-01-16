package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class Checkout {
    private WebDriver driver;
    public Checkout(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(this.driver,this);
    }
}
