package readers;

import enums.DriverType;
import enums.EnvironmentType;
import java.io.*;
import java.util.Properties;

public class ConfigFileReader {
    Properties properties=new Properties();
    public ConfigFileReader(){
        try(BufferedReader bufferedReader= new BufferedReader(
                new FileReader(System.getProperty("user.dir")+
                        File.separator+"src"+ File.separator+"main"+File.separator+"resources"+File.separator+"config.properties")))
        {
            properties.load(bufferedReader);
            properties.setProperty("browserName",System.getProperty("browserName","chrome"));
            properties.setProperty("environmentType",System.getProperty("environmentType","local"));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public DriverType getDriverType(){
        if(properties.getProperty("browserName").equalsIgnoreCase("chrome")){
            return DriverType.CHROME;
        }
        else if(properties.getProperty("browserName").equalsIgnoreCase("firefox")){
            return DriverType.FIREFOX;
        }
        else
            throw new RuntimeException("browser type not implemented");
    }

    public EnvironmentType getEnvironmentType(){
        if(properties.getProperty("environmentType").equalsIgnoreCase("local")){
            return EnvironmentType.LOCAL;
        }
        else if(properties.getProperty("environmentType").equalsIgnoreCase("remote")){
            return EnvironmentType.REMOTE;
        }

        else
            throw new RuntimeException("specified environment not implemented");
    }
    public String getAppUrl(){
        return properties.getProperty("appUrl");
    }
    public long getTimeout(){
        return Long.parseLong(properties.getProperty("timeOut"));
    }
}