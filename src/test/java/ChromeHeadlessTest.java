import driverfactory.WebDriverFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import tools.WaitTools;

public class ChromeHeadlessTest {
    private final String headlessUrl = System.getProperty("headless.url");
    private WebDriver driver;
    private WaitTools waitTools;

    @BeforeEach
    public void init() {
        driver = new WebDriverFactory("--headless").create();
        waitTools = new WaitTools(driver);
        driver.get(headlessUrl);
    }

    @AfterEach
    public void stopDriver() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void headlessChrome() {
        driver.findElement(By.cssSelector("input[aria-autocomplete]")).sendKeys("ОТУС" + Keys.ENTER);
        WebElement oneResalt = driver.findElement(By.xpath("//span[text()='Онлайн‑курсы для профессионалов, дистанционное обучение современным ...']"));
        waitTools.waitForCondition(ExpectedConditions.stalenessOf(oneResalt));
        String factResalt = oneResalt.getText();
        Assertions.assertEquals("Онлайн‑курсы для профессионалов, дистанционное обучение современным ...", factResalt);
    }

}