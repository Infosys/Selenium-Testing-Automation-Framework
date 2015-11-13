package main.java.util;
/**
 * The ReadElementLocators class is used to read the element locators
 *
 * @author Shilpashree_V
 * @version 1.0
 * @since February 2015
 * 
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import java.util.Properties;

public class ReadElementLocators {
	Properties properties = null;
	ReadConfigProperty config = new ReadConfigProperty();

	/*public String getElementLocatorsValue(String elementValue) {
		if (properties == null) {
			try {

				String propFileName = config
						.getConfigValues("ELEMENT_LOCATORS");
				File file = new File(propFileName);
				FileInputStream fileInput = new FileInputStream(file);
				properties = new Properties();
				properties.load(fileInput);

			} catch (IOException e) {
				
				MainTestNG.LOGGER.severe(e.getMessage());
			}
		}
		return properties.getProperty(elementValue);
	}*/

}
