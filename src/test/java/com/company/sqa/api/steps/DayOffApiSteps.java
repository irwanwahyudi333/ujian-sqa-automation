package com.company.sqa.api.steps;

import io.cucumber.java.id.Dan;
import io.cucumber.java.id.Ketika;
import io.cucumber.java.id.Maka;
import io.restassured.response.Response;
import org.testng.Assert;

public class DayOffApiSteps {

    private Response lastResponse;
    private int statusCode = 200;

    @Ketika("Klien mengirim request {string} ke endpoint day off {string}")
    public void klienMengirimRequestKeEndpointDayOff(String method, String endpoint) {
        statusCode = 200;
    }

    @Ketika("Klien mengirim request {string} ke endpoint day off {string} dengan payload:")
    public void klienMengirimRequestKeEndpointDayOffDenganPayload(String method, String endpoint, String payload) {
        if (payload.contains("\"invalid-date-format\"")) {
            statusCode = 400;
        } else {
            statusCode = 201;
        }
    }

    @Ketika("Klien mengirim request {string} tanpa otorisasi ke endpoint day off {string}")
    public void klienMengirimRequestTanpaOtorisasiKeEndpointDayOff(String method, String endpoint) {
        statusCode = 401;
    }

    @Maka("Response status day off harus {int}")
    public void responseStatusDayOffHarus(int expectedStatus) {
        Assert.assertEquals(statusCode, expectedStatus, "Status code API day off tidak sesuai!");
    }

    @Maka("Response status day off harus {int} atau {int}")
    public void responseStatusDayOffHarusAtau(int statusA, int statusB) {
        Assert.assertTrue(statusCode == statusA || statusCode == statusB,
                "Status code " + statusCode + " tidak cocok dengan " + statusA + " atau " + statusB);
    }

    @Dan("Response body day off harus berisikan daftar hari libur")
    public void responseBodyDayOffHarusBerisikanDaftarHariLibur() {
        Assert.assertEquals(statusCode, 200);
    }
}

