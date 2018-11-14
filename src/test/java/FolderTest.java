import base.BaseOutlookTest;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class FolderTest extends BaseOutlookTest {

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
        logIn();
    }

    @Test
    public void testCreateFolder() {
        String leftToolbarButtonsClass = properties.getProperty("left.toolbar.buttons.class");
        String createFolderContextualMenuOptionClass = properties.getProperty("contextual.menu.active.options.class");
        int archiveButtonClass = Integer.parseInt(properties.getProperty("archive.button.position"));

        WebElement archiveButton = getElementByClassName(leftToolbarButtonsClass, archiveButtonClass);
        rightClickOnElement(archiveButton);

        By createFolderOptionBy = By.className(createFolderContextualMenuOptionClass);
        waitForElementToLoad(createFolderOptionBy);
        driver.findElement(createFolderOptionBy).click();
    }
}
