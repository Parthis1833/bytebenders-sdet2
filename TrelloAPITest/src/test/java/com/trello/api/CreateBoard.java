package com.trello.api;

import org.junit.jupiter.api.*;
import com.trello.api.Config.TrelloConfig;

import static io.restassured.RestAssured.*;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;

import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;

import java.io.File;
import java.nio.file.Files;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CreateBoard {

    public static String boardId;

    @BeforeAll
    public static void setup() {
        //base url
        TrelloConfig.setup(); 
    }

    @Test
    @Order(1)
    public void createBoard_ValidData_ShouldCreateSuccessfully() {
        Response response =
        given()
            .contentType(JSON)
            .queryParam("key", TrelloConfig.KEY)
            .queryParam("token", TrelloConfig.TOKEN)
            .queryParam("name", "ParthishBoard")
        .when()
            .post("/boards/")
        .then()
            .statusCode(200) //  Status code validation
            .body("id", notNullValue()) //  Body field validation
            .body("name", equalTo("ParthishBoard"))
            .body(matchesJsonSchema(new File("src/test/resources/schemas/createBoardSchema.json"))) // ✅ Schema validation
            .extract()
            .response();

        boardId = response.path("id");
        String boardName = response.path("name");

        //  Save ID and name to JSON file
        String jsonContent = "{\n" +
            "  \"id\": \"" + boardId + "\",\n" +
            "  \"name\": \"" + boardName + "\"\n" +
            "}";

        try {
            File file = new File("target/createdBoard.json");
            file.getParentFile().mkdirs();
            Files.write(file.toPath(), jsonContent.getBytes());
            System.out.println(" Board data saved to createdBoard.json");
        } catch (Exception e) {
            System.err.println(" Error saving board data: " + e.getMessage());
        }

        System.out.println(" Board Created with ID: " + boardId);
    }


    //key ,tokan  and name validation
    @Test
    @Order(2)
    public void createBoard_InvalidToken_ShouldReturnUnauthorized() {
        RestAssured.registerParser("text/plain", Parser.TEXT); 
        given()
            .contentType(JSON)
            .queryParam("key", TrelloConfig.KEY)
            .queryParam("token", "invalid_token")
            .queryParam("name", "UnauthorizedBoard")
        .when()
            .post("/boards/")
        .then()
            .statusCode(401) 
            .body(containsStringIgnoringCase("invalid")); 
    }
}