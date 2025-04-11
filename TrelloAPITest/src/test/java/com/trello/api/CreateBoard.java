package com.trello.api;

import org.junit.jupiter.api.*;
import com.trello.api.Config.TrelloConfig;
import static io.restassured.RestAssured.*;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CreateBoard {

    public static String boardId;

    @BeforeAll
    public static void setup() {
        TrelloConfig.setup();
    }

    @Test
    @Order(1)
    public void createBoard() {
        boardId = 
        given()
            .contentType(JSON)
            .queryParam("key", TrelloConfig.KEY)
            .queryParam("token", TrelloConfig.TOKEN)
            .body("{ \"name\": \"ByteBenders\" }")
        .when()
            .post("/boards/")
        .then()
            .statusCode(200)
            .body("name", equalTo("ByteBenders"))
            .extract()
            .path("id");

        System.setProperty("boardId", boardId);
    }
}
