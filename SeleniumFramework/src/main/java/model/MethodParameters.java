package main.java.model;
/**
 * The MethodParameters class is used as a bean class to get the details for actions
 * performed on the element
 *
 * @author Shilpashree_V
 * @version 1.0
 * @since February 2015
 * 
 */
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;

public class MethodParameters {

	List<WebElement> elements = new ArrayList<WebElement>();

	String methodType = null;
	String actionType = null;
	String data = null;
	String objectLocators = null;

	public List<WebElement> getElement() {
		return elements;
	}

	public void setElement(List<WebElement> element) {
		this.elements = element;
	}

	public String getMethodType() {
		return methodType;
	}

	public void setMethodType(String methodType) {
		this.methodType = methodType;
	}

	public String getActionType() {
		return actionType;
	}

	public void setActionType(String actionType) {
		this.actionType = actionType;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getObjectLocators() {
		return objectLocators;
	}

	public void setObjectLocators(String objectLocators) {
		this.objectLocators = objectLocators;
	}

}
