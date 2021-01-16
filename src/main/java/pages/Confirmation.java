package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class Confirmation {
    private WebDriver driver;
    public Confirmation(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(this.driver,this);
    }
}
