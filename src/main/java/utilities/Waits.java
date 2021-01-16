package utilities;

import managers.ReaderManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.function.Function;

public class Waits {

    public static void waitAjax(WebDriver driver){
        untilAJAXIsDone(driver, ReaderManager.getInstance().getConfigFileReader().getTimeout());
    }

    public static void untilAJAXIsDone(WebDriver driver, Long timeoutInSeconds){
        until(driver, (d) ->
        {
            Boolean isJqueryCallDone = (Boolean)((JavascriptExecutor) driver).executeScript("return jQuery.active==0");
            if (!isJqueryCallDone){ System.out.println("JQuery call is in Progress");}
            return isJqueryCallDone;
        }, timeoutInSeconds);
    }


    public static void untilPageLoadComplete(WebDriver driver) {
        untilPageLoadComplete(driver, ReaderManager.getInstance().getConfigFileReader().getTimeout());
    }

    public static void untilPageLoadComplete(WebDriver driver, Long timeoutInSeconds){
        until(driver, (d) ->
        {
            Boolean isPageLoaded = (Boolean)((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
            if (!isPageLoaded) System.out.println("Document is loading");
            return isPageLoaded;
        }, timeoutInSeconds);
    }




    private static void until(WebDriver driver, Function<WebDriver, Boolean> waitCondition, Long timeoutInSeconds){
        WebDriverWait webDriverWait = new WebDriverWait(driver, timeoutInSeconds);
        webDriverWait.withTimeout(Duration.ofSeconds(timeoutInSeconds));
        try{
            webDriverWait.until(waitCondition);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
