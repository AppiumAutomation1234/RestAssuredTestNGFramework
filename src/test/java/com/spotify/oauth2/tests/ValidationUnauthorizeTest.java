package com.spotify.oauth2.tests;

import com.spotify.oauth2.api.StatusCodes;
import com.spotify.oauth2.api.applicationApi.PlayListApi;
import com.spotify.oauth2.pojo.Error;
import com.spotify.oauth2.pojo.Playlist;
import com.spotify.oauth2.reuse.Reusable;
import com.spotify.oauth2.utils.FakeUtils;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ValidationUnauthorizeTest extends BaseTest{

    @Test
    public void validationUnauthorize() throws FileNotFoundException {

        Playlist requestPlaylist = Reusable.playlistBuilder(FakeUtils.generateName(),FakeUtils.generateDescription(), false);
        String invalid_Token = "23444";
        Response response = PlayListApi.post(invalid_Token, requestPlaylist);
        assertThat(response.statusCode(), equalTo(StatusCodes.CODE_401.getCode()));
        Error error = response.as(Error.class);

        assertThat(error.getError().getStatus(), equalTo(StatusCodes.CODE_401.getCode()));
        assertThat(error.getError().getMessage(), equalTo(StatusCodes.CODE_401.getMsg()));
    }
}
