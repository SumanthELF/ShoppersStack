package TestCases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import generic_Library.BaseClass;

public class TC_E2E_04 extends BaseClass {
	
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
		
		//Step 7 : Click on 'ADD ADDRESS' button
		driver.findElement(By.xpath("//button[text()='Add Address']")).click();
		explicitWait.until(ExpectedConditions.urlContains("addressform"));
		Reporter.log("Address form page is displayed", true);
		
		//Step 8 : Enter the valid data into the fields and click on 'Add address' button
		driver.findElement(By.id("Name")).sendKeys("Neeraja");
		driver.findElement(By.id("House/Office Info")).sendKeys("961");
		driver.findElement(By.id("Street Info")).sendKeys("MG Road");
		driver.findElement(By.id("Landmark")).sendKeys("Near Metro Station");
		WebElement countyrDropdown = driver.findElement(By.id("Country"));
		WebElement stateDropdown = driver.findElement(By.id("State"));
		WebElement cityDropdown = driver.findElement(By.id("City"));		
		Select countrySelect = new Select(countyrDropdown);
		Select stateSelect = new Select(stateDropdown);
		Select citySelect = new Select(cityDropdown);
		countrySelect.selectByValue("India");	
		stateSelect.selectByValue("Karnataka");
		citySelect.selectByValue("Bengaluru");
		driver.findElement(By.id("Pincode")).sendKeys("432101");
		driver.findElement(By.id("Phone Number")).sendKeys("9876543210");
		driver.findElement(By.id("addAddress")).click();
		WebElement toastMessageElement = driver.findElement(By.xpath("//div[text()='successfully added']"));
		if(toastMessageElement.isDisplayed()) {
			Reporter.log("Successfully added toast message is displayed", true);
		}else {
			Reporter.log("Successfully added toast message is not displayed", true);
		}
		if(driver.findElement(By.xpath("//h3[text()='Neeraja']")).isDisplayed()){
			Reporter.log("Newly added Address is displayed in My Addresses Page", true);
		}else {
			Reporter.log("Newly added Address is not displayed in My Addresses Page", true);
		}
		
	}

}
