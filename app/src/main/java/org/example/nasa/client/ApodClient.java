package org.example.nasa.client;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ApodClient {
    private static final String API_KEY = "9sgAf4QdZpsxs67q12X0SyWzDtKhHQp23oe6sAcS";
    private static final String BASE_URL = "https://api.nasa.gov/planetary/apod?api_key=";

    private final OkHttpClient client = new OkHttpClient();

    private String executeRequest(String url) throws Exception {
        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if(!response.isSuccessful()) {
                throw new RuntimeException("Error accessing API. HTTP code + " + response.code());
            }

            return response.body().string();
        }
    }

    public String getApodJson() throws Exception {
        String url = BASE_URL + API_KEY;
        return executeRequest(url);
    }

    public String getApodJsonByDate(String date) throws Exception {
        String url = BASE_URL + API_KEY + "&date=" + date;
        return executeRequest(url);
    }
}



