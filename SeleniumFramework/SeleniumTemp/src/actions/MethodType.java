package actions;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import model.MethodParameters;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import util.MainTestNG;
import util.ReadElementLocators;
import util.WebDriverClass;

public class MethodType {

	List<WebElement> listOfElements = new ArrayList<WebElement>();
	WebElement element;
	ReadElementLocators read = new ReadElementLocators();

	String alertText = null;
	String titleOfPage = null;

	/**
	 * @param methodType
	 * @param objectLocators
	 * @param actionType
	 * @param data
	 * 
	 * Object locators
	 */
	public void methodExecutor(String methodType, String objectLocators,
			String actionType, String data) {
		MethodParameters mModel = new MethodParameters();
		mModel.setMethodType(methodType);
		mModel.setObjectLocators(objectLocators);
		mModel.setActionType(actionType);
		mModel.setData(data);

		MainTestNG.LOGGER
				.info("methodType= " + methodType + "objectLocators="
						+ objectLocators + "actionType=" + actionType
						+ "data= " + data);

		switch (methodType) {
		
		case "ID":
			findElementById(objectLocators);
			mModel.setElement(listOfElements);
			findMethod(methodType, objectLocators, actionType, data, mModel);
			break;
		case "NAME":
			findElementByName(objectLocators);
			mModel.setElement(listOfElements);
			findMethod(methodType, objectLocators, actionType, data, mModel);
			break;
		case "XPATH":
			findElementByXpath(objectLocators);
			mModel.setElement(listOfElements);
			findMethod(methodType, objectLocators, actionType, data, mModel);
			break;
		case "CSS":
			findElementByCssSelector(objectLocators);
			mModel.setElement(listOfElements);
			findMethod(methodType, objectLocators, actionType, data, mModel);
			break;
		default:
			if (actionType.contains(":")) {
				String[] actsplit = actionType.split(":");
				mModel.setActionType(actsplit[1]);
				actionType = actsplit[0];
				System.out.println(actsplit[1]);
				System.out.println(actsplit[0]);
			}
			findMethod(methodType, objectLocators, actionType, data, mModel);
			break;
		}

	}

	/**
	 *Identifying the method at run time
	 */
	public void findMethod(String methodType, String objectLocators,
			String actionType, String data, MethodParameters model) {
		Class cl = null;
		try {
			cl = (Class) Class.forName("actions.MethodType");
			actions.MethodType clName = (MethodType) cl.newInstance();
			Method[] methods = cl.getMethods();
			Method methodName = findMethods(actionType, methods);
			methodName.invoke(clName, (Object) model);

		} catch (InvocationTargetException e) {
			Throwable cause = e.getCause();

			MainTestNG.LOGGER
					.info("exception occured in finding methods, method name is incorrect"
							+ e);
		} catch (Exception e) {

			MainTestNG.LOGGER
					.info("exception occured in finding methods, method name is incorrect"
							+ e);
		}

	}

	/**
	 *Find Element By CSS
	 */
	private void findElementByCssSelector(String objectLocators) {

		WebDriverWait wait = new WebDriverWait(WebDriverClass.getDriver(), 30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By
				.cssSelector(objectLocators)));

		List<WebElement> list1 = WebDriverClass.getInstance().findElements(
				By.cssSelector(objectLocators));
		listOfElements = list1;

	}

	/**
	 *Find Element By ID
	 */
	public void findElementById(String objectLocators) {

		WebDriverWait wait = new WebDriverWait(WebDriverClass.getDriver(), 30);
		List<WebElement> list1 = wait.until(ExpectedConditions
				.presenceOfAllElementsLocatedBy(By.id(objectLocators)));

		listOfElements = list1;

	}

	/**
	 *Find Element By Xpath
	 */
	public void findElementByXpath(String objectLocators) {

		WebDriverWait wait = new WebDriverWait(WebDriverClass.getDriver(), 30);

		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By
				.xpath(objectLocators)));

		List<WebElement> list1 = wait

		.until(ExpectedConditions.visibilityOfAllElements(WebDriverClass
				.getDriver().findElements(By.xpath(objectLocators))));

		listOfElements = list1;

	}

	/**
	 *Find Element By Name
	 */
	public void findElementByName(String objectLocators) {
		MainTestNG.LOGGER.info("findElementByName==" + objectLocators);

		WebDriverWait wait = new WebDriverWait(WebDriverClass.getDriver(), 30);
		wait.until(ExpectedConditions.visibilityOfAllElements(WebDriverClass
				.getDriver().findElements(By.name(objectLocators))));
		MainTestNG.LOGGER.info("element found==" + objectLocators);

		List<WebElement> list1 = WebDriverClass.getInstance().findElements(
				By.name(objectLocators));
		MainTestNG.LOGGER.info("list size" + list1.size());
		listOfElements = list1;

	}

	public static Method findMethods(String methodName, Method[] methods) {

		for (int i = 0; i < methods.length; i++) {
			if (methodName.equalsIgnoreCase(methods[i].getName().toString())) {
				return methods[i];
			}
		}
		return null;
	}

	/**
	 *Click on button/checkbox/radio button
	 */
	public void click(MethodParameters model) {

		WebDriverWait wait = new WebDriverWait(WebDriverClass.getDriver(), 30);
		wait.until(
				ExpectedConditions.elementToBeClickable(model.getElement().get(
						0))).click();

		MainTestNG.LOGGER.info("click method started"
				+ model.getObjectLocators());
		MainTestNG.LOGGER.info("click method completed");
	}
	
	
	public void submit(MethodParameters model) {
		MainTestNG.LOGGER.info("submit method started"
				+ model.getObjectLocators());
		model.getElement().get(0).submit();
		MainTestNG.LOGGER.info("submit method end");
	}

	/**
	 *Enter data into text field/text area
	 */
	public void enterText(MethodParameters model) {

		MainTestNG.LOGGER
				.info(" inside enterText(), data to entered into the text=="
						+ model.getData());
		System.out.println("model.getElement().get(0)=="
				+ model.getElement().get(0));
		model.getElement().get(0).sendKeys(model.getData());
		MainTestNG.LOGGER.info("enterText() exit");
	}

	/**
	 *Read the value present in the text field
	 */
	public void readTextFieldValue(MethodParameters model) {
		MainTestNG.LOGGER.info("inside readTextFieldValue()"
				+ model.getObjectLocators());
		model.getElement().get(0).getAttribute("value");
		MainTestNG.LOGGER.info("end of readTextFieldValue");
	}

	/**
	 *Alert accept meaning click on OK button
	 */
	public void alertAccept(MethodParameters model) {

		WebDriverWait wait = new WebDriverWait(WebDriverClass.getDriver(), 30);
		wait.until(ExpectedConditions.alertIsPresent());

		MainTestNG.LOGGER.info("inside alertAccept()");

		wait1(2000);

		Alert alert = WebDriverClass.getInstance().switchTo().alert();
		wait1(2000);

		alert.accept();
		MainTestNG.LOGGER.info("completed alertAccept()");
	}

	/**
	 *Alert dismiss meaning click on Cancel button
	 */
	public void alertDismiss(MethodParameters model) {
		WebDriverWait wait = new WebDriverWait(WebDriverClass.getDriver(), 30);
		wait.until(ExpectedConditions.alertIsPresent());

		MainTestNG.LOGGER.info("inside alertDismiss()");
		wait1(2000);
		model.getElement().get(0).click();
		Alert alert = WebDriverClass.getInstance().switchTo().alert();
		wait1(2000);
		alert.dismiss();
	}

	/**
	 *Get the title of the page and verify the title
	 */
	public void verifyTitleOfPage(MethodParameters model) {
		MainTestNG.LOGGER.info("inside verifyTitleOfPage()" + "title=="
				+ WebDriverClass.getInstance().getTitle() + "data from excel="
				+ model.getData());

		wait1(2000);
		String actual = WebDriverClass.getInstance().getTitle().toString();
		String expected = model.getData().toString();
		Assert.assertEquals(actual, expected);
		MainTestNG.LOGGER
				.info("assert verification successful verifyTitleOfPage()");

	}

	public void wait1(long i) {
		try {
			Thread.sleep(i);
		} catch (InterruptedException e) {
			MainTestNG.LOGGER.severe("InvalidFormatException" + e);
		}
	}

	/**
	 *Select from the drop down list,if the drop down element tag is "SELECT" then use this method
	 */
	public void selectDropDownByVisibleText(MethodParameters model) {
		wait1(2000);
		MainTestNG.LOGGER.info("inside selectDropDownByVisibleText");

		WebDriverWait wait = new WebDriverWait(WebDriverClass.getDriver(), 30);
		wait.pollingEvery(2, TimeUnit.SECONDS).until(
				ExpectedConditions.elementToBeClickable(model.getElement().get(
						0)));
		Select sel = new Select(model.getElement().get(0));
		sel.selectByVisibleText(model.getData());
		wait1(2000);
	}

	public void selectDropDownByIndex(MethodParameters model) {
		MainTestNG.LOGGER.info("inside selectDropDownByIndex");
		Select sel = new Select(model.getElement().get(0));
		sel.selectByIndex(Integer.parseInt(model.getData()));
	}

	public void selectDropDownByValue(MethodParameters model) {
		MainTestNG.LOGGER.info("inside selectDropDownByValue");
		Select sel = new Select(model.getElement().get(0));
		sel.selectByValue(model.getData());
	}

	/**
	 *Switch To frame( html inside another html)
	 */
	public void switchToFrame(MethodParameters model) {
		MainTestNG.LOGGER.info("inside switchToFrame");
		WebDriverClass.getInstance().switchTo()
				.frame(model.getElement().get(0));

	}

	public void switchOutOfFrame(MethodParameters model) {
		MainTestNG.LOGGER.info("inside switchOutOfFrame");
		WebDriverClass.getInstance().switchTo().defaultContent();

	}

	public void selectFromListDropDown(MethodParameters model) {
		MainTestNG.LOGGER.info("inside selectFromListDropDown");
		wait1(2000);
		for (WebElement element1 : model.getElement()) {

			if (element1.getText().equals(model.getData())) {
				element1.click();
				break;
			}
		}

		wait1(2000);
	}

	public void moveToNextPage(MethodParameters model) {
		WebDriverClass.getInstance().navigate().forward();
	}

	public void moveToPreviousPage(MethodParameters model) {
		WebDriverClass.getInstance().navigate().back();
	}

	public void maximizeWindow(MethodParameters model) {
		WebDriverClass.getInstance().manage().window().maximize();
	}

	public void readText(MethodParameters model) {
		MainTestNG.LOGGER
				.info("getText() method called  and value of getText==*************"
						+ model.getElement().get(0).getText());
		model.getElement().get(0).getText();
		MainTestNG.LOGGER.info("readText completed");
	}

	public void quit(MethodParameters model) {
		WebDriverClass.getInstance().quit();
	}

	public void close(MethodParameters model) {
		WebDriverClass.getInstance().close();
	}

	public void isDisplayed(MethodParameters model) {
		model.getElement().get(0).isDisplayed();
	}

	public void isEnabled(MethodParameters model) {
		model.getElement().get(0).isEnabled();
	}

	public void selectRadioButton(MethodParameters model) {
		model.getElement().get(0).click();
	}

	public void refreshPage(MethodParameters model) {
		WebDriverClass.getInstance().navigate().refresh();
	}

	public void switchToParentWindow(MethodParameters model) {
		String parentWindow = WebDriverClass.getInstance().getWindowHandle();
		WebDriverClass.getInstance().switchTo().window(parentWindow);
	}

	public void switchToChildWindow(MethodParameters model) {

		model.getElement().get(0).click();

		String parent = WebDriverClass.getInstance().getWindowHandle();
		Set<String> windows = WebDriverClass.getInstance().getWindowHandles();

		try {
			if (windows.size() > 1) {
				for (String child : windows) {
					if (!child.equals(parent)) {

						if (WebDriverClass.getInstance().switchTo()
								.window(child).getTitle()
								.equals(model.getData())) {

							WebDriverClass.getInstance().switchTo()
									.window(child);
						}

					}
				}
			}
		} catch (Exception e) {

			throw new RuntimeException("Exception", e);
		}

	}

	// Tested on IE and is working fine.. Need to check why its not working on
	// Firefox
	public void scrollElementIntoView(MethodParameters model) {
		wait1(1000);

		MainTestNG.LOGGER.info("scrollElementIntoView started");
		((JavascriptExecutor) WebDriverClass.getDriver())
				.executeScript("arguments[0].scrollIntoView(true);", model
						.getElement().get(0));
		wait1(1000);

	}

	// Not test Yet on any browser.
	public void scrollElementIntoViewClick(MethodParameters model) {
		Actions action = new Actions(WebDriverClass.getDriver());
		action.moveToElement(model.getElement().get(0)).click().perform();
	}

	public void readUrlOfPage(MethodParameters model) {
		WebDriverClass.getInstance().getCurrentUrl();
	}

	public void navigateToURL(MethodParameters model) {
		WebDriverClass.getInstance().navigate().to(model.getData());
	}

	public static WebElement waitForElement(By by) {
		int count = 0;
		WebDriverWait wait = null;
		while (!(wait.until(ExpectedConditions.presenceOfElementLocated(by))
				.isDisplayed())) {
			wait = new WebDriverWait(WebDriverClass.getInstance(), 60);
			wait.pollingEvery(5, TimeUnit.MILLISECONDS);
			wait.until(ExpectedConditions.visibilityOfElementLocated(by))
					.isDisplayed();
			wait.until(ExpectedConditions.presenceOfElementLocated(by))
					.isDisplayed();
			count++;
			if (count == 100) {
				break;
			}
			return wait.until(ExpectedConditions.presenceOfElementLocated(by));
		}
		return wait.until(ExpectedConditions.presenceOfElementLocated(by));
	}

	public static void windowAuthenticationLoginName(MethodParameters model) {

		Alert alert = WebDriverClass.getDriver().switchTo().alert();
		alert.sendKeys(model.getData());
	}

	public static void windowAuthenticationPassword(MethodParameters model) {
		Robot robot;
		try {
			robot = new Robot();
			robot.keyPress(KeyEvent.VK_TAB);
			String letter = model.getData();
			for (int i = 0; i < letter.length(); i++) {
				boolean upperCase = Character.isUpperCase(letter.charAt(i));
				String KeyVal = Character.toString(letter.charAt(i));
				String variableName = "VK_" + KeyVal.toUpperCase();
				Class clazz = KeyEvent.class;
				Field field = clazz.getField(variableName);
				int keyCode = field.getInt(null);

				if (upperCase){
					robot.keyPress(KeyEvent.VK_SHIFT);
				}

				robot.keyPress(keyCode);
				robot.keyRelease(keyCode);

				if (upperCase){
					robot.keyRelease(KeyEvent.VK_SHIFT);
				}
			}
			robot.keyPress(KeyEvent.VK_ENTER);
		} catch (AWTException e) {

			MainTestNG.LOGGER.severe(e.getMessage());

		} catch (NoSuchFieldException e) {

			MainTestNG.LOGGER.severe(e.getMessage());
		} catch (SecurityException e) {

			MainTestNG.LOGGER.severe(e.getMessage());
		} catch (IllegalArgumentException e) {

			MainTestNG.LOGGER.severe(e.getMessage());
		} catch (IllegalAccessException e) {

			MainTestNG.LOGGER.severe(e.getMessage());
		}
	}

	/**
	 * @param model
	 * Lets say there is header menu bar, on hovering the mouse, drop down should be displayed
	 */
	public void dropDownByMouseHover(MethodParameters model) {
		Actions action = new Actions(WebDriverClass.getInstance());

		action.moveToElement(model.getElement().get(0)).perform();
		WebElement subElement = WebDriverClass.getInstance().findElement(
				By.xpath(model.getData()));
		action.moveToElement(subElement);
		action.click().build().perform();

	}

	public void verifyTextFieldData(MethodParameters model) {
		Assert.assertEquals(model.getElement().get(0).getAttribute("value"),
				model.getData());
	}

	
	/**
	 * @param model
	 * Read title of the page and verify it
	 */
	public void readTitleOfPage(MethodParameters model) {
		if (!(titleOfPage == null)) {
			titleOfPage = null;
		}
		titleOfPage = WebDriverClass.getInstance().getTitle();
	}

	
	/**
	 * @param model
	 * File upload in IE browser.
	 */
	public void fileUploadinIE(MethodParameters model) {
		model.getElement().get(0).click();
		StringSelection ss = new StringSelection(model.getData());
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
		Robot r;
		try {
			r = new Robot();

			r.keyPress(KeyEvent.VK_ENTER);

			r.keyRelease(KeyEvent.VK_ENTER);

			r.keyPress(KeyEvent.VK_CONTROL);
			r.keyPress(KeyEvent.VK_V);
			r.keyRelease(KeyEvent.VK_V);
			r.keyRelease(KeyEvent.VK_CONTROL);

			r.keyPress(KeyEvent.VK_ENTER);
			r.keyRelease(KeyEvent.VK_ENTER);

		} catch (AWTException e) {

			MainTestNG.LOGGER.severe(e.getMessage());
		}

	}

	
	/**
	 * @param model
	 * Verify the alert text
	 */
	public void verifyalertText(MethodParameters model) {
		model.getElement().get(0).click();
		wait1(1000);
		Alert alert = WebDriverClass.getInstance().switchTo().alert();
		wait1(1000);
		if (!(alertText == null)) {
			alertText = null;
		}
		alertText = alert.getText();
		Assert.assertEquals(alertText.toString(), model.getData());
		alert.accept();
	}


	/**
	 * @param model
	 * SSL errors that appear on IE browser can be resolved
	 */
	public void certificateErrorsIE(MethodParameters model) {

		WebDriverClass
				.getDriver()
				.navigate()
				.to("javascript:document.getElementById('overridelink').click()");
	}

	/**
	 * @param model
	 * Not tested
	 */
	public void DragAndDrop(MethodParameters model) {
		String[] actType = model.getActionType().split("$");

		WebElement sourceElement = WebDriverClass.getDriver().findElement(
				By.xpath(actType[0]));
		WebElement destinationElement = WebDriverClass.getDriver().findElement(
				By.xpath(actType[1]));

		Actions action = new Actions(WebDriverClass.getDriver());
		action.dragAndDrop(sourceElement, destinationElement).build().perform();

	}

	/**
	 * @param model
	 * clear the content of the text field
	 */
	public void clear(MethodParameters model) {
		wait1(2000);
		WebDriverWait wait = new WebDriverWait(WebDriverClass.getDriver(), 60);
		wait.until(ExpectedConditions.visibilityOf(model.getElement().get(0)));
		model.getElement().get(0).clear();
	}

	public void sleep(MethodParameters model) {
		try {
			Integer i = Integer.parseInt(model.getData());
			System.out.println(i);
			Thread.sleep(i);
			
		} catch (InterruptedException e) {

			MainTestNG.LOGGER.info("InterruptedException" + e.getMessage());
		}
	}

	public void verifyText(MethodParameters model) {
		MainTestNG.LOGGER.info("model.getElement().get(0).getText()**********"
				+ model.getElement().get(0).getText());
		MainTestNG.LOGGER.info("model.getData()**********" + model.getData());

		Assert.assertEquals(model.getData(), model.getElement().get(0)
				.getText().toString());
		MainTestNG.LOGGER.info("verify text completed");
	}

	public void verifyFileExists(MethodParameters model) {
		try {
			File file = new File(model.getData());
			if (file.exists() && !(file.isDirectory() && file.isFile())) {
				Assert.assertEquals(file.getAbsoluteFile(), model.getData());
			}
		} catch (Exception e) {
			throw e;
		}

	}

	public void downloadFileIE(MethodParameters model) {
		FileDownloader downloadTestFile = new FileDownloader(
				WebDriverClass.getDriver());
		String downloadedFileAbsoluteLocation;
		try {
			downloadedFileAbsoluteLocation = downloadTestFile
					.downloadFile(model.getElement().get(0));

			Assert.assertTrue(new File(downloadedFileAbsoluteLocation).exists());
		} catch (Exception e) {

			MainTestNG.LOGGER.info("exception occured");
		}

	}

	
	/**
	 * @param model
	 * Not tested
	 */
	public void webTableClick(MethodParameters model) {

		String[] actType = model.getActionType().split("\\$");

		WebElement mytable = WebDriverClass.getDriver().findElement(
				By.xpath(actType[0]));

		List<WebElement> rowstable = mytable.findElements(By.tagName("tr"));

		int rows_count = rowstable.size();

		for (int row = 0; row < rows_count; row++) {

			List<WebElement> Columnsrow = rowstable.get(row).findElements(
					By.tagName("td"));

			int columnscount = Columnsrow.size();

			for (int column = 0; column < columnscount; column++) {

				String celtext = Columnsrow.get(column).getText();
				celtext.getClass();
			}
		}
	}

	/**
	 * @param model
	 * Select date from date picker
	 */
	public void selectDateFromCalendar(MethodParameters model) {

		String[] actionType = model.getActionType().split("$$");

		String[] data = model.getData().split("/");

		List<String> monthList = Arrays.asList("January", "February", "March",
				"April", "May", "June", "July", "August", "September",
				"October", "November", "December");

		int expMonth;
		int expYear;
		String expDate = null;
		// Calendar Month and Year
		String calMonth = null;
		String calYear = null;
		boolean dateNotFound;

		// WebDriverClass.getDriver()
		// .findElement(By.xpath(".//*[@id='ui-datepicker-div']")).click();

		WebDriverClass.getDriver().findElement(By.xpath(actionType[0])).click();

		dateNotFound = true;

		// Set your expected date, month and year.
		expDate = data[0];
		expMonth = Integer.parseInt(data[1]);
		expYear = Integer.parseInt(data[2]);

		// This loop will be executed continuously till dateNotFound Is true.
		while (dateNotFound) {
			// Retrieve current selected month name from date picker popup.
			calMonth = WebDriverClass.getDriver()
					.findElement(By.className("ui-datepicker-month")).getText();

			// Retrieve current selected year name from date picker popup.
			calYear = WebDriverClass.getDriver()
					.findElement(By.className("ui-datepicker-year")).getText();

			/*
			 * If current selected month and year are same as expected month and
			 * year then go Inside this condition.
			 */
			if (monthList.indexOf(calMonth) + 1 == expMonth
					&& (expYear == Integer.parseInt(calYear))) {
				/*
				 * Call selectDate function with date to select and set
				 * dateNotFound flag to false.
				 */
				selectDate(expDate);
				dateNotFound = false;
			}
			// If current selected month and year are less than expected month
			// and year then go Inside this condition.
			else if (monthList.indexOf(calMonth) + 1 < expMonth
					&& (expYear == Integer.parseInt(calYear))
					|| expYear > Integer.parseInt(calYear)) {

				// Click on next button of date picker.
				/*
				 * WebDriverClass .getDriver() .findElement(
				 * By.xpath(".//*[@id='ui-datepicker-div']/div[2]/div/a/span"))
				 * .click();
				 */

				WebDriverClass.getDriver().findElement(By.xpath(actionType[1]))
						.click();
			}
			// If current selected month and year are greater than expected
			// month and year then go Inside this condition.
			else if (monthList.indexOf(calMonth) + 1 > expMonth
					&& (expYear == Integer.parseInt(calYear))
					|| expYear < Integer.parseInt(calYear)) {

				// Click on previous button of date picker.

				/*
				 * WebDriverClass .getDriver() .findElement(
				 * By.xpath(".//*[@id='ui-datepicker-div']/div[1]/div/a/span"))
				 * .click();
				 */

				WebDriverClass.getDriver().findElement(By.xpath(actionType[2]))
						.click();
			}
		}
		wait1(3000);
	}

	public void selectDate(String date) {
		WebElement datePicker = WebDriverClass.getDriver().findElement(
				By.id("ui-datepicker-div"));
		List<WebElement> noOfColumns = datePicker
				.findElements(By.tagName("td"));

		// Loop will rotate till expected date not found.
		for (WebElement cell : noOfColumns) {
			// Select the date from date picker when condition match.
			if (cell.getText().equals(date)) {
				cell.findElement(By.linkText(date)).click();
				break;
			}
		}

	}

	public void doubleClick(MethodParameters model) {
		Actions action = new Actions(WebDriverClass.getDriver());
		action.doubleClick((WebElement) model.getElement()).perform();

	}

	public void singleMouseHover(MethodParameters model) {
		Actions action = new Actions(WebDriverClass.getDriver());
		action.moveToElement((WebElement) model.getElement()).perform();

	}

	public void rightClick(MethodParameters model) {
		Actions action = new Actions(WebDriverClass.getDriver());
		action.contextClick((WebElement) model.getElement()).perform();

	}

	public void selectCheckBox(MethodParameters model) {
		boolean res = true;

		while (!model.getElement().get(0).isSelected()) {
			model.getElement().get(0).click();
			if (model.getElement().get(0).isSelected()) {
				res = false;
				break;
			}

		}

	}

	public void deselectCheckBox(MethodParameters model) {
		boolean res = true;

		while (model.getElement().get(0).isSelected()) {
			model.getElement().get(0).click();
			if (!model.getElement().get(0).isSelected()) {
				res = false;
				break;
			}

		}

	}

	public void deselectAllCheckbox(MethodParameters model) {
		List<WebElement> list = model.getElement();

		for (WebElement element : list) {
			if (element.isSelected()) {
				element.click();
			}
		}
	}

	public void selectAllCheckbox(MethodParameters model) {
		List<WebElement> list = model.getElement();

		for (WebElement element : list) {
			if (!element.isSelected()) {
				element.click();
			}
		}
	}

	public void verifyCheckBoxSelected(MethodParameters model) {

		Assert.assertTrue(model.getElement().get(0).isSelected());

	}

	public void verifyAllCheckBoxSelected(MethodParameters model) {
		for (WebElement element : model.getElement()) {
			Assert.assertTrue(element.isSelected(), "check box is selected");
		}

	}

	public void verifyAllCheckBoxNotSelected(MethodParameters model) {
		for (WebElement element : model.getElement()) {
			Assert.assertFalse(element.isSelected(), "check box not selected");
		}

	}
	
	/**
	 * @param model
	 * File download from Auto It
	 */
	public void filedownloadAUTOIT(MethodParameters model){
		try {
			Runtime.getRuntime().exec(model.getData());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}