# AutomationTesting
A Java based Cucumber-Selenium-TestNG automation framework with CustomRunner for feature/scenario/tag filter, Zalenium as scalable Grid(refer Zalenium documention at https://opensource.zalando.com/zalenium/), jaCoCo as for coverage report, Extent as HTML report

#To trigger a job from jenkins, create job with below Active Choice parameters(pre- requisite: please install Active choice plugin)

#Note : path given in script are ***ux/mac systems specific

#Create Maven Job configure jdk as 1.8, maven >3 auto install or configure your maven Home

#1 Active Choice parameter :Feature

#-groovy script: 

def featureList=[]
featureList.add("")
new File("/Users/ajay35.kumar/.jenkins/workspace/features.txt").eachLine{line->featureList.add(line);}
return featureList;

#-groovy fallback script: return['error']

#2 Active choice reactive parameter: Scenarios

#-groovy script: 

def ScenarioList=[]
if(Features!=""){
for(item in Features.split(",")){
new File("/Users/ajay35.kumar/.jenkins/workspace/scenarios/${item}.txt").eachLine{line->ScenarioList.add(line);}
  }
}

else{
def featureList=[]
new File("/Users/ajay35.kumar/.jenkins/workspace/features.txt").eachLine{line->featureList.add(line);}
ScenarioList.add("");
for(item in featureList){
new File("/Users/ajay35.kumar/.jenkins/workspace/scenarios/${item}.txt").eachLine{line->ScenarioList.add(line);}
 }
}
return ScenarioList;

#-referance variable: Feature


#-groovy fallback script: return['error']
                   
#3 Active Choice parameter: Tag

#-groovy script: return['','@Sanity','@Regression','@Runtime','Developed']

#-groovy fallback script: return['error']



#4 Active Choice parameter: browserName:

#-return['select browser','chrome','firefox']

#-groovy fallback script: return['error']


                           
#5 Active Choice parameter: environmentType:

#-return['select environment','local','remote']

#-groovy fallback script: return['error']
                            
#Naming Convention: Feature- Sample_Sample(same name to Scenarios file of a feature)

#Scenario- Scenario_Scenario

#Maven Goal: clean compile test -DFeatures=$Features -DScenarios=$Scenarios -DTag=$Tag -DenvironmentType=$environmentType -DbrowserName=$browserName

#HTML Report directory: test output/Spark/

#Index Page: ExtentSpark.html

#JaCoCo: default settings



