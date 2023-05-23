package TestCases;

import java.awt.AWTException;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import generic_Library.BaseClass;

public class TC_E2E_29 extends BaseClass {
	
	@Test
	public void TestCase() throws InterruptedException, AWTException {
		//Step 4 : Click on 'BEAUTY' category
		driver.findElement(By.linkText("BEAUTY")).click();
		
		//Step 5 : Click on First product in the list
		driver.findElement(By.xpath("//div[contains(@class,'productCard')]//img")).click();
		
		//Step 6 : Add the product in Cart
		Thread.sleep(2000);
		driver.findElement(By.id("Add To Cart")).click();
		
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
		
		//Step 9 : Select the 'Address' radio button and click on 'Proceed' button	
		driver.findElement(By.name("address")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[text()='Proceed']")).click();
		Thread.sleep(2000);
		String actualPaymentOptionsPageUrl = driver.getCurrentUrl();
		Assert.assertEquals(actualPaymentOptionsPageUrl, expectedPaymentOptionsPagePageUrl, "Payment method page is not displayed");
		Reporter.log("Payment method page is displayed", true);
		
		//Step 10 : Select the 'Cash On Delivery (COD) radio button and click on 'Proceed' button
		driver.findElement(By.xpath("//input[@value='COD']")).click();
		driver.findElement(By.xpath("//button[text()='Proceed']")).click();
		if(driver.findElement(By.xpath("//div[text()='Created']")).isDisplayed()) {
			Reporter.log("Created toast message is displayed", true);
		}else {
			Reporter.log("Created toast message is not displayed", true);
		}
		String actualOrderConfirmationPageUrl = driver.getCurrentUrl();
		Assert.assertEquals(actualOrderConfirmationPageUrl, expectedOrderConfirmationPageUrl, "Order confirmation page is not displayed");
		Reporter.log("Order confirmation page is displayed", true);
		
		//Step 11 : Click on Account Settings 
		for(;;) {
			try {
				driver.findElement(By.xpath("//button[@aria-label='Account settings']")).click();
				break;
			}catch(ElementClickInterceptedException e) {
				
			}
		}		
		
		//Step 12 : Click on 'My Orders' link
		driver.findElement(By.xpath("//li[text()='My Orders']")).click();
		Thread.sleep(2000);
		String shiptToName = driver.findElement(By.xpath("(//h3[text()='SHIP TO'])[1]/../button")).getText();
		if(shiptToName.equalsIgnoreCase("Dinesh Kumar")) {
			Reporter.log("Order is Confirmed and displayed", true);
		}else {
			Reporter.log("Order has not Confirmed and it is not displayed", true);
		}
		
		//Step 13 : Click on 'CANCEL ORDER' button
		driver.findElement(By.xpath("//button[text()='Cancel Order']")).click();
		
		//Step 14 : Click on 'Yes' button in the popup page
		driver.findElement(By.xpath("(//button[text()='Yes'])[last()]")).click();
		if(driver.findElement(By.xpath("//div[text()='Order has been Cancelled']")).isDisplayed()) {
			Reporter.log("Order is canceled and Order canceled toast message is displayed", true);
		}else {
			Reporter.log("Order canceled toast message is not displayed", true);
		}
	}

}
