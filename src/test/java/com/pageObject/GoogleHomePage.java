/**
 * 
 */
package com.pageObject;

import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * @author Jim Bonica
 *
 *         Feb 15, 2019
 */
public class GoogleHomePage extends AbstractPage {

	@FindBy(css = "input[aria-label = 'Search']")
	private WebElement searchField;
	
	@FindBy(css = "input[value = 'btnK']")
	private WebElement googleSearchButton;
	
	@FindBy(css="img[id='hplogo']")
	private WebElement googleLogo;
	
	@FindAll(@FindBy(css="img[id='hplogo']"))
	private List<WebElement> googleLogoList;

	private WebDriver driver;

	public GoogleHomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public GoogleHomePage enterSearchTerm(String searchTerm) {
		searchField.clear();
		searchField.sendKeys(searchTerm);
		return this;
	}
	
	public GoogleHomePage clickGoogleSearchButton() {
		searchField.sendKeys(Keys.RETURN);
		googleSearchButton.click();
		return this;
	}
	
	public GoogleSearchResultsPage pressReturnOnKeyBoard() {
		searchField.sendKeys(Keys.RETURN);
		return new GoogleSearchResultsPage(driver);
	}
	
	public boolean mainGoogleLogoPresent() {
		return googleLogoList.size() > 0;
	}

}
