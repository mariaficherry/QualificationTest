package pageObjects;


import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;


public class SignInPage extends PageObject{
	
	@AndroidFindBy(id="com.sourcey.materialloginexample:id/input_email")
	private WebElement email;
	@AndroidFindBy(id="com.sourcey.materialloginexample:id/input_password")
	private WebElement password;
	@AndroidFindBy(id="com.sourcey.materialloginexample:id/btn_login")
	private WebElement submitButton;
	@AndroidFindBy(xpath = "//android.widget.Button[@resource-id=\"android:id/button1\"]")
	private WebElement okBtn;

	public SignInPage(AndroidDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	public void enterUserName(String emailVal) {
		this.email.clear();
	    this.email.sendKeys(emailVal);
	}
	
	public void enterPassword(String passwordVal) {
		this.password.sendKeys(passwordVal);

	}
	
	public void clickSubmitButton() {
		this.submitButton.click();
	}
	
	public boolean isInitialized() {
		return email.isDisplayed();
	}

	public void closeAlert() {
		okBtn.click();
	}
}
