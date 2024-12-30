package cucumber.Page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class UserManagement extends BasePage {

    private final WebDriver driver;

    @FindBy(xpath = "//*[text()[contains(.,'Options')]]")
    private WebElement optionsButton;

    @FindBy(xpath = "//*[text()[contains(.,'Change User')]][1]")
    private WebElement changeUserNameButton;

    @FindBy(xpath = "//input[@id='mat-input-2']")
    private WebElement userNameInput;

    @FindBy(xpath = "//span[text()[contains(.,'Change')]]")
    private WebElement buttonChange;

    @FindBy(xpath= "//h2[@_ngcontent-c21='']")
    private WebElement nameHeader;

    public UserManagement(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this); // Инициализация аннотаций @FindBy
    }

    public void openUserDialog(String user) {

        String xPath = "//*[text()[contains(.," + "'" + user + "')]][1]";
        WebElement userListElement = driver.findElement(By.xpath(xPath)); // Динамический элемент
        waitForElementToBeClickable(userListElement);
        userListElement.click();
    }

    public void openOptionMenu() {
        waitForElementToBeVisible(optionsButton);
        optionsButton.click();
    }

    public void changeUserDialog() {
        waitForElementToBeVisible(changeUserNameButton);
        changeUserNameButton.click();
    }

    public void changeUserName(String newName) {
        waitForElementToBeVisible(userNameInput);
        userNameInput.clear(); // Очищаем поле перед вводом
        userNameInput.sendKeys(newName);
        buttonChange.click();
    }

    public String getName() {

        return  nameHeader.getText().replace("school", "").trim();
    }
}