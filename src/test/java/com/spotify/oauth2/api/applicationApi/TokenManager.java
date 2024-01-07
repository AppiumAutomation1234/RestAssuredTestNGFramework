package com.spotify.oauth2.api.applicationApi;

import com.spotify.oauth2.api.RestResource;
import com.spotify.oauth2.utils.ConfigLoader;
import io.restassured.response.Response;

import java.io.FileNotFoundException;
import java.time.Instant;
import java.util.HashMap;

public class TokenManager {
    private static String access_Token;
    private static Instant expiry_time;
    public synchronized static String getToken() {
        try {
            if (access_Token == null || Instant.now().isAfter(expiry_time)) {
                System.out.println("Renewing token .......");
                Response response = renewToken();
                access_Token = response.path("access_token");
                int expiryDurationInSeconds = response.path("expires_in");
                expiry_time = Instant.now().plusSeconds(expiryDurationInSeconds - 300);
            } else {
                System.out.println("Token is good to use");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("ABORT!!! Failed to get token");
        }

        return access_Token;
    }

    private static Response renewToken() throws FileNotFoundException {

        HashMap<String, String> formParams = new HashMap<String, String>();
        formParams.put("client_id", ConfigLoader.getInstance().getClientID());
        formParams.put("client_secret", ConfigLoader.getInstance().getClientSecret());
        formParams.put("grant_type", ConfigLoader.getInstance().getGrantType());
        formParams.put("refresh_token", ConfigLoader.getInstance().getRefreshToken());

        Response response = RestResource.postAccount(formParams);

        if (response.statusCode() != 200) {

            throw new RuntimeException("ABORT!!! Renew Token generation failed");
        }
        return response;
    }

}
