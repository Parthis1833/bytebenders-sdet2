package com.trello.api;


import com.trello.api.Config.TrelloConfig;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.*;

public class DeleteBoard {

    String boardId;

    @BeforeEach
    public void setup() {
        TrelloConfig.setup();
        boardId = System.getProperty("boardId");
    }

    @Test
    public void deleteBoard() {
        given()
            .queryParam("key", TrelloConfig.KEY)
            .queryParam("token", TrelloConfig.TOKEN)
        .when()
            .delete("/boards/" + boardId)
        .then()
            .statusCode(200);
    }
}
