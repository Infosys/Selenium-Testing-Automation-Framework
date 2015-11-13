package main.java.model;
/**
 * The TestCase class is used as a bean class to get the details of each test case
 *
 * @author Shilpashree_V
 * @version 1.0
 * @since February 2015
 * 
 */
import java.util.ArrayList;
import java.util.List;

public class TestCase {
	String testCaseName = null;
	List<String> testStepId = new ArrayList<String>();
	List<String> testSteps = new ArrayList<String>();
	List<String> methodType = new ArrayList<String>();
	List<String> ojectNameFromPropertiesFile = new ArrayList<String>();
	List<String> actionType = new ArrayList<String>();
	String onFail = null;
	List testData = new ArrayList();

	public String getTestCaseName() {
		return testCaseName;
	}

	public void setTestCaseName(String testCaseName) {
		this.testCaseName = testCaseName;
	}

	public void addTestSteps(String steps) {
		testSteps.add(steps);
	}

	public List<String> getTestSteps() {
		return testSteps;
	}

	public void setMethodType(String methodype) {
		methodType.add(methodype);
	}

	public List<String> getMethodType() {
		return methodType;
	}

	public void setObjectNameFromPropertiesFile(String name) {
		ojectNameFromPropertiesFile.add(name);
	}

	public List<String> getObjectNameFromPropertiesFile() {
		return ojectNameFromPropertiesFile;
	}

	public void setActionType(String actiontype) {
		actionType.add(actiontype);
	}

	public List<String> getActionType() {
		return actionType;
	}

	public void setTestData(String testdata) {
		testData.add(testdata);
	}

	public List<String> getTestData() {
		return testData;
	}

	public List<String> getTestStepId() {
		return testStepId;
	}

	public void setTestStepId(String tststepId) {
		testStepId.add(tststepId);
	}

	public String getOnFail() {
		return onFail;
	}

	public void setOnFail(String onFail) {
		this.onFail = onFail;
	}

}
