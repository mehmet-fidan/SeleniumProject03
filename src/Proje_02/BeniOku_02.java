package Proje_02;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.BaseStaticDriver;

import java.time.Duration;
import java.util.List;
import java.util.Set;

public class BeniOku_02 extends BaseStaticDriver {

/*
            https://www.etsy.com/  siteye gidiniz
            Wedding & Party  Action class kullanarak uzerine gelin
            Wedding Decorations  Action class ile geliniz ve aciniz
            Cake Toppers  seciniz
            SortBy  : Highest Price  olarak secilecek
            Listedeki urunlerden biri Random olarak secilecek
            "Top wedding searches to shop " bu yaziya kadar ekrani kadirin alt kisimda olacak sekilde
            twitter iconuna basiniz
            Cikan ekranda Email textBox text gönderiniz


            Ana sayfaya gelip "Shop by interest" yazisinin varligini kontrol ediniz
            Title : Wedding Cake Toppers | Etsy oldugunu dogrulayiniz

            */

    public static void main(String[] args) throws InterruptedException {

        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.etsy.com/");

        WebElement wedding = driver.findElement(By.cssSelector("a[href='/c/wedding-and-party?ref=catnav-10983']"));
        Actions builder = new Actions(driver);
        Action action = builder.moveToElement(wedding).build();
        action.perform();

        WebElement decoration = driver.findElement(By.cssSelector("span#side-nav-category-link-10996"));
        // Action action1 = builder.moveToElement(decoration).build();
        //        action1.perform();
        decoration.click();

        WebElement cakeToppers = driver.findElement(By.id("catnav-l3-10998"));
        cakeToppers.click();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        // js.executeScript("arguments[0].scrollIntoView(true);",selection);
        js.executeScript("scroll(0,400)");
        WebElement sortBy = driver.findElement(By.cssSelector("span[class='wt-menu__trigger__label']"));
        sortBy.click();

        WebElement highestPrice = driver.findElement(By.xpath("(//a[@role='menuitemradio'])[3]"));
        highestPrice.click();

        js.executeScript("scroll(0,2000)");
        List<WebElement> urunList = driver.findElements(By.cssSelector("img[class='wt-width-full wt-height-full wt-display-block wt-position-absolute  ']"));

        int random = (int) (Math.random() * urunList.size());
        urunList.get(random).click();

        String ansayfaId = driver.getWindowHandle();

        Set<String> windowId = driver.getWindowHandles();

        for (String windows : windowId) {

            if (!windows.equals(ansayfaId)) {

                driver.switchTo().window(windows);
                WebElement yaziKontrol = driver.findElement(By.cssSelector("h2[class='wt-text-heading-01 wt-mt-md-4 wt-mb-xs-3 wt-mb-md-4']"));
                js.executeScript("arguments[0].scrollIntoView(false);", yaziKontrol);
                Thread.sleep(1000);
                js.executeScript("window.scrollTo(0, document.body.scrollHeight);");

                WebElement twitter = driver.findElement(By.xpath("(//span[@class='etsy-icon wt-icon--larger'])[4]"));
                twitter.click();
                driver.close();
            }
        }

        Set<String> windowId2 = driver.getWindowHandles();

        for (String twitterWindow : windowId2) {

            if (!twitterWindow.equals(ansayfaId)) {

                driver.switchTo().window(twitterWindow);
                WebElement e_mail = driver.findElement(By.xpath("//span[text()='Telefon numarası veya e-posta adresiyle kaydol']"));
                e_mail.click();

                WebElement postaKullan = driver.findElement(By.xpath("//span[text()='E-posta kullan']"));
                postaKullan.click();
                WebElement e_mail2 = driver.findElement(By.cssSelector("input[name='email']"));
                e_mail2.sendKeys("asdjkashd@com.com");
                Thread.sleep(1000);
                driver.close();
            }
        }
        driver.switchTo().window(ansayfaId);

        js.executeScript("window.scrollTo(0, -document.body.scrollHeight);");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        boolean dogruMu = wait.until(ExpectedConditions.textToBe(By.xpath("//h3[text()='Shop by interest']"), "Shop by interest"));
        System.out.println("dogruMu = " + dogruMu);
        //WebElement yaziKontrol_2 = driver.findElement(By.xpath("//h3[text()='Shop by interest']"));

        System.out.println(" sayfa basligi= " + driver.getTitle());
        Thread.sleep(2000);
        driver.quit();

    }
}

