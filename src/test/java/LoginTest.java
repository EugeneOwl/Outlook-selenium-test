import config.ConfigurationService;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Properties;

import static constant.Url.OUTLOOK_URL;

public class LoginTest {

    private Properties properties;

    @Before
    public void setUp() throws Exception {
        ConfigurationService config = new ConfigurationService();
        config.setUpDriverEnvironment();
        properties = config.getConfigProperties();
        System.out.println(properties.getProperty("key"));
    }

    @Test
    public void testLogin() {
        WebDriver driver = new ChromeDriver();
        driver.get(OUTLOOK_URL);
    }
}
