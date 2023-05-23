package TestCases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import generic_Library.BaseClass;

public class TC_E2E_13 extends BaseClass {
	
	@Test
	public void TestCase() throws InterruptedException {
		//Step 4 : Click on 'Account settings' icon
		driver.findElement(By.xpath("//button[@aria-label='Account settings']")).click();
		
		//Step 5 : Click on 'My Profile' option in account settings menu
		driver.findElement(By.xpath("//li[text()='My Profile']")).click();
		explicitWait.until(ExpectedConditions.urlContains("my-profile-info"));
		String actualProfilePageTitle = driver.getTitle();
		Assert.assertEquals(actualProfilePageTitle, expectedProfilePageTitle, "Profile page is not displayed");
		Reporter.log("Profile page is displayed", true);;
		
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
		
		//Step 8 -16 : Enter the valid data into the fields and click on 'Add address' button
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
		
		//Step 17 - 18: Click on 'Deliverable pincodes' icon inside Pincode text field and capture Pincode of valid city 
		driver.findElement(By.xpath("//*[name()='svg' and @data-testid='InfoIcon']")).click();
		String pincode = driver.findElement(By.xpath("//table/tbody/tr[1]/td[2]")).getText();
		driver.findElement(By.xpath("//*[name()='svg' and @data-testid='CloseIcon']")).click();
		driver.findElement(By.id("Pincode")).sendKeys(pincode);
			
		//Step 19 : Enter Data into Phone Number textfield and Click on Add Address button
		driver.findElement(By.id("Phone Number")).sendKeys("9876543210");
		driver.findElement(By.id("addAddress")).click();
		WebElement toastMessageElement = driver.findElement(By.xpath("//div[text()='successfully added']"));
		if(toastMessageElement.isDisplayed()) {
			Reporter.log("Successfully added toast message is displayed", true);
		}else {
			Reporter.log("Successfully added toast message is not displayed", true);
		}
		
		//Step 20 : Click on 'Delete' icon.
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//*[name()='svg' and @data-testid='DeleteIcon'])[last()]")).click();
		
		//Step 21 : Click on 'YES' button
		driver.findElement(By.xpath("(//button[text()='Yes'])[2]")).click();
		
		//Step 22 : Click 'OK' button in confirmation pop up
		explicitWait.until(ExpectedConditions.alertIsPresent());
		String expectedAlertMessage = "Address deleted successfully";
		String actualAlertMessage = driver.switchTo().alert().getText();
		Assert.assertEquals(actualAlertMessage, expectedAlertMessage, "'Address deleted succesfully' Confirmation dialog box not displayed");
		Reporter.log("Address deleted succesfully' Confirmation dialog box is displayed",true);
		driver.switchTo().alert().accept();
				
	}

}
