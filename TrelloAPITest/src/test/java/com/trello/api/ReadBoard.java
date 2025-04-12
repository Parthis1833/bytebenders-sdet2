package com.trello.api;

import org.junit.jupiter.api.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.trello.api.Config.TrelloConfig;

import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.util.Map;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ReadBoard {

    private static String boardId;
    private static String boardName;

    @BeforeAll
    public static void setup() {
        TrelloConfig.setup(); 
        try {
            ObjectMapper mapper = new ObjectMapper();
            Map<String, String> boardData = mapper.readValue(new File("target/createdBoard.json"), Map.class);
            boardId = boardData.get("id");
            boardName = boardData.get("name");
        } catch (Exception e) {
            Assertions.fail(" Could not read createdBoard.json: " + e.getMessage());
        }
    }

    @Test
    @Order(1)
    public void readCreatedBoard() {
        // GET request to read the board
        Response response = given()
            .queryParam("key", TrelloConfig.KEY)
            .queryParam("token", TrelloConfig.TOKEN)
        .when()
            .get("/boards/" + boardId)
        .then()
            .statusCode(200) // Validate status code
            .body("id", equalTo(boardId)) // Validate the board ID in the response
            .body("name", equalTo(boardName)) // Validate the board name
            .extract()
            .response();

        System.out.println("Board fetched with ID: " + boardId + " and Name: " + boardName);
        System.out.println("Full JSON Response: ");
        System.out.println(response.prettyPrint()); // Print 
    }

    @Test
    @Order(2)
    public void readBoardWithInvalidId_ShouldReturnError() {
        given()
            .queryParam("key", TrelloConfig.KEY)
            .queryParam("token", TrelloConfig.TOKEN)
        .when()
            .get("/boards/invalidBoardId123")
        .then()
            .statusCode(400); 
          }
}
