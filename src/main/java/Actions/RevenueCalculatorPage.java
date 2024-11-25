package Actions;



import java.time.Duration;

import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RevenueCalculatorPage {
    WebDriver driver;

    public RevenueCalculatorPage(WebDriver driver) {
        this.driver = driver;
    }

    // Locators
  //  By slider = By.xpath("//span[@class='MuiSlider-root MuiSlider-colorPrimary MuiSlider-sizeMedium css-16i48op']");
    By sliderTextField = By.xpath("//input[@class='MuiInputBase-input MuiOutlinedInput-input MuiInputBase-inputSizeSmall css-1o6z5ng'and @type=\"number\"]");
    By cptCode99091 = By.xpath("//p[text()='CPT-99091']/ancestor::div[@class='MuiBox-root css-1eynrej']//input[@class='PrivateSwitchBase-input css-1m9pwf3']");
    By cptCode99453 = By.xpath("//p[text()='CPT-99453']/ancestor::div[@class='MuiBox-root css-1eynrej']//input[@class='PrivateSwitchBase-input css-1m9pwf3']");
    By cptCode99454 = By.xpath("//p[text()='CPT-99454']/ancestor::div[@class='MuiBox-root css-1eynrej']//input[@class='PrivateSwitchBase-input css-1m9pwf3']");
    By cptCode99474 = By.xpath("//p[text()='CPT-99474']/ancestor::div[@class='MuiBox-root css-1eynrej']//input[@class='PrivateSwitchBase-input css-1m9pwf3']");
    By totalReimbursement = By.xpath("//div[@class='MuiToolbar-root MuiToolbar-gutters MuiToolbar-regular css-1lnu3ao']//p[@class='MuiToolbar-root MuiToolbar-gutters MuiToolbar-regular css-1lnu3ao']");

    // Scroll to slider
    

    // Adjust slider
    public void adjustSlider(String value) {
        WebElement textField = driver.findElement(sliderTextField);
        textField.clear();
        textField.sendKeys(value);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.attributeToBe(textField, "value", value));
    }

    // Validate slider value
    public boolean validateSliderValue(String expectedValue) {
        WebElement textField = driver.findElement(sliderTextField);
        return textField.getAttribute("value").equals(expectedValue);
    }

    // Select CPT Codes
    public void selectCptCodes() {
        driver.findElement(cptCode99091).click();
        driver.findElement(cptCode99453).click();
        driver.findElement(cptCode99454).click();
        driver.findElement(cptCode99474).click();
    }

    // Validate total reimbursement
    public boolean validateTotalReimbursement(String expectedValue) {
        String actualValue = driver.findElement(totalReimbursement).getText();
        return actualValue.contains(expectedValue);
    }
}