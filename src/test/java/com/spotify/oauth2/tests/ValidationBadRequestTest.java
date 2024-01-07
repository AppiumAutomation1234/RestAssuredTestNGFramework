package com.spotify.oauth2.tests;

import com.spotify.oauth2.api.StatusCodes;
import com.spotify.oauth2.api.applicationApi.PlayListApi;
import com.spotify.oauth2.pojo.Error;
import com.spotify.oauth2.pojo.Playlist;
import com.spotify.oauth2.reuse.Reusable;
import com.spotify.oauth2.utils.FakeUtils;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ValidationBadRequestTest extends BaseTest{

    @Test
    public void validationBadRequest() {
        Playlist requestPlaylist = Reusable.playlistBuilder("", FakeUtils.generateDescription(), false);
        Response response = PlayListApi.put(requestPlaylist,"5agmkIlySAOE8iljBz3Jz3");
        assertThat(response.statusCode(),equalTo(StatusCodes.CODE_400.getCode()));

        Error error = response.as(Error.class);

        assertThat(error.getError().getStatus(), equalTo(StatusCodes.CODE_400.getCode()));
        assertThat(error.getError().getMessage(), equalTo(StatusCodes.CODE_400.getMsg()));
    }
}
