
@tag
Feature: Error Validations
  

  @ErrorValidations
  Scenario Outline: Error Validation
    Given I landed on Ecommerce Page
    When Logged in with username <username> and password <password>
    Then "Incorrect email or password."  message is displayed 

    Examples: 
      | username  						 | password   |
      | stestelevate@gmail.com | Passord1! |