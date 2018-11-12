import base.BaseOutlookTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ReadUnreadTest extends BaseOutlookTest {

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();

        String login = properties.getProperty("login");
        String password = properties.getProperty("password");

        loginService.logIn(login, password);
    }

    @Test
    public void testMarkAllAsRead() {
        String markAllAsReadButtonId = properties.getProperty("mark.all.as.read.button.id");
        String mailsFilterButtonClass = properties.getProperty("mails.filter.button.class");
        String mailsFilterUnreadOptionClass = properties.getProperty("mails.filter.unread.option.class");
        String mailsNoUnreadMailsMessageClass = properties.getProperty("mails.no.unread.mails.message.class");

        driver.findElement(By.id(markAllAsReadButtonId)).click();
        driver.findElement(By.className(mailsFilterButtonClass)).click();

        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.className(mailsFilterUnreadOptionClass)));
        driver.findElement(By.className(mailsFilterUnreadOptionClass)).click();

        String expectedMessage = properties.getProperty("mails.no.unread.mails.message");
        String actualMessage = driver.findElement(By.className(mailsNoUnreadMailsMessageClass)).getText();

        Assert.assertEquals(expectedMessage, actualMessage);
    }
}
