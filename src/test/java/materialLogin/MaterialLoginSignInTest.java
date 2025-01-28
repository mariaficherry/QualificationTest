package materialLogin;

import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Allure;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.SignInPage;
import setup.SetupAndroidTest;

public class MaterialLoginSignInTest extends SetupAndroidTest {
    @DataProvider(name = "testDataForSignInTest")
    public Object[][] getDataForSignInTest() {
        return new Object[][]{{"abcd@gmail.com", "abcdefghi"},};
    }

    @BeforeMethod
    public void setUp() throws Exception {
        // Set up the driver before each test
        Allure.step("Setup");
        SetupAndroidTest.setupDriver("Login Android Test");

        // SignInPage signInPage = new SignInPage(driver);
        // signInPage.closeAlert();
    }

    @AfterMethod
    public static void tearDown() {
        // Clean up the driver after each test
        Allure.step("Quit app");
        SetupAndroidTest.tearDown();
    }

    @Test(groups = {"signin", "critical"}, dataProvider = "testDataForSignInTest")
    public void signInTest(String email, String password) throws InterruptedException {
        // Initialize the driver
        AndroidDriver driver = SetupAndroidTest.driver;


        SignInPage signInPage = new SignInPage(driver);

        Allure.step("Enter username");
        signInPage.enterUserName(email);
        System.out.println(email);
        Allure.step("Enter password");
        signInPage.enterPassword(password);
        System.out.println(password);
        signInPage.clickSubmitButton();

        // validation that home page is displayed after sign in.
        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.isHomePageMessageDisplayed());
    }
}
