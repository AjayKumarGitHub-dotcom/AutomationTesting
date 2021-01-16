package managers;

import enums.DriverType;
import enums.EnvironmentType;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;


public class WDriverManager {
    private WebDriver driver;
    private static EnvironmentType enum_environmentType = ReaderManager.getInstance().getConfigFileReader().getEnvironmentType();
    private static DriverType enum_driverType = ReaderManager.getInstance().getConfigFileReader().getDriverType();

    public WebDriver getDriver(){
        return (driver==null)?driver=createWebDriver():driver;
    }


    public WebDriver createWebDriver(){
        switch (enum_environmentType){
            case LOCAL: driver =createLocalDriver();
                break;
            case REMOTE: driver=createRemoteDriver();
                break;
            default:
                throw new RuntimeException("please check the Environment passed, currently supporting Local and Remote");
        }
        return driver;
    }

    public WebDriver createLocalDriver(){
        switch (enum_driverType){
            case CHROME:
                WebDriverManager.chromedriver().setup();
                System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY,"true");
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.setAcceptInsecureCerts(true);

                driver = new ChromeDriver(chromeOptions);
                break;
            case FIREFOX:
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.setAcceptInsecureCerts(true);
                driver = new FirefoxDriver(firefoxOptions);
                break;
            default:
                throw new RuntimeException("please check the browser passed, currently supporting chrome and firefox");
        }
        driver.manage().timeouts().implicitlyWait((ReaderManager.getInstance().getConfigFileReader().getTimeout()), TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(ReaderManager.getInstance().getConfigFileReader().getAppUrl());
        return driver;
    }

    public WebDriver createRemoteDriver(){
        DesiredCapabilities capabilities =new DesiredCapabilities();
        switch (enum_driverType){
            case CHROME:
                capabilities.setBrowserName(BrowserType.CHROME);
                capabilities.setJavascriptEnabled(true);
                capabilities.setPlatform(Platform.LINUX);
                capabilities.setAcceptInsecureCerts(true);
                break;
            case FIREFOX:
                capabilities.setBrowserName(BrowserType.FIREFOX);
                capabilities.setJavascriptEnabled(true);
                capabilities.setPlatform(Platform.LINUX);
                capabilities.setAcceptInsecureCerts(true);
                break;
            default:
                throw  new RuntimeException("given Remote Browser facility not implemented yet");
        }

        try {
            driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),capabilities);
            driver.manage().timeouts().implicitlyWait((ReaderManager.getInstance().getConfigFileReader().getTimeout()), TimeUnit.SECONDS);
            driver.manage().window().maximize();
            driver.get(ReaderManager.getInstance().getConfigFileReader().getAppUrl());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return driver;
    }

}
