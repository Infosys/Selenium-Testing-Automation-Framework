package main.java.util;
/**
 * The WebDriverClass class is used to generate a single instance of driver to
 * launch the browser
 *
 * @author Shilpashree_V
 * @version 1.0
 * @since February 2015
 * 
 */
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;

public class WebDriverClass {
	static WebDriver driver;

	private WebDriverClass() {
		WebDriverClass.getDriver();
	}

	public static WebDriver getDriver() {
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		
		return driver;
	}

	public static void setDriver(WebDriver driver) {
		WebDriverClass.driver = driver;
	}

	/**
	 * @return
	 * Getting the instance of the driver
	 */
	public static WebDriver getInstance() {
		if (driver == null) {

			driver = (WebDriver) new WebDriverClass();

			return driver;
		} else {

			return driver;
		}

	}

}
