Feature: City Weather Forecast
  Background:
    Given I launch weather forecast site

  Scenario Outline: 5 day weather forecast
    When I enter city name <city>
    Then I should see 5 day weather forecast

    Examples:
      | city      |
      | Edinburgh |
      | Glasgow   |

  Scenario Outline: 5 day weather forecast with Hourly forecast
    When I enter city name <city>
    And I select a day <day>
    Then I should see forecast hourly for selected <day>

    Examples:
      | city      | day  |
      | Edinburgh | 1  |
      | Glasgow   | 2  |

  Scenario Outline:  Minimum and maximum temperatures
    When I enter city name <city>
    And I select a day <day>
    Then I should see the aggregate minimum temp and maximum temperatures for selected <day> and hour <hour>

    Examples:
      | city      | day  |
      | Edinburgh | 1    |
      | Glasgow   | 2    |

