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

#  Scenario Outline: 5 day weather forecast
#    When I enter city name <city>
#    And I select a day <day>
#    Then I should see 3 hourly forecast
#    When I click on same day again
#    Then I should not see 3 hourly forecast
#
#    Examples:
#      | city      | day  |
#      | Edinburgh | Tue  |
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

