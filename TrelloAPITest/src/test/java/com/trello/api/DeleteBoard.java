package com.trello.api;

import com.trello.api.Config.TrelloConfig;

import io.restassured.response.Response;

import org.junit.jupiter.api.*;
import static io.restassured.RestAssured.*;

public class DeleteBoard {

    String boardId;

    @BeforeEach
    public void setup() {
        TrelloConfig.setup();
        
        // Directly assign the boardId here
        boardId = "67faa1df8ca5f811f049d03d"; // The board ID you want to delete
        System.out.println("Board ID for deletion: " + boardId);
    }

    @Test
    public void deleteBoard() {
        // Print the response to check the status
        Response response = given()
            .queryParam("key", TrelloConfig.KEY)
            .queryParam("token", TrelloConfig.TOKEN)
            .when()
            .delete("/boards/" + boardId);

        // Print the response body to help diagnose any issues
        System.out.println(response.body().asString());

        // Check the status code and ensure it's 200
        response.then().statusCode(200);
    }

    @Test
    public void deleteBoardWithInvalidId_ShouldReturnNotFound() {
       
        given()
            .queryParam("key", TrelloConfig.KEY)
            .queryParam("token", TrelloConfig.TOKEN)
        .when()
        .then()
            .statusCode(404); // Expect 404 for not found error
    }
}
