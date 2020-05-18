package SeleniumIntro;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.lang.reflect.Constructor;
import java.util.*;

public class SeleniumHomeWork2 {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        /*
         *Navigate to "https://www.seleniumeasy.com/test/basiccheckbox-demo.html"
        Validate Check All button is displayed
        Validate button has "Check All" text
        Click the Check All button on Multiple Checkbox Demo
        Validate all check boxes are selected
        Validate button has "Uncheck All" text
         */
        driver.get("https://www.seleniumeasy.com/test/basic-checkbox-demo.html");
        WebElement checkAllButton = driver.findElement(By.xpath("//div[@class='panel-body']/input[@value='Check All']"));
        if (checkAllButton.isDisplayed()) {
            System.out.println("1. Check All button is displayed");
        } else {
            System.out.println("1. Check All button is not displayed");
        }
        if (checkAllButton.getAttribute("value").equals("Check All")) {
            System.out.println("2. Check All button's text test - Passed");
        } else {
            System.out.println("2. Check All button's text test - Failed");
        }
        /*
        Validate 4 checkbox is NOT selected
        Click the 4 checkbox on Multiple Checkbox Demo
        Validate button has "Uncheck All" text
         */
        checkAllButton.click();
        List<WebElement> options = driver.findElements(By.className("cb1-element"));
        WebElement uncheck = driver.findElement(By.xpath("//div[@class='panel-body']/input[@value='Uncheck All']"));
        int i = 1;
        for (WebElement option : options) {
            if (option.isSelected() && uncheck.getAttribute("value").equals("Uncheck All")) {
                System.out.println("Option " + i + " button test case passed");
            } else {
                System.out.println("Test case failed");
            }
            i++;
        }
        /*
        Validate Check All button is displayed
        Validate button has "Check All" text
        Validate 4 checkbox is NOT selected
        Click the 4 checkbox on Multiple Checkbox Demo
        Validate button has "Uncheck All" text
         */
        uncheck.click();
        int j = 1;
        for (WebElement option : options) {
            if ((!option.isSelected()) && checkAllButton.getAttribute("value").equals("Check All")) {
                System.out.println("Option " + j + " uncheck button test case passed");
            } else {
                System.out.println("Test case failed");
            }
            j++;
        }
        for (WebElement option : options) {
            option.click();
        }
        if (uncheck.getAttribute("value").equals("Uncheck All")) {
            System.out.println("Uncheck button test case passed");
        } else {
            System.out.println("Uncheck button test case failed");
        }
        /*
        Navigate to "https://www.saucedemo.com/"
        Enter username "Java"
        Enter password "Selenium"
        Click Login button
        Validate "Epic sadface: Username and password do not
        match any user in this service" message
         */
        driver.navigate().to("https://www.saucedemo.com/");
        WebElement userNameInput = driver.findElement(By.id("user-name"));
        userNameInput.sendKeys("Java");
        WebElement passwordInput = driver.findElement(By.id("password"));
        passwordInput.sendKeys("Selenium");
        WebElement logInButton = driver.findElement(By.className("btn_action"));
        logInButton.click();
        WebElement errorMessage = driver.findElement(By.xpath("//h3[@data-test='error']"));
        if (errorMessage.getText().equals("Epic sadface: Username and password do not match any user in this service")) {
            System.out.println("Negative scenario test case passed");
        } else {
            System.out.println("Negative scenario test case failed");
        }
        /*
        Enter username "standard_user"
        Enter password "secret_sauce"
        Click Login button
        Validate current url is
        "https://www.saucedemo.com/inventory.html"
         */
        userNameInput.clear();
        userNameInput.sendKeys("standard_user");
        passwordInput.clear();
        passwordInput.sendKeys("secret_sauce");
        logInButton.click();
        if (driver.getCurrentUrl().equals("https://www.saucedemo.com/inventory.html")) {
            System.out.println("URL test case is passed");
        } else {
            System.out.println("URL test case is failed");
        }
        /*
        Create two more test cases with different input for login functionality and implement
        the test cases in different classes
        Expected login and wrong password(login id)
        wrong login ID(password) and Expected password
         */
        driver.navigate().back();
        WebElement userNameInput2 = driver.findElement(By.id("user-name"));
        userNameInput2.sendKeys("standard_user");
        WebElement passwordInput2 = driver.findElement(By.id("password"));
        passwordInput2.sendKeys("standard_user");
        WebElement logInButton2 = driver.findElement(By.className("btn_action"));
        logInButton2.click();
        WebElement errorMessage2 = driver.findElement(By.xpath("//h3[@data-test='error']"));
        if (errorMessage2.getText().equals("Epic sadface: Username and password do not match any user in this service")) {
            System.out.println("Negative scenario test case passed");
        } else {
            System.out.println("Negative scenario test case failed");
        }
        userNameInput2.clear();
        userNameInput2.sendKeys("secret_sauce");
        passwordInput2.clear();
        passwordInput2.sendKeys("secret_sauce");
        logInButton2.click();
        if (errorMessage2.getText().equals("Epic sadface: Username and password do not match any user in this service")) {
            System.out.println("Negative scenario test case passed");
        } else {
            System.out.println("Negative scenario test case failed");
        }
        /*
        Navigate to "https://www.saucedemo.com/"
        Enter username "standard_user"
        Enter password "secret_sauce"
        Click Login button
        Get the all product name from home page
        Validate the product names are unique values
        Validate the product names are displayed in ascending order
         */
        userNameInput2.clear();
        userNameInput2.sendKeys("standard_user");
        passwordInput2.clear();
        passwordInput2.sendKeys("secret_sauce");
        logInButton2.click();
        List<WebElement> items = driver.findElements(By.className("inventory_item_name"));
        Set<String> unique = new TreeSet();
        for (WebElement item : items) {
            unique.add(item.getText());
        }
        if (items.size() == unique.size()) {
            System.out.println("Unique value test passed");
        } else {
            System.out.println("Unique value test failed");
        }
        int k=0;
        boolean isAscendding=false;
        for(String u:unique){

           if(u.equals(items.get(k).getText())){
               isAscendding=true;
           }else{
               System.out.println("Ascending test failed");
           }
            k++;
        }if(isAscendding==true)
            System.out.println("Ascending test passed");
    }
}
