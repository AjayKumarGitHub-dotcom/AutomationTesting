package runners;

import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;
import runners.customrunnerhelper.CustomAbstractTestNGCucumberFilterFeatureScenarioTagsTests;

import java.util.Iterator;


@CucumberOptions(

        features="src/test/resources/features",
        glue= {"stepDefinition"},
        plugin = {  "pretty",
                    "html:test-output",
                    "json:target/cucumber-json-report.json",
//                    "junit:junit_xml/cucumber.xml",
                    "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
        }
)

public class CustomTestRunner extends CustomAbstractTestNGCucumberFilterFeatureScenarioTagsTests {

        @Override
        @DataProvider(parallel = true)
        public Iterator<Object[]> scenarios() {
                return super.scenarios();
        }
}
