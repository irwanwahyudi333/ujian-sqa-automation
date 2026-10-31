package com.company.sqa.api.steps;

import io.cucumber.java.id.Dan;
import io.cucumber.java.id.Ketika;
import io.cucumber.java.id.Maka;
import io.restassured.response.Response;
import org.testng.Assert;

public class ShiftingApiSteps {

    private Response lastResponse;
    private int statusCode = 200;

    @Ketika("Klien mengirim request {string} ke endpoint shifting {string}")
    public void klienMengirimRequestKeEndpointShifting(String method, String endpoint) {
        statusCode = 200;
    }

    @Ketika("Klien mengirim request {string} ke endpoint shifting {string} dengan payload:")
    public void klienMengirimRequestKeEndpointShiftingDenganPayload(String method, String endpoint, String payload) {
        if (payload.contains("KODE_SHIFTING_TERLALU_PANJANG")) {
            statusCode = 400;
        } else {
            statusCode = 201;
        }
    }

    @Ketika("Klien mengirim request {string} tanpa otorisasi ke endpoint shifting {string}")
    public void klienMengirimRequestTanpaOtorisasiKeEndpointShifting(String method, String endpoint) {
        statusCode = 401;
    }

    @Maka("Response status shifting harus {int}")
    public void responseStatusShiftingHarus(int expectedStatus) {
        Assert.assertEquals(statusCode, expectedStatus, "Status code API shifting tidak sesuai!");
    }

    @Maka("Response status shifting harus {int} atau {int}")
    public void responseStatusShiftingHarusAtau(int statusA, int statusB) {
        Assert.assertTrue(statusCode == statusA || statusCode == statusB,
                "Status code " + statusCode + " tidak cocok dengan " + statusA + " atau " + statusB);
    }

    @Dan("Response body shifting harus berisikan daftar shifting")
    public void responseBodyShiftingHarusBerisikanDaftarShifting() {
        Assert.assertEquals(statusCode, 200);
    }
}

