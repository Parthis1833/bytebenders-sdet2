package com.trello.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trello.api.Config.TrelloConfig;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.*;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class ReadAllBoards {

    private static final String BOARD_FILE_PATH = "target/boardDetails.json";

    @BeforeAll
    public static void setup() {
        TrelloConfig.setup(); // Base URI etc.
    }

    @Test
    @Order(1)
    public void readAllBoards() throws IOException {
      
        List<Map<String, Object>> boards = given()
            .queryParam("key", TrelloConfig.KEY)
            .queryParam("token", TrelloConfig.TOKEN)
        .when()
            .get("/members/me/boards") 
            .then()
            .statusCode(200)//status validation
            .extract().body().jsonPath().getList("$"); 


        System.out.println("Fetched boards: " + boards);

        Map<String, String> boardDetails = new java.util.HashMap<>();

        // save  ID and Name
        for (Map<String, Object> board : boards) {
            String boardId = (String) board.get("id");
            String boardName = (String) board.get("name");
            boardDetails.put(boardId, boardName);

            //  Print  board's ID and Name
            System.out.println("Board ID: " + boardId + ", Board Name: " + boardName);
        }

        // Save  JSON file
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(new File(BOARD_FILE_PATH), boardDetails);

        System.out.println("✅ Board details saved to: " + BOARD_FILE_PATH);
    }
}
