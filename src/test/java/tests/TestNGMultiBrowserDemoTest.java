package tests;

import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;

import com.pageObject.GoogleHomePage;
import com.pageObject.GoogleSearchResultsPage;

public class TestNGMultiBrowserDemoTest {

	WebDriver driver;

	String newUrl = "http://localhost:4444/wd/hub";

	GoogleHomePage googleHomePage;

	GoogleSearchResultsPage googleSearchResultsPage;

	@Parameters("browserName")
	@BeforeTest
	public void setUp(String browserName) throws MalformedURLException {
		System.out.println("SetUp " + browserName);
		System.out.println("Thread id is " + Thread.currentThread().getId());
		if (browserName.equalsIgnoreCase("chrome")) {
			ChromeOptions chromeOptions = new ChromeOptions();
			DesiredCapabilities caps = new DesiredCapabilities();
			chromeOptions.merge(caps);
			driver = new RemoteWebDriver(new URL(newUrl), chromeOptions);
		} else if (browserName.equalsIgnoreCase("firefox")) {
			FirefoxOptions firefoxOptions = new FirefoxOptions();
			DesiredCapabilities caps = new DesiredCapabilities();
			firefoxOptions.merge(caps);
			driver = new RemoteWebDriver(new URL(newUrl), firefoxOptions);
		} else if (browserName.equalsIgnoreCase("edge")) {
			EdgeOptions edgeOptions = new EdgeOptions();
			DesiredCapabilities caps = new DesiredCapabilities();
			edgeOptions.merge(caps);
			driver = new RemoteWebDriver(new URL(newUrl), edgeOptions);
		}

	}

	@AfterTest
	public void tearDown() {
		System.out.println("TearDown");
		if (driver != null) {
			driver.quit();
		}
	}

	@Test
	public void enterSearchTermInGoogle() {
		System.out.println("Test");

		driver.get("https://google.com");
		googleHomePage = new GoogleHomePage(driver);
		String topSearchResult = googleHomePage.enterSearchTerm("The Mighty Thor").pressReturnOnKeyBoard().getTopSearchResult();
		// Assertion = First Result in Search Contains "Thor"
		assertThat(topSearchResult, containsString("Thor"));
	}
	
	@Test
	public void enterBlankSearchTermInGoogle() {
		driver.get("https://google.com");
		googleHomePage = new GoogleHomePage(driver);
		googleHomePage.enterSearchTerm("").pressReturnOnKeyBoard();
		// Confirm user is still on HomePage by confirming the main logo remains present
		assertThat(googleHomePage.mainGoogleLogoPresent(), is(true));
	}

}
