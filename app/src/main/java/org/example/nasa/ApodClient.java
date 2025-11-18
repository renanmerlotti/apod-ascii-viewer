package org.example.nasa;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ApodClient {
    private static final String API_KEY = "DEMO_KEY";
    private static final String BASE_URL = "https://api.nasa.gov/planetary/apod?api_key=";

    private final OkHttpClient client = new OkHttpClient();

    public String getApodJson() throws Exception {
        String url = BASE_URL + API_KEY;

        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()){
            if(!response.isSuccessful()) {
                throw new RuntimeException("Erro ao acessar API. HTTP code: " + response.code());
            }

            return response.body().string();
        }
    }
}
