package tests.apis;

import io.opentelemetry.api.trace.StatusCode;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import utils.PropertiesReader;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import static org.hamcrest.Matchers.equalTo;

public class APITests {

    SoftAssert softAssert = new SoftAssert();
    Map<String, Object> createdUser = new HashMap<>();

    @BeforeSuite
    public static void setBaseUrl() {
        RestAssured.baseURI = PropertiesReader.apiProperties.getProperty("baseurl");

    }

    @Test(priority = 1)
    public void createUser() {
        // Create JSON body as a Map
        System.out.println("<<<<<<<<< createUser >>>>>>>>");
        createdUser.put("name", "mamduh");
        createdUser.put("job", "sdet");

        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(createdUser)
                .post("/api/users");

        // simple logging
        response.getBody().prettyPrint();

        response.then().statusCode(201);
        String createdUserId = response.jsonPath().getString("id");
        createdUser.put("id", createdUserId);
    }

    @Test(priority = 2)
    public void retrieveUser() {
        System.out.println("<<<<<<<<< retrieveUser >>>>>>>>");
        Response response = RestAssured.given()
                .get("/api/users/" + createdUser.get("id"));

        // simple logging
        response.getBody().prettyPrint();

        System.out.println("Verifying Status Code...");
        softAssert.assertEquals(response.getStatusCode(), 200, "Status code mismatch");

        System.out.println("Verifying Response Body...");
        softAssert.assertEquals(response.jsonPath().getString("data.id"), createdUser.get("id"), "ID mismatch");
        softAssert.assertEquals(response.jsonPath().getString("data.name"), createdUser.get("name"), "Name mismatch");
        softAssert.assertEquals(response.jsonPath().getString("data.job"), createdUser.get("job"), "Job mismatch");

        // Assert all to report the failures
        softAssert.assertAll();
    }

    @Test(priority = 3)
    public void updateUser() {
        System.out.println("<<<<<<<<< updateUser >>>>>>>>");
        Response response = RestAssured.given()
                .body(createdUser)
                .put("/api/users/" + createdUser.get("id"));

        // simple logging
        response.getBody().prettyPrint();



        System.out.println("Verifying Status Code...");
        softAssert.assertEquals(response.getStatusCode(), 200, "Status code mismatch");

        System.out.println("Verifying Response Body...");
        softAssert.assertEquals(response.jsonPath().getString("name"), createdUser.get("name"), "Name mismatch");
        softAssert.assertEquals(response.jsonPath().getString("job"), createdUser.get("job"), "Job mismatch");

        // Assert all to report the failures
        softAssert.assertAll();
    }
}
