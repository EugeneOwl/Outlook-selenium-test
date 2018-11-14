import base.BaseOutlookTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class PeopleTest extends BaseOutlookTest {

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
        logIn();
    }

    @Test
    public void testReachPeople() {
        reachPeople();
        String expectedUrl = properties.getProperty("people.url");
        String actualUrl = driver.getCurrentUrl();

        Assert.assertEquals(expectedUrl, actualUrl);
    }

    private void reachPeople() {
        String goToPeopleButtonClass = properties.getProperty("bottom.left.options.class");
        int goToPeopleButtonPosition = Integer.parseInt(properties.getProperty("people.button.position"));
        String peopleUrl = properties.getProperty("people.url");
        getElementByClassName(goToPeopleButtonClass, goToPeopleButtonPosition).click();
        switchToWindow(1);
        webDriverWait.until(ExpectedConditions.urlToBe(peopleUrl));
    }
}
