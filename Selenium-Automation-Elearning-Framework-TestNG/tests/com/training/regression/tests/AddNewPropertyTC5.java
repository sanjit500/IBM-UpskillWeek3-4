package com.training.regression.tests;

import static org.testng.Assert.assertEquals;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.training.generics.ScreenShot;
import com.training.pom.AddPropertyTC5POM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;
/*Author: Sanjit Tripathy (IBM)
Contact: +91-8888862990
Purpose of this code: To verify whether application allows admin to add new property with all details & added property details in home screen for user*/
public class AddNewPropertyTC5 
{

	private WebDriver driver;
	private String baseUrl;
	private AddPropertyTC5POM AddPropertyTC5POM;
	private static Properties properties;
	private ScreenShot screenShot;


	@BeforeClass
	public static void setUpBeforeClass() throws IOException 
	{
		properties = new Properties();
		FileInputStream inStream = new FileInputStream("./resources/others.properties");
		properties.load(inStream);
	}

	@BeforeMethod
	public void setUp() throws Exception 
	{
		//Before method executes the basic operations like opening Link & Logging in..
		driver = DriverFactory.getDriver(DriverNames.CHROME);
		AddPropertyTC5POM = new AddPropertyTC5POM(driver); 
		baseUrl = properties.getProperty("baseURL");
		screenShot = new ScreenShot(driver); 
		// open the browser 
		driver.get(baseUrl);
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"responsive\"]/li[7]/a")));
		AddPropertyTC5POM.clickSignInBtn(); 
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("login")));
		AddPropertyTC5POM.sendUserName("admin");
		AddPropertyTC5POM.sendPassword("admin@123");
		AddPropertyTC5POM.clickLoginBtn();
	}
	
	@AfterMethod
	public void tearDown() throws Exception 
	{
		Thread.sleep(1000);
		driver.quit();
		
	}
	@Test
	public void ClicknAddNewProperty() throws InterruptedException 
	{
		// Click on Properties tab
		AddPropertyTC5POM.clickPropertiesBtn(); 
		screenShot.captureScreenShot("PropertiesPageScreenshot");
		Thread.sleep(3000);
		// Click on Add New link
		AddPropertyTC5POM.clickAddNewBtn();
		// Enter valid credentials in Enter Title Here textbox
		AddPropertyTC5POM.enterTitle("sanjit new launch6");
		//To Verify and assertion purpose
		String EnteredTitle = ("sanjit new launch6");
		// Enter valid credentials in Enter Title Here textbox
		AddPropertyTC5POM.enterTextBox("sanjit new launch6");
		Thread.sleep(1000);
		// Enter valid credentials in Price Here textbox
		AddPropertyTC5POM.enterPriceBox("50000.00");
		// Enter valid credentials in Price per sq. meter/sq. ft textbox
		AddPropertyTC5POM.enterSqFtPrice("200.00");
		Thread.sleep(1000);
		// Click on Main Details tab
		AddPropertyTC5POM.clickMainDetailsTab();
		// Enter valid credentials in Status textbox
		AddPropertyTC5POM.enterStatus("New");
		// Enter valid credentials in Location textbox
		AddPropertyTC5POM.enterLocation("Electronic city");
		// Enter valid credentials in Possession textbox
		AddPropertyTC5POM.enterPosession("immediate");
		Thread.sleep(1000);
		// Click on Location tab
		AddPropertyTC5POM.clickLocationTab();
		// Enter valid credentials in Address textbox
		AddPropertyTC5POM.enterAddress1("yeshwanthapur");
		// Enter valid credentials in Google Maps Address textbox and click on drop-down populated after entering the below data.
		AddPropertyTC5POM.enterGoogleAddrs("yeshwanthapur");
		Thread.sleep(3000);
		WebElement celltext = driver.findElement(By.xpath("//*[@id=\"locations_tab\"]/div[2]/div[2]/p"));
	    Actions actions1 = new Actions(driver);
		actions1.moveToElement(celltext).click(celltext).build().perform();
		Thread.sleep(3000);
		// Enter valid credentials in Latitude textbox
		AddPropertyTC5POM.enterLatitue("120");
		// Enter valid credentials in Longitude textbox
		AddPropertyTC5POM.enterLogitude("56");
		Thread.sleep(1000);
		// Click on Details tab
		AddPropertyTC5POM.clickDetailsTab();
		// Enter valid credentials in Storage Room textbox
		AddPropertyTC5POM.enterStorageRoom("2");
		Thread.sleep(1000);
		// To scroll down to click on a particular checkbox
        JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,800)");
		Thread.sleep(4000);
		// Click on Central Bangalore Checkbox
		AddPropertyTC5POM.clickCBChkBox();
		Thread.sleep(1000);
		//To Scroll up to click on particular check boxes
		JavascriptExecutor js1 = (JavascriptExecutor) driver;
		js1.executeScript("window.scrollBy(0,-1200)");
		Thread.sleep(4000);

		// Click on checkbox beside  Feature of Features section
		AddPropertyTC5POM.clickFeaturebox();
		Thread.sleep(1000);
		// Click on checkbox beside  Region of Regions section
		AddPropertyTC5POM.clickRegionBox();
		Thread.sleep(1000);
		// Click on Publish button
		AddPropertyTC5POM.clickPublishBtn();
		Thread.sleep(4000);
		
		//String DisplMsg is being Asserted at the end. And wait till the Post published message is displayed.
		String DisplMsg = ("Post published. View post"); 
		WebDriverWait wait = new WebDriverWait(driver, 50);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"message\"]/p")));
		String PublishMsg = driver.findElement(By.xpath("//*[@id=\"message\"]/p")).getText();
		//Test Case: Post published. View post message should get displayed
		System.out.println("Message appears after Property Published: " +PublishMsg); 
		assertEquals(PublishMsg, DisplMsg);
		
		//Mouse hover operation to b able to click on Logout link
		WebElement celltext1 = driver.findElement(By.xpath("//*[@id=\"wp-admin-bar-my-account\"]/a/span"));
	    Actions actions2 = new Actions(driver);
		actions2.moveToElement(celltext1).build().perform();
		Thread.sleep(1000);
		// Click on Logout
		AddPropertyTC5POM.clickLogout();
		Thread.sleep(3000);

		//Click on Real Estate icon
		AddPropertyTC5POM.clickRealEstateBtn();
		Thread.sleep(1000);
		//search for added property
		AddPropertyTC5POM.clickAndSearchSpace(EnteredTitle);
		Thread.sleep(3000);
		// Mouse Hover and click on the newly created Property.
		WebElement celltext2 = driver.findElement(By.xpath("//div/div[1]/div[1]/h3/a"));
	    Actions actions3 = new Actions(driver);
		actions3.moveToElement(celltext2).click(celltext2).build().perform();
		Thread.sleep(2000);
		
		//To handle and change the focus of the program to another tab. After clicking on new property above, it opens up in a new tab
		   ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
		   driver.switchTo().window(tabs2.get(1));
		
		//Added property details should get displayed.    
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[4]/div/div[1]/div/p")));
		String expTitle = driver.findElement(By.xpath("//div[4]/div/div[1]/div/p")).getText();
		
		if (EnteredTitle.equals(expTitle))
		{
			System.out.println("Property has been posted successfully: "+expTitle);
		}
		else
		{
			System.out.println("New Property post unsuccessful: "+expTitle);
		}
		
		//Asserted if Entered title of the property is same as displayed to the user.
		assertEquals(expTitle, EnteredTitle);
		//To Close the popped up tab
	    driver.close();
	    //Change the focus of the program to the original tab.
	    driver.switchTo().window(tabs2.get(0));

		
	}
}


