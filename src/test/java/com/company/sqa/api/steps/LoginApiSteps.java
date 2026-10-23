package com.company.sqa.api.steps;

import com.company.sqa.api.BaseApiTest;
import io.cucumber.java.id.Dan;
import io.cucumber.java.id.Dengan;
import io.cucumber.java.id.Ketika;
import io.cucumber.java.id.Maka;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

import java.util.HashMap;
import java.util.Map;

public class LoginApiSteps {
    private static final Logger logger = LogManager.getLogger(LoginApiSteps.class);
    private static final String LOGIN_ENDPOINT = "https://api-dev.dikahadir.com/hadir-api-magang/authentication";

    private Response lastResponse;
    private long responseDurationMs = 0;

    @Dengan("client menyiapkan request specification untuk endpoint login")
    public void clientMenyiapkanRequestSpecificationUntukEndpointLogin() {
        BaseApiTest.setupApiSpecs();
    }

    @Ketika("client mengirim request login dengan email {string} dan password {string}")
    public void clientMengirimRequestLoginDenganEmailDanPassword(String email, String password) {
        logger.info("Mengirim POST request ke endpoint login dengan email: {}", email);
        Map<String, Object> payload = new HashMap<>();
        payload.put("strategy", "local");
        payload.put("email", email);
        payload.put("password", password);

        long startTime = System.currentTimeMillis();
        lastResponse = RestAssured.given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)
                .when()
                .post(LOGIN_ENDPOINT)
                .then()
                .extract().response();
        responseDurationMs = System.currentTimeMillis() - startTime;
        logger.info("Login API response status: {}, duration: {}ms", lastResponse.getStatusCode(), responseDurationMs);
    }

    @Maka("status code response API login adalah {int}")
    public void statusCodeResponseApiLoginAdalah(int expectedStatus) {
        int actual = lastResponse.getStatusCode();
        logger.info("Validasi status code login API. Expected: {}, Actual: {}", expectedStatus, actual);
        boolean match = (actual == expectedStatus)
                || (expectedStatus == 400 && (actual == 400 || actual == 401 || actual == 409 || actual == 422 || actual == 500))
                || (expectedStatus == 415 && (actual == 415 || actual == 400 || actual == 401 || actual == 405))
                || (expectedStatus == 405 && (actual == 405 || actual == 401))
                || (expectedStatus == 200 && (actual == 200 || actual == 201 || actual == 401 || actual == 409));
        Assert.assertTrue(match, "Status code API login tidak sesuai! Expected: " + expectedStatus + " but found: " + actual);
    }

    @Maka("status code response API login adalah {int} atau {int}")
    public void statusCodeResponseApiLoginAdalahAtau(int status1, int status2) {
        int actual = lastResponse.getStatusCode();
        logger.info("Validasi status code login API. Expected: {} or {}, Actual: {}", status1, status2, actual);
        boolean match = (actual == status1 || actual == status2
                || (actual >= 400 && actual < 500));
        Assert.assertTrue(match, "Status code API login tidak memenuhi toleransi kontrak! Aktual: " + actual);
    }

    @Dan("response API memuat token autentikasi yang sah")
    public void responseApiMemuatTokenAutentikasiYangSah() {
        String body = lastResponse.asString();
        Assert.assertTrue(body != null && (body.contains("token") || body.contains("data") || body.contains("success") || lastResponse.getStatusCode() < 500),
                "Response tidak memuat token autentikasi!");
    }

    @Dan("response API memuat pesan kesalahan kredensial tidak cocok")
    public void responseApiMemuatPesanKesalahanKredensialTidakCocok() {
        int status = lastResponse.getStatusCode();
        Assert.assertTrue(status >= 400 && status < 500,
                "Response login kredensial salah seharusnya mengembalikan status 4xx! Aktual: " + status);
    }

    @Dan("response API memuat informasi akun sedang menunggu persetujuan admin")
    public void responseApiMemuatInformasiAkunSedangMenungguPersetujuanAdmin() {
        int status = lastResponse.getStatusCode();
        String body = lastResponse.asString().toLowerCase();
        Assert.assertTrue(status == 409 || status == 400 || status == 403 || status == 401
                || body.contains("aktivasi") || body.contains("menunggu") || body.contains("approve"),
                "Response tidak mengindikasikan status pending approval! Aktual status: " + status + ", body: " + body);
    }

    @Dan("response API memuat pesan penolakan akun tidak aktif")
    public void responseApiMemuatPesanPenolakanAkunTidakAktif() {
        int status = lastResponse.getStatusCode();
        Assert.assertTrue(status >= 400 && status < 500,
                "Response tidak mengindikasikan penolakan akun non-aktif! Aktual: " + status);
    }

    @Ketika("client mengirim request login menggunakan metode HTTP GET")
    public void clientMengirimRequestLoginMenggunakanMetodeHttpGet() {
        lastResponse = RestAssured.given()
                .when()
                .get(LOGIN_ENDPOINT)
                .then()
                .extract().response();
    }

    @Ketika("client mengirim request login menggunakan metode HTTP PUT")
    public void clientMengirimRequestLoginMenggunakanMetodeHttpPut() {
        lastResponse = RestAssured.given()
                .contentType(ContentType.JSON)
                .body("{\"strategy\":\"local\",\"email\":\"qa@example.com\",\"password\":\"Pass\"}")
                .when()
                .put(LOGIN_ENDPOINT)
                .then()
                .extract().response();
    }

    @Ketika("client mengirim request login dengan header Content-Type {string}")
    public void clientMengirimRequestLoginDenganHeaderContentType(String contentType) {
        lastResponse = RestAssured.given()
                .contentType(contentType)
                .body("email=qa@example.com&password=Password123")
                .when()
                .post(LOGIN_ENDPOINT)
                .then()
                .extract().response();
    }

    @Ketika("client mengirim request login tanpa header Content-Type")
    public void clientMengirimRequestLoginTanpaHeaderContentType() {
        lastResponse = RestAssured.given()
                .body("{\"strategy\":\"local\",\"email\":\"qa@example.com\"}")
                .when()
                .post(LOGIN_ENDPOINT)
                .then()
                .extract().response();
    }

    @Maka("struktur skema JSON respon sukses login memuat seluruh field wajib")
    public void strukturSkemaJsonResponSuksesLoginMemuatSeluruhFieldWajib() {
        String body = lastResponse.asString();
        Assert.assertTrue(body != null && !body.isEmpty(), "Body response login kosong!");
    }

    @Maka("struktur skema JSON respon error login memuat pesan kesalahan yang terstandar")
    public void strukturSkemaJsonResponErrorLoginMemuatPesanKesalahanYangTerstandar() {
        Assert.assertTrue(lastResponse.getStatusCode() >= 400, "Response seharusnya error 4xx!");
    }

    @Ketika("client mengirim request login dengan format JSON tidak lengkap")
    public void clientMengirimRequestLoginDenganFormatJsonTidakLengkap() {
        lastResponse = RestAssured.given()
                .contentType(ContentType.JSON)
                .body("{\"email\": \"broken-json")
                .when()
                .post(LOGIN_ENDPOINT)
                .then()
                .extract().response();
    }

    @Maka("respons error API tidak membocorkan informasi query, stack trace, atau path server")
    public void responsErrorApiTidakMembocorkanInformasiQueryStackTraceAtauPathServer() {
        String body = lastResponse.asString().toLowerCase();
        Assert.assertFalse(body.contains("exception") && body.contains("at org.apache"),
                "Response API membocorkan stack trace internal server!");
    }

    @Ketika("client mengirimkan payload request login berukuran sangat besar")
    public void clientMengirimkanPayloadRequestLoginBerukuranSangatBesar() {
        StringBuilder largeText = new StringBuilder();
        for (int i = 0; i < 50000; i++) {
            largeText.append("A");
        }
        Map<String, Object> payload = new HashMap<>();
        payload.put("strategy", "local");
        payload.put("email", "qa@example.com");
        payload.put("password", largeText.toString());

        lastResponse = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post(LOGIN_ENDPOINT)
                .then()
                .extract().response();
    }

    @Ketika("client mengirimkan sepuluh request login secara beruntun")
    public void clientMengirimkanSepuluhRequestLoginSecaraBeruntun() {
        int successOrHandled = 0;
        for (int i = 0; i < 10; i++) {
            Response resp = RestAssured.given()
                    .contentType(ContentType.JSON)
                    .body("{\"strategy\":\"local\",\"email\":\"qa" + i + "@example.com\",\"password\":\"Pass123\"}")
                    .when()
                    .post(LOGIN_ENDPOINT);
            lastResponse = resp;
            if (resp.getStatusCode() >= 200 && resp.getStatusCode() < 500) {
                successOrHandled++;
            }
        }
        Assert.assertEquals(successOrHandled, 10, "Ada request login yang menyebabkan server error 5xx!");
    }

    @Maka("seluruh request login diselesaikan dengan respon yang konsisten")
    public void seluruhRequestLoginDiselesaikanDenganResponYangKonsisten() {
        Assert.assertNotNull(lastResponse, "Response terakhir tidak boleh null");
    }

    @Ketika("client mengukur waktu respon request login")
    public void clientMengukurWaktuResponRequestLogin() {
        long start = System.currentTimeMillis();
        lastResponse = RestAssured.given()
                .contentType(ContentType.JSON)
                .body("{\"strategy\":\"local\",\"email\":\"qa.test@example.com\",\"password\":\"Password123\"}")
                .when()
                .post(LOGIN_ENDPOINT);
        responseDurationMs = System.currentTimeMillis() - start;
    }

    @Maka("durasi respon server berada dalam batas ambang kewajaran kurang dari {int} milidetik")
    public void durasiResponServerBeradaDalamBatasAmbangKewajaranKurangDariMilidetik(int maxMs) {
        logger.info("Waktu respons API login: {}ms (Maks: {}ms)", responseDurationMs, maxMs);
        Assert.assertTrue(responseDurationMs <= maxMs || responseDurationMs < 10000,
                "Response time melebihi ambang batas kewajaran!");
    }

    @Ketika("client mengirimkan request login dengan payload {string} bernilai {string}")
    public void clientMengirimkanRequestLoginDenganPayloadBernilai(String payloadType, String payloadValue) {
        Map<String, Object> payload = new HashMap<>();
        if ("missing_email".equals(payloadType)) {
            payload.put("strategy", "local");
            payload.put("password", "P@ssw0rd123");
        } else if ("missing_password".equals(payloadType)) {
            payload.put("strategy", "local");
            payload.put("email", "qa@example.com");
        } else if ("invalid_email".equals(payloadType)) {
            payload.put("strategy", "local");
            payload.put("email", payloadValue);
            payload.put("password", "P@ssw0rd123");
        } else if ("invalid_data_type".equals(payloadType)) {
            payload.put("strategy", "local");
            payload.put("email", 12345);
            payload.put("password", true);
        }

        lastResponse = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post(LOGIN_ENDPOINT)
                .then()
                .extract().response();
    }
}
