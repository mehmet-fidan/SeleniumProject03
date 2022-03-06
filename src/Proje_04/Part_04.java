package Proje_04;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.BaseStaticDriver;

import org.junit.Assert.*;
import java.time.Duration;
import java.util.List;

public class Part_04 extends BaseStaticDriver {

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

        WebElement computers = driver.findElement(By.xpath("(//a[@href='/computers'])[3]"));
        computers.click();

        WebElement desktops = driver.findElement(By.cssSelector("ul[class='sublist'] a[href='/desktops']"));
        desktops.click();

        WebElement expensive = driver.findElement(By.xpath("(//input[@class='button-2 product-box-add-to-cart-button'])[3]"));
        expensive.click();

        WebElement processor = driver.findElement(By.cssSelector("input[id='product_attribute_74_5_26_82']"));
        processor.click();

        WebElement ram = driver.findElement(By.cssSelector("input[id='product_attribute_74_6_27_85']"));
        ram.click();

        WebElement hdd = driver.findElement(By.cssSelector("input[id='product_attribute_74_3_28_87']"));
        hdd.click();

        WebElement software = driver.findElement(By.cssSelector("input[id='product_attribute_74_8_29_90']"));
        software.click();
        WebElement addCart = driver.findElement(By.cssSelector("input[id='add-to-cart-button-74']"));
        addCart.click();

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, -document.body.height);");

        WebElement shoppingCart = driver.findElement(By.xpath("//span[text()='Shopping cart']"));
        shoppingCart.click();

        Thread.sleep(3000);

        WebElement agree = driver.findElement(By.cssSelector("input#termsofservice"));
        agree.click();

        WebElement checkOut = driver.findElement(By.cssSelector("button#checkout"));
        checkOut.click();
        Thread.sleep(1000);

        WebElement adressOption = driver.findElement(By.cssSelector("select[id='billing-address-select']"));
        Select adressSelect = new Select(adressOption);
        int count = 0;

        for (int a = 0; a < adressSelect.getOptions().size(); a++) {

            if (adressSelect.getOptions().equals(adressSelect.getOptions().get(count))) {
                adressSelect.getOptions().get(count).click();

            } else {
                adressSelect.getOptions().get(adressSelect.getOptions().size()-1).click();
            }

        }
        WebElement selection = driver.findElement(By.cssSelector("select[id='BillingNewAddress_CountryId']"));
        Select select = new Select(selection);
        List<WebElement> countryList = select.getOptions();
        Thread.sleep(1000);
        int random = (int) (Math.random() * countryList.size());
        Thread.sleep(1000);
        countryList.get(random).click();


        // select.getOptions().get(random).click();

        WebElement city = driver.findElement(By.cssSelector("input#BillingNewAddress_City"));
        city.sendKeys("Ankara");
        Thread.sleep(1000);
        WebElement adress1 = driver.findElement(By.cssSelector("input[id='BillingNewAddress_Address1']"));
        adress1.sendKeys("kurtulus parki yani");
        Thread.sleep(1000);
        WebElement adress2 = driver.findElement(By.cssSelector("input[id='BillingNewAddress_Address2']"));
        adress2.sendKeys("itfaiye");
        Thread.sleep(1000);
        WebElement zipCode = driver.findElement(By.cssSelector("input[id='BillingNewAddress_ZipPostalCode']"));
        zipCode.sendKeys("06060");
        Thread.sleep(1000);
        WebElement phone = driver.findElement(By.cssSelector("input[id='BillingNewAddress_PhoneNumber']"));
        phone.sendKeys("03125459897");


        WebElement billingAdress = driver.findElement(By.cssSelector("input[onclick='Billing.save()']"));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[onclick='Billing.save()']")));
        billingAdress.click();

        WebElement shipping = driver.findElement(By.cssSelector("input[onclick='Shipping.save()']"));
        shipping.click();

        WebElement shippingMethod = driver.findElement(By.cssSelector("input[onclick='ShippingMethod.save()']"));
        shippingMethod.click();

        WebElement cashDelivery = driver.findElement(By.cssSelector("input[value='Payments.CashOnDelivery']"));
        cashDelivery.click();

        WebElement weiter = driver.findElement(By.cssSelector("input[onclick='PaymentMethod.save()']"));
        weiter.click();

        WebElement paymentInfo = driver.findElement(By.cssSelector("input[onclick='PaymentInfo.save()']"));
        paymentInfo.click();

        WebElement confirm = driver.findElement(By.cssSelector("input[onclick='ConfirmOrder.save()']"));
        confirm.click();

        WebElement message = driver.findElement(By.xpath("//strong[text()='Your order has been successfully processed!']"));
        String messageControl = "Your order has been successfully processed!"; ////div[@class='title'] //strong

        Assert.assertEquals("Hatali sonuc aldiniz...",messageControl,message.getText());
        /*
        if (messageControl.equals(message.getText())) {
            System.out.println("message is received.");
        }

         */
        Thread.sleep(2000);
        driver.quit();

    }

}
