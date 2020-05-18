package SelectClass;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.lang.reflect.Array;
import java.util.*;

public class SeleniumHomework3 {
    public static WebDriver driver;

    /*Test Case-1
    Navigate to "https://www.saucedemo.com/"
    Enter username "standard_user"
    Enter password "secret_sauce"
    Click Login button
    Select Name (Z-A) from drop down box
    Validate the products are displayed in descending order
     */
    @Test
    public void test1() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.navigate().to("https://www.saucedemo.com/");
        WebElement userNameInput = driver.findElement(By.id("user-name"));
        userNameInput.sendKeys("standard_user");
        WebElement passwordInput = driver.findElement(By.id("password"));
        passwordInput.sendKeys("secret_sauce");
        WebElement logInButton = driver.findElement(By.className("btn_action"));
        logInButton.click();
        WebElement filterDropDownBox = driver.findElement(By.className("product_sort_container"));
        Select select = new Select(filterDropDownBox);
        select.selectByValue("za");
        List<WebElement> items = driver.findElements(By.className("inventory_item_name"));
        List itemsListActual = new ArrayList();
        for (WebElement item : items) {
            String strItem = item.getText();
            itemsListActual.add(strItem);
        }
        List itemsListExpected = new ArrayList();
        itemsListExpected.addAll(itemsListActual);
        Collections.sort(itemsListExpected, Collections.reverseOrder());
        Assert.assertEquals(itemsListActual, itemsListExpected);
    }

    /*Test Case-2
    Navigate to "https://www.saucedemo.com/"
    Enter username "standard_user"
    Enter password "secret_sauce"
    Click Login button
    Select Price (low-high) from drop down box
    Validate the products are displayed according to their price
    and they are displayed low price to high price
     */
    @Test
    public void test2() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.navigate().to("https://www.saucedemo.com/");
        WebElement userNameInput = driver.findElement(By.id("user-name"));
        userNameInput.sendKeys("standard_user");
        WebElement passwordInput = driver.findElement(By.id("password"));
        passwordInput.sendKeys("secret_sauce");
        WebElement logInButton = driver.findElement(By.className("btn_action"));
        logInButton.click();
        WebElement filterDropDownBox = driver.findElement(By.className("product_sort_container"));
        Select select = new Select(filterDropDownBox);
        select.selectByValue("lohi");
        List<WebElement> items = driver.findElements(By.className("inventory_item_price"));
        List itemsListActual = new ArrayList();
        for (WebElement item : items) {
            Double priceItem = Double.parseDouble(item.getText().substring(1));
            itemsListActual.add(priceItem);
        }
        List itemsListExpected = new ArrayList();
        itemsListExpected.addAll(itemsListActual);
        Collections.sort(itemsListExpected);
        System.out.println(itemsListActual.get(5).toString());
        System.out.println(itemsListExpected.get(5).toString());
        Assert.assertEquals(itemsListActual, itemsListExpected);
    }

    /*Functionality-Acceptance Criteria(Gherkin Language)
        Given the user on saucedemo home page
        Then user click the burger button on saucedemo home page
        And the user will click the logout button
        And user should see the login page
        When the user navigate back from login page
        Then the user still should see login page
    Description: The user should not see the home page after
    logout since the user already click the logout button. Write the
    test case to test this function and validate it is working
    correctly or not.
     */
    @Test
    public void test3() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.navigate().to("https://www.saucedemo.com/");
        WebElement userNameInput = driver.findElement(By.id("user-name"));
        userNameInput.sendKeys("standard_user");
        WebElement passwordInput = driver.findElement(By.id("password"));
        passwordInput.sendKeys("secret_sauce");
        WebElement logInButton = driver.findElement(By.className("btn_action"));
        logInButton.click();
        WebElement burgerButton = driver.findElement(By.xpath("//div[@class='bm-burger-button']//button"));
        burgerButton.click();
        Thread.sleep(1500);
        WebElement logOutOption=driver.findElement(By.id("logout_sidebar_link"));
        logOutOption.click();
        String actualURL=driver.getCurrentUrl();
        String expectedURL="https://www.saucedemo.com/index.html";
        Assert.assertTrue(actualURL.equals(expectedURL));
        driver.navigate().back();
        actualURL=driver.getCurrentUrl();
        expectedURL="https://www.saucedemo.com/index.html";
        Assert.assertEquals(actualURL,expectedURL);
    }
    /*Test Case-4
    Navigate to "http://uitestpractice.com/Students/Select#"
    Validate India is selected by default on drop down box
    Validate the size of the Drop down box is 4
    Validate the values for Drop down box are :
        India
        United States of America
        China
        England
    Select the China with index number
    Select the England with value
    Select the United States with visible text
     */
        @Test
        public void test4 () throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.navigate().to("http://uitestpractice.com/Students/Select#");
        WebElement dropDownBox=driver.findElement(By.id("countriesSingle"));
        Select select= new Select(dropDownBox);
        String actualDefaultSelect=select.getFirstSelectedOption().getText();
        String expectedDefaultSelect="India";
        Assert.assertEquals(actualDefaultSelect,expectedDefaultSelect);
        List <WebElement> options=driver.findElements(By.xpath("//div[@class='row']//select[@id='countriesSingle']//option"));
        int expectedSize=4;
        int actualSize=options.size();
        Assert.assertEquals(actualSize,expectedSize);
        List expectedValues=new ArrayList(Arrays.asList("India","United States of America","China","England"));
        boolean dropDownValuesMatch=false;
        int i=0;
        for(WebElement option:options){
            if(option.getText().equals(expectedValues.get(i))){
                dropDownValuesMatch=true;
            }
            i++;
        }
        Assert.assertTrue(dropDownValuesMatch==true);
        select.selectByIndex(2);
        Thread.sleep(1000);
        select.selectByValue("england");
        Thread.sleep(1000);
        select.selectByVisibleText("United states of America");
        }
    /*Test Case-5
    Navigate to "http://uitestpractice.com/Students/Select#"
    Validate the values for Multiple Select are :
        India
        United States of America
        China
        England
    Select China and England
    Validate "China England" is displayed
    Deselect all the countries
    Select All the countries
    Validate "India United states of America China England" is
    displayed
    Deselect the China with index number
    Deselect the England with value
     */
        @Test
        public void test5 () {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            driver.navigate().to("http://uitestpractice.com/Students/Select#");
            List <WebElement> mulstipleSelectBoxOptions=driver.findElements(By.xpath("//select[@id='countriesMultiple']//option"));
            List expectedValues=new ArrayList(Arrays.asList("India","United states of America","China","England"));
            boolean dropDownValuesMatch=false;
            int i=0;
            for(WebElement option:mulstipleSelectBoxOptions){
                if(option.getText().equals(expectedValues.get(i).toString())){
                    dropDownValuesMatch=true;
                }
                i++;
            }
            Assert.assertTrue(dropDownValuesMatch==true);
            Select select=new Select(driver.findElement(By.xpath("//select[@id='countriesMultiple']")));
            select.selectByValue("china");
            select.selectByValue("england");
            WebElement displaySelected=driver.findElement(By.id("result"));
            String actualResult=displaySelected.getText();
            String expectedResult="China England";
            Assert.assertEquals(actualResult,expectedResult);
            select.deselectAll();
            int j=0;
            for(WebElement option:mulstipleSelectBoxOptions){
                select.selectByVisibleText(expectedValues.get(j).toString().trim());
                j++;
            }
            dropDownValuesMatch=false;
            int k=0;
            for(WebElement option:mulstipleSelectBoxOptions){
                if(option.getText().equals(expectedValues.get(k).toString())){
                    dropDownValuesMatch=true;
                }
                k++;
            }
            Assert.assertTrue(dropDownValuesMatch==true);
            select.deselectByIndex(2);
            select.deselectByValue("england");
        }
    }