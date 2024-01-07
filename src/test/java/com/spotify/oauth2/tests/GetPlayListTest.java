package com.spotify.oauth2.tests;

import com.spotify.oauth2.api.StatusCodes;
import com.spotify.oauth2.api.applicationApi.PlayListApi;
import com.spotify.oauth2.pojo.Playlist;
import com.spotify.oauth2.reuse.Reusable;
import com.spotify.oauth2.utils.DataLoader;
import com.spotify.oauth2.utils.FakeUtils;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class GetPlayListTest extends BaseTest{


    @Test
    public void getPlayList() throws FileNotFoundException {

        Playlist requestPlaylist = Reusable.playlistBuilder(FakeUtils.generateName(),FakeUtils.generateDescription(), false);
        Response response = PlayListApi.get(DataLoader.getInstance().getGetPlayListID());
        assertThat(response.statusCode(), equalTo(StatusCodes.CODE_200.getCode()));

        Playlist responsePlayList =response.as(Playlist.class);


        assertThat(responsePlayList.getName(), equalTo(requestPlaylist.getName()));
        assertThat(responsePlayList.getDescription(), equalTo(requestPlaylist.getDescription()));
        assertThat(false, equalTo(requestPlaylist.get_public()));
    }
}
