package runners.customrunnerhelper;

import io.cucumber.testng.FeatureWrapper;
import io.cucumber.testng.PickleWrapper;
import io.cucumber.testng.TestNGCucumberRunner;
import managers.ReaderManager;
import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class CustomAbstractTestNGCucumberFilterFeatureScenarioTagsTests {

    private TestNGCucumberRunner testNGCucumberRunner;
    private String desiredFeatures;
    private String desiredScenarios;
    private String desiredTag;
    private final String inDevTag="@UnderDevelopment";
    public CustomAbstractTestNGCucumberFilterFeatureScenarioTagsTests() {
        desiredFeatures=System.getProperty("Features","");
        desiredScenarios=System.getProperty("Scenarios","");
        desiredTag=System.getProperty("Tag","");
    }

    @BeforeClass(alwaysRun = true)
    public void setUpClass() {
        this.testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
    }

    @Test(groups = {"cucumber"}, description = "Runs Cucumber Scenarios", dataProvider = "scenarios")
    public void runScenario(PickleWrapper pickleWrapper, FeatureWrapper featureWrapper) {
        this.testNGCucumberRunner.runScenario(pickleWrapper.getPickle());
    }

    @DataProvider
    public Iterator<Object[]> scenarios() {
        ArrayList<Object[]> FilteredListWithDesiredFeatures = new ArrayList<>();
         if(this.testNGCucumberRunner == null) {
             return FilteredListWithDesiredFeatures.iterator();
         }
            FilteredListWithDesiredFeatures=filterTheFeature(this.testNGCucumberRunner.provideScenarios());
            return FilteredListWithDesiredFeatures.iterator();
    }

    @AfterClass(alwaysRun = true)
    public void tearDownClass() {
        if (this.testNGCucumberRunner != null) {
            this.testNGCucumberRunner.finish();
        }
    }

    /**==============================================================================================================================
     * filtering logic below
     * @param data
     * @return ArrayListog "<Object[]>" type
     *
     */

    private ArrayList<Object[]> filterTheFeature(Object[][] data) {
        if(desiredFeatures.isEmpty()){
            if(desiredScenarios.isEmpty()){
                if(desiredTag.isEmpty()){
                    ArrayList<Object[]> list1=getAllFeatures(data);
                    return excludeInDevTagsScenarios(list1);
                }
                else{
                    ArrayList<Object[]> list2=filterScenariosByTags(data);
                    return excludeInDevTagsScenarios(list2);
                }
            }
            else{
                ArrayList<Object[]> list3=filterTheScenarios(data);
                return excludeInDevTagsScenarios(list3);
            }
        }
        List<String>desiredFeaturesList= Arrays.asList(desiredFeatures.split(","));
        ArrayList<Object[]> filteredListWithDesiredFeatures= new ArrayList<>();

        if(data!=null){
            for (Object[] datum : data) {
                FeatureWrapper featureWrapper = (FeatureWrapper) datum[1];
                String fullFeatureName = featureWrapper.toString();
                String actualFeatureName = fullFeatureName.substring(fullFeatureName.indexOf('[') + 1, fullFeatureName.indexOf(']'));
                if (desiredFeaturesList.contains(actualFeatureName)) {
                    filteredListWithDesiredFeatures.add(datum);
                }
            }
        }
        ArrayList<Object[]> list4=filterScenariosAfterFeatures(filteredListWithDesiredFeatures);
        return excludeInDevTagsScenarios(list4);
    }



    //if user wants filter on scenarios only
    private ArrayList<Object[]> filterTheScenarios(Object[][] data) {
        List<String> desiredScenariosList= Arrays.asList(desiredScenarios.split(","));
        ArrayList<Object[]> filteredListWithDesiredScenarios= new ArrayList<>();

        if (data!=null){
            for (Object[] datnum:data){
                PickleWrapper scenario= (PickleWrapper)datnum[0];
                String desiredScenarioName= scenario.getPickle().getName();
                if(desiredScenariosList.contains(desiredScenarioName)){
                    filteredListWithDesiredScenarios.add(datnum);
                }
            }
        }
        return filteredListWithDesiredScenarios;

    }
    // if feature not empty and user wants to filter the scenarios as well from filtered features
    private ArrayList<Object[]> filterScenariosAfterFeatures(ArrayList<Object[]> filteredListWithDesiredFeatures) {

            if(desiredScenarios.isEmpty()){
                return filteredListWithDesiredFeatures;
            }
            List<String> desiredScenariosList= Arrays.asList(desiredScenarios.split(","));
            ArrayList<Object[]> filteredlistwithDesiredFeaturesAndScenarios= new ArrayList<>();
            for (Object[] data:filteredListWithDesiredFeatures){
                PickleWrapper scenario= (PickleWrapper)data[0];
                String desiredScenarioName= scenario.getPickle().getName();
                if(desiredScenariosList.contains(desiredScenarioName)){
                    filteredlistwithDesiredFeaturesAndScenarios.add(data);
                }
            }
            return filteredlistwithDesiredFeaturesAndScenarios;

    }

    private ArrayList<Object[]> getAllFeatures(Object[][] data) {
         ArrayList<Object[]> allFeatures= new ArrayList<>();
         if(data!=null){
             allFeatures.addAll(Arrays.asList(data));
         }
         return allFeatures;
    }

    private ArrayList<Object[]> excludeInDevTagsScenarios(ArrayList<Object[]> allFeatures) {
        ArrayList<Object[]> excludeIndevTagsList= new ArrayList<>();
        for (Object[] data:allFeatures){
            PickleWrapper pickleWrapper = (PickleWrapper)data[0];
            if((pickleWrapper.getPickle().getTags().contains(inDevTag))){
                continue;
            }
            excludeIndevTagsList.add(data);
        }
        return excludeIndevTagsList;
    }

    private ArrayList<Object[]> filterScenariosByTags(Object[][] data) {
        ArrayList<Object[]> filteredScenariosByTags=new ArrayList<>();
        for (Object[] datum : data) {
            PickleWrapper pickleWrapper = (PickleWrapper)datum[0];
            if (pickleWrapper.getPickle().getTags().contains(desiredTag)) {
                filteredScenariosByTags.add(datum);
            }

        }
        return filteredScenariosByTags;

    }


}
