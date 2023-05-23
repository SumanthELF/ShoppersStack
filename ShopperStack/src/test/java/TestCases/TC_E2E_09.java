package TestCases;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import generic_Library.BaseClass;

public class TC_E2E_09 extends BaseClass {
	
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
		
		//Step 6 : Click on My Likes
		driver.findElement(By.xpath("//div[text()='My Likes']")).click();
		explicitWait.until(ExpectedConditions.urlContains("my-likes"));
		String actualMyLikesPageTitle = driver.getTitle();
		Assert.assertEquals(actualMyLikesPageTitle, expectedMyLikesPageTitle, "My Likes page is not displayed");
		Reporter.log("My Likes page is displayed", true);
		
		//Step 7 : Select a Category
		WebElement categoryDropdown = driver.findElement(By.id("Category"));
		Select categorySelect = new Select(categoryDropdown);
		categorySelect.selectByValue("men");
		
		//Step 8 : Select Two Sub Categories
		Thread.sleep(2000);
		WebElement subCategoryDropdown = driver.findElement(By.id("Category Type"));
		Select subCategorySelect = new Select(subCategoryDropdown);
		subCategorySelect.selectByVisibleText("Tshirt");
		subCategorySelect.selectByVisibleText("Shirt");
		
		//Step 9 : Click on Submit button
		driver.findElement(By.id("Submit")).click();
		List<WebElement> previousLikedProducts = driver.findElements(By.xpath("//div[contains(@class,'profile_likedProducts')]//span"));
		Reporter.log("Selected items under my previous likes are : ",true);
		for(WebElement ele : previousLikedProducts) {
			Reporter.log(ele.getText(),true);
		}
	}

}
