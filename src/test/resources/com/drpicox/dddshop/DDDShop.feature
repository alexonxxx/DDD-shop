Feature: DDD Shop

  Scenario: Register one price
    Given item 1 with price 20 in money
    And cash register 1

    When record item 1 at cash register 1
    And end cash register 1 item recording
    And total is cash register 1 total
    And record 25 as delivered money at cash register 1
    And change is cash register 1 change

    Then total is 20
    And change is 5
    And cash register 1 is ready to record a new item