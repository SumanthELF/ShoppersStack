package TestCases;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import generic_Library.BaseClass;

public class TC_E2E_05 extends BaseClass {
	
	@Test
	public void TestCase() throws InterruptedException {
		//Step 4 : Click on 'Account settings' icon
		driver.findElement(By.xpath("//button[@aria-label='Account settings']")).click();
		
		//Step 5 : Click on 'My Profile' option in account settings menu
		driver.findElement(By.xpath("//li[text()='My Profile']")).click();
		explicitWait.until(ExpectedConditions.urlContains("my-profile-info"));
		String actualProfilePageTitle = driver.getTitle();
		Assert.assertEquals(actualProfilePageTitle, expectedProfilePageTitle, "Profile page is not displayed");
		Reporter.log("Profile page is displayed", true);
		
		//Step 6 : Click on 'My Addresses' tab
		driver.findElement(By.xpath("//div[text()='My Addresses']")).click();
		explicitWait.until(ExpectedConditions.urlContains("my-addresses"));
		String actualAddressPageTitle = driver.getTitle();
		Assert.assertEquals(actualAddressPageTitle, expectedAddressPageTitle, "My Addresses page is not displayed");
		Reporter.log("My Addresses page is displayed", true);
		
		//Step 7 : Click on 'Edit' button of first address
		driver.findElement(By.xpath("//*[name()='svg' and @id='editaddress_fl']")).click();
		explicitWait.until(ExpectedConditions.urlContains("editaddress"));
		Reporter.log("Edit Address page is displayed", true);
		
		//Step 8 : Enter the new phone number into phone number text field and Click on 'Update Address' button
		Thread.sleep(2000);
		driver.findElement(By.id("Phone Number")).click();
		Thread.sleep(2000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("document.getElementById('Phone Number').value='';");
		driver.findElement(By.id("Phone Number")).sendKeys("7234567891",Keys.ENTER);
		Thread.sleep(2000);
		
		if(driver.findElement(By.xpath("//div[text()='successfully updated']")).isDisplayed()) {
			Reporter.log("Successfully Updated toast message is displayed", true);
		}else {
			Reporter.log("Successfully Updated toast message is not displayed", true);
		}
		if(driver.findElement(By.xpath("//div[text()='7234567891']")).isDisplayed()){
			Reporter.log("Old phone number is updated with new Phone number in My Addresses Page", true);
		}else {
			Reporter.log("Old phone number is not updated with new Phone number in My Addresses Page", true);
		}
		
	}

}
