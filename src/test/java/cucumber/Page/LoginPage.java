package cucumber.Page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasePage {
    WebDriver driver;



    // Локатор для поля ввода email
    @FindBy(xpath = "//input[@formcontrolname='email']")
    private WebElement loginElement;

    // Локатор для поля ввода пароля
    @FindBy(xpath = "//input[@formcontrolname='password']")
    private WebElement passwordElement;

    // Локатор для кнопки входа
    @FindBy(xpath = "//*[text()='Sign In']")
    private WebElement signInButton;

    public LoginPage(WebDriver driver){
        super(driver);
        this.driver= driver;

        PageFactory.initElements(driver, this);
    }




    public void setEmail(String email){
        waitForElementToBeVisible(loginElement);
        loginElement.sendKeys(email);
    }

    public void setPassword(String pass){
        waitForElementToBeVisible(passwordElement);
        passwordElement.sendKeys(pass);
    }

    public void sign_in(){
        waitForElementToBeVisible(signInButton);
        signInButton.click();
    }
}
