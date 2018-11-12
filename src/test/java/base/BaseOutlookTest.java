package base;

import config.ConfigurationService;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

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
    }

    @After
    public void tearDown() throws Exception {
        driver.close();
    }
}
