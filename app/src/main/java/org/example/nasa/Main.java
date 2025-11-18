package org.example.nasa;

public class Main {
    public static void main(String[] args) {
        ApodClient client = new ApodClient();

        try {
            String json = client.getApodJson();
            System.out.println("=== APOD JSON (primeiros 1000 chars) ===");
            System.out.println(json.length() > 1000 ? json.substring(0, 1000) + "...\n(omitted)" : json);
        } catch (Exception e) {
            System.err.println("Falha ao buscar APOD: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
