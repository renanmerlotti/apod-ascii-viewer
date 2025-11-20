package org.example.nasa.service;

import org.example.nasa.client.ApodClient;
import org.example.nasa.client.ImageDownloader;
import org.example.nasa.model.ApodResponse;
import org.example.nasa.util.AsciiRenderer;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;

public class ApodService {
    private final ApodClient client;
    private final JsonParserService parser;
    private final ImageDownloader downloader;
    private final AsciiRenderer renderer;

    public ApodService(ApodClient client, JsonParserService parser,
                       ImageDownloader downloader, AsciiRenderer renderer) {
        this.client = client;
        this.parser = parser;
        this.downloader = downloader;
        this.renderer = renderer;
    }

    public String getApodAsAscii(int width) throws Exception {
        String json = client.getApodJson();
        ApodResponse apod = parser.parse(json);
        return convertToAscii(apod, width);
    }

    public String getApodAsAscii(String date, int width) throws Exception {
        String json = client.getApodJsonByDate(date);
        ApodResponse apod = parser.parse(json);
        return convertToAscii(apod, width);
    }

    private String convertToAscii(ApodResponse apod, int width) throws Exception {
        byte[] imageBytes = downloader.download(apod.getHdurl());
        BufferedImage image = ImageIO.read(new ByteArrayInputStream(imageBytes));
        return renderer.render(image, width);
    }
}


