Feature: Creating a new checking account


  Scenario: Create a standard individual checking account
    Given the user logged in as "joze@gmail.com" "Qwe123123"
    When the user creates a new checking account with the following data
    | checkingAccountType| accountOwnership | accountName            | initialDepositAmount|
    | Standard Checking  | Individual       | Jose Pa Second Checking| 100000.0            |
    Then the user should see green "Successfully created new Standard Checking account named Jose Pa Second Checking" massage
    And the user should see newly added account card
    |accountName             |accountType       |ownership |accountNumber|interestRate|balance  |
    |Jose Pa Second Checking |Standard Checking |Individual| 486131073   |0.0%        |100000.00|
    And the user should see the following transactions
    |date      |category|description              |amount    |balance   |
    |2023-09-06|Income  |845321772 (DPT) - Deposit|100000.0  |100000.0  |


  Scenario: Create a withdraw
    Given the user logged in as "johndoe1@gmail.com" "Email1234"
    And the user withdraw button
    And the user see message "Welcome John"
    And the user Select Account click
    And the user selects account
    When the user Withdraw Amount "50"
    And the user click Submit
    Then the user should see the following transactions
      |date            |category |description                       | amount|balance |
      |2023-09-06 20:41|Misc     |845322882 (WTH) - Online Withdrawl|  -50   |9950.00|