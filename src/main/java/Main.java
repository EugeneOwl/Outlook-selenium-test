import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Main {
    public static void main(String[] args) {
        System.setProperty(
                "webdriver.chrome.driver",
                "/home/eugene/code/java/university/term5/Outlooktest/lib/chromedriver"
        );
        WebDriver driver = new ChromeDriver();
        driver.get("https://login.live.com/login.srf?wa=wsignin1.0&rpsnv=13&ct=1541948756&rver=7.0.6737.0&wp=MBI_SSL&wreply=https%3a%2f%2foutlook.live.com%2fowa%2f%3fnlp%3d1%26RpsCsrfState%3dfe2b45fe-4bb7-9718-1ad7-467c74ad3e38&id=292841&CBCXT=out&lw=1&fl=dob%2cflname%2cwld&cobrandid=90015");

    }
}
