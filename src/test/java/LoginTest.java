import config.ConfigurationService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Properties;

public class LoginTest {

    private ConfigurationService config = new ConfigurationService();
    private WebDriver driver;
    private WebDriverWait webDriverWait;
    private Properties properties;

    @Before
    public void setUp() throws Exception {
        config.setUpDriverEnvironment();
        driver = new ChromeDriver();
        webDriverWait = new WebDriverWait(driver, 1);
        properties = config.getConfigProperties();

        driver.get(properties.getProperty("login.url"));
    }

    @After
    public void tearDown() throws Exception {
        //driver.close();
    }

    @Test
    public void testLoginTitle() {
        String actualTitle = driver.getTitle();
        String expectedTitle = properties.getProperty("login.title");

        Assert.assertEquals(expectedTitle, actualTitle);
    }

    @Test
    public void testLoginSuccess() throws InterruptedException {
        String loginInputId = properties.getProperty("login.input.id");
        String loginSubmitId = properties.getProperty("login.submit.id");

        String passwordInputId = properties.getProperty("password.input.id");
        String passwordSubmitId = properties.getProperty("password.submit.id");

        String login = properties.getProperty("login");
        String password = properties.getProperty("password");
        System.out.println(password);

        driver.findElement(By.id(loginInputId)).sendKeys(login);
        driver.findElement(By.id(loginSubmitId)).click();

        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id(passwordInputId)));

        driver.findElement(By.id(passwordInputId)).sendKeys(password);
        driver.findElement(By.id(passwordSubmitId)).click();

        String expectedUrl = properties.getProperty("inbox.url");
        String actualUrl = driver.getCurrentUrl();

        Assert.assertEquals(expectedUrl, actualUrl);
    }
}
