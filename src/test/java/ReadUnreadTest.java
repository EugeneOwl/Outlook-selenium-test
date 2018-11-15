import base.BaseOutlookTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

public class ReadUnreadTest extends BaseOutlookTest {

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
        logIn();
    }

    @Test
    public void testMarkAllAsRead() {
        String markAllAsReadButtonClass = properties.getProperty("mails.mark.all.as.read.button.class");
        String mailsFilterButtonClass = properties.getProperty("mails.filter.button.class");
        String mailsFilterOptionsClass = properties.getProperty("mails.filter.options.class");
        String mailsNoUnreadMailsMessageClass = properties.getProperty("mails.no.unread.mails.message.class");
        int mailsFilterOptionUnreadButtonPosition = Integer.parseInt(properties.getProperty("mails.filter.option.unread.position"));

        driver.findElement(By.className(markAllAsReadButtonClass)).click();
        driver.findElement(By.className(mailsFilterButtonClass)).click();

        waitForElementToLoad(By.className(mailsFilterOptionsClass));
        getElementByClassName(mailsFilterOptionsClass, mailsFilterOptionUnreadButtonPosition).click();

        String expectedMessage = properties.getProperty("mails.no.unread.mails.message");
        waitForElementsToLoad(By.className(mailsNoUnreadMailsMessageClass));
        String actualMessage = driver.findElement(By.className(mailsNoUnreadMailsMessageClass)).getText();

        Assert.assertEquals(expectedMessage, actualMessage);
    }
}
