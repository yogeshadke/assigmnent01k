package Wrapper;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class BrowserFactory {
    public static WebDriver setBrowser(String browser, boolean headless) {
        if ("chrome".equalsIgnoreCase(browser)) {
            System.setProperty("webdriver.chrome.driver", "/Users/sam/Downloads");
            ChromeOptions options = new ChromeOptions();
            if (headless) options.addArguments("--headless");
            options.addArguments("--disable-gpu", "--disable-extensions", "--start-maximized");
            return new ChromeDriver(options);
        }
        throw new IllegalArgumentException("Unsupported browser: " + browser);
    }
}