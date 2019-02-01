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
import com.training.pom.AddUseDB_TC2_POM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class AddUserDB_TC2 {
	private WebDriver driver;
	private String baseUrl;
	private AddUseDB_TC2_POM AddUseDB_TC2_POM;
	private static Properties properties;
	private ScreenShot screenShot;
	
	// To Verify whether application allows admin to add new user & details get captured from database
	@BeforeClass
	public static void setUpBeforeClass() throws IOException {
		properties = new Properties();
		FileInputStream inStream = new FileInputStream("./resources/others.properties");
		properties.load(inStream);
	}

	@BeforeMethod
	public void setUp() throws Exception {
		// Pre Condition: user should have launched the application by entering valid URL admin should be logged in
		driver = DriverFactory.getDriver(DriverNames.CHROME);
		AddUseDB_TC2_POM = new AddUseDB_TC2_POM(driver);
		baseUrl = properties.getProperty("baseURL");
		screenShot = new ScreenShot(driver);
		// open the browser
		driver.get(baseUrl);
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"responsive\"]/li[7]/a")));
		AddUseDB_TC2_POM.clickSignInBtn(); 
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("login")));
		AddUseDB_TC2_POM.sendUserID("admin");
		AddUseDB_TC2_POM.sendpasswd("admin@123");
		AddUseDB_TC2_POM.clickLogInBtn();
		AddUseDB_TC2_POM.clickUsersBtn();
		AddUseDB_TC2_POM.clickAddNwBtn();
	}

	@AfterMethod
	public void tearDown() throws Exception {
	driver.quit();
	}

	// Input data is from MYSQL database installed on Windows 7 system.
	//Files used to capture & format the data: AddUseDb_TC2Bean, AddUserDBDAO, LoginDataProviders, DB Properties file, SQL properties file.
	@Test(dataProvider = "db-inputs", dataProviderClass = LoginDataProviders.class)
	public void loginDBTest(String username, String email, String firstName, String lastName, String website, String password, String role) throws InterruptedException {
		
		// Enter Valid credentials in Username textbox
		AddUseDB_TC2_POM.sendUserName(username);
		
		// Enter Valid credentials in Email textbox
		AddUseDB_TC2_POM.sendEmail(email);
		
		// Enter Valid credentials in First Name textbox
		AddUseDB_TC2_POM.sendFstName(firstName);
		
		// Enter Valid credentials in Last Name textbox
		AddUseDB_TC2_POM.sendLstName(lastName);
		
		// Enter Valid credentials in Website textbox
		AddUseDB_TC2_POM.sendWebsite(website);
		
		// Click on Show Password button
		AddUseDB_TC2_POM.clickShwPwd();
		Thread.sleep(2000);
		
		// Enter Valid credentials in Password textbox
		AddUseDB_TC2_POM.sendPasswd(password);
		Thread.sleep(2000);
		
	   //  Click on Role list box &  Select Valid credentials in Role list box
	    WebElement dropDownArrow = driver.findElement(By.id("role"));
	    dropDownArrow.click();
		Thread.sleep(2000);
		Select dropdown = new Select(driver.findElement(By.id("role")));
		dropdown.selectByVisibleText(role);
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
			AddUseDB_TC2_POM.clikaddusr();
			Thread.sleep(2000);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"wpbody-content\"]/div[3]/a")));
			String UsrCrtdMsg = ("New user created. Edit user");
			String PublishMsg = driver.findElement(By.xpath("//*[@id=\"message\"]/p")).getText();
					
					//Below if loop is to assert if the user has been created successfully or not.
					if (UsrCrtdMsg.equals(PublishMsg))
					System.out.println("User has been created with a Weak Password: " +username);
					assertEquals(UsrCrtdMsg, PublishMsg);
		}
		
		//Else loop is used to handle the user created with strong password
		else
		{
			AddUseDB_TC2_POM.clikaddusr();
			String UsrCrtdMsg = ("New user created. Edit user");
			String PublishMsg = driver.findElement(By.xpath("//*[@id=\"message\"]/p")).getText();
					
					//Below if loop is to assert if the user has been created successfully or not.
					if (UsrCrtdMsg.equals(PublishMsg))
					Thread.sleep(2000);
					System.out.println("User has been created with a strong Password: " +username);
					assertEquals(UsrCrtdMsg, PublishMsg);
		}
		
		// To find the newly created user in User List to verify if the user has been added successfully or not. Followed by an assertion.
		AddUseDB_TC2_POM.SearchBox(username);
		AddUseDB_TC2_POM.clikSrchBtn();
		Thread.sleep(4000);
		String UsrNm = driver.findElement(By.xpath("//tbody/tr/td/strong")).getText();
		String ActUsrNm = (username);
		if (UsrNm.equals(ActUsrNm))
		{
			System.out.println("User is being displayed in users List: " +username);
		}
		assertEquals(UsrNm, ActUsrNm);

	}

}