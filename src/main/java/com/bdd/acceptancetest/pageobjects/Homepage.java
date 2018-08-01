package com.bdd.acceptancetest.pageobjects;


import com.bdd.acceptancetest.util.BrowserDriver;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.TimeUnit;
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

  String dayPartOne = "#root > div > div:nth-child(";
  String dayPartTwo = ") > div.summary > span:nth-child(1)";

  String hourPartOne = "#root > div > div:nth-child(";
  String hourPartTwo =  ") > div.details > div:nth-child(1) > span:nth-child(1) > span";


  String hourlyForecastPartOne = "#root > div > div:nth-child(";
  String hourForecastPartOne = "#root > div > div:nth-child(";
  String hourlyForecastPartTwo = ") > div.details > div > span:nth-child(1) > span";

  String hourForecastPartTwo = ") > div.details > div:nth-child(";
  String hourForecastPartThree = ") > span:nth-child(1) > span";


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


  public void choseADay(int day) {
    int daySelector = day + 1;
    clickOnElement(By.cssSelector(dayPartOne + daySelector + dayPartTwo));
    waitForElement(find(By.cssSelector(hourPartOne + daySelector + hourPartTwo)));


  }

  public boolean getHourlyForecastTimes(int day){
    boolean result = false;
    int daySelector = day + 1;
    int totalHourlyForecasts = findElements(By.cssSelector(hourlyForecastPartOne + daySelector + hourlyForecastPartTwo)).size();
    for(int i=1; i < totalHourlyForecasts-1; i ++ ){

      int firstHour = Integer.parseInt(find(By.cssSelector(hourForecastPartOne + daySelector + hourForecastPartTwo + i  + hourForecastPartThree )).getText());
      int secondHour = Integer.parseInt(find(By.cssSelector(hourForecastPartOne + daySelector + hourForecastPartTwo + (i+1) + hourForecastPartThree )).getText());

      if (secondHour-firstHour != 300){
        break;
      } else {
        result = true;
      }

    }
    return result;
  }

  // TO-DO Improvement
  // Ensure CSS Selectors moved to Page Object locators section

  /**
   *
   * @param day
   * @return Return boolean indicating whether Max Temperature Aggregate Correct
   */
  public boolean isMaxTempAggregate(int day) {

    boolean result = false;
    int daySelector = day + 1;

    //Min Temp Aggregate displayed in website
    String maxTempAggregate = find(By.cssSelector("#root > div > div:nth-child(" + daySelector + ") > div.summary > span:nth-child(3) > span.max")).getText().split("°")[0];
    int totalHourlyForecasts = findElements(By.cssSelector(hourlyForecastPartOne + daySelector + hourlyForecastPartTwo)).size();

    int actualAggregate = 0;
    for(int i=1; i <= totalHourlyForecasts; i ++ ){
      String maxTemp =  find(By.cssSelector("#root > div > div:nth-child(" + daySelector + ") > div.details > div:nth-child(" + i + ") > span:nth-child(3) > span.max")).getText().split("°")[0];
      actualAggregate += Integer.parseInt(maxTemp) ;
    }

    actualAggregate = Math.round(actualAggregate / totalHourlyForecasts);
    LOGGER.info(" Max Temp Aggregate : " + actualAggregate );
    LOGGER.info("Actual displayed Max Temp Aggregate : " + maxTempAggregate );

    if(Integer.parseInt(maxTempAggregate)== actualAggregate){
      result = true;
    }
    return result;

  }

  /**
   *
   * @param day
   * @return Return boolean indicating whether Min Temperature Aggregate Correct
   */
  public boolean isMinTempAggregate(int day) {
    boolean result = false;
    int daySelector = day + 1;

    //Min Temp Aggregate displayed in website
    String minTempAggregate = find(By.cssSelector("#root > div > div:nth-child(" + daySelector + ") > div.summary > span:nth-child(3) > span.rmq-5ea3c959.min")).getText().split("°")[0];
    int totalHourlyForecasts = findElements(By.cssSelector(hourlyForecastPartOne + daySelector + hourlyForecastPartTwo)).size();
    int actualAggregate = 0;

    for(int i=1; i <= totalHourlyForecasts; i ++ ){

      String minTemp =  find(By.cssSelector("#root > div > div:nth-child(" + daySelector + ") > div.details > div:nth-child(" + i + ") > span:nth-child(3) > span.rmq-5ea3c959.min")).getText().split("°")[0];
      actualAggregate += Integer.parseInt(minTemp) ;
    }

    actualAggregate = Math.round(actualAggregate / totalHourlyForecasts);
    if(Integer.parseInt(minTempAggregate)== actualAggregate){
      result = true;
    }

    return result;
  }
}
