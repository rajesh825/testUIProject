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
#
#  Scenario Outline: 5 day weather forecast
#    When I enter city name <city>
#    And I select a day <day>
#    Then I should see summary for minimim and maximum temperatures
#    And I should see summary for Aggregate rainfall
#
#    Examples:
#      | city      | day  |
#      | Edinburgh | Tue  |

