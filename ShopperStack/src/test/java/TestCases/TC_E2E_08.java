package TestCases;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import generic_Library.BaseClass;

public class TC_E2E_08 extends BaseClass {
	
	@Test
	public void TestCase() throws InterruptedException, AWTException {
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
		
		 //Step 8 : Double click on Product
		WebElement itemInsideCart = driver.findElement(By.xpath("//div[contains(@class,'cart_checkoutProduct')]"));
		Actions actions = new Actions(driver);
		actions.doubleClick(itemInsideCart).perform();
		
		//Step 9 : Click on BUY NOW
		driver.findElement(By.id("Buy Now")).click();
		Thread.sleep(2000);
		String actualAddressPageUrl = driver.getCurrentUrl();
		Assert.assertEquals(actualAddressPageUrl, expectedAddressPagePageUrl, "Address page is not displayed");
		Reporter.log("Address page is displayed", true);
		
		//Step 10 : Click on Add New Address
		driver.findElement(By.linkText("Add New Address")).click();
		explicitWait.until(ExpectedConditions.urlContains("addressform"));
		Reporter.log("Address form page is displayed", true);
		
		//Step 11 - 17 : Enter Data into all the text fields
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
	
		//Step 18 : Scroll until Help center is visible
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,document.body.scrollHeight);");
		
		//Step 19 : Right click on Help center
		actions.contextClick(driver.findElement(By.linkText("Help Center"))).perform();
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_RIGHT);
		Thread.sleep(1000);
		robot.keyPress(KeyEvent.VK_ENTER);
		Thread.sleep(1000);
		robot.keyRelease(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_RIGHT);
		
		//Step 20 : Open link in new tab
		String parentWindowId = driver.getWindowHandle();
		Set<String> allwindowIds = driver.getWindowHandles();
		allwindowIds.remove(parentWindowId);
		for(String windowId : allwindowIds) {
			driver.switchTo().window(windowId);
		}
		Thread.sleep(1000);
		
		//Step 21 : Copy Pincode from Deliverable pins and paste it in Pincode textfield
		String pincode = driver.findElement(By.xpath("//table/tbody/tr[1]/td[2]")).getText();
		Thread.sleep(1000);
		driver.switchTo().window(parentWindowId);
		driver.findElement(By.id("Pincode")).sendKeys(pincode);		
		
		//Step 22 : Enter Data into Phone Number textfield
		driver.findElement(By.id("Phone Number")).sendKeys("9876543210");
		
		//Step 23 : Click on Add Address button
		driver.findElement(By.id("addAddress")).click();
		WebElement toastMessageElement = driver.findElement(By.xpath("//div[text()='successfully added']"));
		if(toastMessageElement.isDisplayed()) {
			Reporter.log("Successfully added toast message is displayed", true);
		}else {
			Reporter.log("Successfully added toast message is not displayed", true);
		}
		
		//Step 24 : Select the Address
		driver.findElement(By.xpath("//strong[text()='Dinesh Kumar']/../../..//input[@type='radio']")).click();
		Thread.sleep(2000);
		
		//Step 25 : Click on Proceed button
		driver.findElement(By.xpath("//button[text()='Proceed']")).click();
		Thread.sleep(2000);
		String actualPaymentOptionsPageUrl = driver.getCurrentUrl();
		Assert.assertEquals(actualPaymentOptionsPageUrl, expectedPaymentOptionsPagePageUrl, "Payment method page is not displayed");
		Reporter.log("Payment method page is displayed", true);
		
		//Step 26 : Select "COD" under Select a payment method
		driver.findElement(By.xpath("//input[@value='COD']")).click();
				
		//Step 27 : Click on Proceed button
		driver.findElement(By.xpath("//button[text()='Proceed']")).click();
		if(driver.findElement(By.xpath("//div[text()='Created']")).isDisplayed()) {
			Reporter.log("Created toast message is displayed", true);
		}else {
			Reporter.log("Created toast message is not displayed", true);
		}
		String actualOrderConfirmationPageUrl = driver.getCurrentUrl();
		Assert.assertEquals(actualOrderConfirmationPageUrl, expectedOrderConfirmationPageUrl, "Order confirmation page is not displayed");
		Reporter.log("Order confirmation page is displayed", true);
		
		//Step 28 : Click on Account Settings 
		for(;;) {
			try {
				driver.findElement(By.xpath("//button[@aria-label='Account settings']")).click();
				break;
			}catch(ElementClickInterceptedException e) {
				
			}
		}		
		
		//Step 29 : Click on My Orders
		driver.findElement(By.xpath("//li[text()='My Orders']")).click();
		Thread.sleep(2000);
		String shiptToName = driver.findElement(By.xpath("(//h3[text()='SHIP TO'])[1]/../button")).getText();
		if(shiptToName.equalsIgnoreCase("Dinesh Kumar")) {
			Reporter.log("Order is Confirmed and displayed", true);
		}else {
			Reporter.log("Order has not Confirmed and it is not displayed", true);
		}
	}

}
