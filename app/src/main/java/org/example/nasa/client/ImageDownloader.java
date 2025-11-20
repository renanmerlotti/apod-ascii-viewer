package org.example.nasa.client;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class ImageDownloader {
    private final OkHttpClient client = new OkHttpClient();

    public byte[] download(String imageUrl) throws Exception {
        Request request = new Request.Builder()
                .url(imageUrl)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if(!response.isSuccessful()) {
                throw new RuntimeException("Error downloading image. HTTP code: " + response.code());
            }

            ResponseBody body = response.body();

            if(body == null) {
                throw new RuntimeException("Image body is null");
            }

            return body.bytes();
        }
    }
}


