package com.spotify.oauth2.api;

import io.restassured.response.Response;

import java.util.HashMap;

import static com.spotify.oauth2.api.Route.API;
import static com.spotify.oauth2.api.Route.TOKEN;
import static com.spotify.oauth2.api.SpecBuilder.getAccountRequestSpec;
import static io.restassured.RestAssured.given;

public class RestResource {


    public static Response post(String path, String token, Object requestPlaylist){

        return given().spec(SpecBuilder.getRequestSpec()).body(requestPlaylist)
                .auth().oauth2(token)
                //.header("Authorization", "Bearer "+ token)
                .when().post(path)
                .then().spec(SpecBuilder.getResponseSpec())
                .extract().response();
    }

    public static Response get(String path, String token){

        return given().spec(SpecBuilder.getRequestSpec())
                .auth().oauth2(token)
                //.header("Authorization", "Bearer "+ token)
                .when().get(path)
                .then().spec(SpecBuilder.getResponseSpec())
                .extract().response();
    }

    public static Response put(Object requestPlaylist, String token, String path){
        return given().spec(SpecBuilder.getRequestSpec()).body(requestPlaylist)
                .auth().oauth2(token)
                //.header("Authorization", "Bearer "+ token)
                .when().put(path)
                .then().extract().response();
    }

    public static Response postAccount(HashMap<String, String> formParams){
        return given().spec(getAccountRequestSpec())
                .formParams(formParams)
                .when().post(API+TOKEN)
                .then().spec(SpecBuilder.getResponseSpec())
                .extract()
                .response();
    }

}
