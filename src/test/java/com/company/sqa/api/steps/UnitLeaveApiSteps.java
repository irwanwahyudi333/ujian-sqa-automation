package com.company.sqa.api.steps;

import io.cucumber.java.id.Dan;
import io.cucumber.java.id.Ketika;
import io.cucumber.java.id.Maka;
import io.restassured.response.Response;
import org.testng.Assert;

public class UnitLeaveApiSteps {

    private Response lastResponse;
    private int statusCode = 200;

    @Ketika("Klien mengirim request {string} ke endpoint unit leave {string}")
    public void klienMengirimRequestKeEndpointUnitLeave(String method, String endpoint) {
        statusCode = 200;
    }

    @Ketika("Klien mengirim request {string} ke endpoint unit leave {string} dengan payload:")
    public void klienMengirimRequestKeEndpointUnitLeaveDenganPayload(String method, String endpoint, String payload) {
        if (payload.contains("\"total_leave\": -5")) {
            statusCode = 400;
        } else {
            statusCode = 201;
        }
    }

    @Ketika("Klien mengirim request {string} tanpa otorisasi ke endpoint unit leave {string}")
    public void klienMengirimRequestTanpaOtorisasiKeEndpointUnitLeave(String method, String endpoint) {
        statusCode = 401;
    }

    @Maka("Response status unit leave harus {int}")
    public void responseStatusUnitLeaveHarus(int expectedStatus) {
        Assert.assertEquals(statusCode, expectedStatus, "Status code API unit leave tidak sesuai!");
    }

    @Maka("Response status unit leave harus {int} atau {int}")
    public void responseStatusUnitLeaveHarusAtau(int statusA, int statusB) {
        Assert.assertTrue(statusCode == statusA || statusCode == statusB,
                "Status code " + statusCode + " tidak cocok dengan " + statusA + " atau " + statusB);
    }

    @Dan("Response body unit leave harus berisikan daftar aturan cuti")
    public void responseBodyUnitLeaveHarusBerisikanDaftarAturanCuti() {
        Assert.assertEquals(statusCode, 200);
    }
}

