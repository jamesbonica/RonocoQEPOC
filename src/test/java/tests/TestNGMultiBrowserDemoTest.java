package tests;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.io.FileUtils;
import org.apache.maven.surefire.shade.java5.org.apache.maven.shared.utils.StringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.pageObject.GoogleHomePage;
import com.pageObject.GoogleSearchResultsPage;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;

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

		driver.manage().window().maximize();

	}

	@AfterTest(alwaysRun = true)
	public void tearDown() {
		System.out.println("TearDown");
		if (driver != null) {
			driver.quit();
		}
	}

	@Parameters("browserName")
	@Test(description = "A user enters a search term and clicks the Google Search button for results")
	public void enterSearchTermInGoogle(String browserName) throws IOException {
		driver.get("https://google.com");

		googleHomePage = new GoogleHomePage(driver);
		String topSearchResult = googleHomePage.enterSearchTerm("The Mighty Thor").pressReturnOnKeyBoard()
				.getTopSearchResult();

		// Assertion = First Result in Search Contains "Thor"
		assertThat(topSearchResult, containsString("Thor"));
		takeScreenshot(browserName);
	}

	@Parameters("browserName")
	@Test(description = "A user attempts to click Google Search Button without entering a search term")
	public void enterBlankSearchTermInGoogle(String browserName) {
		driver.get("https://google.com");
		googleHomePage = new GoogleHomePage(driver);
		googleHomePage.enterSearchTerm("").pressReturnOnKeyBoard();
		// Confirm user is still on HomePage by confirming the main logo remains present
		assertThat(googleHomePage.mainGoogleLogoPresent(), is(true));
		takeScreenshot(browserName);
	}

	public void takeScreenshot(String browserType) {
		String timeStamp;
		File screenShotName;
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		// The below method will save the screen shot in d drive with name
		// "screenshot.png"
		timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmssSSSS").format(Calendar.getInstance().getTime());
		screenShotName = new File(
				System.getProperty("user.dir") + "/target/screenshots/" + browserType + "-" + timeStamp + ".png");
		try {
			FileUtils.copyFile(scrFile, screenShotName);
		} catch (IOException e) {
			e.printStackTrace();
		}

		String path = StringUtils.replace(screenShotName.toString(), "\\", "/");
		Reporter.log("<a href=\"file:///" + path + "\" target = \"_blank\"><img width = \"500px\" src = \"file:///"
				+ path + "\"/></a>");
	}

}
