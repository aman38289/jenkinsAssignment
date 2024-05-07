package TestCases;

import org.testng.annotations.Test;
import org.testng.Assert;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import Configuration.ConfigReader;
import PageObjectModel.AmazonFreshPage;
import PageObjectModel.AmazonSearchPage;
import PageObjectModel.HomePage;
import PageObjectModel.LoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import com.aventstack.extentreports.MediaEntityBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TestMethods {

	private static final Logger logger = LogManager.getLogger(TestMethods.class);

	private ExtentReports extent;
	private ExtentTest test;
	WebDriver driver= null;

	LoginPage loginPage;
	ConfigReader configReader;
	AmazonSearchPage amazonSearchPage;
	HomePage homePage;

	@Parameters("browserName")
	@BeforeClass
	public void beforeTest(String browserName) {
		
		System.out.println("Browser Name :-" + browserName);

		// Set up ExtentReports
		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("extent.html");
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		
		if (browserName.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if (browserName.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        } else if (browserName.equalsIgnoreCase("ie")) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        } else {
            throw new IllegalArgumentException("Invalid browser name provided: " + browserName);
        }
		
		logger.info("Chrome browser launched successfully");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Integer.valueOf(configReader.getProperty("global_wait_time")),
				TimeUnit.SECONDS);
		
		
	
	}

	// Method to capture screenshot
	private String captureScreenshot(String methodName) {
		try {
			File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			String screenshotPath = "./screenshots/" + methodName + ".png";
			FileUtils.copyFile(screenshotFile, new File(screenshotPath));
			System.out.println("Screenshot saved at: " + screenshotPath);
			return screenshotPath;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}
	
	
	// Open google test case
	@Test(priority = 0)
	public void openGoogle() {
		
		test = extent.createTest("Google Opening Operation");
		
		try {
			this.driver.get(configReader.getProperty("googleUrl"));
			logger.info("Google website opened");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("error"+e.getMessage());
		}
		
		//
		test.log(Status.INFO, "Google Opened successful");
		
	}
	
	// Search Amazon website test case
	@Test(priority = 1)
	public void searchOperation() {

		test = extent.createTest("searchOperation");
		
		AmazonSearchPage page = new AmazonSearchPage(driver);
		driver.get("https://www.amazon.in");

		test.log(Status.INFO, "Amazon site searched");
		logger.info("Amazon site searched");
	}

	// Verify Homepage Title
	@Test(priority = 2)
	public void verifyHomepage() {

		test = extent.createTest("Amazonsite Homepage Verification");
		
		AmazonSearchPage page = new AmazonSearchPage(driver);
		driver.get("https://www.amazon.in");

		String actualTitle = driver.getTitle();
		String expectedTitle = "Online Shopping site in India: Shop Online for Mobiles, Books, Watches, Shoes and More - Amazon.in";

		Assert.assertEquals(actualTitle, expectedTitle, "Homepage title verification failed");

		test.log(Status.INFO, "Amazon site clicked");
		logger.info("Amazon site clicked");


	}

	//signing test case of Amazon 
	@Test(priority = 3,enabled = false)
	public void signInClick() {

		test = extent.createTest("signInClick");

		loginPage = new LoginPage(driver);
		loginPage.clickSingIn();

		test.log(Status.INFO, "Clicked Sign In link");
		logger.info("Clicked Sign In link");

	}

	
	// verifying homepage after successful signin.
	@Test(priority = 4,enabled = false)
	public void signIn() {

		test = extent.createTest("signIn");

		loginPage = new LoginPage(driver);
		loginPage.verifyAndLogin();

		// Initialize homePage after successful login
		homePage = new HomePage(driver);

		// Assertion to verify landing on the homepage after login
		Assert.assertTrue(homePage.isHomePageDisplayed(), "Homepage is not displayed after successful login");

		test.log(Status.INFO, "Logged in successfully");
		logger.info("Logged in successfully");
	}

	//Seaching product test case
	@Test(priority = 5)
	public void searchProduct() {

		test = extent.createTest("searchProduct");

		amazonSearchPage = new AmazonSearchPage(driver);
		amazonSearchPage.search_Product();

		test.log(Status.INFO, "Searched for product");
		logger.info("Searched for product");
	}

	//selecting test case after searching
	@Test(priority = 6)
	public void selectProduct() {
		
		test = extent.createTest("After seaching product select it.");
		
		amazonSearchPage = new AmazonSearchPage(driver);
		amazonSearchPage.selectProduct();
		
		test.log(Status.INFO, "Product selected successfully");
		logger.info("Product selected successfully");

	}


	@Test(priority = 9)
	public void selectAmazonFresh() throws IOException{

		test = extent.createTest("select AmazonFresh");

		AmazonFreshPage amazonFreshPage = new AmazonFreshPage(driver);
		amazonFreshPage.navigateToAmazonFreshPage();
		
		try {
			
			if (!amazonFreshPage.isAmazonFreshPage()) {
				String screenshotPath = captureScreenshot("CouldNotOpenedAmazonFreshPage");
				test.log(Status.INFO, "Failed to Open Amazon Fresh");
				test.fail("Failed to Open Amazon Fresh", MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
				logger.info("Failed to Open Amazon Fresh");
				throw new AssertionError("Failed to Open Amazon Fresh");
			}else {
				test.log(Status.INFO, "Navigated to Amazon Fresh page");
				logger.info("Navigated to Amazon Fresh page");
			}
			
		} catch (AssertionError e) {
			// TODO: handle exception
			
		}
		
		
		
		
		//Assert.assertTrue(amazonFreshPage.isAmazonFreshPage(), "Fresh page title verification failed");

		
	}


	@AfterClass
	public void afterTest() throws InterruptedException {
		// Flush ExtentReports
		extent.flush();

		// Quit WebDriver
		driver.quit();
		logger.info("Browser closed");
	}

}
