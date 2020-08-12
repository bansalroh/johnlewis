@shopping
Feature: User journey from browse to basket on Johnlewis website

  #This suite will work with chrome version 84/77 on mac OS and chrome version 84 on window machine
  Background:
    Given Initialize "chrome" browser version "77"
    And Navigate to Johnlewis url

  @pass
  Scenario Outline: User1 browsing product and adding to cart
    Given Click on accept cookies to submit popup
    When Search "<keyword>" in search input to land on search result page
    And Select first available product on SRP and land user to Product Details Page
    And Add "<quantity>" items to cart
    And Reduce product quantity from cart
    And Remove items from cart
    Then Check all products has been removed from cart
    Then Delete cookies from cart and print cookie state in console

    Examples:
      | keyword        | quantity |
      | shampoo        | 2        |
      | Coffee Machine | 3        |

  @fail
  Scenario: User2 browsing product and adding to cart
    Given Click on accept cookies to submit popup
    When Search "juicer" in search input to land on search result page
    And Select first available product on SRP and land user to Product Details Page
    And Add "3" items to cart
    And Reduce product quantity from cart
    Then Check all products has been removed from cart
