package tools;

import io.qameta.allure.Attachment;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import pages.HomePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import java.util.concurrent.TimeUnit;

public abstract class TestRunner {

    protected WebDriver driver;

    @BeforeClass
    public void setUp() {

        System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @BeforeMethod
    public void beforeMethod() {
        driver.get("http://ec2-18-219-238-40.us-east-2.compute.amazonaws.com/en/");
    }

    protected HomePage loadApplication() {
        return new HomePage(driver);
    }

    protected void delayExplicitExecution(WebElement webElement) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(webElement));
    }

    protected void delayExecution(long miliseconds) {
        try {
            Thread.sleep(miliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    @Attachment(value = "Web Page Screenshot", type = "image/png")
    public byte[] takeScreenshot() {
        // Take a screenshot as byte array and return
        return ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
    }



    @AfterMethod
    public void afterMethod(ITestResult testResult) {
        if (!testResult.isSuccess()) {
            takeScreenshot();
        }
    }

    @AfterClass//(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }

}

