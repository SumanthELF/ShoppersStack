package TestCases;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import generic_Library.BaseClass;

public class TC_E2E_01 extends BaseClass {
	
	@Test
	public void TestCase() throws InterruptedException {
		//Step 4 : Enter 'men' in 'Search' text field and click on 'search' icon
		driver.findElement(By.id("search")).sendKeys("men");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[name()='svg' and @id='searchBtn']")).click();
		Thread.sleep(3000);
		explicitWait.until(ExpectedConditions.urlContains("search-products"));
		if(driver.getCurrentUrl().contains("search-products/men")) {
			Reporter.log("Men Category page is displayed", true);
		}else {
			Reporter.log("Men Category page is not displayed", true);
		}
				
		//Step 5 : Click on 'ADD TO CART' button of first product
		String firstProductInfo = driver.findElement(By.xpath("(//div[contains(@class,'featuredProducts_productCard')])[1]//div[contains(@class,'featuredProducts_footerLeft')]/span")).getText();
		String buttonTextBeforeClick = driver.findElement(By.xpath("(//div[contains(@class,'featuredProducts_productCard')])[1]//button")).getText();
		Reporter.log("Button text before click is " + buttonTextBeforeClick, true);
		driver.findElement(By.xpath("(//div[contains(@class,'featuredProducts_productCard')])[1]//button")).click();
		Thread.sleep(2000);
		String updatedButtonText = driver.findElement(By.xpath("(//div[contains(@class,'featuredProducts_productCard')])[1]//button")).getText();
		if(updatedButtonText.equals("ADDED")) {
			Reporter.log("Button text is Updated to Added", true);
		}else {
			Reporter.log("Button text is not Updated to Added", true);
		}
		
		//Step 6 : Product should be added to cart and the same product should be displayed in cart
		Thread.sleep(2000);
		driver.findElement(By.id("cart")).click();
		Thread.sleep(3000);
		explicitWait.until(ExpectedConditions.urlContains("cart"));
		String actualCartPageUrl = driver.getCurrentUrl();
		Assert.assertEquals(actualCartPageUrl, expectedCartPageUrl, "Cart page is not displayed");
		Reporter.log("Cart page is displayed", true);
		
		String productDetailsInCart = driver.findElement(By.xpath("//div[contains(@class,'cartContainer')]/div[contains(@class,'cart_checkoutProductContainer')]/div[1]//h3")).getText();
		if(firstProductInfo.contentEquals(productDetailsInCart)) {
			Reporter.log("Added product is displayed in cart", true);
		}else {
			Reporter.log("Added product is displayed in cart", true);
		}
		
	}

}
