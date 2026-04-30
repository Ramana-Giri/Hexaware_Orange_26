Feature: SauceDemo Order Flow

  Scenario Outline: User login 
    Given user in home page
    When user enter "<username>" and "<password>"
    And clicks loginBtn
    Then products should be visible
    
    Examples:
    |username|password|
    |standard_user|secret_sauce|
    
  Scenario: Add prodcut to cart
    Given user selects a "Backpack"
    When user add product to cart
    And goes to cart page
    Then "Backpack" visible in the cart
    
   Scenario: Complete Checkout
   When user has a "Backpack" in the cart
   And user proceeds to checkout
   And user enters "firstName", "LastName" and "zipCode"
   And user finishs the order
   Then order should be placed