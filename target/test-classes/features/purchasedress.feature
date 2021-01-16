@Sanity
Feature: Purchase Dress
  Description: purpose is to test automated E2E test flow

  Scenario: TC01_Customer_Places_Order_From_Search
    Given user is on home page
    When  user searches for "dress"
    And   choose to buy the first item
    And   And moves to checkout from mini cart
    And   enter personal details on signup page
    And   select same delivery address
    And   select payment method as "check" payment
    And   place the order
    Then  gets order placed message with order id