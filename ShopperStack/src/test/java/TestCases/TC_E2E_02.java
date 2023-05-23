package TestCases;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import generic_Library.BaseClass;

public class TC_E2E_02 extends BaseClass {
	
	@Test
	public void TestCase() throws InterruptedException {
		//Step 4 : Enter 'women' in 'Search' text field and click on 'search' icon
		driver.findElement(By.id("search")).sendKeys("women");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[name()='svg' and @id='searchBtn']")).click();
		Thread.sleep(3000);
		explicitWait.until(ExpectedConditions.urlContains("search-products"));
		if(driver.getCurrentUrl().contains("search-products/women")) {
			Reporter.log("Women Category page is displayed", true);
		}else {
			Reporter.log("Women Category page is not displayed", true);
		}
				
		//Step 5 : Click on first products image in 'Women' page
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//div[contains(@class,'featuredProducts_productCard')])[2]//img")).click();
		
		//Step 6 : Click on 'ADD TO CART' button 
		Thread.sleep(2000);
		String buttonTextBeforeClick = driver.findElement(By.id("Add To Cart")).getText();
		Reporter.log("Button text before click is " + buttonTextBeforeClick, true);
		driver.findElement(By.id("Add To Cart")).click();
		//explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("(//div[contains(@class,'featuredProducts_productCard')])[1]//button[text()='add to cart']")));
		Thread.sleep(2000);
		String updatedButtonText = driver.findElement(By.id("Add To Cart")).getText();
		if(updatedButtonText.equals("ADDED")) {
			Reporter.log("Button text is Updated to Added", true);
		}else {
			Reporter.log("Button text is not Updated to Added", true);
		}
		
		//Step 7 : Click on 'Cart' icon
		Thread.sleep(2000);
		driver.findElement(By.id("cart")).click();
		Thread.sleep(3000);
		explicitWait.until(ExpectedConditions.urlContains("cart"));
		String actualCartPageUrl = driver.getCurrentUrl();
		Assert.assertEquals(actualCartPageUrl, expectedCartPageUrl, "Cart page is not displayed");
		Reporter.log("Cart page is displayed", true);
		
		//Step 8 : Click on 'Buy Now' button
		driver.findElement(By.id("buynow_fl")).click();
		Thread.sleep(2000);
		String actualAddressPageUrl = driver.getCurrentUrl();
		Assert.assertEquals(actualAddressPageUrl, expectedAddressPagePageUrl, "Address page is not displayed");
		Reporter.log("Address page is displayed", true);
		
		//Step 9 : Select first address radio button and click on 'Proceed' button
		driver.findElement(By.name("address")).click();
		driver.findElement(By.xpath("//button[text()='Proceed']")).click();
		Thread.sleep(2000);
		String actualPaymentOptionsPageUrl = driver.getCurrentUrl();
		Assert.assertEquals(actualPaymentOptionsPageUrl, expectedPaymentOptionsPagePageUrl, "Payment method page is not displayed");
		Reporter.log("Payment method page is displayed", true);
		
		//Step 10 : Select 'Cash On Delivery (COD)' radio button and click on 'Proceed' button
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
		
	}

}
