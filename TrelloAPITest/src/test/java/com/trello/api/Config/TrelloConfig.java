package com.trello.api.Config;

import io.restassured.RestAssured;

public class TrelloConfig {

    public static final String KEY = System.getenv("7a80158f491840d73839883eff86b6bd");
    public static final String TOKEN = System.getenv("ATTAb2ed8e844cc29f58477df79dab1ec028094248997892955798af3bb81807abd9E2A16E25");
    public static final String BASE_URI = "https://api.trello.com/1";

    public static void setup() {
        RestAssured.baseURI = BASE_URI;
    }
}