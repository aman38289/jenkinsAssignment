package Configuration;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
	
	 private static Properties configproperties;
	 private static Properties testDataproperties;

	    static {
	        try {
	            FileInputStream fileInputStream = new FileInputStream("src/test/resources/config.properties");
	            FileInputStream testDatafileInputStream = new FileInputStream("src/test/resources/testData.properties");
	            
	            configproperties = new Properties();
	            configproperties.load(fileInputStream);
	            
	            testDataproperties = new Properties();
	            testDataproperties.load(testDatafileInputStream);
	            
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }

	    public static String getProperty(String key) {
	        return configproperties.getProperty(key);
	    }
	    
	    public static String getPropertydata(String key) {
	    	return testDataproperties.getProperty(key);
	    }
	    
	    
	
	

}
