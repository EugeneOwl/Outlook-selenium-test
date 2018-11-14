import base.BaseOutlookTest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.Calendar;

public class CalendarTest extends BaseOutlookTest {

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
        logIn();
    }

    @Test
    public void testReachCalendar() {
        reachCalendar();
        String expectedUrl = properties.getProperty("calendar.url");
        String actualUrl = driver.getCurrentUrl();

        Assert.assertEquals(expectedUrl, actualUrl);
    }

    @Test
    public void testCalendarCurrentDate() {
        String currentDayCellClass = properties.getProperty("calendar.today.cell.class");

        reachCalendar();
        String currentDayCellText = driver.findElement(By.className(currentDayCellClass)).getText();
        String currentDayOfMonth = Integer.toString(getCurrentDayOfMonth());

        Assert.assertEquals(currentDayCellText, currentDayOfMonth);
    }

    private void reachCalendar() {
        String goToCalendarButtonClass = properties.getProperty("calendar.open.button.class");
        String calendarUrl = properties.getProperty("calendar.url");
        driver.findElement(By.className(goToCalendarButtonClass)).click();
        switchToWindow(1);
        webDriverWait.until(ExpectedConditions.urlToBe(calendarUrl));
    }

    private int getCurrentDayOfMonth() {
        return Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
    }
}
