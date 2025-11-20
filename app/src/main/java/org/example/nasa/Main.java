package org.example.nasa;

import org.example.nasa.client.ApodClient;
import org.example.nasa.client.ImageDownloader;
import org.example.nasa.service.ApodService;
import org.example.nasa.service.JsonParserService;
import org.example.nasa.util.AsciiRenderer;

import java.nio.file.Files;
import java.nio.file.Path;

public class Main {
    public static void main(String[] args) throws Exception {
        ApodClient client = new ApodClient();
        JsonParserService parser = new JsonParserService();
        ImageDownloader downloader = new ImageDownloader();
        AsciiRenderer renderer = new AsciiRenderer();
        ApodService service = new ApodService(client, parser, downloader, renderer);

        String date = "2000-08-22";

        String ascii = service.getApodAsAscii(date, 400);

        Files.writeString(Path.of("ascii.txt"), ascii);
        System.out.println("Arquivo gerado: ascii.txt");
    }
}
