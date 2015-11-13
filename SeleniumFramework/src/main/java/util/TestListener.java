package main.java.util;
/**
 * The TestListener class is used to help in generating html Report
 *
 * @author Shilpashree_V
 * @version 1.0
 * @since February 2015
 * 
 */
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import main.java.util.WebDriverClass;

/**
 * @author Shilpashree_V
 *
 */
public class TestListener implements ITestListener {
	WebDriver driver = null;

	/* (non-Javadoc)
	 * @see org.testng.ITestListener#onTestFailure(org.testng.ITestResult)
	 * capture the screenshot of a page on failure
	 */
	@Override
	public void onTestFailure(ITestResult result) {
		
		printTestResults(result);
		String methodName = result.getName().toString().trim();
		takeScreenShot(methodName);

	}

	
	/**
	 * @param methodName
	 * Capturing the screenshot of a page
	 */
	public void takeScreenShot(String methodName) {

		driver = WebDriverClass.getDriver();
		File scrFile = ((TakesScreenshot) driver)
				.getScreenshotAs(OutputType.FILE);

		try {
			DateFormat dateFormat = new SimpleDateFormat(
					"dd_MMM_yyyy__hh_mm_ssaa");
			Date date = new Date();
			
			FileUtils.copyFile(scrFile, new File(ReadConfigProperty.configpath
					+ "\\SCREENSHOT\\OnFailure" + "\\" + methodName + "__"
					+ dateFormat.format(date) + ".png"));

		} catch (IOException e) {
			MainTestNG.LOGGER.severe("Io Exception occured");
		}
	}

	@Override
	public void onFinish(ITestContext arg0) {
		Reporter.log("About to end executing Test " + arg0.getName(), true);

	}

	@Override
	public void onStart(ITestContext arg0) {
		Reporter.log("About to begin executing Test " + arg0.getName(), true);

	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		Reporter.log("About to end executing Test " + arg0.getName(), true);
	}

	@Override
	public void onTestSkipped(ITestResult arg0) {
		Reporter.log("About to end executing Test " + arg0.getName(), true);
	}

	@Override
	public void onTestStart(ITestResult arg0) {
		Reporter.log("About to end executing Test " + arg0.getName(), true);
	}

	@Override
	public void onTestSuccess(ITestResult arg0) {
		printTestResults(arg0);

	}

	private void printTestResults(ITestResult result) {

		if (result.getParameters().length != 0) {

			String params = null;

			for (Object parameter : result.getParameters()) {

				params += parameter.toString() + ",";

			}

		}

		String status = null;

		switch (result.getStatus()) {

		case ITestResult.SUCCESS:

			status = "Pass";

			break;

		case ITestResult.FAILURE:

			status = "Failed";

			break;

		case ITestResult.SKIP:

			status = "Skipped";

		}

		Reporter.log("Test Status: " + status, true);

	}

}