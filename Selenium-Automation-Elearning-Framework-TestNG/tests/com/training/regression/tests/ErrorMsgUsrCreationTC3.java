package com.training.regression.tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

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
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.training.bean.LoginBean;
import com.training.dao.ELearningDAO;
import com.training.dataproviders.LoginDataProviders;
import com.training.generics.ScreenShot;
import com.training.pom.ErrorMsgUsrCreationPOMTC3;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class ErrorMsgUsrCreationTC3 
{
	private WebDriver driver;
	private String baseUrl;
	private ErrorMsgUsrCreationPOMTC3 ErrorMsgUsrCreationPOMTC3;
	private static Properties properties;
	private ScreenShot screenShot;

	//To Verify whether application displays error message upon entering invalid details in required fields while creating user by admin
	
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
		
		//Pre Condition: user should have launched the application by entering valid URL admin should be logged in
		
		driver = DriverFactory.getDriver(DriverNames.CHROME);
		ErrorMsgUsrCreationPOMTC3 = new ErrorMsgUsrCreationPOMTC3(driver);
		baseUrl = properties.getProperty("baseURL");
		screenShot = new ScreenShot(driver);
		// open the browser
		driver.get(baseUrl);
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"responsive\"]/li[7]/a")));
		ErrorMsgUsrCreationPOMTC3.clickSignInBtn(); 
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("login")));
		ErrorMsgUsrCreationPOMTC3.sendUserID("admin");
		ErrorMsgUsrCreationPOMTC3.sendpasswd("admin@123");
		ErrorMsgUsrCreationPOMTC3.clickLogInBtn();
		ErrorMsgUsrCreationPOMTC3.clickUsersBtn();
		ErrorMsgUsrCreationPOMTC3.clickAddNwBtn();
	}

	@AfterMethod
	public void tearDown() throws Exception 
	{
	driver.quit();
	}
 
	//Takes data from excel sheet. 
	//Files used to capture and format data: Apache POI, LoginDataProviders, others.proerties
	
	@Test(dataProvider = "excel-inputs", dataProviderClass = LoginDataProviders.class)
	public void loginExlTest(String UserName, String EMail, String FirstName, String LastName, String WebSite, String Password, String Role) throws InterruptedException 
	{
		// Enter Valid credentials in Username textbox
		ErrorMsgUsrCreationPOMTC3.sendUserName(UserName);
		
		// Enter Valid credentials in Email textbox
		ErrorMsgUsrCreationPOMTC3.sendEmail(EMail);
		
		// Enter Valid credentials in First Name textbox
		ErrorMsgUsrCreationPOMTC3.sendFstName(FirstName);
		
		// Enter Valid credentials in Last Name textbox
		ErrorMsgUsrCreationPOMTC3.sendLstName(LastName);
		
		// Enter Valid credentials in Website textbox
		ErrorMsgUsrCreationPOMTC3.sendWebsite(WebSite);
		
		// Click on Show Password button &  Enter Valid credentials in Password textbox
		ErrorMsgUsrCreationPOMTC3.clickShwPwd();
		Thread.sleep(2000);
		ErrorMsgUsrCreationPOMTC3.sendPasswd(Password);
		Thread.sleep(2000);
		
		// Click on Role list box &  Select Valid credentials in Role list box
	    WebElement dropDownArrow = driver.findElement(By.id("role"));
	    dropDownArrow.click();
		Thread.sleep(2000);
		Select dropdown = new Select(driver.findElement(By.id("role")));
		dropdown.selectByVisibleText(Role);
		WebDriverWait wait = new WebDriverWait(driver, 50);
		
		// If weak password Entered, it will capture the newly created checkbox/field
		String CheckbxDtls = driver.findElement(By.xpath("//*[@id=\"createuser\"]/table/tbody/tr[8]/td/label")).getText();
		String ActualMsg = ("Confirm use of weak password");
		Thread.sleep(2000);

		
		// Main 'If' loop  from the nested 'If-Else' will work on weak password  user created with Invalid data, will capture the error message followed by assertion.
		if (CheckbxDtls.equals(ActualMsg))
		{
			driver.findElement(By.name("pw_weak")).click();
			Thread.sleep(2000);
			ErrorMsgUsrCreationPOMTC3.clikaddusr();
			
			assertEquals(CheckbxDtls, ActualMsg);

			wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("error")));
			String PublishMsg1 = driver.findElement(By.className("error")).getText();
		    boolean ErrFound = PublishMsg1.contains("ERROR");
		    if (ErrFound)
		    {
				System.out.println("Displayed Error message for Username '" +UserName+ "' is: " +PublishMsg1);

				System.out.println("PLEASE REFER THE ABOVE ERROR AND PROVIDE VALID DATA FOR '" +UserName+ "'");
				
			    assertTrue(PublishMsg1.contains("ERROR"));

		    }
		    else 
		    {
				System.out.println("Please raise a defect as Error Message is not getting displayed with Invalid data '" +UserName+ "'");
	
		    }

		}
		
		//Main 'Else' from the nested 'If-Else' loop will work on Strong password user created with Invalid data, will capture the error message followed by assertion.
		else
		{
			ErrorMsgUsrCreationPOMTC3.clikaddusr();
			Thread.sleep(4000);

			String PublishMsg1 = driver.findElement(By.className("error")).getText();
		    boolean ErrFound = PublishMsg1.contains("ERROR");
		    if (ErrFound)
		    {
				System.out.println("Displayed Error message for Username '" +UserName+ "' is: " +PublishMsg1);

				System.out.println("PLEASE REFER THE ABOVE ERROR AND PROVIDE VALID DATA FOR '" +UserName+ "'");
				
			    assertTrue(PublishMsg1.contains("ERROR"));

		    }
		    else 
		    {
				System.out.println("Please raise a defect as Error Message is not getting displayed with Invalid data '" +UserName+ "'");
	
		    }

		}

	}
}