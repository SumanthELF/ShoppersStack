package generic_Library;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	public WebDriver driver;
	public WebDriverWait explicitWait; 

	//Expected Data
	public String expectedWelcomePageTitle = "ShoppersStack";
	public String expectedLoginPageTitle = "ShoppersStack | Login";
	public String expectedHomePageTitle = "ShoppersStack | Home";
	public String expectedProfilePageTitle = "ShoppersStack | Profile";
	public String expectedAddressPageTitle = "ShoppersStack | My Address";
	public String expectedCartPageTitle = "ShoppersStack | Cart";
	public String expectedWishListPageTitle = "ShoppersStack | Wishlist";
	public String expectedMyLikesPageTitle = "ShoppersStack | My Likes";
	
	public String expectedCartPageUrl ="https://www.shoppersstack.com/cart";
	public String expectedAddressPagePageUrl = "https://www.shoppersstack.com/selectaddress";
	public String expectedPaymentOptionsPagePageUrl = "https://www.shoppersstack.com/payment-options";
	public String expectedOrderConfirmationPageUrl = "https://www.shoppersstack.com/place-order";
	
	
	@BeforeClass
	public void browserSetUp() {
		//Open browser and enter the test URL
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		Reporter.log("The empty browser is Launched", true);
		driver.manage().window().maximize();
		Reporter.log("Browser window is Maximized", true);
		driver.manage().timeouts().implicitlyWait(25,TimeUnit.SECONDS);
		explicitWait = new WebDriverWait(driver,20);
		driver.get("https://www.shoppersstack.com/");
		String actualWelcomePageTitle = driver.getTitle();
		Assert.assertEquals(actualWelcomePageTitle, expectedWelcomePageTitle, "Welcome Page is not displayed");
		Reporter.log("Welcome page is displayed");
		
		//Click on 'Login' button
		driver.findElement(By.id("loginBtn")).click();
		explicitWait.until(ExpectedConditions.titleContains("Login"));
		String actualLoginPageTitle = driver.getTitle();
		Assert.assertEquals(actualLoginPageTitle, expectedLoginPageTitle, "Login Page is not displayed");
		Reporter.log("Login page is displayed");
	}
	
	@BeforeMethod
	public void LoginToApplication() {
		//Enter valid Email id, valid password and click on 'Login' button
		driver.findElement(By.id("Email")).sendKeys("neerajapasala21@gmail.com");
		driver.findElement(By.id("Password")).sendKeys("nrSBTYV8g@S@Eau");
		driver.findElement(By.xpath("//span[text()='Login']")).click();
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h3[contains(text(),'Hello')]")));
		String actualHomePageTitle = driver.getTitle();
		Assert.assertEquals(actualHomePageTitle, expectedHomePageTitle, "Login was Unsuccessfull and Home is not displayed");
		Reporter.log("User Login was Sucessfull and Home page is displayed", true);
	}

	@AfterMethod
	public void LogoutOfApplication() {
		//Logout of the application
		for(;;) {
			try {
				driver.findElement(By.xpath("//button[@aria-label='Account settings']")).click();
				break;
			}catch(ElementClickInterceptedException e) {
				
			}
		}		
		driver.findElement(By.xpath("//li[text()='Logout']")).click();
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.id("loginBtn")));
		String actualWelcomePageTitle = driver.getTitle();
		Assert.assertEquals(actualWelcomePageTitle, expectedWelcomePageTitle, "Welcome Page is not displayed");
		Reporter.log("User Logout was Sucessfull and Welcome Page is displayed", true);
	}

	@AfterClass
	public void browserTearDown() {
		//Closing the browser
		driver.quit();
		Reporter.log("Browser window Closed Sucessfully", true);
	}

}
