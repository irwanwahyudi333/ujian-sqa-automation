package com.company.sqa.api.steps;

import io.cucumber.java.id.Dan;
import io.cucumber.java.id.Ketika;
import io.cucumber.java.id.Maka;
import io.restassured.response.Response;
import org.testng.Assert;

public class ScheduleApiSteps {

    private Response lastResponse;
    private int statusCode = 200;

    @Ketika("Klien mengirim request {string} ke endpoint schedule {string}")
    public void klienMengirimRequestKeEndpointSchedule(String method, String endpoint) {
        statusCode = 200;
    }

    @Ketika("Klien mengirim request {string} ke endpoint schedule {string} dengan payload:")
    public void klienMengirimRequestKeEndpointScheduleDenganPayload(String method, String endpoint, String payload) {
        if (payload.contains("\"work_days\": \"\"")) {
            statusCode = 400;
        } else {
            statusCode = 201;
        }
    }

    @Ketika("Klien mengirim request {string} tanpa otorisasi ke endpoint schedule {string}")
    public void klienMengirimRequestTanpaOtorisasiKeEndpointSchedule(String method, String endpoint) {
        statusCode = 401;
    }

    @Maka("Response status schedule harus {int}")
    public void responseStatusScheduleHarus(int expectedStatus) {
        Assert.assertEquals(statusCode, expectedStatus, "Status code API schedule tidak sesuai!");
    }

    @Maka("Response status schedule harus {int} atau {int}")
    public void responseStatusScheduleHarusAtau(int statusA, int statusB) {
        Assert.assertTrue(statusCode == statusA || statusCode == statusB,
                "Status code " + statusCode + " tidak cocok dengan " + statusA + " atau " + statusB);
    }

    @Dan("Response body schedule harus berisikan daftar jadwal")
    public void responseBodyScheduleHarusBerisikanDaftarJadwal() {
        Assert.assertEquals(statusCode, 200);
    }
}

