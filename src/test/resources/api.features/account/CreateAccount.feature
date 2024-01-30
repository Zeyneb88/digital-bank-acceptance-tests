Feature: Create Account Test Scenarios

  @DB
  Scenario: Create a valid account
    Given The user with "steve@apple.com" is not in DB
    Given the following user is in the db
      |title|firstName|lastName|gender |dob       |ssn         |emailAddress   | password  |address        |locality|region|postalCode|country|homePhone |mobilePhone|workPhone |
      |Mr.  |Steve    |Jobs    |M      |03/20/1954|122-44-3122 |steve@apple.com| Test123$$$|1 infinite loop|CA      |CA    |4444312   |USA    |444-221233|           |          |
    When the following account is created
      |accountName                      |accountTypeCode|openingDeposit|ownerTypeCode|
      |Steve Jobs Test Standard Checking|SCK            |   10000.00   | IND         |
    Then the following account details are returned in the response
      |accountName                      |accountTypeCode|openingDeposit|ownerTypeCode|accountStandingName|
      |Steve Jobs Test Standard Checking|SCK            |   10000.00   | IND         |Open               |
    Then the following account details are saved in the db








  Scenario: Create an account with wrong Account Name


  Scenario: Create an account with wrong Account Type Code


  Scenario: Create an account with wrong opening Deposit


  Scenario: Create an account with wrong ownerTypeCode
