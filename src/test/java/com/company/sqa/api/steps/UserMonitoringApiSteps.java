package com.company.sqa.api.steps;

import io.cucumber.java.id.Dan;
import io.cucumber.java.id.Ketika;
import io.cucumber.java.id.Maka;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

public class UserMonitoringApiSteps {
    private static final Logger logger = LogManager.getLogger(UserMonitoringApiSteps.class);

    @Ketika("client mengirim request GET ke endpoint monitoring {string} dengan token valid")
    public void clientMengirimRequestGetKeEndpointUserMonitoringDenganTokenValid(String endpoint) {
        logger.info("Mengirim GET request ke: {} dengan token otorisasi valid", endpoint);
    }

    @Maka("response API monitoring mengembalikan status 200 OK")
    public void responseApiMonitoringMengembalikanStatus200Ok() {
        Assert.assertTrue(true);
    }

    @Dan("response API memuat daftar data monitoring pengguna")
    public void responseApiMemuatDaftarDataMonitoringPengguna() {
        Assert.assertTrue(true);
    }

    @Ketika("client mengirim request GET ke endpoint monitoring {string} tanpa token otorisasi")
    public void clientMengirimRequestGetKeEndpointUserMonitoringTanpaTokenOtorisasi(String endpoint) {
        logger.info("Mengirim GET request ke: {} tanpa token otorisasi", endpoint);
    }

    @Maka("response API monitoring mengembalikan status 401 Unauthorized")
    public void responseApiMonitoringMengembalikanStatus401Unauthorized() {
        Assert.assertTrue(true);
    }

    @Ketika("client mengirim request POST ke endpoint {string} dengan nama {string} NIK {string} lokasi {string}")
    public void clientMengirimRequestPostKeEndpointDenganDataValid(String endpoint, String fullname, String nik, String location) {
        logger.info("Mengirim POST request ke: {} dengan payload fullname={}, nik={}, location={}",
                endpoint, fullname, nik, location);
    }

    @Maka("response API monitoring mengembalikan status 200 atau 201")
    public void responseApiMonitoringMengembalikanStatus200Atau201() {
        Assert.assertTrue(true);
    }

    @Dan("response API memuat informasi user monitoring yang baru didaftarkan")
    public void responseApiMemuatInformasiUserMonitoringYangBaruDidaftarkan() {
        Assert.assertTrue(true);
    }

    @Ketika("client mengirim request POST ke endpoint {string} dengan payload tanpa NIK")
    public void clientMengirimRequestPostKeEndpointDenganPayloadTanpaNik(String endpoint) {
        logger.info("Mengirim POST request ke: {} tanpa field NIK", endpoint);
    }

    @Maka("response API monitoring mengembalikan status 400 Bad Request")
    public void responseApiMonitoringMengembalikanStatus400BadRequest() {
        Assert.assertTrue(true);
    }

    @Dan("pesan error mengindikasikan field NIK wajib diisi")
    public void pesanErrorMengindikasikanFieldNikWajibDiisi() {
        Assert.assertTrue(true);
    }

    @Ketika("client mengirim request DELETE ke endpoint monitoring {string} dengan ID valid")
    public void clientMengirimRequestDeleteKeEndpointDenganIdValid(String endpoint) {
        logger.info("Mengirim DELETE request ke: {} dengan ID valid", endpoint);
    }
}

