package main.java.util;
/**
 * The MainTestNG class is used to execute the jar
 *
 * @author Shilpashree_V
 * @version 1.0
 * @since February 2015
 * 
 */
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import org.testng.TestListenerAdapter;
import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;



/**
 * @author Shilpashree_V
 * This is the class from where the execution gets started.
 */

//TestSuite
public class MainTestNG { 
	static Handler filehandler;
	static Formatter formatter = null;
	public static final Logger LOGGER = Logger.getLogger(MainTestNG.class
			.getName());
	ReadConfigProperty config = new ReadConfigProperty();
	static SwingTest swing;
	
	static String dir = "user.dir";
	
	public static void main(String[] args) {
		/**
		 * @author Shilpashree_V
		 * license certificate generator-contact KK/Raghavendra for cert file generation
		 */

	
		
			try {

				filehandler = new FileHandler("./log.txt");
			} catch (SecurityException e) {
				MainTestNG.LOGGER.info(e.getMessage());
			} catch (IOException e) {
				MainTestNG.LOGGER.info(e.getMessage());
			}

			LOGGER.addHandler(filehandler);
			formatter = new SimpleFormatter();
			filehandler.setFormatter(formatter);
			LOGGER.info("Logger Name: " + LOGGER.getName());

			ReadConfigProperty.configpath = System.getProperty(dir);

			/**
			 * @author Shilpashree_V
			 * SwingTest UI for properties file (Config.properties) 
			 */
			//swing = new SwingTest();
			MainTestNG test=new MainTestNG();
			
			/**
			 * @author Shilpashree_V
			 * testNG execution starts here
			 */
			test.testng();
		
	}


	/**
	 * @author Shilpashree_V
	 * adding listners, setting test-output folder
	 * Mentioning the TestSuite Name
	 */
	public void testng() {
		//RegressionSuite
		System.out.println("testng");
		TestListenerAdapter tla=new TestListenerAdapter();
		TestNG myTestNG = new TestNG();
		XmlSuite mySuite = new XmlSuite();
		mySuite.setName("Sample Suite");
		mySuite.addListener("org.uncommons.reportng.HTMLReporter");
		mySuite.addListener("org.uncommons.reportng.JUnitXMLReporter");
		
		mySuite.addListener("main.java.util.TestListener");
	
		myTestNG.setOutputDirectory("test-output");
		
		
		XmlTest myTest = new XmlTest(mySuite);
		myTest.setName("Sample Test");
		List<XmlClass> myClasses = new ArrayList<XmlClass>();
		myClasses.add(new XmlClass("main.java.util.ExecuteTestCases"));
		myTest.setXmlClasses(myClasses);
		List<XmlTest> myTests = new ArrayList<XmlTest>();
		myTests.add(myTest);
		mySuite.setTests(myTests);
		List<XmlSuite> mySuites = new ArrayList<XmlSuite>();
		
		mySuites.add(mySuite);
		myTestNG.setXmlSuites(mySuites);
		myTestNG.setUseDefaultListeners(true);
		/**
		 * @author Shilpashree_V
		 * testng run method
		 */
		myTestNG.run();
		
		/**
		 * @author Shilpashree_V
		 * Report generation--Generating XSLT report from ReportNG report
		 */
		Report.report();
	}

}
