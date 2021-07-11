import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        Actions action = new Actions(driver);
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        driver.manage().window().maximize();
        driver.get("https://www.thesparksfoundationsingapore.org/");

        WebElement title = driver.findElement(By.xpath("//*[@id=\"home\"]/div/div[1]/h1/a"));
        if (title.getText().contains("The Sparks Foundation"))
            System.out.println("1. Title of page is :\n"+title.getText());
        else System.out.println("1. Title doesn't match");

        WebElement logo = driver.findElement(By.xpath("//*[@id=\"home\"]/div/div[1]/h1/a/img"));
        if (logo.isDisplayed())
            System.out.println("2. Logo is present");
        else System.out.println("2. Logo is absent");
        pause(2500);

        WebElement youtube = driver.findElement(By.id("youtube-video"));
        js.executeScript("arguments[0].scrollIntoView();", youtube);
        pause(2000);
        System.out.println("3. Youtube player is visible");
        youtube.click();
        System.out.println("4. Video is playing");
        pause(8000);
        youtube.click();
        System.out.println("5. Video is paused");
        pause(3000);

        WebElement button = driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/a"));
        js.executeScript("arguments[0].scrollIntoView();", button);
        pause(3000);

        WebElement hover = driver.findElement(By.xpath("/html/body/div[3]/div[1]/div[1]/div[1]"));
        action.moveToElement(hover).perform();
        pause(2000);
        hover = driver.findElement(By.xpath("/html/body/div[3]/div[1]/div[2]/div[2]"));
        action.moveToElement(hover).perform();
        pause(2000);
        hover = driver.findElement(By.xpath("/html/body/div[3]/div[1]/div[1]/div[3]"));
        action.moveToElement(hover).perform();
        pause(2000);

        button.click();
        pause(2000);

        WebElement aboutUs = driver.findElement(By.xpath("//*[@id=\"link-effect-3\"]/ul/li[1]/a"));
        action.moveToElement(aboutUs).perform();
        aboutUs.click();
        pause(2000);
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"link-effect-3\"]/ul/li[1]/ul")));
        WebElement news = driver.findElement(By.xpath("//*[@id=\"link-effect-3\"]/ul/li[1]/ul/li[7]/a"));
        news.click();
        pause(2000);

        js.executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath("/html/body/div[3]/div/div[2]/div/h4")));
        pause(2500);

        WebElement partners = driver.findElement(By.xpath("/html/body/div[3]/div/div[2]/div/ul/li[5]/a"));
        partners.click();
        pause(2500);

        WebElement AINE = driver.findElement(By.xpath("/html/body/div[2]/div/div[1]/div/div[1]/div/div[2]/a/img"));
        if (AINE.isDisplayed()) {
            System.out.println("6. Image is present");
            AINE.click();
            pause(5000);

            ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
            driver.switchTo().window(tabs.get(1));
            System.out.println("7. Closing "+driver.getCurrentUrl());
            driver.close();
            driver.switchTo().window(tabs.get(0));
        }
        else System.out.println("8. Image is absent");

        WebElement contactUs = driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/nav/div[2]/nav/ul/li[6]/a"));
        action.moveToElement(contactUs).perform();
        contactUs.click();

        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        pause(2500);
        System.out.println("9. Closing "+driver.getCurrentUrl());
        driver.quit();
    }

    private static void pause(long l) {
        try {
            Thread.sleep(l);
        } catch (InterruptedException e) {
            System.out.println("ERROR: "+e.getLocalizedMessage());
        }
    }
}
