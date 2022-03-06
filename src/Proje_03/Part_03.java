package Proje_03;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.BaseStaticDriver;

import java.time.Duration;
import java.util.Set;

public class Part_03 extends BaseStaticDriver {

    public static void main(String[] args) throws InterruptedException {

        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("http://demowebshop.tricentis.com/");

        String anasayfaId = driver.getWindowHandle();

        WebElement login = driver.findElement(By.className("ico-login"));
        login.click();

        WebElement e_mail = driver.findElement(By.cssSelector("input[id='Email']"));
        e_mail.sendKeys("zeydinToprak@gmail.com");

        WebElement password = driver.findElement(By.cssSelector("input[id='Password']"));
        password.sendKeys("Zeydin34");

        WebElement login_2 = driver.findElement(By.cssSelector("input[value='Log in']"));
        login_2.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='Facebook']")));

        WebElement facebook = driver.findElement(By.xpath("//a[text()='Facebook']"));
        facebook.click();

        Set<String> windowId = driver.getWindowHandles();

        for (String windows : windowId) {

            if (!windows.equals(anasayfaId)) {

                driver.switchTo().window(windows);
                boolean dogruMu = driver.getTitle().contains("Facebook");
                System.out.println("dogru = " + dogruMu);
                Thread.sleep(2000);
                driver.close();
            }
        }

        driver.switchTo().window(anasayfaId);

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");

        WebElement contactUs = driver.findElement(By.linkText("Contact us"));
        contactUs.click();
        Thread.sleep(2000);
        WebElement message = driver.findElement(By.cssSelector("textarea.enquiry"));
        message.sendKeys("projcet is almost done");
        Thread.sleep(3000);
        WebElement submit = driver.findElement(By.cssSelector("input[name='send-email']"));
        submit.click();

        WebElement result = driver.findElement(By.cssSelector("div.result"));
        System.out.println("result message is =" + result.getText());

        String resultMessage = "Your enquiry has been successfully sent to the store owner.";

        if (resultMessage.equals(result.getText())) {
            System.out.println("message is true");
        }

        Thread.sleep(2000);
        driver.quit();
    }
}
