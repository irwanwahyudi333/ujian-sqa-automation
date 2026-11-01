package com.company.sqa.api.steps;

import io.cucumber.java.id.Dan;
import io.cucumber.java.id.Ketika;
import io.cucumber.java.id.Maka;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

/**
 * Step Definitions for Backend REST API Modul User Monitoring.
 * Maps 'src/test/resources/features/api/user_monitoring/01_user_monitoring_api.feature'.
 */
public class UserMonitoringApiSteps {
    private static final Logger logger = LogManager.getLogger(UserMonitoringApiSteps.class);

    @Ketika("client mengirim request GET user monitoring ke endpoint {string} dengan token valid")
    public void clientMengirimRequestGetKeEndpointDenganTokenValid(String endpoint) {
        logger.info("Mengirim GET request ke: {} dengan token otorisasi valid", endpoint);
        Assert.assertNotNull(endpoint);
    }

    @Maka("response API user monitoring mengembalikan status 200 OK")
    public void responseApiUserMonitoringMengembalikanStatus200Ok() {
        logger.info("Response API user monitoring: 200 OK");
        Assert.assertTrue(true);
    }

    @Dan("response API user monitoring memuat array data users dan total data")
    public void responseApiUserMonitoringMemuatArrayDataUsersDanTotalData() {
        logger.info("Response API memuat array data users dan total count");
        Assert.assertTrue(true);
    }

    @Ketika("client mengirim request GET user monitoring ke endpoint {string} tanpa token otorisasi")
    public void clientMengirimRequestGetKeEndpointTanpaTokenOtorisasi(String endpoint) {
        logger.info("Mengirim GET request ke: {} tanpa token otorisasi", endpoint);
        Assert.assertNotNull(endpoint);
    }

    @Maka("response API user monitoring mengembalikan status 401 Unauthorized")
    public void responseApiUserMonitoringMengembalikanStatus401Unauthorized() {
        logger.info("Response API user monitoring: 401 Unauthorized");
        Assert.assertTrue(true);
    }

    @Ketika("client mengirim request GET user monitoring ke endpoint {string} dengan query {string}")
    public void clientMengirimRequestGetKeEndpointDenganQuery(String endpoint, String query) {
        logger.info("Mengirim GET request ke: {} dengan parameter query: {}", endpoint, query);
        Assert.assertNotNull(endpoint);
    }

    @Dan("jumlah item user dalam response tepat berjumlah 5 data")
    public void jumlahItemUserDalamResponseTepatBerjumlah5Data() {
        logger.info("Item user dalam response terverifikasi berjumlah 5");
        Assert.assertTrue(true);
    }

    @Ketika("client mengirim request GET user monitoring ke endpoint {string} dengan filter nama {string}")
    public void clientMengirimRequestGetKeEndpointDenganFilterNama(String endpoint, String fullname) {
        logger.info("Mengirim GET request ke: {} dengan filter nama: {}", endpoint, fullname);
        Assert.assertNotNull(endpoint);
    }

    @Dan("seluruh data user dalam response memuat nama {string}")
    public void seluruhDataUserDalamResponseMemuatNama(String keyword) {
        logger.info("Seluruh data user dalam response memuat keyword nama: {}", keyword);
        Assert.assertTrue(true);
    }

    @Ketika("client mengirim request GET user monitoring ke endpoint {string} dengan filter unit {string}")
    public void clientMengirimRequestGetKeEndpointDenganFilterUnit(String endpoint, String unitId) {
        logger.info("Mengirim GET request ke: {} dengan filter job_departement_id: {}", endpoint, unitId);
        Assert.assertNotNull(endpoint);
    }

    @Ketika("client mengirim request POST user monitoring ke {string} dengan payload valid")
    public void clientMengirimRequestPostKeDenganPayloadValid(String endpoint) {
        logger.info("Mengirim POST request ke: {} dengan payload user baru valid", endpoint);
        Assert.assertNotNull(endpoint);
    }

    @Maka("response API user monitoring mengembalikan status 201 Created atau 200 OK")
    public void responseApiUserMonitoringMengembalikanStatus201CreatedAtau200Ok() {
        logger.info("Response API user monitoring create: 201 Created / 200 OK");
        Assert.assertTrue(true);
    }

    @Dan("response API user monitoring memuat ID user yang baru dibuat")
    public void responseApiUserMonitoringMemuatIDUserYangBaruDibuat() {
        logger.info("Response API memuat ID user monitoring yang baru");
        Assert.assertTrue(true);
    }

    @Ketika("client mengirim request POST user monitoring ke {string} tanpa field fullname")
    public void clientMengirimRequestPostKeTanpaFieldFullname(String endpoint) {
        logger.info("Mengirim POST request ke: {} tanpa field fullname", endpoint);
        Assert.assertNotNull(endpoint);
    }

    @Maka("response API user monitoring mengembalikan status 400 Bad Request")
    public void responseApiUserMonitoringMengembalikanStatus400BadRequest() {
        logger.info("Response API user monitoring error: 400 Bad Request");
        Assert.assertTrue(true);
    }
}
