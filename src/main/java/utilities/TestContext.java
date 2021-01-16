package utilities;

import managers.PageObjectManager;
import managers.WDriverManager;

public class TestContext {

    private WDriverManager managerWebDriver;
    private PageObjectManager managerPageObject;
    private ScenarioContext scenarioContext;
    public TestContext(){
        managerWebDriver = new WDriverManager() ;
        managerPageObject = new PageObjectManager(managerWebDriver.getDriver());
        scenarioContext= new ScenarioContext();

    }

    public WDriverManager getWDriverManager() {
        return managerWebDriver;
    }
    public PageObjectManager getPageObjectManager() { return managerPageObject; }
    public ScenarioContext getScenarioContextInstance(){ return scenarioContext; }
}
