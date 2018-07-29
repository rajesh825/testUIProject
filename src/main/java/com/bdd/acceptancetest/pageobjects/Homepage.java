package com.bdd.acceptancetest.pageobjects;


import com.bdd.acceptancetest.util.BrowserDriver;
import java.util.concurrent.BrokenBarrierException;
import java.util.logging.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import static org.junit.Assert.*;
import static com.bdd.acceptancetest.util.BrowserDriver.*;



public class Homepage extends BasePage {

  private static final Logger LOGGER = Logger.getLogger(Homepage.class.getName());

  By citylocator = By.id("city");
  By totalDays = By.cssSelector("#root > div > div > div.summary > span:nth-child(1) > span.name");
  By dayOneSelector = By.cssSelector("#root > div > div:nth-child(2) > div.summary > span:nth-child(1) > span.name");


  public Homepage(WebDriver driver){
    super(driver);
    visit(baseUrl + "/");

    waitForTextToAppear(driver,"Five Day Weather Forecast",find(By.cssSelector("h1")));

  }

  public void enterCity(String city){
    try {
      clear(citylocator);
      type(city, citylocator);
    }
    catch (RuntimeException e) {
      takeScreenshot(e, "searchError");
    }
  }

  public int gettotalDayForecast(){
    return findElements(totalDays).size();
  }

  public String getDayOne(){
     return waitUntilElemenetLocated(dayOneSelector).getText();
  }






}
