package co.wedevx.digitalbank.automation.ui.steps.data_transformers;

import co.wedevx.digitalbank.automation.ui.utils.DBUtils;
import co.wedevx.digitalbank.automation.ui.utils.Driver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;


import java.sql.SQLException;

import static co.wedevx.digitalbank.automation.ui.utils.Driver.getDriver;

public class Hooks {
    @Before("@DB")
    public void establishConnectionToDB() throws SQLException {
        DBUtils.establishConnection();
    }

 //   @Before("not @Registration")
  public void the_user_is_on_dbank_homepage() {
        getDriver().get("https://dbank-qa.wedevx.co/bank/login");
    }



  //  @After("not @NegativeRegistrationCases")
    public void afterEachScenario(Scenario scenario){
        Driver.takeScreenShot(scenario);
        Driver.closeDriver();
    }
    @After()
    public static void closeConnectionToDB(){
        DBUtils.closeConnection();
    }

}
