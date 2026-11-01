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

    @Test
    public void testMonthlyScheduleRouteWithoutExtension() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:" + TEST_PORT + "/management/monthly-schedule"))
                .GET()
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        Assert.assertEquals(response.statusCode(), 200, "Expected HTTP 200 for /management/monthly-schedule");
        Assert.assertTrue(response.body().contains("HADIR - Monthly Schedule"), "HTML should contain page title");
        Assert.assertTrue(response.body().contains("Import Monthly Schedule"), "HTML should contain import section");
        Assert.assertTrue(response.body().contains("modal-edit-schedule"), "HTML should contain edit modal");
        Assert.assertTrue(response.body().contains("modal-delete-schedule"), "HTML should contain delete modal");
    }

    @Test
    public void testLaporanActivityRoute() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:" + TEST_PORT + "/laporan/activity"))
                .GET()
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        Assert.assertEquals(response.statusCode(), 200, "Expected HTTP 200 for /laporan/activity");
        Assert.assertTrue(response.body().contains("HADIR - Laporan Activity"), "HTML should contain page title");
        Assert.assertTrue(response.body().contains("table-laporan"), "HTML should contain table-laporan");
        Assert.assertTrue(response.body().contains("btn-search"), "HTML should contain btn-search");
    }
    @Test
    public void testUserMonitoringRouteWithoutExtension() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:" + TEST_PORT + "/management/user-monitoring"))
                .GET()
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        Assert.assertEquals(response.statusCode(), 200, "Expected HTTP 200 for /management/user-monitoring");
        Assert.assertTrue(response.body().contains("HADIR - User Monitoring"), "HTML should contain page title");
        Assert.assertTrue(response.body().contains("user-monitoring-table"), "HTML should contain table");
        Assert.assertTrue(response.body().contains("modal-tambah-user"), "HTML should contain add modal");
        Assert.assertTrue(response.body().contains("modal-edit-user"), "HTML should contain edit modal");
        Assert.assertTrue(response.body().contains("modal-delete-user"), "HTML should contain delete modal");
        Assert.assertTrue(response.body().contains("modal-filter-user"), "HTML should contain filter modal");
    }

    @Test
    public void testDepartemensRoute() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:" + TEST_PORT + "/management/departemens"))
                .GET()
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        Assert.assertEquals(response.statusCode(), 200, "Expected HTTP 200 for /management/departemens");
        Assert.assertTrue(response.body().contains("HADIR - Departemens"));
        Assert.assertTrue(response.body().contains("btn-tambah-departemen"));
    }

    @Test
    public void testPositionRoute() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:" + TEST_PORT + "/management/position"))
                .GET()
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        Assert.assertEquals(response.statusCode(), 200, "Expected HTTP 200 for /management/position");
        Assert.assertTrue(response.body().contains("HADIR - Position"));
        Assert.assertTrue(response.body().contains("btn-tambah-position"));
    }

    @Test
    public void testLocationPointRoute() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:" + TEST_PORT + "/management/location-point"))
                .GET()
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        Assert.assertEquals(response.statusCode(), 200, "Expected HTTP 200 for /management/location-point");
        Assert.assertTrue(response.body().contains("HADIR - Location Point"));
        Assert.assertTrue(response.body().contains("btn-tambah-lokasi"));
    }

    @Test
    public void testShiftingRoute() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:" + TEST_PORT + "/management/shifting"))
                .GET()
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        Assert.assertEquals(response.statusCode(), 200, "Expected HTTP 200 for /management/shifting");
        Assert.assertTrue(response.body().contains("HADIR - Shifting"));
        Assert.assertTrue(response.body().contains("btn-tambah-shifting"));
    }

    @Test
    public void testScheduleRoute() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:" + TEST_PORT + "/management/schedule"))
                .GET()
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        Assert.assertEquals(response.statusCode(), 200, "Expected HTTP 200 for /management/schedule");
        Assert.assertTrue(response.body().contains("HADIR - Schedule"));
        Assert.assertTrue(response.body().contains("btn-tambah-jadwal"));
    }

    @Test
    public void testOtReasonRoute() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:" + TEST_PORT + "/management/ot-reason"))
                .GET()
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        Assert.assertEquals(response.statusCode(), 200, "Expected HTTP 200 for /management/ot-reason");
        Assert.assertTrue(response.body().contains("HADIR - Overtime Reason"));
        Assert.assertTrue(response.body().contains("btn-tambah-ot"));
    }

    @Test
    public void testLeaderIzinOffRoute() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:" + TEST_PORT + "/leader/izin-off"))
                .GET()
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        Assert.assertEquals(response.statusCode(), 200, "Expected HTTP 200 for /leader/izin-off");
        Assert.assertTrue(response.body().contains("HADIR - Leader Izin Off"), "HTML should contain page title");
        Assert.assertTrue(response.body().contains("table-izin-off"), "HTML should contain table");
        Assert.assertTrue(response.body().contains("modal-approval-izin-off"), "HTML should contain approval modal");
        Assert.assertTrue(response.body().contains("modal-tambah-izin-off"), "HTML should contain add modal");
        Assert.assertTrue(response.body().contains("modal-detail-izin-off"), "HTML should contain detail modal");
    }

    @Test
    public void testLeaderStaffListsRoute() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:" + TEST_PORT + "/leader/staff/lists"))
                .GET()
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        Assert.assertEquals(response.statusCode(), 200, "Expected HTTP 200 for /leader/staff/lists");
        Assert.assertTrue(response.body().contains("HADIR - Leader Staff Lists"), "HTML should contain page title");
        Assert.assertTrue(response.body().contains("staff-table"), "HTML should contain staff-table");
        Assert.assertTrue(response.body().contains("modal-tambah-staff"), "HTML should contain add staff modal");
        Assert.assertTrue(response.body().contains("modal-edit-staff"), "HTML should contain edit staff modal");
        Assert.assertTrue(response.body().contains("modal-detail-staff"), "HTML should contain detail staff modal");
        Assert.assertTrue(response.body().contains("modal-delete-staff"), "HTML should contain delete staff modal");
        Assert.assertTrue(response.body().contains("modal-filter-staff"), "HTML should contain filter staff modal");
    }

    @Test
    public void testLeaderAllRoutes() throws IOException, InterruptedException {
        String[] leaderRoutes = {
                "/leader/all",
                "/leader/kehadiran",
                "/leader/koreksi",
                "/leader/generate",
                "/leader/cuti/semua",
                "/leader/cuti/pending",
                "/leader/lembur/semua",
                "/leader/lembur/pending",
                "/leader/shifting/semua",
                "/leader/shifting/pending"
        };

        for (String route : leaderRoutes) {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("http://localhost:" + TEST_PORT + route))
                    .GET()
                    .build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            Assert.assertEquals(response.statusCode(), 200, "Expected HTTP 200 for " + route);
            Assert.assertTrue(response.body().contains("HADIR - Leader"), "HTML should contain Leader page title for " + route);
        }
    }
}
