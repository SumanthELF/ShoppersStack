package TestCases;

import java.awt.AWTException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import generic_Library.BaseClass;

public class TC_E2E_26 extends BaseClass {

	@Test
	public void TestCase() throws InterruptedException, AWTException {
		// Step 4 : Click on 'WOMEN' category and click on 'TOPS' subcategory
		Actions actions = new Actions(driver);
		WebElement womenNavBarElement = driver.findElement(By.linkText("WOMEN"));
		actions.moveToElement(womenNavBarElement).perform();
		Thread.sleep(1000);
		WebElement subCategoryElement = driver.findElement(By.linkText("SHORTS & SKIRTS"));
		Thread.sleep(1000);
		actions.click(subCategoryElement).perform();
		Thread.sleep(1000);
		actions.moveToElement(driver.findElement(By.linkText("Women Short Skirt"))).perform();

		// Step 6 : Add the product in Cart
		Thread.sleep(1000);
		driver.findElement(By.xpath("//button[text()='add to cart']")).click();

		// Step 7 : Click on Cart icon
		Thread.sleep(2000);
		driver.findElement(By.id("cart")).click();
		Thread.sleep(3000);
		explicitWait.until(ExpectedConditions.urlContains("cart"));
		String actualCartPageUrl = driver.getCurrentUrl();
		Assert.assertEquals(actualCartPageUrl, expectedCartPageUrl, "Cart page is not displayed");
		Reporter.log("Cart page is displayed", true);

		// Step 7 : Click on '+' icon
		Thread.sleep(1000);
		String priceBeforeIncrease = driver.findElement(By.xpath("(//span[contains(text(),'₹')])[last()]")).getText();
		String quantityBeforeIncrease = driver.findElement(By.xpath("//div[contains(@class,'cart_quantity')]//span")).getText();
		Reporter.log("The quantity is : " + quantityBeforeIncrease,true);
		Reporter.log("The price is : " + priceBeforeIncrease,true);
		driver.findElement(By.xpath("//*[name()='svg' and @id='increase']")).click();
		Thread.sleep(1000);
		String quantityAfterIncrease = driver.findElement(By.xpath("//div[contains(@class,'cart_quantity')]//span")).getText();
		String priceAfterIncrease = driver.findElement(By.xpath("(//span[contains(text(),'₹')])[last()]")).getText();
		Reporter.log("The quantity after increasing is : " + quantityAfterIncrease,true);
		Reporter.log("The price after increasing is : " + priceAfterIncrease,true);
				
		// Step 8 : Click on '-' icon
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[name()='svg' and @id='decrease']")).click();
		Thread.sleep(1000);
		String quantityAfterDecrease = driver.findElement(By.xpath("//div[contains(@class,'cart_quantity')]//span")).getText();
		String priceAfterDecrease = driver.findElement(By.xpath("(//span[contains(text(),'₹')])[last()]")).getText();
		Reporter.log("The quantity after decreasing is : " + quantityAfterDecrease,true);
		Reporter.log("The price after decreasing is : " + priceAfterDecrease,true);
		
		// Step 9 : Click on 'REMOVE FROM CART' button
		driver.findElement(By.xpath("//button[text()='Remove from cart']")).click();
		Thread.sleep(2000);
		if(driver.findElement(By.xpath("//h3[text()='Remove Product']")).isDisplayed()){
			Reporter.log("Remove Product pop up is displayed",true);
		}else {
			Reporter.log("Remove Product pop up is not displayed",true);
		}
		
		// Step 10 : Click on 'YES' button in the popup page
		driver.findElement(By.xpath("//button[text()='Yes']")).click();
		Thread.sleep(2000);
		if(driver.findElement(By.xpath("//h2[text()='Your cart is empty!']")).isDisplayed()){
			Reporter.log("'Your cart is empty!' text is displayed",true);
		}else {
			Reporter.log("'Your cart is empty!' text is not displayed",true);
		}		
		
		// Step 11 : Click on 'Continue Shopping' button
		driver.findElement(By.xpath("//button[.='Continue Shopping']")).click();
		Thread.sleep(1000);
		String actualPageTitle = driver.getTitle();
		Assert.assertEquals(actualPageTitle, expectedHomePageTitle, "Home page is not displayed");
		Reporter.log("Home page is displayed",true);
	}

}
