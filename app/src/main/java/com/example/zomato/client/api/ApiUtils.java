package com.example.zomato.client.api;
import com.example.zomato.client.api.RetroClient;

public class ApiUtils {
    private final static String BASE_URL = "https://developers.zomato.com/api/v2.1/";
    public static Api getApiService(){
       return RetroClient.getClient(BASE_URL).create(Api.class);
    }
}


