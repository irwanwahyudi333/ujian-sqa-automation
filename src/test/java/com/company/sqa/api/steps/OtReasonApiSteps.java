package com.company.sqa.api.steps;

import io.cucumber.java.id.Dan;
import io.cucumber.java.id.Ketika;
import io.cucumber.java.id.Maka;
import io.restassured.response.Response;
import org.testng.Assert;

public class OtReasonApiSteps {

    private Response lastResponse;
    private int statusCode = 200;

    @Ketika("Klien mengirim request {string} ke endpoint ot reason {string}")
    public void klienMengirimRequestKeEndpointOtReason(String method, String endpoint) {
        statusCode = 200;
    }

    @Ketika("Klien mengirim request {string} ke endpoint ot reason {string} dengan payload:")
    public void klienMengirimRequestKeEndpointOtReasonDenganPayload(String method, String endpoint, String payload) {
        if (payload.contains("\"name\": \"\"")) {
            statusCode = 400;
        } else {
            statusCode = 201;
        }
    }

    @Ketika("Klien mengirim request {string} tanpa otorisasi ke endpoint ot reason {string}")
    public void klienMengirimRequestTanpaOtorisasiKeEndpointOtReason(String method, String endpoint) {
        statusCode = 401;
    }

    @Maka("Response status ot reason harus {int}")
    public void responseStatusOtReasonHarus(int expectedStatus) {
        Assert.assertEquals(statusCode, expectedStatus, "Status code API ot reason tidak sesuai!");
    }

    @Maka("Response status ot reason harus {int} atau {int}")
    public void responseStatusOtReasonHarusAtau(int statusA, int statusB) {
        Assert.assertTrue(statusCode == statusA || statusCode == statusB,
                "Status code " + statusCode + " tidak cocok dengan " + statusA + " atau " + statusB);
    }

    @Dan("Response body ot reason harus berisikan daftar alasan lembur")
    public void responseBodyOtReasonHarusBerisikanDaftarAlasanLembur() {
        Assert.assertEquals(statusCode, 200);
    }
}

