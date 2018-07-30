package com.bdd.acceptancetest.stepdefinitions;


import com.bdd.acceptancetest.Config.Config;
import com.bdd.acceptancetest.pageobjects.Homepage;
import com.bdd.acceptancetest.util.BrowserDriver;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;


public class CityWeatherForecastSteps implements Config{

  private Homepage homepage;


  @Given("^I launch weather forecast site$")
  public void i_launch_weather_forecast_site() throws Throwable {
     homepage = new Homepage(BrowserDriver.getCurrentDriver());

  }

  @When("^I enter city name (.*)$")
  public void i_enter_city_name_Edinburgh(String city) throws Throwable {
    homepage.enterCity(city);

  }

  @When("^I select a day (.*)$")
  public void i_select_a_day_Tue(int day) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
    homepage.choseADay(day);

  }


  @Then("^I should see (\\d+) day weather forecast$")
  public void i_should_see_day_weather_forecast(int noOfDays) throws Throwable {
    Assert.assertEquals(homepage.gettotalDayForecast(),noOfDays);
    Assert.assertEquals(homepage.getDayOne(),"Tue");

  }

  @Then("^I should see forecast hourly for selected (\\d+)$")
  public void i_should_see_forecast_hourly_for_selected(int day) throws Throwable {
    // Write code here that turns the phrase above into concrete actions
    Assert.assertTrue("Hourly forecast not displayed for every 3 hours", homepage.getHourlyForecastTimes(day));
  }

}
