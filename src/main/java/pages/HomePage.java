package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import utilities.CommonUtilities;
import utilities.Waits;

public class HomePage {
    private WebDriver driver;
    public HomePage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(this.driver,this);
    }

    @FindBy(how = How.ID,using = "search_query_top")
    private WebElement searchBox;

    @FindBy(how = How.XPATH,using = "//button[@name='submit_search']")
    private WebElement searchIcon;

    public void searchItem(String value){
        Waits.untilPageLoadComplete(driver);
        CommonUtilities.setValue(searchBox,value);
        CommonUtilities.clickWebElement(searchIcon);
    }

}
