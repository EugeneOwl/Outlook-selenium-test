package base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.Properties;

public class LoginService {

    private WebDriver driver;
    private WebDriverWait webDriverWait;
    private Properties properties;

    protected LoginService(WebDriver driver, WebDriverWait webDriverWait, Properties properties) {
        this.driver = driver;
        this.webDriverWait = webDriverWait;
        this.properties = properties;
    }

    public void logIn(String login, String password) {
        String loginInputId = properties.getProperty("login.input.id");
        String loginSubmitId = properties.getProperty("login.submit.id");

        String passwordInputId = properties.getProperty("password.input.id");
        String passwordSubmitId = properties.getProperty("password.submit.id");

        driver.findElement(By.id(loginInputId)).sendKeys(login);
        driver.findElement(By.id(loginSubmitId)).click();

        waiForElementToLoad(By.id(passwordInputId));
        driver.findElement(By.id(passwordInputId)).sendKeys(password);
        driver.findElement(By.id(passwordSubmitId)).click();
    }

    public void logOut() {
        String upperRightIconClass = properties.getProperty("upper.right.icon.class");
        String logoutButtonClass = properties.getProperty("logout.button.class");
        int logoutButtonPosition = Integer.parseInt(properties.getProperty("logout.button.position"));

        driver.findElement(By.className(upperRightIconClass)).click();

        waiForElementsToLoad(By.className(logoutButtonClass));
        getElementByClassName(logoutButtonClass, logoutButtonPosition).click();
    }

    private WebElement getElementByClassName(String className, int number) {
        return new ArrayList<>(driver.findElements(By.className(className))).get(number);
    }

    private void waiForElementToLoad(By elementBy) {
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(elementBy));
    }

    private void waiForElementsToLoad(By elementBy) {
        webDriverWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(elementBy));
    }
}
