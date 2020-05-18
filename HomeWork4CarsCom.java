package ActionPractice;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class HomeWork4CarsCom {
 public static WebDriver driver;
    /*
    Navigate to "https://www.cars.com/"
    Select "Certified Cars" from drop down
    Select "Toyota" from All Makes
    Select "Corolla" from drop down
    Select max price is "$30000"
    Select 40 miles from drop down box
    Insert 60018 as zip code
    Click search button
    Validate title has Certified Used
    Validate "Certified Used Toyota Corolla for Sale" is displayed
    Validate 40 miles selected in dropdown once you click search button
    Validate Certified Pre-Owned selected in radio button
    Validate Toyota is selected in checkbox
    Validate Corolla is selected in drop down
     */
    @Test
    public void testCase1() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.navigate().to("https://www.cars.com/");
        WebElement newUsed=driver.findElement(By.xpath("//select[@aria-label='Select a stock type']"));
        Select select=new Select(newUsed);
        select.selectByVisibleText("Certified Cars");
        WebElement allMakesDropDown=driver.findElement(By.xpath("//select[@aria-label='Select a make']"));
        new Select(allMakesDropDown).selectByValue("20088");
        WebElement allModelsDropDown=driver.findElement(By.xpath("//select[@aria-label='Select a model']"));
        new Select(allModelsDropDown).selectByVisibleText("Corolla");
        WebElement noMaxPrice=driver.findElement(By.xpath("//select[@aria-label='Select a maximum price']"));
        new Select(noMaxPrice).selectByIndex(8);
        WebElement mileDropDown=driver.findElement(By.xpath("//select[@aria-label='Select a maximum distance']"));
        new Select(mileDropDown).selectByValue("40");
        WebElement zipCodeInput=driver.findElement(By.xpath("//div//input[@aria-label='Enter a Zip Code']"));
        zipCodeInput.clear();
        zipCodeInput.sendKeys("60018");
        WebElement searchButton=driver.findElement(By.xpath("//input[@class='NZE2g']"));
        searchButton.submit();
        Thread.sleep(500);
        String actualTitle=driver.getTitle();
        String expectedTitle="Certified Used Toyota Corolla - in 60018 - on Cars.com";
        System.out.println(actualTitle);
        Assert.assertEquals(actualTitle,expectedTitle);
        WebElement header=driver.findElement(By.xpath("//h1[@class='srp-header']"));
        String actualHeader=header.getText();
        String expectedHeader="Certified Used Toyota Corolla for Sale";
        System.out.println(actualHeader);
        Assert.assertEquals(actualHeader,expectedHeader);
        WebElement searchWithinDropDown=driver.findElement(By.xpath("//select[@name='rd']"));
        String actualFirstSelected=new Select(searchWithinDropDown).getFirstSelectedOption().getText();
        String expectedFirstSelected="40 miles";
        Assert.assertEquals(actualFirstSelected,expectedFirstSelected);
        List<WebElement> radioButtons=driver.findElements(By.xpath("//form//li[@id='stkTypId']/ul/li//input[@type='radio']"));
        boolean isSelected=false;
        for(WebElement radioButton:radioButtons){
            if((radioButton.isSelected()==true)&&radioButton.getAttribute("value").equals("28444")){
                isSelected=true;
            }
        }
        Assert.assertTrue(isSelected==true);
        WebElement carsCheckBox=driver.findElement(By.xpath("//li//input[@type='checkbox' and @value='20088']"));
        Assert.assertTrue(carsCheckBox.isSelected());//@value='20088'=>Toyota
        WebElement modelsCheckBox=driver.findElement(By.xpath("//li//input[@type='checkbox' and @value='20861']"));
        Assert.assertTrue(modelsCheckBox.isSelected());//value='20861'=>Corolla
        }

    /*
    Navigate to "https://www.cars.com/"
    Select "Certified Cars" from drop down
    Select "Lexus" from All Makes
    Select "ES 350" from drop down
    Select max price is "$50000"
    Select 50 miles from drop down box
    Insert 60016 as zip code
    Click search button
    Get count of all the cars which is displayed on first page
    Validate count of the cars is not more than 20
    Get all car names in first page
    Validate All car names has "Lexus ES 350"
    Get the Mile distance from zip code for every car
    Validate mile distance is no more than 50mil in first page
    Select Sort By --> Price:Highest in drop down
    Validate highest price is not more than $50000
     */
    @Test
    public void testCase2() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.cars.com/");
        WebElement newUsed=driver.findElement(By.xpath("//select[@aria-label='Select a stock type']"));
        Select select=new Select(newUsed);
        select.selectByVisibleText("Certified Cars");
        WebElement allMakesDropDown=driver.findElement(By.xpath("//select[@aria-label='Select a make']"));
        new Select(allMakesDropDown).selectByValue("20070");//Lexus
        WebElement allModelsDropDown=driver.findElement(By.xpath("//select[@aria-label='Select a model']"));
        new Select(allModelsDropDown).selectByValue("21027");//ES350
        WebElement noMaxPrice=driver.findElement(By.xpath("//select[@aria-label='Select a maximum price']"));
        new Select(noMaxPrice).selectByValue("50000");//$50000
        WebElement mileDropDown=driver.findElement(By.xpath("//select[@aria-label='Select a maximum distance']"));
        new Select(mileDropDown).selectByValue("50");//50 Miles
        WebElement zipCodeInput=driver.findElement(By.xpath("//div//input[@aria-label='Enter a Zip Code']"));
        zipCodeInput.clear();
        zipCodeInput.sendKeys("60016");
        WebElement searchButton=driver.findElement(By.xpath("//input[@class='NZE2g']"));
        searchButton.submit();
        Thread.sleep(500);
        WebElement resultPerPage=driver.findElement(By.xpath("//div[@class='select per-page']//select[@aria-label='Results Per Page']"));
        String actualResultPerPage=new Select(resultPerPage).getFirstSelectedOption().getText();
        String expectedResultPerPage="20 Per Page";
        System.out.println(actualResultPerPage);
        Assert.assertEquals(actualResultPerPage,expectedResultPerPage);
        List <WebElement> vehicleTitles=driver.findElements(By.className("listing-row__title"));
        int actualAmount=vehicleTitles.size();
        int expectedAmount=20;
        Assert.assertEquals(actualAmount,expectedAmount);
        String expectedPartOfTitle="Lexus ES 350";
        boolean containsRequiredExpectedPartOfTitle=false, withInDistance=false;
        List<WebElement> distance=driver.findElements(By.xpath("//div[@class='listing-row__dealer listing-row__dealer--cpo']//div//div[@class='listing-row__distance ']"));
        for (WebElement vehicleTitle:vehicleTitles) {
            int i=0;
           if (vehicleTitle.getText().contains(expectedPartOfTitle)){
               System.out.println(vehicleTitle.getText()+" "+distance.get(i).getText());
               containsRequiredExpectedPartOfTitle=true;
           }
           int milesFrom=Integer.parseInt(distance.get(i).getText().substring(0,distance.get(i).getText().indexOf(" ")));
           if (milesFrom<50){
               withInDistance=true;
           }
           i++;
        }
       Assert.assertTrue(containsRequiredExpectedPartOfTitle==true);
        Assert.assertTrue(withInDistance==true);
        WebElement sortByDropDown=driver.findElement(By.xpath("//div//select[@class='select-sort-options']"));
        new Select(sortByDropDown).selectByValue("price-highest");
        List<WebElement> prices=driver.findElements(By.xpath("//div[@class='payment-section']//span[@class='listing-row__price new']"));
        int actualHighestPrice=Integer.parseInt(prices.get(0).getText().replace("$","").replace(",",""));
        Assert.assertTrue(actualHighestPrice<50000);
    }
}