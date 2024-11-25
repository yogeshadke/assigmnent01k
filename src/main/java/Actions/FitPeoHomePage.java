package Actions;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FitPeoHomePage {
    WebDriver driver;

    public FitPeoHomePage(WebDriver driver) {
        this.driver = driver;
    }

    // Locator for Revenue Calculator link
    By revenueCalculatorLink = By.xpath("//div[@class='MuiToolbar-root MuiToolbar-gutters MuiToolbar-regular css-1v7ycw3']//div[contains(text(),'Revenue Calculator')]");

    // Navigate to Revenue Calculator Page
    public void navigateToRevenueCalculator() {
        driver.findElement(revenueCalculatorLink).click();
    }
}