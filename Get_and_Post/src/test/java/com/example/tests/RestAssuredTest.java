package com.example.tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class RestAssuredTest {

    @Test
    public void testGetRequest() {
        // Base URI
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";

        // GET request
        Response response = given()
                .when()
                .get("/posts/1")
                .then()
                .statusCode(200)
                .body("userId", equalTo(1))
                .body("id", equalTo(1))
                .body("title", notNullValue())
                .extract()
                .response();

        // Print the response
        System.out.println(response.asString());
    }

    @Test
    public void testPostRequest() {
        // Base URI
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";

        // POST request
        String requestBody = "{\"title\": \"foo\", \"body\": \"bar\", \"userId\": 1}";

        Response response = given()
                .header("Content-type", "application/json")
                .and()
                .body(requestBody)
                .when()
                .post("/posts")
                .then()
                .statusCode(201)
                .body("id", notNullValue())
                .extract()
                .response();

        // Print the response
        System.out.println(response.asString());
    }
}
