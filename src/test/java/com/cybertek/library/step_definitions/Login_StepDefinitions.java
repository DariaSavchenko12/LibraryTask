package com.cybertek.library.step_definitions;

import com.cybertek.library.pages.LandingPage;
import com.cybertek.library.pages.LoginPage;
import com.cybertek.library.utilities.BrowserUtils;
import com.cybertek.library.utilities.ConfigurationReader;
import com.cybertek.library.utilities.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Login_StepDefinitions {

    LoginPage loginPage = new LoginPage();
    LandingPage landingPage = new LandingPage();
    WebDriverWait wait = new WebDriverWait(Driver.getDriver(), 10);

    @Given("I am on the login page")
    public void i_am_on_the_login_page() {
        String url = ConfigurationReader.getProperty("qa2_url");
        Driver.getDriver().get(url);
    }

    @When("I login as a librarian")
    public void i_login_as_a_librarian() {
        String username = ConfigurationReader.getProperty("lib22_user");
        String password = ConfigurationReader.getProperty("lib22_pass");

        loginPage.usernameInput.sendKeys(username);
        loginPage.passwordInput.sendKeys(password);
        loginPage.signInButton.click();

    }

    @Then("dashboard should be displayed")
    public void dashboard_should_be_displayed() throws InterruptedException{
        String expected = "dashboard";

        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), 10);
        wait.until(ExpectedConditions.urlContains(expected));

        String actual = Driver.getDriver().getCurrentUrl();
        Assert.assertTrue(actual.contains(expected));

        Driver.closeDriver();
    }

    @When("I login as a student")
    public void iLoginAsAStudent() {
        String username = ConfigurationReader.getProperty("student95_user");
        String password = ConfigurationReader.getProperty("student95_pass");

        loginPage.usernameInput.sendKeys(username);
        loginPage.passwordInput.sendKeys(password);
        loginPage.signInButton.click();
    }

    @Then("books should be displayed")
    public void booksShouldBeDisplayed() {
        String expected = "books";


        wait.until(ExpectedConditions.urlContains(expected));

        String actual = Driver.getDriver().getCurrentUrl();

        Assert.assertTrue(actual.contains(expected));

        Driver.closeDriver();
    }

    @When("I enter username {string}")
    public void i_enter_username(String string1) {
        String username = string1;
        loginPage.usernameInput.sendKeys(username);
    }

    @When("I enter password {string}")
    public void i_enter_password(String string2) {
        String password = string2;
        loginPage.passwordInput.sendKeys(password);
    }
    @When("click the sign in button")
    public void click_the_sign_in_button() {
        loginPage.signInButton.click();
    }
    @Then("there should be {int} users")
    public void there_should_be_users(Integer int1) {
        int expectedUserNumber = int1; //667
        wait.until(ExpectedConditions.visibilityOf(landingPage.userCount));

        String expected = String.valueOf(expectedUserNumber);
        String actual = landingPage.userCount.getText();

        Assert.assertEquals("Actual user number is not as expected!", actual, expected);
    }


    @When("I login using {string} and {string}")
    public void iLoginUsingAnd(String username, String password) {

        loginPage.usernameInput.sendKeys(username);
        loginPage.passwordInput.sendKeys(password);
        loginPage.signInButton.click();

    }

    @Then("account holder name should be {string}")
    public void accountHolderNameShouldBe(String expectedAccountUsername) {

        BrowserUtils.waitForVisibility(landingPage.accountUsername, 5);

        String actualAccountUsername = landingPage.accountUsername.getText();

        Assert.assertEquals("Account username is not as expected!"
                , expectedAccountUsername, actualAccountUsername);

        Driver.closeDriver();
    }
}
