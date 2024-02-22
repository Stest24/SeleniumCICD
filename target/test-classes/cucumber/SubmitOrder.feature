
@tag
Feature: Purchase product from ecommerce website

Background:
Given I landed on Ecommerce Page
  @Regression
  Scenario Outline: Positive Test of Submitting the order
    Given Logged in with username <username> and password <password>
    When I add product <productName> to cart 
    And Checkout <productName> and submit the order
    Then "THANKYOU FOR THE ORDER."  message is displayed on ConfirmationPage

    Examples: 
      | username  						 | password   | productName |
      | stestelevate@gmail.com | Password1! | ZARA COAT 3|

