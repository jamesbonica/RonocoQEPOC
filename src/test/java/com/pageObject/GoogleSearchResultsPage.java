/**
 * 
 */
package com.pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * @author Jim Bonica
 *
 *         Feb 15, 2019
 */
public class GoogleSearchResultsPage extends AbstractPage {

	@FindBy(css = "h3.LC20lb")
	private WebElement topResultHeadline;	
	

	private WebDriver driver;

	public GoogleSearchResultsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public String getTopSearchResult() {
		waitForElement(topResultHeadline, driver);
		return topResultHeadline.getText();
	}

}
