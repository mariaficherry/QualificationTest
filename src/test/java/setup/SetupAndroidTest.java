package setup;

import java.net.MalformedURLException;
import java.net.URL;

import browserstack.shaded.org.json.JSONObject;
import io.appium.java_client.android.options.UiAutomator2Options;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.JavascriptExecutor;

public class SetupAndroidTest {
    public static AndroidDriver driver;

    public static void setupDriver(String testName) throws MalformedURLException {
//        UiAutomator2Options options = new UiAutomator2Options();
//        options.setCapability("deviceName", "emulator-5554");
//        options.setCapability("app", "C:/Users/m.molina/Desktop/AppiumProject/appiumTests/MaterialLoginExample.apk");
//        options.setCapability("newCommandTimeout", 60);
//        options.setCapability("automationName", "UiAutomator2");
//        options.setCapability("platformVersion", "15");
//        options.setCapability("appium:fullReset", true);
//        options.setCapability("appium:noReset", false);
//        URL appiumServerURL = new URL("http://127.0.0.1:4723");
//
//        // Initialize AndroidDriver (only once)
//        driver = new AndroidDriver(appiumServerURL, options);

        ConfigReader config = new ConfigReader("browserstack.yml");
        String userName = config.get("userName");
        String accessKey = config.get("accessKey");

        UiAutomator2Options options = new UiAutomator2Options();
        options.setCapability("userName", userName);
        options.setCapability("accessKey", accessKey);
        options.setCapability("deviceName", config.getList("platforms").get(0).get("deviceName"));
        options.setCapability("osVersion", config.getList("platforms").get(0).get("osVersion"));
        options.setCapability("app", config.getList("platforms").get(0).get("app"));

// Add BrowserStackLocal if enabled
        // options.setCapability("browserstack.local", "true");

// Initialize driver
        driver = new AndroidDriver(new URL("https://"+userName+":"+accessKey+"@hub-cloud.browserstack.com/wd/hub"), options);

        //Set test name
        setTestName(testName);
    }

    public static void setTestName(String testName) {
        if (driver == null) {
            throw new IllegalStateException("Driver not initialized. Cannot set test name.");
        }
        final JavascriptExecutor jse = (JavascriptExecutor) driver;
        JSONObject executorObject = new JSONObject();
        JSONObject argumentsObject = new JSONObject();
        argumentsObject.put("name", testName);
        executorObject.put("action", "setSessionName");
        executorObject.put("arguments", argumentsObject);
        jse.executeScript(String.format("browserstack_executor: %s", executorObject));
    }

    public static void tearDown() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
