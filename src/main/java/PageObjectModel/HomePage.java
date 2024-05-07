package PageObjectModel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage{
	
	WebDriver driver;

	public HomePage(WebDriver driver) {
		this.driver = driver;
	}

    public boolean isHomePageDisplayed() {
      
        return driver.findElement(By.id("nav-logo-sprites")).isDisplayed();
    }

}
