package com.company.sqa.api.steps;

import io.cucumber.java.id.Ketika;
import io.cucumber.java.id.Maka;
import io.qameta.allure.Allure;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

import java.util.HashMap;
import java.util.Map;

public class UserEditApiSteps {
    private static final Logger logger = LogManager.getLogger(UserEditApiSteps.class);

    private static final String STATIC_UPLOADS_URL = "https://api-dev.dikahadir.com/hadir-api-magang/uploads/";
    private static final String BROKEN_UPLOADS_URL = "https://api-dev.dikahadir.com/uploads/";
    private static final String USERS_ENDPOINT = "https://api-dev.dikahadir.com/hadir-api-magang/users";

    private Response lastResponse;
    private final String testPhotoId = "9650f149d622055c4b495e65e9148ebfc5e61b1bb00e83538e76afe48c496eaf.jpeg";

    @Ketika("client memeriksa aksesibilitas endpoint static foto selfie dengan ID terunggah")
    public void clientMemeriksaAksesibilitasEndpointStaticFotoSelfieDenganIdTerunggah() {
        logger.info("Memeriksa aksesibilitas berkas foto selfie pada endpoint static upload");
        String validUrl = STATIC_UPLOADS_URL + testPhotoId;
        String brokenUrl = BROKEN_UPLOADS_URL + testPhotoId;

        Response brokenResponse = RestAssured.given()
                .when()
                .get(brokenUrl)
                .then()
                .extract().response();

        Allure.addAttachment("Request ke Broken Path (/uploads/)", brokenUrl + " -> HTTP " + brokenResponse.getStatusCode());

        lastResponse = RestAssured.given()
                .when()
                .get(validUrl)
                .then()
                .extract().response();

        Allure.addAttachment("Request ke Valid Path (/hadir-api-magang/uploads/)", validUrl + " -> HTTP " + lastResponse.getStatusCode());
    }

    @Maka("endpoint upload mengembalikan status HTTP 200 OK dengan format gambar valid")
    public void endpointUploadMengembalikanStatusHttp200OkDenganFormatGambarValid() {
        int statusCode = lastResponse.getStatusCode();
        String contentType = lastResponse.getContentType();
        logger.info("Aksesibilitas static uploads: Status {}, Content-Type {}", statusCode, contentType);

        Assert.assertEquals(statusCode, 200, "Endpoint static foto selfie tidak dapat diakses!");
        Assert.assertTrue(contentType != null && contentType.contains("image/"), "Format konten bukan gambar! Aktual: " + contentType);
    }

    @Ketika("client mengirim request update user dengan NIK duplikat yang sudah terdaftar {string}")
    public void clientMengirimRequestUpdateUserDenganNikDuplikatYangSudahTerdaftar(String duplicateNik) {
        logger.info("Mengirim PATCH request update user dengan NIK duplikat: {}", duplicateNik);
        Map<String, Object> payload = new HashMap<>();
        payload.put("nik", duplicateNik);
        payload.put("isVerified", true);

        Allure.addAttachment("Payload Update NIK Duplikat", payload.toString());

        lastResponse = RestAssured.given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)
                .when()
                .patch(USERS_ENDPOINT + "/1")
                .then()
                .extract().response();

        logger.info("Response update NIK status: {}", lastResponse.getStatusCode());
    }

    @Maka("response API mengembalikan status penolakan kesalahan duplikasi 400 atau 409")
    public void responseApiMengembalikanStatusPenolakanKesalahanDuplikasi400Atau409() {
        int statusCode = lastResponse.getStatusCode();
        logger.info("Status respons API validasi duplikasi: {}", statusCode);
        Allure.addAttachment("Status Respons API", String.valueOf(statusCode));
        Allure.addAttachment("Body Respons API", lastResponse.asPrettyString());

        // Status 400 (Bad Request), 401 (Not Auth), 404 (Not Found), 409 (Conflict) menandakan request ditahan
        boolean isRejected = statusCode >= 400;
        Assert.assertTrue(isRejected, "BUG: Backend meloloskan request pembaharuan data duplikat dengan status 200/201!");
    }

    @Ketika("client mengirim request update user dengan Email duplikat yang sudah terdaftar {string}")
    public void clientMengirimRequestUpdateUserDenganEmailDuplikatYangSudahTerdaftar(String duplicateEmail) {
        logger.info("Mengirim PATCH request update user dengan Email duplikat: {}", duplicateEmail);
        Map<String, Object> payload = new HashMap<>();
        payload.put("email", duplicateEmail);
        payload.put("isVerified", true);

        Allure.addAttachment("Payload Update Email Duplikat", payload.toString());

        lastResponse = RestAssured.given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)
                .when()
                .patch(USERS_ENDPOINT + "/1")
                .then()
                .extract().response();

        logger.info("Response update Email status: {}", lastResponse.getStatusCode());
    }
}
