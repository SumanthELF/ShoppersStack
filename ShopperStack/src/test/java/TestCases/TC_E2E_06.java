package TestCases;

import static org.testng.Assert.assertEquals;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import generic_Library.BaseClass;

public class TC_E2E_06 extends BaseClass {
	
	@Test
	public void TestCase() throws InterruptedException {
		//Step 4 : Click on 'Account settings' icon
		driver.findElement(By.xpath("//button[@aria-label='Account settings']")).click();
		
		//Step 5 : Click on 'My Profile' option in account settings menu
		driver.findElement(By.xpath("//li[text()='My Profile']")).click();
		Thread.sleep(2000);
		explicitWait.until(ExpectedConditions.urlContains("my-profile-info"));
		String actualProfilePageTitle = driver.getTitle();
		Assert.assertEquals(actualProfilePageTitle, expectedProfilePageTitle, "Profile page is not displayed");
		Reporter.log("Profile page is displayed", true);
		
		//Step 6 : Click on Edit profile
		driver.findElement(By.xpath("//button[contains(text(),'Edit Profile')]")).click();
		Thread.sleep(2000);
		if(driver.findElement(By.xpath("//h2[contains(text(),'Edit Profile')]")).isDisplayed()){
			Reporter.log("Edit profile pop up is displayed", true);
		}else {
			Reporter.log("Edit profile pop up is not displayed", true);			
		}
		
		//Step 7 - 13 : Modify all the details of the user and click on Subimit button
		Thread.sleep(2000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("document.getElementById('First Name').value='Dinesh';");
		Thread.sleep(1000);
		js.executeScript("document.getElementById('Last Name').value='Kumar';");
		Thread.sleep(1000);
		WebElement dateField = driver.findElement(By.xpath("//input[@placeholder='mm/dd/yyyy']"));
		js.executeScript("arguments[0].value='03-01-1998';",dateField);
		Thread.sleep(1000);
		js.executeScript("document.getElementById('Phone Number').value='9876543210';");
		Thread.sleep(1000);
		WebElement countyrDropdown = driver.findElement(By.id("Country"));
		WebElement stateDropdown = driver.findElement(By.id("State"));
		WebElement cityDropdown = driver.findElement(By.id("City"));
		Select countrySelect = new Select(countyrDropdown);
		Select stateSelect = new Select(stateDropdown);
		Select citySelect = new Select(cityDropdown);
		countrySelect.selectByValue("Canada");
		Thread.sleep(1000);
		stateSelect.selectByValue("Alberta");
		Thread.sleep(1000);
		citySelect.selectByValue("Blackfalds");
		Thread.sleep(1000);
		
		//Step 14 : Click on SUBMIT Button
		driver.findElement(By.id("submit")).click();
		
		//Step 15 :  Click on Alert Popup "OK"
		explicitWait.until(ExpectedConditions.alertIsPresent());
		String expectedAlertMessage = "Profile updated";
		Alert alert = driver.switchTo().alert();
		String actualAlertMessage = alert.getText();
		assertEquals(actualAlertMessage, expectedAlertMessage, "Alert Pop up is not displayed with correct message");
		Reporter.log("Alert pop up is displayed",true);
		alert.accept();		
	}

}
