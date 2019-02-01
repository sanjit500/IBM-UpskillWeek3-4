
package com.training.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ErrorMsgUsrCreationPOMTC3 
{
	private WebDriver driver;
	
	public ErrorMsgUsrCreationPOMTC3(WebDriver driver) 
	{
		this.driver = driver; 
		PageFactory.initElements(driver, this);

	}
	@FindBy(xpath="//*[@id=\"responsive\"]/li[7]/a")
	private WebElement signin;
		
	
	@FindBy(id="user_login")
	private WebElement userID; 
	
	@FindBy(id="user_pass")
	private WebElement UserPswd;
	
	@FindBy(name="login")
	private WebElement loginBtn; 
	
	@FindBy(xpath="//*[@id=\"menu-users\"]/a/div[2]")
	private WebElement Users;
	
	@FindBy(xpath="//*[@id=\"wpbody-content\"]/div[3]/a")
	private WebElement clickAddNewLink;
	
	@FindBy(id="user_login")
	private WebElement Username;
	
	@FindBy(id="email")
	private WebElement Email;
	
	@FindBy(id="first_name")
	private WebElement Firstname;
	
	@FindBy(id="last_name")
	private WebElement Lastname;
	
	@FindBy(id="url")
	private WebElement Website;
	
	@FindBy(xpath="//*[@id=\"createuser\"]/table/tbody/tr[6]/td/button")
	private WebElement ShwPasswd;
	
	@FindBy(id="pass1-text")
	private WebElement Passwd;
	
	@FindBy(id="createusersub")
	private WebElement ClkAddUser;
	

	public void clickSignInBtn() {
		this.signin.click(); 
	}
	
	public void sendUserID(String UserID) {
		this.userID.clear();
		this.userID.sendKeys(UserID);
	}
	public void sendpasswd(String userPaswd) {
		this.UserPswd.clear();
		this.UserPswd.sendKeys(userPaswd);
	}
	public void clickLogInBtn() {
		this.loginBtn.click(); 
	}
	
	public void clickUsersBtn() {
		this.Users.click(); 
	}
	
	public void clickAddNwBtn() {
		this.clickAddNewLink.click(); 
	}
	public void sendUserName(String UserName) {
		this.Username.clear();
		this.Username.sendKeys(UserName);
	}
	
	public void sendEmail(String EMail) {
		this.Email.clear();
		this.Email.sendKeys(EMail);
	}
	public void sendFstName(String FirstName) {
		this.Firstname.clear();
		this.Firstname.sendKeys(FirstName);
	}
	public void sendLstName(String LastName) {
		this.Lastname.clear(); 
		this.Lastname.sendKeys(LastName); 
	}
	public void sendWebsite(String WebSite) {
		this.Website.clear(); 
		this.Website.sendKeys(WebSite); 
	}
	public void clickShwPwd() {
		this.ShwPasswd.click(); 
	}
	public void sendPasswd(String Password) {
		this.Passwd.clear(); 
		this.Passwd.sendKeys(Password); 
	}
	public void clikaddusr() {
		this.ClkAddUser.click(); 
	}
	
}