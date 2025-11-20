package org.example.nasa.service;

import com.google.gson.Gson;
import org.example.nasa.model.ApodResponse;

public class JsonParserService {
    private final Gson gson = new Gson();

    public ApodResponse parse(String json) {
        return gson.fromJson(json, ApodResponse.class);
    }
}
