package stepDefinition;

import enums.Context;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import managers.ReaderManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import utilities.TestContext;
import java.io.File;
import java.util.List;

public class Hooks {
    private WebDriver driver;
    private TestContext context;
    public Hooks(TestContext context){
        this.context=context;
        driver=context.getWDriverManager().getDriver();
    }

    @Before
    public void beforeScenario(Scenario scenario){
       List<String> tags= (List<String>) scenario.getSourceTagNames();
       if(!tags.contains("@NoData")) {
           String scenarioId = scenario.getId();
           String testCaseName = scenario.getName();
           int start = scenarioId.indexOf(File.separator + "features" + File.separator);
           int end = scenarioId.lastIndexOf(".");
           String sheetName = scenarioId.substring(start, end).split(File.separator + "features" + File.separator)[1];
           context.getScenarioContextInstance().setContext(Context.TESTDATA, ReaderManager.getInstance().getExcelReader().getTestDataInMap(sheetName,testCaseName));
       }
    }

    @After
    public void afterScenario(Scenario scenario){
        if (scenario.isFailed()) {
            byte[] screenShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenShot, "image/png", scenario.getName());
        }
        driver.close();

    }
}
