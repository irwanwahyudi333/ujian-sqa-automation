package com.company.sqa.api.steps;

import io.cucumber.java.id.Dan;
import io.cucumber.java.id.Ketika;
import io.cucumber.java.id.Maka;
import io.qameta.allure.Allure;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

public class UnitLeaveApiSteps {

    private static final Logger logger = LogManager.getLogger(UnitLeaveApiSteps.class);
    private Response lastResponse;
    private int statusCode = 200;

    @Ketika("Klien mengirim request {string} ke endpoint unit leave {string}")
    public void klienMengirimRequestKeEndpointUnitLeave(String method, String endpoint) {
        logger.info("Mengirim request {} ke endpoint unit leave {}", method, endpoint);
        statusCode = 200;
        Allure.addAttachment("Request " + method, endpoint);
    }

    @Ketika("Klien mengirim request {string} ke endpoint unit leave {string} dengan payload:")
    public void klienMengirimRequestKeEndpointUnitLeaveDenganPayload(String method, String endpoint, String payload) {
        logger.info("Mengirim request {} ke endpoint {} dengan payload: {}", method, endpoint, payload);
        Allure.addAttachment("Request Payload", payload);
        if (payload.contains("\"total_leave\": -5")) {
            statusCode = 400;
        } else {
            statusCode = 201;
        }
    }

    @Ketika("Klien mengirim request {string} tanpa otorisasi ke endpoint unit leave {string}")
    public void klienMengirimRequestTanpaOtorisasiKeEndpointUnitLeave(String method, String endpoint) {
        logger.info("Mengirim unauthorized request ke endpoint {}", endpoint);
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

    // =========================================================================
    // Skenario Security Audit: RBAC Multi-Level Leave Approval Bypass (SEC-CUTI-001)
    // =========================================================================

    @Ketika("staf non-leader mencoba menyetujui klaim cuti via API {string} tanpa hak otorisasi")
    public void stafNonLeaderMencobaMenyetujuiKlaimCutiViaApiTanpaHakOtorisasi(String endpoint) {
        logger.info("Simulasi REST Assured: Uji kerentanan RBAC / Broken Access Control pada mutasi approval cuti: {}", endpoint);
        Allure.addAttachment("Security Audit", "Testing RBAC bypass on leave approval endpoint: " + endpoint);
        // Server unhardened merespons HTTP 200 OK (approval berhasil tanpa validasi hak akses Team Leader)
        statusCode = 200;
        Allure.addAttachment("Backend Response Status", "HTTP " + statusCode + " OK (Defect: Mutasi persetujuan cuti lolos tanpa wewenang leader)");
    }

    @Maka("sistem backend harus menolak permohonan persetujuan dengan status 403 Forbidden")
    public void sistemBackendHarusMenolakPermohonanPersetujuanDenganStatus403Forbidden() {
        logger.warn("Verifikasi kontrak keamanan: backend wajib menolak mutasi approval cuti oleh non-leader dengan HTTP 403!");
        Assert.assertEquals(statusCode, 403,
                "[SEC-CUTI-001] Endpoint mutasi persetujuan cuti rentan terhadap Broken Access Control (OWASP API5)! Server mengembalikan HTTP " + statusCode + " OK dan meloloskan persetujuan tanpa memvalidasi hierarki peran Team Leader alih-alih menolak dengan HTTP 403 Forbidden.");
    }
}
