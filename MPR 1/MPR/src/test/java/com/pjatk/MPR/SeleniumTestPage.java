package com.pjatk.MPR;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SeleniumTestPage {

    public static final String URL = "http://localhost:8080/addBike";

    WebDriver driver;

    @FindBy(id = "name")
    WebElement nameInput;

    @FindBy(id = "color")
    WebElement colorInput;

    public SeleniumTestPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void fillInName() {
        nameInput.sendKeys("Capybara");
    }

    public void fillInColor() {
        colorInput.sendKeys("Orange");
    }

    public void open() {
        driver.get(URL);
    }


}
