package com.training.regression.tests;

import static org.testng.Assert.assertEquals;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.testng.internal.reflect.MethodMatcherException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.training.bean.LoginBean;
import com.training.dao.ELearningDAO;
import com.training.dataproviders.LoginDataProviders;
import com.training.generics.ScreenShot;
import com.training.pom.AddNewFeaturePOMTC4;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class AddNewFeatureTC4 {
	private WebDriver driver;
	private String baseUrl;
	private AddNewFeaturePOMTC4 AddNewFeaturePOMTC4;
	private static Properties properties;
	private ScreenShot screenShot;

	//To Verify whether application allows admin to Add multiple New Feature in the application
	@BeforeClass
	public static void setUpBeforeClass() throws IOException {
		properties = new Properties();
		FileInputStream inStream = new FileInputStream("./resources/others.properties");
		properties.load(inStream);
	}
	
	@BeforeMethod
	// user should have launched the application by entering valid URL admin should be logged in
	public void setUp() throws Exception {
		driver = DriverFactory.getDriver(DriverNames.CHROME);
		AddNewFeaturePOMTC4 = new AddNewFeaturePOMTC4(driver);
		baseUrl = properties.getProperty("baseURL");
		screenShot = new ScreenShot(driver);
		// open the browser
		driver.get(baseUrl);
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"responsive\"]/li[7]/a")));
		AddNewFeaturePOMTC4.clickSignInBtn(); 
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("login")));
		AddNewFeaturePOMTC4.sendUserID("admin");
		AddNewFeaturePOMTC4.sendpasswd("admin@123");
		AddNewFeaturePOMTC4.clickLogInBtn();
		// Click on Properties link
		AddNewFeaturePOMTC4.clickPropertyBtn();
		// Click on Features link
		AddNewFeaturePOMTC4.clickFeatureBtn();
	}

	@AfterMethod
	public void tearDown() throws Exception {
	driver.quit();
	}

	//Takes data from excel sheet. 
	//Files used to capture and format data: Apache POI, LoginDataProviders, others.proerties.
	
	@Test(dataProvider = "excel-inputs", dataProviderClass = LoginDataProviders.class)
	public void loginExlTest(String Name, String Slug, String ParenFeature, String Description) throws InterruptedException {
		
		// Enter Valid Credentials in Name textbox
		AddNewFeaturePOMTC4.sendName(Name);
		
		// Enter Valid Credentials in Slug textbox
		AddNewFeaturePOMTC4.sendSlug(Slug);
		Thread.sleep(2000);
		
		//Select details from drop-down Parent Feature list box
	    WebElement dropDownArrow = driver.findElement(By.id("parent"));
	    dropDownArrow.click();
		Thread.sleep(2000);
		Select dropdown = new Select(driver.findElement(By.id("parent")));
		dropdown.selectByVisibleText(ParenFeature);
		
		// Enter Valid Credentials in Description textbox
		AddNewFeaturePOMTC4.sendDescription(Description);
		
		// Click on Submit button
		AddNewFeaturePOMTC4.clikSubmitBtn();
		Thread.sleep(8000);
		
		// Click on Add New Feature button
		AddNewFeaturePOMTC4.clickFeatureBtn();
		Thread.sleep(4000);
		
		//Added feature in existing feature module should get displayed
		AddNewFeaturePOMTC4.SearchBox(Name);
		AddNewFeaturePOMTC4.clikSrchBtn();
		Thread.sleep(2000);
		
		// Capturing the table row value to verify if Feature has been added or not followed by assertion.
		String Tablename = driver.findElement(By.xpath("//tbody/tr/td")).getText();
		String ActualNm = (Name);
		if (Tablename.equals(ActualNm))
		{
			System.out.println("Feature is being displayed in added Feature List: " +Name);
		}
		else
		{
			System.out.println("Please check for any data issue or rerun the program again. Else raise a Defect: " +Name);
		}
		assertEquals(Tablename, ActualNm);

		
		

	}

}