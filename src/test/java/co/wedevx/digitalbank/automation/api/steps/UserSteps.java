package co.wedevx.digitalbank.automation.api.steps;

import co.wedevx.digitalbank.automation.api.models.UserAPIModel;
import co.wedevx.digitalbank.automation.api.models.UserDomainForDataTable;
import co.wedevx.digitalbank.automation.api.models.UserProfileAPIModel;
import co.wedevx.digitalbank.automation.api.utils.RestHttpRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import static co.wedevx.digitalbank.automation.api.utils.ObjectMapperUtils.objectMapper;
import io.cucumber.java.en.Given;
import io.restassured.response.Response;

import java.util.List;

public class UserSteps {
    public static int testUserID = 0;
    @Given("the following user is in the db")
    public void the_following_user_is_in_the_db(List<UserDomainForDataTable> userDomainForDataTables) throws JsonProcessingException {
  //      RestAssured.baseURI = "http://zeyneb.mydevx.com/bank/api/v1";
  //      Response authRequestResponse = given()
   //             .queryParam("username","admin@demo.io")
  //              .queryParam("password", "Demo123!")
  //              .when()
  //              .post("/auth");
 //       JsonPath authResponseJsonPath = authRequestResponse.jsonPath();
  //      String authToken = authResponseJsonPath.getString("authToken");



                    //convert the POJO of users into a single user JSON string

   //     ObjectMapper = new ObjectMapper();
   //     String createUserBodyPayLoad = objectMapper.writeValueAsString(users.get(0));
  //      System.out.println(createUserBodyPayLoad);

                    //to send a create user request
    //   Response createUserRequestResponse = given()
    //          .header("Authorization", "Bearer " + authToken)
    //           .contentType(ContentType.JSON)
    //           .accept(ContentType.JSON)
     //          .body(createUserBodyPayLoad)
     //          .when()
    //           .post("user");
   //  createUserRequestResponse.prettyPrint();

        String createUserBodyPayLoad = objectMapper.writeValueAsString(userDomainForDataTables.get(0));
        Response response = RestHttpRequest.requestSpecification
                .body(createUserBodyPayLoad)
                .when()
                .post("user");

        UserAPIModel testUser = objectMapper.readValue(response.asString(), UserAPIModel.class);
        System.out.println(testUser.getId());
        testUserID = testUser.getId();


    }

}
//baseURI -> take from config
//generate auth token only once per the whole test suite run
//apply generated auth to all request
//move objectMapper to utils class to make sure we are creating the objectMapper object only once
