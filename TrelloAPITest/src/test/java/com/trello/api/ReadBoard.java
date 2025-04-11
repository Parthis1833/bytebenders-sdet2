package com.trello.api;

import com.trello.api.Config.TrelloConfig;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class ReadBoard {

    String boardId;

    @BeforeEach
    public void setup() {
        TrelloConfig.setup();
        boardId = System.getProperty("boardId");
    }

    @Test
    public void readBoard() {
        given()
            .queryParam("key", TrelloConfig.KEY)
            .queryParam("token", TrelloConfig.TOKEN)
        .when()
            .get("/boards/" + boardId)
        .then()
            .statusCode(200)
            .body("id", equalTo(boardId));
    }
}
