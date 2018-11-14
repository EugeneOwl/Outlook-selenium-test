import base.BaseOutlookTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class FavouritesTest extends BaseOutlookTest {

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
        logIn();
    }

    @Test
    public void testArchiveOptionFavourite() {
        String leftToolbarButtonsClass = properties.getProperty("left.toolbar.buttons.class");
        String addToFavouritesContextualMenuOptionClass =
                properties.getProperty("contextual.menu.active.options.class");
        String addToFavouritesOptionMessage = properties.getProperty("add.to.favourites.option.message");
        String removeFromToFavouritesOptionMessage = properties.getProperty("remove.from.favourites.option.message");
        int archiveButtonPosition = Integer.parseInt(properties.getProperty("archive.button.position"));
        int addToFavouritesOptionPosition = Integer.parseInt(
                properties.getProperty("contextual.menu.add.to.favourites.option.position")
        );

        // before
        WebElement archiveButton = getElementByClassName(leftToolbarButtonsClass, archiveButtonPosition);
        rightClickOnElement(archiveButton);

        By addToFavouritesOptionBy = By.className(addToFavouritesContextualMenuOptionClass);
        waitForElementToLoad(addToFavouritesOptionBy);

        WebElement lastContextualMenuOption = getElementByClassName(
                addToFavouritesContextualMenuOptionClass,
                addToFavouritesOptionPosition
        );
        String lastContextualMenuOptionTextBefore = lastContextualMenuOption.getText();
        lastContextualMenuOption.click();

        // after
        archiveButton = getElementByClassName(leftToolbarButtonsClass, archiveButtonPosition + 2);
        rightClickOnElement(archiveButton);

        addToFavouritesOptionBy = By.className(addToFavouritesContextualMenuOptionClass);
        waitForElementToLoad(addToFavouritesOptionBy);

        lastContextualMenuOption = getElementByClassName(
                addToFavouritesContextualMenuOptionClass,
                addToFavouritesOptionPosition
        );

        String lastContextualMenuOptionTextAfter = lastContextualMenuOption.getText();
        lastContextualMenuOption.click();

        Assert.assertEquals(lastContextualMenuOptionTextBefore, addToFavouritesOptionMessage);
        Assert.assertEquals(lastContextualMenuOptionTextAfter, removeFromToFavouritesOptionMessage);
    }
}
