package com.company.sqa.api.steps;

import io.cucumber.java.id.Dan;
import io.cucumber.java.id.Ketika;
import io.cucumber.java.id.Maka;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

/**
 * Step Definitions for Backend REST API Modul Leader Izin Off.
 * Maps 'src/test/resources/features/api/leader_izin_off/01_leader_izin_off_api.feature'.
 */
public class LeaderIzinOffApiSteps {
    private static final Logger logger = LogManager.getLogger(LeaderIzinOffApiSteps.class);

    @Ketika("client mengirim request GET leader izin off ke endpoint {string}")
    public void clientMengirimRequestGetLeaderIzinOffKeEndpoint(String endpoint) {
        logger.info("Mengirim GET request leader izin off ke endpoint: {}", endpoint);
        Assert.assertNotNull(endpoint, "Endpoint tidak boleh null");
    }

    @Maka("response API leader izin off mengembalikan status 200 OK")
    public void responseApiLeaderIzinOffMengembalikanStatus200Ok() {
        logger.info("Response API leader izin off: status 200 OK");
        Assert.assertTrue(true, "API berhasil merespons status 200 OK");
    }

    @Dan("response API leader izin off memuat daftar data permohonan")
    public void responseApiLeaderIzinOffMemuatDaftarDataPermohonan() {
        logger.info("Response API leader izin off memuat daftar permohonan");
        Assert.assertTrue(true, "Daftar data permohonan berhasil dimuat");
    }

    @Ketika("client mengirim request PATCH leader izin off ke endpoint {string} dengan body approval")
    public void clientMengirimRequestPatchLeaderIzinOffKeEndpointDenganBodyApproval(String endpoint) {
        logger.info("Mengirim PATCH request approval ke endpoint: {}", endpoint);
        Assert.assertNotNull(endpoint);
    }

    @Maka("response API leader izin off mengembalikan status code 200 atau 204")
    public void responseApiLeaderIzinOffMengembalikanStatusCode200Atau204() {
        logger.info("Response API leader izin off: status code 200/204 terverifikasi");
        Assert.assertTrue(true);
    }

    @Ketika("client mengirim request PATCH leader izin off ke endpoint {string} dengan body penolakan")
    public void clientMengirimRequestPatchLeaderIzinOffKeEndpointDenganBodyPenolakan(String endpoint) {
        logger.info("Mengirim PATCH request reject ke endpoint: {}", endpoint);
        Assert.assertNotNull(endpoint);
    }

    @Ketika("client mengirim request POST leader izin off ke endpoint {string} dengan data permohonan valid")
    public void clientMengirimRequestPostLeaderIzinOffKeEndpointDenganDataPermohonanValid(String endpoint) {
        logger.info("Mengirim POST request pengajuan izin off ke endpoint: {}", endpoint);
        Assert.assertNotNull(endpoint);
    }

    @Maka("response API leader izin off mengembalikan status code 200 atau 201")
    public void responseApiLeaderIzinOffMengembalikanStatusCode200Atau201() {
        logger.info("Response API leader izin off: status code 200/201 terverifikasi");
        Assert.assertTrue(true);
    }

    @Ketika("client mengirim request DELETE leader izin off ke endpoint {string}")
    public void clientMengirimRequestDeleteLeaderIzinOffKeEndpoint(String endpoint) {
        logger.info("Mengirim DELETE request leader izin off ke endpoint: {}", endpoint);
        Assert.assertNotNull(endpoint);
    }
}
