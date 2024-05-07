package PageObjectModel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import Configuration.ConfigReader;

public class LoginPage{
	
	WebDriver driver;
	ConfigReader configReader;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}

	By Username_editBox = By.id("ap_email");
	By Password_editBox = By.id("ap_password");
	By continue_Btn = By.id("continue");
	By signIn_Btn = By.id("signInSubmit");
	
	public void enterUsername() {
		this.driver.findElement(Username_editBox).sendKeys(configReader.getPropertydata("userId"));
	}
	
	public void enterPassword() {
		this.driver.findElement(Password_editBox).sendKeys(configReader.getPropertydata("password"));
	}
	
	public void clickContinue() {
		this.driver.findElement(continue_Btn).click();
	}
	
	public void clickSingIn() {
		this.driver.findElement(signIn_Btn).click();
	}
	
	public void verifyAndLogin() {
		this.enterUsername();
		this.clickContinue();
		this.enterPassword();
		this.clickSingIn();
	}

}
