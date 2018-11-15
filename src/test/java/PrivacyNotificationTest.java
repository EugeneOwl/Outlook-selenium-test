import base.BaseOutlookTest;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class PrivacyNotificationTest extends BaseOutlookTest {

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
        logIn();
    }

    @Test
    public void testReachPrivacyNotification() {
        reachPrivacyNotifications();
        String expectedUrl = properties.getProperty("privacy.notifications.url");
        String actualUrl = driver.getCurrentUrl();

        Assert.assertEquals(expectedUrl, actualUrl);
    }

    @Test
    public void testPrivacyNotificationsTitleAndContentContent() {
        String expectedTitle = properties.getProperty("privacy.notifications.title");
        String expectedHeader = properties.getProperty("privacy.notifications.header");

        reachPrivacyNotifications();

        String actualHeader = driver.findElement(By.tagName("h1")).getText();
        String actualTitle = driver.getTitle();

        Assert.assertEquals(expectedHeader, actualHeader);
        Assert.assertEquals(expectedTitle, actualTitle);
    }

    private void reachPrivacyNotifications() {
        String privacyNotificationButtonsClass = properties.getProperty("upper.right.buttons.class");
        String goToPrivacyNotificationsButtonClass = properties.getProperty("go.to.privacy.notifications.button.class");
        int questionButtonPosition = Integer.parseInt(properties.getProperty("question.button.position"));
        int goToPrivacyNotificationsButtonPosition =
                Integer.parseInt(properties.getProperty("go.to.privacy.notifications.button.position"));
        getElementByClassName(privacyNotificationButtonsClass, questionButtonPosition).click();

        waitForElementsToLoad(By.className(goToPrivacyNotificationsButtonClass));

        String privacyNotificationUrl = properties.getProperty("privacy.notifications.url");
        getElementByClassName(goToPrivacyNotificationsButtonClass, goToPrivacyNotificationsButtonPosition).click();
        switchToWindow(1);
        driver.get(privacyNotificationUrl);
        webDriverWait.until(ExpectedConditions.urlToBe(privacyNotificationUrl));
    }
}
