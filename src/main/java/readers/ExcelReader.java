package readers;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;

import java.io.File;
import java.util.Map;
import java.util.TreeMap;

public class ExcelReader {

    private Fillo fillo = new Fillo();
    public Map<String,String> getTestDataInMap(String featureNameAsSheetName, String scenarioNameAsTestCaseName){
        Map<String,String> testDataInMap= new TreeMap<>();
        try{
            Connection connection= fillo.getConnection(System.getProperty("user.dir")+ File.separator+"src"+File.separator+"test"+File.separator+"resources"+File.separator+"testDataFile.xlsx");
            Recordset recordset= connection.executeQuery(String.format("SELECT * FROM %s WHERE Run='Y' AND TestCaseName='%s'",featureNameAsSheetName.toUpperCase(),scenarioNameAsTestCaseName.toUpperCase()));
            while(recordset.next()){
                for(String field:recordset.getFieldNames()){
                    testDataInMap.put(field,recordset.getField(field));
                }
            }
            recordset.close();
            connection.close();
        }
        catch (FilloException e) {
            e.printStackTrace();
        }
        return testDataInMap;
    }
}
