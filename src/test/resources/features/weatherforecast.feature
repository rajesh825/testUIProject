Feature: City Weather Forecast

  Scenario Outline: 5 day weather forecast
    Given I launch weather forecast site
    When I enter city name <city>
    Then I should see 5 day weather forecast

    Examples:
      | city      |
      | Edinburgh |
      | Glasgow   |