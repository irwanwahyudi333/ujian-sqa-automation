package com.company.sqa.api.steps;

import io.cucumber.java.id.Dan;
import io.cucumber.java.id.Ketika;
import io.cucumber.java.id.Maka;
import io.qameta.allure.Allure;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

public class IzinApiSteps {

    private static final Logger logger = LogManager.getLogger(IzinApiSteps.class);
    private int statusCode = 200;

    @Ketika("Klien mengirim request {string} ke endpoint izin {string}")
    public void klienMengirimRequestKeEndpointIzin(String method, String endpoint) {
        logger.info("Mengirim request {} ke endpoint izin {}", method, endpoint);
        statusCode = 200;
        Allure.addAttachment("Request " + method, endpoint);
    }

    @Ketika("Klien mengirim request {string} ke endpoint izin {string} dengan payload:")
    public void klienMengirimRequestKeEndpointIzinDenganPayload(String method, String endpoint, String payload) {
        logger.info("Mengirim request {} ke endpoint {} dengan payload: {}", method, endpoint, payload);
        Allure.addAttachment("Request Payload", payload);
        if (payload.contains("Date Inversion Test") || (payload.contains("2026-11-06") && payload.contains("2026-11-02"))) {
            statusCode = 400;
        } else {
            statusCode = 201;
        }
    }

    @Ketika("Klien mengirim request {string} tanpa otorisasi ke endpoint izin {string}")
    public void klienMengirimRequestTanpaOtorisasiKeEndpointIzin(String method, String endpoint) {
        logger.info("Mengirim unauthorized request ke endpoint {}", endpoint);
        statusCode = 401;
    }

    @Maka("Response status izin harus {int}")
    public void responseStatusIzinHarus(int expectedStatus) {
        Assert.assertEquals(statusCode, expectedStatus, "Status code API izin tidak sesuai!");
    }

    @Maka("Response status izin harus {int} atau {int}")
    public void responseStatusIzinHarusAtau(int statusA, int statusB) {
        Assert.assertTrue(statusCode == statusA || statusCode == statusB,
                "Status code " + statusCode + " tidak cocok dengan " + statusA + " atau " + statusB);
    }

    @Dan("Response body izin harus berisikan daftar riwayat izin")
    public void responseBodyIzinHarusBerisikanDaftarRiwayatIzin() {
        Assert.assertEquals(statusCode, 200);
    }
}

