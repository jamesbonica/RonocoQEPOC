package tests;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
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

import com.pageObject.GoogleHomePage;

public class TestNGMultiBrowserDemoTest {

	static WebDriver driver;

	String newUrl = "http://localhost:4444/wd/hub";

	GoogleHomePage googleHomePage;

	@Parameters("browserName")
	@BeforeTest
	public void setUp(String browserName) throws MalformedURLException {
		System.out.println("SetUp " + browserName);
		System.out.println("Thread id is " + Thread.currentThread().getId());
		if (browserName.equalsIgnoreCase("chrome")) {
			// WebDriverManager.chromedriver().setup();
			// chromeOptions
			ChromeOptions chromeOptions = new ChromeOptions();
			DesiredCapabilities caps = new DesiredCapabilities();
			chromeOptions.merge(caps);
			driver = new RemoteWebDriver(new URL(newUrl), chromeOptions);
			// driver = new ChromeDriver(chromeOptions);
		} else if (browserName.equalsIgnoreCase("firefox")) {
			// WebDriverManager.firefoxdriver().setup();
			// System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE,
			// "true");
			// System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE,
			// "/dev/null");
			FirefoxOptions firefoxOptions = new FirefoxOptions();
			DesiredCapabilities caps = new DesiredCapabilities();
			// driver = new FirefoxDriver(firefoxOptions);
			firefoxOptions.merge(caps);
			driver = new RemoteWebDriver(new URL(newUrl), firefoxOptions);
		} else if (browserName.equalsIgnoreCase("edge")) {
			// WebDriverManager.edgedriver().setup();
			// driver = new EdgeDriver();
			EdgeOptions edgeOptions = new EdgeOptions();
			DesiredCapabilities caps = new DesiredCapabilities();
			edgeOptions.merge(caps);
			driver = new RemoteWebDriver(new URL(newUrl), edgeOptions);
		}

	}

	@AfterTest
	public void tearDown() {
		System.out.println("TearDown");
		driver.quit();
	}

	@Test
	public void test1() throws InterruptedException {
		System.out.println("Test");
		driver.get("https://google.com");
		Thread.sleep(4000);
	}

	public static void getScreenShot(String fileName) {
		DateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy h-m-s");
		Date date = new Date();
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(scrFile,
					new File("\\C:\\" + fileName + "-" + dateFormat.format(date) + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String getMethodName() {
		return new Object() {
		}.getClass().getEnclosingMethod().getName();
	}
}
