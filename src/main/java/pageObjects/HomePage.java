package pageObjects;

import org.openqa.selenium.WebElement; // Use WebElement instead of MobileElement
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage extends PageObject {

    public HomePage(AndroidDriver driver) {
        super(driver);
    }

    @AndroidFindBy(id = "com.sourcey.materialloginexample:id/action_bar")
    private WebElement actionBar;
    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"Hello world!\"]")
    private WebElement helloWorldMsg;

    public boolean isInitialized() {
        return actionBar.isDisplayed();
    }

    public boolean isHomePageMessageDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Set timeout
        WebElement element = wait.until(ExpectedConditions.visibilityOf(helloWorldMsg));
        return element.isDisplayed();    }
}