import base.BaseOutlookTest;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginTest extends BaseOutlookTest {

    @Test
    public void testLoginTitle() {
        String actualTitle = driver.getTitle();
        String expectedTitle = properties.getProperty("login.title");

        Assert.assertEquals(expectedTitle, actualTitle);
    }

    @Test
    public void testLoginSuccess() {
        String login = properties.getProperty("login");
        String password = properties.getProperty("password");

        loginService.logIn(login, password);

        String expectedUrl = properties.getProperty("inbox.url");
        String actualUrl = driver.getCurrentUrl();

        Assert.assertEquals(expectedUrl, actualUrl);
    }

    @Test
    public void testLogoutSuccess() {
        String login = properties.getProperty("login");
        String password = properties.getProperty("password");

        loginService.logIn(login, password);
        loginService.logOut();

        String expectedUrl = properties.getProperty("after.logout.url");
        webDriverWait.until(ExpectedConditions.urlToBe(expectedUrl));
        String actualUrl = driver.getCurrentUrl();

        Assert.assertEquals(expectedUrl, actualUrl);
    }

    @Test
    public void testLoginError() {
        String login = properties.getProperty("login");
        String passwordInvalid = properties.getProperty("password.invalid");
        String passwordInvalidMessageId = properties.getProperty("password.invalid.message.id");

        loginService.logIn(login, passwordInvalid);

        String expectedMessage = properties.getProperty("password.invalid.message");
        String actualErrorMessage = driver.findElement(By.id(passwordInvalidMessageId)).getText();

        Assert.assertEquals(expectedMessage, actualErrorMessage);
    }
}
