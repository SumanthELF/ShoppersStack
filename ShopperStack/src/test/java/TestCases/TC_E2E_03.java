package TestCases;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import generic_Library.BaseClass;

public class TC_E2E_03 extends BaseClass {
	
	@Test
	public void TestCase() throws InterruptedException {
		//Step 	4 : Click on 'Cart' icon
		Thread.sleep(2000);
		driver.findElement(By.id("cart")).click();
		Thread.sleep(3000);
		explicitWait.until(ExpectedConditions.urlContains("cart"));
		String actualCartPageUrl = driver.getCurrentUrl();
		Assert.assertEquals(actualCartPageUrl, expectedCartPageUrl, "Cart page is not displayed");
		Reporter.log("Cart page is displayed", true);
		
		//Step 5 : Click on 'REMOVE FROM CART' button of first product
		driver.findElement(By.xpath("//button[text()='Remove from cart']")).click();
		Thread.sleep(2000);
		if(driver.findElement(By.xpath("//h3[text()='Remove Product']")).isDisplayed()){
			Reporter.log("Remove Product pop up is displayed",true);
		}else {
			Reporter.log("Remove Product pop up is not displayed",true);
		}
		
		//Step 6 : Click on 'YES' button in the Remove Product popup
		driver.findElement(By.xpath("//button[text()='Yes']")).click();
		Thread.sleep(2000);
		if(driver.findElement(By.xpath("//h2[text()='Your cart is empty!']")).isDisplayed()){
			Reporter.log("'Your cart is empty!' text is displayed",true);
		}else {
			Reporter.log("'Your cart is empty!' text is not displayed",true);
		}
	}

}
