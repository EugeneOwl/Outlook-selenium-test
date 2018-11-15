import base.BaseOutlookTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class UseConditionsTest extends BaseOutlookTest {

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
        logIn();
    }

    @Test
    public void testReachUseConditions() {
        reachUseConditions();
        String expectedUrl = properties.getProperty("use.conditions.url");
        String actualUrl = driver.getCurrentUrl();

        Assert.assertEquals(expectedUrl, actualUrl);
    }

    @Test
    public void testUseConditionsTitleAndContentContent() {
        String expectedTitle = properties.getProperty("use.conditions.title");
        String useConditionsHeaderClass = properties.getProperty("use.conditions.title.class");
        String expectedHeader = properties.getProperty("use.conditions.header");

        reachUseConditions();

        String actualHeader = driver.findElement(By.className(useConditionsHeaderClass)).getText();
        String actualTitle = driver.getTitle();

        Assert.assertEquals(expectedHeader, actualHeader);
        Assert.assertEquals(expectedTitle, actualTitle);
    }

    private void reachUseConditions() {
        String upperRightButtonsClass = properties.getProperty("upper.right.buttons.class");
        String goToUseConditionsButtonClass = properties.getProperty("go.to.use.conditions.button.class");
        int questionButtonPosition = Integer.parseInt(properties.getProperty("question.button.position"));
        int goToUseConditionsButtonPosition =
                Integer.parseInt(properties.getProperty("go.to.use.conditions.button.position"));
        getElementByClassName(upperRightButtonsClass, questionButtonPosition).click();


        waitForElementsToLoad(By.className(goToUseConditionsButtonClass));

        String useConditionsUrl = properties.getProperty("use.conditions.url");
        getElementByClassName(goToUseConditionsButtonClass, goToUseConditionsButtonPosition).click();
        switchToWindow(1);
        driver.get(useConditionsUrl);
        webDriverWait.until(ExpectedConditions.urlToBe(useConditionsUrl));
    }
}
