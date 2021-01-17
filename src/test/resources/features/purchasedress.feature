Feature: Purchase_Dress
  Description: purpose is to test automated E2E test flow
  @Sanity
  Scenario: TC01_Customer_Places_Order_By_Cheque
    Given user is on home page
    When  user searches for "dress"
    And   choose to buy the first item
    And   And moves to checkout from mini cart
    And   enter personal details on signup page
    And   select same delivery address
    And   select payment method as "Cheque" payment
    And   place the order
    Then  gets order placed message

  Scenario: TC02_Customer_Places_Order_By_Bank_Wire
    Given user is on home page
    When  user searches for "dress"
    And   choose to buy the first item
    And   And moves to checkout from mini cart
    And   enter personal details on signup page
    And   select same delivery address
    And   select payment method as "bank wire" payment
    And   place the order
    Then  gets order placed message
