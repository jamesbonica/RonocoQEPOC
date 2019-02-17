package com.pageObject;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class AbstractPage {

	public AbstractPage pause(double d) {
		try {
			Thread.sleep((long) (d * 1000));
		} catch (InterruptedException e) {
		}
		return this;
	}
	
	public AbstractPage waitForElement(WebElement element, WebDriver driver) {
		try {
		WebDriverWait wait = new WebDriverWait(driver, 6);
		wait.until(ExpectedConditions.elementToBeClickable(element));
			} catch(TimeoutException t) {
			System.out.println("element not found");
		}
		return this;
	}

}
