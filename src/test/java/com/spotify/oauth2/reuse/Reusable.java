package com.spotify.oauth2.reuse;

import com.spotify.oauth2.pojo.Playlist;
import io.qameta.allure.Step;

public class Reusable {

    @Step
    public static Playlist playlistBuilder(String name, String description, boolean _public){
        Playlist playlist = new Playlist();

        playlist.setName(name);
        playlist.setDescription(description);
        playlist.set_public(_public);

        return playlist;
    }

}
