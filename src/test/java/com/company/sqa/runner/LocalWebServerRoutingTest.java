package com.company.sqa.runner;

import com.company.sqa.utils.LocalWebServer;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class LocalWebServerRoutingTest {

    private static final int TEST_PORT = 8089;
    private HttpClient client;

    @BeforeClass
    public void setup() {
        LocalWebServer.start(TEST_PORT);
        client = HttpClient.newHttpClient();
    }

    @AfterClass
    public void tearDown() {
        LocalWebServer.stop();
    }

    @Test
    public void testDivisiRouteWithoutExtension() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:" + TEST_PORT + "/management/divisi"))
                .GET()
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        Assert.assertEquals(response.statusCode(), 200, "Expected HTTP 200 for /management/divisi");
        Assert.assertTrue(response.body().contains("HADIR - Divisi"), "HTML should contain page title");
        Assert.assertTrue(response.body().contains("Tambahkan"), "HTML should contain Tambahkan button");
        Assert.assertTrue(response.body().contains("cari berdasarkan nama"), "HTML should contain search placeholder");
        Assert.assertTrue(response.body().contains("modal-tambah-divisi"), "HTML should contain modal-tambah-divisi");
    }

    @Test
    public void testDivisiRouteWithExtension() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:" + TEST_PORT + "/management/divisi.html"))
                .GET()
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        Assert.assertEquals(response.statusCode(), 200, "Expected HTTP 200 for /management/divisi.html");
        Assert.assertTrue(response.body().contains("HADIR - Divisi"));
    }

    @Test
    public void testDivisiNextJsChunkDownloaded() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:" + TEST_PORT + "/_next/static/chunks/pages/management/divisi-8a158dfa0e346947.js"))
                .GET()
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        Assert.assertEquals(response.statusCode(), 200, "Next.js page chunk should be accessible");
        Assert.assertTrue(response.headers().firstValue("Content-Type").orElse("").contains("javascript"));
    }

    @Test
    public void testChunk3457() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:" + TEST_PORT + "/_next/static/chunks/3457-7d98ff9b4c5f60ce.js"))
                .GET()
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        Assert.assertEquals(response.statusCode(), 200, "Chunk 3457 should be accessible");
    }

    @Test
    public void testChunk848() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:" + TEST_PORT + "/_next/static/chunks/848-db047b83fd5075c8.js"))
                .GET()
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        Assert.assertEquals(response.statusCode(), 200, "Chunk 848 should be accessible");
    }
}
