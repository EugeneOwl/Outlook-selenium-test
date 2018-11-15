package base;

import config.ConfigurationService;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.Properties;

public class BaseOutlookTest {

    protected WebDriver driver;
    protected WebDriverWait webDriverWait;
    protected Properties properties;
    protected LoginService loginService;

    @Before
    public void setUp() throws Exception {
        ConfigurationService config = new ConfigurationService();
        config.setUpDriverEnvironment();
        driver = new ChromeDriver();
        webDriverWait = new WebDriverWait(driver, 2);
        properties = config.getConfigProperties();
        loginService = new LoginService(driver, webDriverWait, properties);

        driver.get(properties.getProperty("login.url"));
        driver.manage().window().maximize();
    }

    @After
    public void tearDown() throws Exception {
        closeAllTabs();
    }

    protected void logIn() {
        String login = properties.getProperty("login");
        String password = properties.getProperty("password");

        loginService.logIn(login, password);
    }

    protected WebElement getElementByClassName(String className, int number) {
        return new ArrayList<>(driver.findElements(By.className(className))).get(number);
    }

    protected void switchToWindow(int number) {
        driver.switchTo().window(new ArrayList<>(driver.getWindowHandles()).get(number));
    }

    protected void rightClickOnElement(WebElement webElement) {
        new Actions(driver)
                .contextClick(webElement)
                .sendKeys(Keys.ARROW_DOWN)
//                .sendKeys(Keys.RETURN)
                .build()
                .perform();
    }

    protected void waitForElementToLoad(By elementBy) {
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(elementBy));
    }

    protected void waitForElementsToLoad(By elementBy) {
        webDriverWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(elementBy));
    }

    private void closeAllTabs() {
        driver.getWindowHandles().forEach(it -> closeFirstTab());
    }

    private void closeFirstTab() {
        switchToWindow(0);
        driver.close();
    }
}
