package com.spotify.oauth2.api.applicationApi;

import com.spotify.oauth2.api.RestResource;
import com.spotify.oauth2.pojo.Playlist;
import com.spotify.oauth2.utils.ConfigLoader;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import java.io.FileNotFoundException;

import static com.spotify.oauth2.api.Route.PLAYLISTS;
import static com.spotify.oauth2.api.Route.USERS;
import static com.spotify.oauth2.api.applicationApi.TokenManager.getToken;

public class PlayListApi {

    @Step
   public static Response post(Playlist requestPlaylist) throws FileNotFoundException {
       return RestResource.post(USERS+"/"+ ConfigLoader.getInstance().getUserID() +PLAYLISTS, getToken(), requestPlaylist);
   }

    @Step
    public static Response post(String token, Playlist requestPlaylist) throws FileNotFoundException {
        return RestResource.post(USERS+"/"+ ConfigLoader.getInstance().getUserID() +PLAYLISTS, token, requestPlaylist);
    }

    @Step
    public static Response get(String playListID){
        return RestResource.get(PLAYLISTS+"/"+playListID+"",getToken());
    }

    @Step
    public static Response put(Playlist requestPlaylist, String playListID){
        return RestResource.put(requestPlaylist,getToken(),""+PLAYLISTS+"/"+playListID);
    }

}
