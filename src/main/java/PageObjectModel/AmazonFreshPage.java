package PageObjectModel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AmazonFreshPage {

	WebDriver driver;
	
	By img_click = By.xpath("//a[contains(@href, '/alm/category/ref') and contains(@aria-label, 'F&V')]\r\n");

	public AmazonFreshPage(WebDriver driver) {
		this.driver = driver;
	}

	public void navigateToAmazonFreshPage() {
		this.driver.findElement(By.xpath("//*[@id=\"nav-link-groceries\"]/span[1]")).click();
	}
	
	
	public boolean isAmazonFreshPage() {
        String actualTitle = driver.getTitle();
        System.out.println("Title of AmazonFreshPage :-"+driver.getTitle());
        String expectedTitle = "Amazon.in: Amazon Fresh";
        return actualTitle.equals(expectedTitle);
    }
	
	public void selectFruitsAndVegitable() {
		try {
			Thread.sleep(2000);
			this.driver.findElement(img_click).click();
			Thread.sleep(2000);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error Occured"+e.getMessage());
		}
	}

}
