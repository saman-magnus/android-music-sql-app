package com.example.musicapp.API;

public class APIService {
    private static String base_url = "https://www.parsclip.com";

    public static Dataservice getService() {
        return APIRetrofitClient.getClient(base_url).create(Dataservice.class);
    }
}
