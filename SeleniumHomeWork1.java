package SeleniumIntro;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class SeleniumHomeWork1 {
    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver","chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.navigate().to("https://www.seleniumeasy.com/test/basic-first-form-demo.html");
        String titleSE=driver.getTitle();
        String expectedTitle="Selenium Easy Demo - Simple Form to Automate using Selenium";
        /*
   Validating if title of "www.seleniumeasy.com" is "Selenium Easy Demo - Simple Form to Automate using Selenium"
         */
        if(titleSE.equals(expectedTitle)){
            System.out.println("First Test Passed");
        }else{
            System.out.println("First Test Failed");
        }
        WebElement enterMessage=driver.findElement(By.xpath("//input[@id=\"user-message\"]"));
        enterMessage.sendKeys("First Test Case");
        WebElement showMessage=driver.findElement(By.xpath("//form[@id='get-input']/button[@onclick='showInput();']"));
        showMessage.click();
        /*
         Validating if the result is "Your Message: First Test Case"
         */
        if(driver.findElement(By.xpath("//div[@id='user-message']")).getText().equals("Your Message: First Test Case")){
            System.out.println("Second Test Case Passed");
        }else{
            System.out.println("Second Test Case Failed");
        }
    /*
    Enter a - 12 on Two Input Fields
    Enter b - 17 on Two Input Fields
    Click Get Total button
    Validate the result is "Total a + b = 29"

     */
        WebElement enterA=driver.findElement(By.xpath("//input[@id='sum1']"));
        enterA.sendKeys("12");
        WebElement enterB=driver.findElement(By.xpath("//input[@id='sum2']"));
        enterB.sendKeys("17");
        WebElement getTotalButton=driver.findElement(By.xpath("//form[@id='gettotal']//button[@onclick='return total()']"));
        getTotalButton.click();
        if(driver.findElement(By.xpath("//div[@class='panel-body']/div[@style='height: 50px; width: 100%;']")).getText().equals("Total a + b = 29")){
            System.out.println("Third Test Case Passed");
        }else{
           System.out.println("Third Test Case Failed");
        }
        /*
        Enter a - Chicago on Two Input Fields
        Enter b - Illinois Two Input Fields
        Click Get Total button
        Validate the result is "Total a + b = NaN"
         */
        enterA.clear();
        enterA.sendKeys("Chicago");
        enterB.clear();
        enterB.sendKeys("Illinois");
        getTotalButton.click();
        if(driver.findElement(By.xpath("//div[@class='panel-body']/div[@style='height: 50px; width: 100%;']")).getText().equals("Total a + b = NaN")){
            System.out.println("Fourth Test Case Passed");
        }else{
            System.out.println("Fourth Test Case Failed");
        }
        Thread.sleep(2000);
        /*
        Navigate to "https://www.seleniumeasy.com/test/basiccheckbox-demo.html"
        Click the checkbox on Single Checkbox Demo
        Validate the text "Success - Check box is checked" is displayed
         */
        driver.navigate().to("https://www.seleniumeasy.com/test/basic-checkbox-demo.html");
        WebElement singleCheckBox=driver.findElement(By.xpath("//div[@class='panel panel-default']//input[@id='isAgeSelected']"));
        singleCheckBox.click();
        WebElement checkBoxActualMessage=driver.findElement(By.xpath("//div[@class='panel panel-default']//div[@id='txtAge']"));
        if(singleCheckBox.isSelected() && checkBoxActualMessage.isDisplayed()){
            System.out.println("Fifth Test Case - Success - Check box is checked");
        }else{
            System.out.println("Fifth Test Case - Check box is not checked.");
        }
        /*
        Navigate to "http://blazedemo.com/purchase.php"
        Enter Name "David"
        Enter Address "2000 E devon Ave"
        Enter City "Chicago"
        Enter State "Illinois"
        Enter ZipCode "60606"
        Enter Credit Card Number "4566998734522988"
        Enter Month 09
        Enter Year 2024
        Enter Name on Card "David Trump"
        Click Remember me check box
        Click Purchase Flight Button
        Validate the current url is
        "http://blazedemo.com/confirmation.php"
        Validate title of page is "BlazeDemo Confirmation"
         */
        driver.navigate().to("http://blazedemo.com/purchase.php");
        driver.findElement(By.xpath("//input[@id='inputName']")).sendKeys("David");
        driver.findElement(By.xpath("//input[@id='address']")).sendKeys("2000 E devon Ave");
        driver.findElement(By.xpath("//input[@id='city']")).sendKeys("Chicago");
        driver.findElement(By.xpath("//input[@id='state']")).sendKeys("Illinois");
        driver.findElement(By.xpath("//input[@id='zipCode']")).sendKeys("60606");
        driver.findElement(By.xpath("//input[@id='creditCardNumber']")).sendKeys("4566998734522988");
        driver.findElement(By.xpath("//input[@id='creditCardMonth']")).sendKeys("09");
        driver.findElement(By.xpath("//input[@id='creditCardYear']")).sendKeys("2024");
        driver.findElement(By.xpath("//input[@id='nameOnCard']")).sendKeys("David Trump");
        driver.findElement(By.xpath("//input[@id='rememberMe']")).click();
        driver.findElement(By.xpath("//input[@value='Purchase Flight']")).click();
        if(driver.getCurrentUrl().equals("http://blazedemo.com/confirmation.php")){
            System.out.println("URL test case is passed");
        }else{
            System.out.println("URL test case is failed");
        }
        if(driver.getTitle().equals("BlazeDemo Confirmation")){
            System.out.println("Title test case is passed");
        }else{
            System.out.println("Title test case is failed");
        }







}
}
