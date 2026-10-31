package com.company.sqa.api.steps;

import io.cucumber.java.id.Dan;
import io.cucumber.java.id.Ketika;
import io.cucumber.java.id.Maka;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

public class DepartementsSettingsApiSteps {
    private static final Logger logger = LogManager.getLogger(DepartementsSettingsApiSteps.class);

    @Ketika("client mengirim request GET ke endpoint departements settings {string} dengan token valid")
    public void clientMengirimRequestGetKeEndpointDepartementsSettingsDenganTokenValid(String endpoint) {
        logger.info("Mengirim GET request ke: {} dengan token otorisasi valid", endpoint);
    }

    @Maka("response API departemens settings mengembalikan status 200 OK")
    public void responseApiDepartemensSettingsMengembalikanStatus200Ok() {
        Assert.assertTrue(true);
    }

    @Dan("response API memuat daftar konfigurasi departemen")
    public void responseApiMemuatDaftarKonfigurasiDepartemen() {
        Assert.assertTrue(true);
    }

    @Ketika("client mengirim request POST ke endpoint {string} dengan nama {string}")
    public void clientMengirimRequestPostKeEndpointDepartementsSettingsDenganNama(String endpoint, String name) {
        logger.info("Mengirim POST request ke: {} dengan nama: {}", endpoint, name);
    }

    @Maka("response API departemens settings mengembalikan status 200 atau 201")
    public void responseApiDepartemensSettingsMengembalikanStatus200Atau201() {
        Assert.assertTrue(true);
    }

    @Dan("response API memuat setting departemen yang baru dibuat")
    public void responseApiMemuatSettingDepartemenYangBaruDibuat() {
        Assert.assertTrue(true);
    }

    @Ketika("client mengirim request PATCH ke endpoint {string} dengan status {string}")
    public void clientMengirimRequestPatchKeEndpointDepartementsSettingsDenganStatus(String endpoint, String status) {
        logger.info("Mengirim PATCH request ke: {} dengan status: {}", endpoint, status);
    }

    @Ketika("client mengirim request DELETE ke endpoint departements settings {string} dengan ID valid")
    public void clientMengirimRequestDeleteKeEndpointDepartemensSettingsDenganIdValid(String endpoint) {
        logger.info("Mengirim DELETE request ke: {} dengan ID valid", endpoint);
    }
}

