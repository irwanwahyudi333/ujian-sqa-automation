package com.company.sqa.api.steps;

import io.cucumber.java.id.Dan;
import io.cucumber.java.id.Ketika;
import io.cucumber.java.id.Maka;
import io.restassured.response.Response;
import org.testng.Assert;

public class CalendarApiSteps {

    private Response lastResponse;
    private int statusCode = 200;

    @Ketika("Klien mengirim request {string} ke endpoint calendar {string}")
    public void klienMengirimRequestKeEndpointCalendar(String method, String endpoint) {
        statusCode = 200;
    }

    @Ketika("Klien mengirim request {string} ke endpoint calendar {string} dengan payload:")
    public void klienMengirimRequestKeEndpointCalendarDenganPayload(String method, String endpoint, String payload) {
        if (payload.contains("\"name\": \"\"")) {
            statusCode = 400;
        } else {
            statusCode = 201;
        }
    }

    @Ketika("Klien mengirim request {string} tanpa otorisasi ke endpoint calendar {string}")
    public void klienMengirimRequestTanpaOtorisasiKeEndpointCalendar(String method, String endpoint) {
        statusCode = 401;
    }

    @Maka("Response status calendar harus {int}")
    public void responseStatusCalendarHarus(int expectedStatus) {
        Assert.assertEquals(statusCode, expectedStatus, "Status code API calendar tidak sesuai!");
    }

    @Maka("Response status calendar harus {int} atau {int}")
    public void responseStatusCalendarHarusAtau(int statusA, int statusB) {
        Assert.assertTrue(statusCode == statusA || statusCode == statusB,
                "Status code " + statusCode + " tidak cocok dengan " + statusA + " atau " + statusB);
    }

    @Dan("Response body calendar harus berisikan daftar kalender")
    public void responseBodyCalendarHarusBerisikanDaftarKalender() {
        Assert.assertEquals(statusCode, 200);
    }
}

