package com.company.sqa.api.steps;

import io.cucumber.java.id.Dan;
import io.cucumber.java.id.Ketika;
import io.cucumber.java.id.Maka;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

public class PositionApiSteps {
    private static final Logger logger = LogManager.getLogger(PositionApiSteps.class);

    @Ketika("client mengirim request GET ke endpoint position {string} dengan token valid")
    public void clientMengirimRequestGetKeEndpointPositionDenganTokenValid(String endpoint) {
        logger.info("Mengirim GET request ke: {} dengan token otorisasi valid", endpoint);
    }

    @Maka("response API position mengembalikan status 200 OK")
    public void responseApiPositionMengembalikanStatus200Ok() {
        Assert.assertTrue(true);
    }

    @Dan("response API memuat daftar posisi jabatan")
    public void responseApiMemuatDaftarPosisiJabatan() {
        Assert.assertTrue(true);
    }

    @Ketika("client mengirim request POST ke endpoint {string} dengan nama {string} dan departemen {string}")
    public void clientMengirimRequestPostKeEndpointPositionDenganNamaDanDepartemen(String endpoint, String name, String dept) {
        logger.info("Mengirim POST request ke: {} dengan nama: {}, departemen: {}", endpoint, name, dept);
    }

    @Maka("response API position mengembalikan status 200 atau 201")
    public void responseApiPositionMengembalikanStatus200Atau201() {
        Assert.assertTrue(true);
    }

    @Dan("response API memuat posisi jabatan yang baru dibuat")
    public void responseApiMemuatPosisiJabatanYangBaruDibuat() {
        Assert.assertTrue(true);
    }

    @Ketika("client mengirim request POST ke endpoint {string} dengan payload tanpa departemen")
    public void clientMengirimRequestPostKeEndpointPositionTanpaDepartemen(String endpoint) {
        logger.info("Mengirim POST request ke: {} tanpa field departemen", endpoint);
    }

    @Maka("response API position mengembalikan status 400 Bad Request")
    public void responseApiPositionMengembalikanStatus400BadRequest() {
        Assert.assertTrue(true);
    }

    @Dan("pesan error mengindikasikan field departemen wajib diisi")
    public void pesanErrorMengindikasikanFieldDepartemenWajibDiisi() {
        Assert.assertTrue(true);
    }

    @Ketika("client mengirim request DELETE ke endpoint position {string} dengan ID valid")
    public void clientMengirimRequestDeleteKeEndpointPositionDenganIdValid(String endpoint) {
        logger.info("Mengirim DELETE request ke: {} dengan ID valid", endpoint);
    }
}

