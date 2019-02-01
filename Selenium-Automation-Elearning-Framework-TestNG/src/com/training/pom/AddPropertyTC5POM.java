
package com.training.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddPropertyTC5POM 
{
	private WebDriver driver;
	
	public AddPropertyTC5POM(WebDriver driver) 
	{
		this.driver = driver; 
		PageFactory.initElements(driver, this);

	}
	@FindBy(xpath="//*[@id=\"responsive\"]/li[7]/a")
	private WebElement signin;
		
	
	@FindBy(id="user_login")
	private WebElement userName; 
	
	@FindBy(id="user_pass")
	private WebElement password;
	
	@FindBy(name="login")
	private WebElement loginBtn; 
	
	@FindBy(xpath="//*[@id=\"menu-posts-property\"]/a/div[3]")
	private WebElement properties;
	
	@FindBy(xpath="//*[@id=\"wpbody-content\"]/div[3]/a")
	private WebElement clickAddNewBtn;
	
	@FindBy(xpath="//*[@id=\"title\"]")
	private WebElement EnterTitle;
		
	@FindBy(xpath="//*[@id=\"content\"]")
	private WebElement EnterTextBox;
	
	@FindBy(id="_price")
	private WebElement PriceBox;
	
	@FindBy(id="_price_per")
	private WebElement PricePerSQft;
	
	@FindBy(id="ui-id-2")
	private WebElement mainDetailsTab;
	
	@FindBy(id="_status")
	private WebElement Status;
	
	@FindBy(id="_location")
	private WebElement location;
	
	@FindBy(id="_possession")
	private WebElement Posession;
	
	@FindBy(id="ui-id-3")
	private WebElement LocationTab;
	
	@FindBy(id="_friendly_address")
	private WebElement Address;
	
	@FindBy(id="_address")
	private WebElement Googleaddress2;
	
	@FindBy(id="_geolocation_lat")
	private WebElement Latitude;
	
	@FindBy(id="_geolocation_long")
	private WebElement Logitude;
	
	@FindBy(id="ui-id-4")
	private WebElement DetaisTab;
	
	@FindBy(id="_storage_room")
	private WebElement StorageRoom;
	
	@FindBy(xpath="//div/div/div[2]/div/div[2]/ul/li[3]/label/span")
	private WebElement ClickChkBox;
	
	@FindBy(xpath="//*[@id=\"property_feature-293\"]/label")
	private WebElement ClickFeatureChkBox;
	
	@FindBy(xpath="//*[@id=\"region-282\"]")
	private WebElement ClickRegionChkBox;
	
	@FindBy(id="publish")
	private WebElement ClickPublishBtn;
	
	@FindBy(xpath="//*[@id=\"wp-admin-bar-logout\"]/a")
	private WebElement ClickLogout;
	
	@FindBy(xpath="//*[@id=\"logo\"]/h2/a")
	private WebElement ClickRealEstate;
	
	@FindBy(xpath="//*[@id=\"ajaxsearchlite1\"]/div/div[3]/form/input[1]")
	private WebElement ClickSearchSpace;

	
	public void clickSignInBtn() {
		this.signin.click(); 
	}
	public void sendUserName(String userName) {
		this.userName.clear();
		this.userName.sendKeys(userName);
	}
	public void sendPassword(String password) {
		this.password.clear(); 
		this.password.sendKeys(password); 
	}
	public void clickLoginBtn() {
		this.loginBtn.click(); 
	}
	public void clickPropertiesBtn() {
		this.properties.click(); 
	}
	public void clickAddNewBtn() {
		this.clickAddNewBtn.click(); 
	}
	public void enterTitle(String titleEnter) {
		this.EnterTitle.sendKeys(titleEnter); 
	}
	public void enterTextBox(String contentEnter) {
		this.EnterTextBox.sendKeys(contentEnter); 
	}
	public void enterPriceBox(String PriceEnter) {
		this.PriceBox.sendKeys(PriceEnter); 
	}
	public void enterSqFtPrice(String SqFtPriceEnter) {
		this.PricePerSQft.sendKeys(SqFtPriceEnter); 
	}
	public void clickMainDetailsTab() {
		this.mainDetailsTab.click();
	}
	public void enterStatus(String EnterStatus) {
		this.Status.sendKeys(EnterStatus); 
	}
	public void enterLocation(String EnterLocation) {
		this.location.sendKeys(EnterLocation); 
	}
	public void enterPosession(String EnterPosession) {
		this.Posession.sendKeys(EnterPosession); 
	}
	public void clickLocationTab() {
		this.LocationTab.click();
	}
	public void enterAddress1(String Enteraddress1) {
		this.Address.sendKeys(Enteraddress1); 
	}
	public void enterGoogleAddrs(String EntergoogleAddrs) {
		this.Googleaddress2.sendKeys(EntergoogleAddrs); 
	}
	public void enterLatitue(String Enterlatitude) {
		this.Latitude.clear();
		this.Latitude.sendKeys(Enterlatitude); 
	}
	public void enterLogitude(String EnterLongitude) {
		this.Logitude.clear();
		this.Logitude.sendKeys(EnterLongitude); 
	}
	public void clickDetailsTab() {
		this.DetaisTab.click(); 
	}
	public void enterStorageRoom(String EnterStorage) {
		this.StorageRoom.sendKeys(EnterStorage); 
	}
	public void clickCBChkBox() {
		this.ClickChkBox.click();
	}
	public void clickFeaturebox() {
		this.ClickFeatureChkBox.click();
	}
	public void clickRegionBox() {
		this.ClickRegionChkBox.click();
	}
	public void clickPublishBtn() {
		this.ClickPublishBtn.click();
	}
	public void clickLogout() {
		this.ClickLogout.click();
	}
	public void clickRealEstateBtn() {
		this.ClickRealEstate.click();
	}
	public void clickAndSearchSpace(String EnterNewTitle) {
		this.ClickSearchSpace.clear();
		this.ClickSearchSpace.sendKeys(EnterNewTitle); 
	}
	
}