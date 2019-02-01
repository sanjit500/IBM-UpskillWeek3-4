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
import com.training.pom.AddMultiUsersTC1POM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class AddMultiUsersTC1 {
	private WebDriver driver;
	private String baseUrl;
	private AddMultiUsersTC1POM AddMultiUsersTC1POM;
	private static Properties properties;
	private ScreenShot screenShot;
//To Verify whether application allows admin to add multiple users for different roles
	
	@BeforeClass
	public static void setUpBeforeClass() throws IOException {
		properties = new Properties();
		FileInputStream inStream = new FileInputStream("./resources/others.properties");
		properties.load(inStream);
	}

	@BeforeMethod
	public void setUp() throws Exception {
		//Pre Condition: User should have launched the application by entering valid URL admin should be logged in
		driver = DriverFactory.getDriver(DriverNames.CHROME);
		AddMultiUsersTC1POM = new AddMultiUsersTC1POM(driver);
		baseUrl = properties.getProperty("baseURL");
		screenShot = new ScreenShot(driver);
		// open the browser
		driver.get(baseUrl);
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"responsive\"]/li[7]/a")));
		AddMultiUsersTC1POM.clickSignInBtn(); 
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("login")));
		AddMultiUsersTC1POM.sendUserID("admin");
		AddMultiUsersTC1POM.sendpasswd("admin@123");
		AddMultiUsersTC1POM.clickLogInBtn();
		AddMultiUsersTC1POM.clickUsersBtn();
		AddMultiUsersTC1POM.clickAddNwBtn();
	}

	@AfterMethod
	public void tearDown() throws Exception {
	driver.quit();
	}

	//Takes data from excel sheet. 
	//Files used to capture and format data: Apache POI, LoginDataProviders, others.proerties
	@Test(dataProvider = "excel-inputs", dataProviderClass = LoginDataProviders.class)
	public void loginExlTest(String UserName, String EMail, String FirstName, String LastName, String WebSite, String Password, String Role) throws InterruptedException {
		
		// Enter Valid credentials in Username textbox
		AddMultiUsersTC1POM.sendUserName(UserName);
		
		// Enter Valid credentials in Email textbox
		AddMultiUsersTC1POM.sendEmail(EMail);
		// Enter Valid credentials in First Name textbox
		AddMultiUsersTC1POM.sendFstName(FirstName);
		// Enter Valid credentials in Last Name textbox
		AddMultiUsersTC1POM.sendLstName(LastName);
		// Enter Valid credentials in Website textbox
		AddMultiUsersTC1POM.sendWebsite(WebSite);
		// Click on Show Password button
		AddMultiUsersTC1POM.clickShwPwd();
		Thread.sleep(2000);
		// Enter Valid credentials in Password textbox
		AddMultiUsersTC1POM.sendPasswd(Password);
		Thread.sleep(2000);
		
	   //  Roles should get displayed in Role list box & Click on Role list box
	    WebElement dropDownArrow = driver.findElement(By.id("role"));
	    dropDownArrow.click();
		Thread.sleep(2000);
		Select dropdown = new Select(driver.findElement(By.id("role")));
		dropdown.selectByVisibleText(Role);
		WebDriverWait wait = new WebDriverWait(driver, 50);
		
		// If weak password Enetered, it will capture the newly created checkbox/field
		String CheckbxDtls = driver.findElement(By.xpath("//*[@id=\"createuser\"]/table/tbody/tr[8]/td/label")).getText();
		String ActualMsg = ("Confirm use of weak password");
		Thread.sleep(2000);

		
		// Below If loop is used to handle the users created with weak password
		if (CheckbxDtls.equals(ActualMsg))
		{
			driver.findElement(By.name("pw_weak")).click();
			Thread.sleep(2000);
			AddMultiUsersTC1POM.clikaddusr();
			Thread.sleep(2000);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"wpbody-content\"]/div[3]/a")));
			String UsrCrtdMsg = ("New user created. Edit user");
			String PublishMsg = driver.findElement(By.xpath("//*[@id=\"message\"]/p")).getText();
					
					//Below if loop is to assert if the user has been created successfully or not.
					if (UsrCrtdMsg.equals(PublishMsg))
					System.out.println("User has been created with a Weak Password: " +UserName);
					assertEquals(UsrCrtdMsg, PublishMsg);
		}
		
		//Else loop is used to handle the user created with strong password
		else
		{
			AddMultiUsersTC1POM.clikaddusr();
			String UsrCrtdMsg = ("New user created. Edit user");
			String PublishMsg = driver.findElement(By.xpath("//*[@id=\"message\"]/p")).getText();
				
					//Below if loop is to assert if the user has been created successfully or not.
					if (UsrCrtdMsg.equals(PublishMsg))
					Thread.sleep(2000);
					System.out.println("User has been created with a strong Password: " +UserName);
					assertEquals(UsrCrtdMsg, PublishMsg);
		}
		
		// To find the newly created user in User List to verify if the user has been added successfully or not. Followed by an assertion.
		AddMultiUsersTC1POM.SearchBox(UserName);
		AddMultiUsersTC1POM.clikSrchBtn();
		Thread.sleep(4000);
		String UsrNm = driver.findElement(By.xpath("//tbody/tr/td/strong")).getText();
		String ActUsrNm = (UserName);
		if (UsrNm.equals(ActUsrNm))
		{
			System.out.println("User is being displayed in users List: " +UserName);
		}
		assertEquals(UsrNm, ActUsrNm);

	}

}