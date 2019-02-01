
package com.training.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddNewFeaturePOMTC4 
{
	private WebDriver driver;
	
	public AddNewFeaturePOMTC4(WebDriver driver) 
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
	
	@FindBy(xpath="//*[@id=\"menu-posts-property\"]/a/div[3]")
	private WebElement Properties;
	
	@FindBy(xpath="//*[@id=\"menu-posts-property\"]/ul/li[4]/a")
	private WebElement clickFeature;
	
	@FindBy(id="tag-name")
	private WebElement Name;
	
	@FindBy(id="tag-slug")
	private WebElement Slug;
	
	@FindBy(id="tag-description")
	private WebElement Description;
	
	@FindBy(id="submit")
	private WebElement SubmitFeature;
	
	@FindBy(id="tag-search-input")
	private WebElement SrchBox;
	
	@FindBy(id="search-submit")
	private WebElement SrchBtn;
	
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
	public void clickPropertyBtn() {
		this.Properties.click(); 
	}
	public void clickFeatureBtn() {
		this.clickFeature.click(); 
	}
	public void sendName(String Name) {
		this.Name.clear();
		this.Name.sendKeys(Name);
	}
	public void sendSlug(String Slug) {
		this.Slug.clear();
		this.Slug.sendKeys(Slug);
	}
	public void sendDescription(String Description) {
		this.Description.clear();
		this.Description.sendKeys(Description);
	}
	public void clikSubmitBtn() {
		this.SubmitFeature.click(); 
	}
	public void SearchBox(String UserName) {
		this.SrchBox.clear(); 
		this.SrchBox.sendKeys(UserName); 
	}
	public void clikSrchBtn() {
		this.SrchBtn.click(); 
	}
	
}