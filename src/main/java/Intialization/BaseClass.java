package Intialization;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Optional;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import Wrappers.BrowserFactory;
import Wrappers.WebCommonPath;
import dataSources.PropertiesReader;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class BaseClass {
    private static final String DEFAULT_ENV = "Live";
    private static final String DEFAULT_BROWSER = "chrome";
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();
    private static ExtentReports report;
    private static String environment;

    @BeforeSuite(alwaysRun = true)
    public void beforeSuite(ITestContext context) {
        setupExtentReports();
        environment = Optional.ofNullable(context.getSuite().getParameter("env")).orElse(DEFAULT_ENV);
    }

    private void setupExtentReports() {
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter(WebCommonPath.extentReportPath);
        sparkReporter.config().setTheme(Theme.STANDARD);
        sparkReporter.config().setDocumentTitle("Automation Report");
        sparkReporter.config().setReportName("Test Report");

        report = new ExtentReports();
        report.attachReporter(sparkReporter);
        report.setSystemInfo("Tester", "Yogesh");
        report.setSystemInfo("Environment", environment);
    }

    @BeforeMethod(alwaysRun = true)
    public void beforeEachMethod(Method testMethod, ITestContext context) throws IOException {
        WebDriver browserDriver = BrowserFactory.setBrowser(DEFAULT_BROWSER, false);
        driver.set(browserDriver);
        driver.get().get(PropertiesReader.getPropertyValue(WebCommonPath.testDatafile1, environment + "_url"));
        driver.get().manage().window().maximize();

        ExtentTest test = report.createTest(testMethod.getName()).assignAuthor("QA").assignDevice("Windows");
        extentTest.set(test);
    }

    @AfterMethod(alwaysRun = true)
    public void afterEachMethod(ITestResult result) {
        ExtentTest logger = extentTest.get();
        if (result.getStatus() == ITestResult.FAILURE) {
            logger.log(Status.FAIL, result.getThrowable());
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            logger.log(Status.PASS, "Test passed");
        }
        //driver.get().quit();
        driver.remove();
    }

    @AfterSuite(alwaysRun = true)
    public void afterSuite() {
        report.flush();
    }

    public static WebDriver getDriver() {
        return driver.get();
    }
}