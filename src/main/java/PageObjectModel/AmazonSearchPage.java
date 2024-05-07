package PageObjectModel;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Configuration.ConfigReader;

public class AmazonSearchPage {
	
	WebDriver driver;
	ConfigReader configReader;
	
	public AmazonSearchPage(WebDriver driver) {
		this.driver = driver;
	}
	
	By prod_search = By.id("twotabsearchtextbox");
	By select_prod = By.xpath("//span[text()='Apple iPhone 15 (128 GB) - Blue']");
	
	
	public void search_Product() {
		try {
			this.driver.findElement(prod_search).sendKeys(configReader.getPropertydata("prod_tobe_searched")+ Keys.ENTER);
			Thread.sleep(1000);
		} catch (Exception e) {
			// TODO: handle exception
		 System.out.println("Exception occured"+e.getMessage());
		}
	}
	
	public void selectProduct() {
		try {
			
			this.driver.findElement(select_prod).click();
			Thread.sleep(2000);
			
			
			// Get handles of all windows or tabs
			Set<String> windowHandles = driver.getWindowHandles();

			// Iterate through each handle to find the newly opened tab
			String originalTab = driver.getWindowHandle();
			for (String windowHandle : windowHandles) {
			    if (!windowHandle.equals(originalTab)) {
			        // Switch to the newly opened tab
			        driver.switchTo().window(windowHandle);

			        // Close the newly opened tab
			        driver.close();
			    }
			}

			// Switch back to the original tab
			driver.switchTo().window(originalTab);
			
		} catch (Exception e) {
			// TODO: handle exception
			 System.out.println("Exception occured"+e.getMessage());
		}
	}
	

	


}
