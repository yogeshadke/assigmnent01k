package Test;


import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Intialization.BaseClass;
import Actions.FitPeoHomePage;
import Actions.RevenueCalculatorPage;

public class FitPeoAutomationTest extends BaseClass {
    private FitPeoHomePage homePage;
    private RevenueCalculatorPage calculatorPage;

    @BeforeMethod(alwaysRun = true)
    public void setup() {
        // Initialize page objects after the WebDriver is set up
        homePage = new FitPeoHomePage(getDriver());
        calculatorPage = new RevenueCalculatorPage(getDriver());
    }

    @Test
    public void testRevenueCalculator() {
        // Step 2: Navigate to Revenue Calculator Page
        homePage.navigateToRevenueCalculator();

        // Step 3: Scroll to Slider Section
      //  calculatorPage.scrollToSlider();

        // Step 4: Adjust Slider to 820
        calculatorPage.adjustSlider("820");
        Assert.assertTrue(calculatorPage.validateSliderValue("820"), "Slider value is not updated to 820.");

        // Step 5: Update Text Field to 560
        calculatorPage.adjustSlider("560");
        Assert.assertTrue(calculatorPage.validateSliderValue("560"), "Slider value is not updated to 560.");

        // Step 7: Select CPT Codes
        calculatorPage.selectCptCodes();

        // Step 8: Validate Total Reimbursement
        Assert.assertTrue(calculatorPage.validateTotalReimbursement("$110700"), "Total Reimbursement is incorrect.");
    }
}