package TestCases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import generic_Library.BaseClass;

public class TC_E2E_07 extends BaseClass {
	
	@Test
	public void TestCase() throws InterruptedException {
		//Step 4 : Click on 'Account settings' icon
		driver.findElement(By.xpath("//button[@aria-label='Account settings']")).click();
		
		//Step 5 : Click on Wish List
		driver.findElement(By.xpath("//li[text()='Wish List']")).click();
		Thread.sleep(2000);
		explicitWait.until(ExpectedConditions.urlContains("wishlist"));
		String actualWishListPageTitle = driver.getTitle();
		Assert.assertEquals(actualWishListPageTitle, expectedWishListPageTitle, "WishList page is not displayed");
		Reporter.log("WishList page is displayed", true);
		
		//Step 6 : Add the product in Cart
		driver.findElement(By.xpath("//button[text()='Add To Cart']")).click();
		
		//Step 7 : Click on Cart icon
		Thread.sleep(2000);
		driver.findElement(By.id("cart")).click();
		Thread.sleep(3000);
		explicitWait.until(ExpectedConditions.urlContains("cart"));
		String actualCartPageUrl = driver.getCurrentUrl();
		Assert.assertEquals(actualCartPageUrl, expectedCartPageUrl, "Cart page is not displayed");
		Reporter.log("Cart page is displayed", true);
		
		
		//Step 8 : Click on BUY NOW
		driver.findElement(By.id("buynow_fl")).click();
		Thread.sleep(2000);
		String actualAddressPageUrl = driver.getCurrentUrl();
		Assert.assertEquals(actualAddressPageUrl, expectedAddressPagePageUrl, "Address page is not displayed");
		Reporter.log("Address page is displayed", true);
		
		//Step 9 : Click on Add New Address
		driver.findElement(By.linkText("Add New Address")).click();
		explicitWait.until(ExpectedConditions.urlContains("addressform"));
		Reporter.log("Address form page is displayed", true);
		
		//Step 10 - 16 : Enter Data into all the text fields
		driver.findElement(By.id("Name")).sendKeys("Dinesh Kumar");
		driver.findElement(By.id("House/Office Info")).sendKeys("833");
		driver.findElement(By.id("Street Info")).sendKeys("12th Main");
		driver.findElement(By.id("Landmark")).sendKeys("Central Mall");
		WebElement countyrDropdown = driver.findElement(By.id("Country"));
		WebElement stateDropdown = driver.findElement(By.id("State"));
		WebElement cityDropdown = driver.findElement(By.id("City"));		
		Select countrySelect = new Select(countyrDropdown);
		Select stateSelect = new Select(stateDropdown);
		Select citySelect = new Select(cityDropdown);
		countrySelect.selectByValue("Canada");
		stateSelect.selectByValue("Alberta");
		citySelect.selectByValue("Blackfalds");
		
		//Step 17 : Click "Info" icon in Pincode textfield
		driver.findElement(By.xpath("//*[name()='svg' and @data-testid='InfoIcon']")).click();
		
		//Step 18 : Copy the Pincode from Deliverable Pins and Paste it in Pincode textfield
		String pincode = driver.findElement(By.xpath("//table/tbody/tr[1]/td[2]")).getText();
		driver.findElement(By.xpath("//*[name()='svg' and @data-testid='CloseIcon']")).click();
		driver.findElement(By.id("Pincode")).sendKeys(pincode);
		
		//Step 19 : Enter Data into Phone Number textfield
		driver.findElement(By.id("Phone Number")).sendKeys("9876543210");
		
		//Step 20 : Click on Add Address button
		driver.findElement(By.id("addAddress")).click();
		WebElement toastMessageElement = driver.findElement(By.xpath("//div[text()='successfully added']"));
		if(toastMessageElement.isDisplayed()) {
			Reporter.log("Successfully added toast message is displayed", true);
		}else {
			Reporter.log("Successfully added toast message is not displayed", true);
		}
		
		//Step 21 : Select the Address
		driver.findElement(By.xpath("//strong[text()='Dinesh Kumar']/../../..//input[@type='radio']")).click();
		Thread.sleep(2000);
		
		//Step 22 : Click on Proceed button
		driver.findElement(By.xpath("//button[text()='Proceed']")).click();
		Thread.sleep(2000);
		String actualPaymentOptionsPageUrl = driver.getCurrentUrl();
		Assert.assertEquals(actualPaymentOptionsPageUrl, expectedPaymentOptionsPagePageUrl, "Payment method page is not displayed");
		Reporter.log("Payment method page is displayed", true);
		
		//Step 23 : Select "COD" under Select a payment method
		driver.findElement(By.xpath("//input[@value='COD']")).click();
				
		//Step 24 : Click on Proceed button
		driver.findElement(By.xpath("//button[text()='Proceed']")).click();
		if(driver.findElement(By.xpath("//div[text()='Created']")).isDisplayed()) {
			Reporter.log("Created toast message is displayed", true);
		}else {
			Reporter.log("Created toast message is not displayed", true);
		}
		String actualOrderConfirmationPageUrl = driver.getCurrentUrl();
		Assert.assertEquals(actualOrderConfirmationPageUrl, expectedOrderConfirmationPageUrl, "Order confirmation page is not displayed");
		Reporter.log("Order confirmation page is displayed", true);
	}

}
