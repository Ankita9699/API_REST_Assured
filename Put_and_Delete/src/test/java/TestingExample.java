import static io.restassured.RestAssured.*;
import static jdk.internal.vm.vector.VectorSupport.extract;
import static org.hamcrest.Matchers.*;

import io.restassured.response.ValidatableResponse;
import org.testng.annotations.Test;
import java.lang.*;
import java.util.HashMap;



class TestingExample {
    int userId;


    @Test(priority = 1)
    public void getUsers() {
        given()
                .when()
                .get("https://reqres.in/api/users?page=2")
                .then()
                .statusCode(200)  // Checks if response status is 200
                .body("page", equalTo(2))
                .body(containsString("email"))// Validates 'page' value in JSON response
                .log().all();
    }


    @Test(priority = 2)
    public void createUsers() {
        HashMap<String, String> data = new HashMap<>();
        data.put("name","Ankuri");
        data.put("job","Tester");
        Object userID = given()
                .contentType("Application/json")
                .body(data)
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .statusCode(201)
                .body("name", equalTo("Ankuri"))
                .body("job", equalTo("Tester"))
                .body(containsString("id"))
                .log().all()
                .extract().jsonPath().getInt("id");
    }
    @Test(priority = 3)
    public void updateUsers() {
        HashMap<String, String> data = new HashMap<>();
        data.put("name","Ankur");
        data.put("job","CEO");
        given()
                .contentType("Application/json")
                .body(data)
                .when()
                .put("https://reqres.in/api/users/"+userId)
                .then()
                .statusCode(200)
                .body("name",equalTo("Ankur"))
                .body("job",equalTo("CEO"))
                .log().all();
    }
    @Test(priority = 4)
    public void deleteUsers() {
        given()
                .when()
                .delete("https://reqres.in/api/users/2")
                .then()
                .statusCode(204)  // Expecting 204 No Content
                .log().all();  // Log all details for debugging
    }
}
