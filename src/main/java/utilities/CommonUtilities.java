package utilities;

import managers.WDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class CommonUtilities {
    private static WebDriver driver;
    private static Robot robot;
    private static Select select;
    private static Actions actions;
    private static java.util.List<WebElement> activeLinksAndImageLinks;
    private static java.util.List<WebElement> brokenlinks;
    private static java.util.List<WebElement> javscripts;
    public CommonUtilities(){
        driver=new WDriverManager().getDriver();
        try {
            robot=new Robot();
        } catch (Exception e) {
            e.printStackTrace();
        }
        actions = new Actions(driver);
        activeLinksAndImageLinks = new ArrayList<>();
        brokenlinks = new ArrayList<>();
        javscripts = new ArrayList<>();
    }


    public static void clickWebElement(WebElement element){
        if(element.isDisplayed() && element.isEnabled()){
            element.click();}
    }

    public  static void setValue(WebElement element,String value){
        if(element.isDisplayed() && element.isEnabled()) {
            element.sendKeys(value);
        }
    }

    public  static void hitEnter(){
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
    }

    public  static void scrollTillElement(WebElement element){
        ((JavascriptExecutor)driver).executeScript("argument[0].scrollIntoView",element);
    }

    public  static void selectDropdown(WebElement element,String value){
        select=new Select(element);
        select.selectByValue(value);
    }

    public  static void selectDropDown(java.util.List<WebElement> element, String value){
        clickWebElement(element.stream().filter(e -> e.getText().equalsIgnoreCase(value)).findFirst().orElse(null));
    }

    public  static void selectCheckBoxOrRadioButton(WebElement element){
        if(!(element.isDisplayed() && element.isSelected())){
            element.click();
        }
    }

    public static void deselectCheckbox(WebElement element ){
        if(element.isDisplayed() && element.isSelected()){
            element.click();
        }

    }

    public  static void multiselect(java.util.List<WebElement> elements, java.util.List<String> items){

        robot.keyPress(KeyEvent.VK_CONTROL);
        for(String item:items){
            try {
                clickWebElement(elements.stream().filter(e ->e.getText().equalsIgnoreCase(item)).findFirst().orElseThrow(Exception::new));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        robot.keyRelease(KeyEvent.VK_CONTROL);
    }

    public  static void doubleClicking(WebElement element){
        actions.doubleClick(element).perform();
    }

    public  static void rightClick(WebElement element){
        actions.contextClick(element).perform();
    }

    public  static void hoverMouse(WebElement element){
        actions.moveToElement(element).perform();
    }

    public  static void dragDropitem(WebElement element,WebElement target){
        actions.dragAndDrop(element,target).perform();
    }

    public  static void getTooltipContent(){

    }

    public  static void checkLinks(){
        linkChecker();
    }

    private  static void linkChecker(){
        List<WebElement> total_LinksAndImageLinks = driver.findElements(By.tagName("a"));

        for(WebElement links:total_LinksAndImageLinks){
            try {
                if(links.getAttribute("href").contains("javascript")){
                    javscripts.add(links);
                }
                else if(links.getAttribute("href")==null|| (!verifyActiveLinks(links.getAttribute("href")))){
                    brokenlinks.add(links);
                }
                else{
                    activeLinksAndImageLinks.add(links);
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
    }

    private static boolean verifyActiveLinks(String url) throws MalformedURLException {
        URL ul= new URL(url);
        try {
            HttpURLConnection connection =  (HttpURLConnection)ul.openConnection();
            connection.connect();
            int resCode = connection.getResponseCode();
            connection.disconnect();
            if (resCode==200 ||resCode==401){
                return true;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }
}
