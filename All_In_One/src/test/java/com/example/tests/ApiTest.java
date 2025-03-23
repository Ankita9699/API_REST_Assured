package com.example.tests;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import io.restassured.http.ContentType;
import org.testng.annotations.Test;
import java.util.HashMap;
import java.util.Map;

public class ApiTest {
    private static final String BASE_URL = "https://jsonplaceholder.typicode.com/posts";

    // GET Request - Fetch All Posts
    @Test(priority = 1)
    public void testGetAllPosts() {
        given()
                .when()
                .get(BASE_URL)
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0)) // Ensure the response contains data
                .log().all();
    }

    // POST Request - Create a New Post
    @Test(priority = 2)
    public void testCreatePost() {
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("title", "Advanced API Testing");
        requestBody.put("body", "This is a test post using RestAssured.");
        requestBody.put("userId", 1);

        given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post(BASE_URL)
                .then()
                .statusCode(201)
                .body("title", equalTo("Advanced API Testing"))
                .body("userId", equalTo(1))
                .log().all();
    }

    // PUT Request - Update an Existing Post
    @Test(priority = 3)
    public void testUpdatePost() {
        Map<String, Object> updateData = new HashMap<>();
        updateData.put("title", "Updated Title");
        updateData.put("body", "Updated Body Content");

        given()
                .contentType(ContentType.JSON)
                .body(updateData)
                .when()
                .put(BASE_URL + "/1")
                .then()
                .statusCode(200)
                .body("title", equalTo("Updated Title"))
                .log().all();
    }

    // DELETE Request - Delete a Post
    @Test(priority = 4)
    public void testDeletePost() {
        given()
                .when()
                .delete(BASE_URL + "/1")
                .then()
                .statusCode(200)
                .log().all();
    }
}

