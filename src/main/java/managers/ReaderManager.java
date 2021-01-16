package managers;

import readers.ConfigFileReader;
import readers.ExcelReader;

public class ReaderManager {
    private static ReaderManager readerManager=new ReaderManager();
    private ConfigFileReader configFileReader;
    private ExcelReader excelReader;
    private ReaderManager(){}

    public static ReaderManager getInstance() {
        return readerManager;
    }

    public ConfigFileReader getConfigFileReader() {
        return (configFileReader==null)?configFileReader=new ConfigFileReader():configFileReader;
    }

    public ExcelReader getExcelReader() {
        return (excelReader==null)?excelReader=new ExcelReader():excelReader;
    }
}
