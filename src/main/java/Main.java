import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        driver.manage().window();
        driver.get("https://www.thesparksfoundationsingapore.org/");

        WebElement title = driver.findElement(By.xpath("//*[@id=\"home\"]/div/div[1]/h1/a"));
        if (title.getText().contains("The Sparks Foundation"))
            System.out.println("1. Title of page is :\n"+title.getText());
        else System.out.println("1. Title doesn't match");

        WebElement logo = driver.findElement(By.xpath("//*[@id=\"home\"]/div/div[1]/h1/a/img"));
        if (logo.isDisplayed())
            System.out.println("2. Logo is present");
        else System.out.println("2. Logo is absent");

        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement youtube = driver.findElement(By.id("youtube-video"));
        js.executeScript("arguments[0].scrollIntoView();", youtube);
        System.out.println("3. Youtube player is visible");
        youtube.click();
        System.out.println("4. Video is playing");

        WebElement button = driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/a"));
        button.click();
    }
}
