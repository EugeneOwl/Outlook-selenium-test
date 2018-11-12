package base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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

        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id(passwordInputId)));
        driver.findElement(By.id(passwordInputId)).sendKeys(password);
        driver.findElement(By.id(passwordSubmitId)).click();
    }

    public void logOut() {
        String upperRightIconClass = properties.getProperty("upper.right.icon.class");
        String logoutButtonId = properties.getProperty("logout.button.id");

        driver.findElement(By.className(upperRightIconClass)).click();

        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id(logoutButtonId)));
        driver.findElement(By.id(logoutButtonId)).click();
    }
}
