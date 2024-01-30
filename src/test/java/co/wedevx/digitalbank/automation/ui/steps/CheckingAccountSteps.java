package co.wedevx.digitalbank.automation.ui.steps;

import co.wedevx.digitalbank.automation.ui.models.AccountCard;
import co.wedevx.digitalbank.automation.ui.models.BankingTransaction;
import co.wedevx.digitalbank.automation.ui.models.NewCheckingAccountInfo;
import co.wedevx.digitalbank.automation.ui.pages.CreateCheckingPage;
import co.wedevx.digitalbank.automation.ui.pages.LoginPage;
import co.wedevx.digitalbank.automation.ui.pages.ViewCheckingAccountPage;
import co.wedevx.digitalbank.automation.ui.utils.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;
import java.util.Map;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class CheckingAccountSteps {
    WebDriver driver = Driver.getDriver();
    private LoginPage loginPage = new LoginPage(driver);
    private CreateCheckingPage createCheckingPage = new CreateCheckingPage(driver);
    private ViewCheckingAccountPage viewCheckingAccountPage = new ViewCheckingAccountPage(driver);

    @Given("the user logged in as {string} {string}")
    public void the_user_logged_in_as(String username, String password) {

        loginPage.login(username, password);
    }

    @When("the user creates a new checking account with the following data")
    public void the_user_creates_a_new_checking_account_with_the_following_data(List<NewCheckingAccountInfo> checkingAccountInfoList) {
        createCheckingPage.createNewChecking(checkingAccountInfoList);
    }

    @Then("the user should see green {string} massage")
    public void the_user_should_see_green_massage(String expectedConfMessage) {
        expectedConfMessage = "Confirmation " + expectedConfMessage + "\n×";
        assertEquals(expectedConfMessage, viewCheckingAccountPage.getActualCreateAccountConfirmationMessage());
    }

    @Then("the user should see newly added account card")
    public void the_user_should_see_newly_added_account_card(List<AccountCard> accountCardList) {
     Map<String,String> actualResultMap = viewCheckingAccountPage.getNewlyAddedCheckingAccountInfoMap();

      AccountCard expectedResult = accountCardList.get(0);

      assertEquals(expectedResult.getAccountName(), actualResultMap.get("actualAccountName"));
      assertEquals("Account: " + expectedResult.getAccountType(), actualResultMap.get("actualAccountType"));
      assertEquals("Ownership: " + expectedResult.getOwnership(), actualResultMap.get("actualOwnership"));
      assertEquals("Interest Rate: " + expectedResult.getInterestRate(), actualResultMap.get("actualInterestRate"));


      String expectedBalance = String.format("%.2f", expectedResult.getBalance());
      assertEquals("Balance: $" +expectedBalance, actualResultMap.get("actualBalance"));
    }
    @Then("the user should see the following transactions")
    public void the_user_should_see_the_following_transactions(List<BankingTransaction> expectedTransactions) {
     Map<String,String> actualResultMap = viewCheckingAccountPage.getNewlyAddedCheckingAccountTransactionInfoMap();
     BankingTransaction expectedTransaction = expectedTransactions.get(0);

     assertEquals(expectedTransaction.getCategory(), actualResultMap.get("actualCategory"),"transaction category mismatch");
     //assertEquals(expectedTransaction.getDescription(), actualDescription,"transaction description mismatch");
     assertEquals( expectedTransaction.getAmount(),Double.parseDouble(actualResultMap.get("actualAmount")),"transaction amount mismatch");
     assertEquals( expectedTransaction.getBalance(),Double.parseDouble(actualResultMap.get("actualBalance")),"transaction balance mismatch");



    }

    @Given("the user withdraw button")
    public void the_user_withdraw_button() {
        WebElement withdraw = driver.findElement(By.id("withdraw-menu-item"));
        withdraw.click();
    }
    @Given("the user see message {string}")
    public void the_user_see_message(String mes) {
        WebElement message = driver.findElement(By.className("active"));
        assertEquals(mes,message.getText());
    }
    @Given("the user Select Account click")
    public void the_user_select_account_click() {
        WebElement select = driver.findElement(By.id("selectedAccount"));
        select.click();
    }
    @Given("the user selects account")
    public void the_user_selects_account() {
        List<WebElement> allFirstRowDiv = driver.findElements(By.xpath("(//*[@id='selectedAccount']/option)"));
        WebElement lastAccountCard = allFirstRowDiv.get(allFirstRowDiv.size() - 1);
        lastAccountCard.click();
    }
    @When("the user Withdraw Amount {string}")
    public void the_user_withdraw_amount(String amount) {
        WebElement withdrawAmount = driver.findElement(By.id("amount"));
        withdrawAmount.sendKeys(amount);
    }
    @Then("the user click Submit")
    public void the_user_click_submit() {
        WebElement submit = driver.findElement(By.xpath("//*[@id='right-panel']/div[2]/div/div/div/div/form/div[2]/button[1]"));
        submit.click();
      // fail();
    }

}
