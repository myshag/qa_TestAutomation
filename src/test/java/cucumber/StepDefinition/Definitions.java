package cucumber.StepDefinition;

import cucumber.Page.LoginPage;
import cucumber.Page.UserManagement;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;



public class Definitions {
    private final String URL = "http://ask-qa.portnov.com/";
    private final WebDriver driver;
    LoginPage loginPage;
    UserManagement userManagement;

    public void addPause(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public Definitions() {
        driver = Hooks.getDriver();
    }


    @Given("I am on the login page")
    public void I_am_on_login_page() {
        driver.get(URL);

    }

    @When("I log in with email and password")
    public void I_log_in() throws InterruptedException {
        //driver.get(URL);
        loginPage = new LoginPage(driver);
        loginPage.setEmail("qa.sofi@gmail.com");
        loginPage.setPassword("ABC123");
        loginPage.sign_in();

    }

    @And("I navigate to 'User Management'")
    public void navigateToSection() throws InterruptedException {
       // addPause(5);
       driver.get("http://ask-qa.portnov.com/#/users-management");

        userManagement = new UserManagement(driver);

    }

    @And("I click on user with name {string}")
    public void selectUserWithName(String oldName) {
        // Code to select a user by name
        userManagement.openUserDialog(oldName);
    }

    @When("I click 'Options'")
    public void clickButton() {
        // Code to click a button or link with the given name
        userManagement.openOptionMenu();
        addPause(5);
        userManagement.changeUserDialog();
    }

    @And("I type as the new full name {string}")
    public void typeNewName(String newName) {
        // Code to input the new name in the relevant field
       userManagement.changeUserName(newName);
        addPause(5);
    }

    @When("I confirm the name change")
    public void confirmNameChange() {
        // Code to confirm the name change (e.g., clicking Save)
        //userManagementPage.confirmNameChange();
    }

    @Then("the user's full name should be updated to {string}")
    public void verifyUserNameChange(String newName) {
        // Assertion to verify that the user's name has been updated
        Assert.assertEquals("User's name was not updated", userManagement.getName(), newName);
    }


}
