package com.spotify.oauth2.tests;

import com.spotify.oauth2.api.StatusCodes;
import com.spotify.oauth2.api.applicationApi.PlayListApi;
import com.spotify.oauth2.pojo.Playlist;
import com.spotify.oauth2.reuse.Reusable;
import com.spotify.oauth2.utils.FakeUtils;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@Epic("Spotify Oauth 2.0")
@Feature("PlayList API")
public class AddPlayListTest extends BaseTest{

    @Story("Create a Play List Story")
    @Link("https://example.org")
    @Link(name ="allure", type ="myLink")
    @TmsLink("12345")
    @Issue("1234545")
    @Description("Shareef")
    @Test(description = "Mother")
    public void shouldBeAbleToCreateAPlayList() throws FileNotFoundException {


        //Play List class consist of pojo information for Play list data
        Playlist requestPlaylist = Reusable.playlistBuilder(FakeUtils.generateName(),FakeUtils.generateDescription(), false);
        //getting data in response
        Response response = PlayListApi.post(requestPlaylist);
        assertThat(response.statusCode(), equalTo(StatusCodes.CODE_201.getCode()));

        //Deserialization the data
        Playlist responsePlayList = response.as(Playlist.class);

        //compare the expected and actual values
        assertThat(responsePlayList.getName(), equalTo(requestPlaylist.getName()));
        assertThat(responsePlayList.getDescription(), equalTo(requestPlaylist.getDescription()));
        assertThat(responsePlayList.get_public(), equalTo(requestPlaylist.get_public()));

    }

}
