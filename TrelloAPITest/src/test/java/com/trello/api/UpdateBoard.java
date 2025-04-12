package com.trello.api;

import com.trello.api.Config.TrelloConfig;

import io.restassured.response.Response;

import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.*;
import static io.restassured.http.ContentType.JSON;


public class UpdateBoard {

    String boardId;

    @BeforeEach
    public void setup() {
        TrelloConfig.setup();
        boardId = "67fa9f88b1be270535731cf0"; 
        System.out.println("Board ID for updating: " + boardId);
    }

    @Test
    public void updateBoard() {
        String updateBody = "{ \"name\": \"ByteBenders Updated213e\" }";
      
        Response response = given()
            .queryParam("key", TrelloConfig.KEY)
            .queryParam("token", TrelloConfig.TOKEN)
            .contentType(JSON)
            .body(updateBody)
        .when()
            .put("/boards/" + boardId) 
        .then()
            .statusCode(200) 
            .extract()
            .response();

        System.out.println("Board updated with ID: " + boardId);
        System.out.println("Full JSON Response: ");
        System.out.println(response.prettyPrint());
    }

    @Test
    public void updateBoardWithInvalidId_ShouldReturnError() {
        given()
            .contentType(JSON)
            .queryParam("key", TrelloConfig.KEY)
            .queryParam("token", TrelloConfig.TOKEN)
         
        .then()
            .statusCode(404); 
    }
}
