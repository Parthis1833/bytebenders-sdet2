package com.trello.api;

import com.trello.api.Config.TrelloConfig;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.*;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.*;

public class UpdateBoard {

    String boardId;

    @BeforeEach
    public void setup() {
        TrelloConfig.setup();
        boardId = System.getProperty("boardId");
    }

    @Test
    public void updateBoard() {
        given()
            .contentType(JSON)
            .queryParam("key", TrelloConfig.KEY)
            .queryParam("token", TrelloConfig.TOKEN)
            .body("{ \"name\": \"ByteBenders Updated\" }")
        .when()
            .put("/boards/" + boardId)
        .then()
            .statusCode(200)
            .body("name", equalTo("ByteBenders Updated"));
    }
}
