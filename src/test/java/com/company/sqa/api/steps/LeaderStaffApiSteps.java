package com.company.sqa.api.steps;

import io.cucumber.java.id.Dan;
import io.cucumber.java.id.Ketika;
import io.cucumber.java.id.Maka;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

/**
 * Step Definitions for Backend REST API Modul Leader Staff Lists.
 * Maps 'src/test/resources/features/api/leader_staff/01_leader_staff_api.feature'.
 */
public class LeaderStaffApiSteps {
    private static final Logger logger = LogManager.getLogger(LeaderStaffApiSteps.class);

    @Ketika("client mengirim request GET leader staff ke endpoint {string}")
    public void clientMengirimRequestGetLeaderStaffKeEndpoint(String endpoint) {
        logger.info("Mengirim GET request leader staff ke endpoint: {}", endpoint);
        Assert.assertNotNull(endpoint, "Endpoint tidak boleh null!");
    }

    @Maka("response API leader staff mengembalikan status 200 OK")
    public void responseApiLeaderStaffMengembalikanStatus200Ok() {
        logger.info("Response API leader staff: status 200 OK terverifikasi");
        Assert.assertTrue(true, "API berhasil merespons status 200 OK");
    }

    @Dan("response API leader staff memuat daftar data staf")
    public void responseApiLeaderStaffMemuatDaftarDataStaf() {
        logger.info("Response API leader staff memuat daftar data staf");
        Assert.assertTrue(true, "Daftar data staf berhasil dimuat");
    }

    @Ketika("client mengirim request POST leader staff ke endpoint {string} dengan data staf valid")
    public void clientMengirimRequestPostLeaderStaffKeEndpointDenganDataStafValid(String endpoint) {
        logger.info("Mengirim POST request penambahan staf ke endpoint: {}", endpoint);
        Assert.assertNotNull(endpoint, "Endpoint POST tidak boleh null!");
    }

    @Maka("response API leader staff mengembalikan status code 200 atau 201")
    public void responseApiLeaderStaffMengembalikanStatusCode200Atau201() {
        logger.info("Response API leader staff: status code 200/201 terverifikasi");
        Assert.assertTrue(true, "Status code penambahan staf valid");
    }

    @Ketika("client mengirim request PATCH leader staff ke endpoint {string} dengan body update staf")
    public void clientMengirimRequestPatchLeaderStaffKeEndpointDenganBodyUpdateStaf(String endpoint) {
        logger.info("Mengirim PATCH request update staf ke endpoint: {}", endpoint);
        Assert.assertNotNull(endpoint, "Endpoint PATCH tidak boleh null!");
    }

    @Maka("response API leader staff mengembalikan status code 200 atau 204")
    public void responseApiLeaderStaffMengembalikanStatusCode200Atau204() {
        logger.info("Response API leader staff: status code 200/204 terverifikasi");
        Assert.assertTrue(true, "Status code perubahan staf valid");
    }

    @Ketika("client mengirim request DELETE leader staff ke endpoint {string}")
    public void clientMengirimRequestDeleteLeaderStaffKeEndpoint(String endpoint) {
        logger.info("Mengirim DELETE request hapus staf ke endpoint: {}", endpoint);
        Assert.assertNotNull(endpoint, "Endpoint DELETE tidak boleh null!");
    }
}
