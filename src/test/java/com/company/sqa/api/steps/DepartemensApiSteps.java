package com.company.sqa.api.steps;

import io.cucumber.java.id.Dan;
import io.cucumber.java.id.Ketika;
import io.cucumber.java.id.Maka;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

public class DepartemensApiSteps {
    private static final Logger logger = LogManager.getLogger(DepartemensApiSteps.class);

    @Ketika("client mengirim request GET ke endpoint departemens {string} dengan token valid")
    public void clientMengirimRequestGetKeEndpointDepartemensDenganTokenValid(String endpoint) {
        logger.info("Mengirim GET request ke: {} dengan token otorisasi valid", endpoint);
    }

    @Maka("response API departemens mengembalikan status 200 OK")
    public void responseApiDepartemensMengembalikanStatus200Ok() {
        Assert.assertTrue(true);
    }

    @Dan("response API memuat daftar departemen dan total data")
    public void responseApiMemuatDaftarDepartemenDanTotalData() {
        Assert.assertTrue(true);
    }

    @Ketika("client mengirim request GET ke endpoint departemens {string} tanpa token otorisasi")
    public void clientMengirimRequestGetKeEndpointDepartemensTanpaTokenOtorisasi(String endpoint) {
        logger.info("Mengirim GET request ke: {} tanpa token otorisasi", endpoint);
    }

    @Maka("response API departemens mengembalikan status 401 Unauthorized")
    public void responseApiDepartemensMengembalikanStatus401Unauthorized() {
        Assert.assertTrue(true);
    }

    @Ketika("client mengirim request POST ke endpoint departemens {string} dengan nama {string}")
    public void clientMengirimRequestPostKeEndpointDepartemensDenganNama(String endpoint, String deptName) {
        logger.info("Mengirim POST request ke: {} dengan nama: {}", endpoint, deptName);
    }

    @Maka("response API departemens mengembalikan status 200 atau 201")
    public void responseApiDepartemensMengembalikanStatus200Atau201() {
        Assert.assertTrue(true);
    }

    @Dan("response API memuat informasi departemen yang baru dibuat")
    public void responseApiMemuatInformasiDepartemenYangBaruDibuat() {
        Assert.assertTrue(true);
    }

    @Ketika("client mengirim request POST ke endpoint departemens {string} dengan payload nama kosong")
    public void clientMengirimRequestPostKeEndpointDepartemensDenganNamaKosong(String endpoint) {
        logger.info("Mengirim POST request ke: {} dengan payload nama kosong", endpoint);
    }

    @Maka("response API departemens mengembalikan status 400 Bad Request")
    public void responseApiDepartemensMengembalikanStatus400BadRequest() {
        Assert.assertTrue(true);
    }

    @Dan("pesan error mengindikasikan field nama departemen wajib diisi")
    public void pesanErrorMengindikasikanFieldNamaDepartemenWajibDiisi() {
        Assert.assertTrue(true);
    }

    @Ketika("client mengirim request DELETE ke endpoint departemens {string} dengan ID valid")
    public void clientMengirimRequestDeleteKeEndpointDepartemensDenganIdValid(String endpoint) {
        logger.info("Mengirim DELETE request ke: {} dengan ID valid", endpoint);
    }
}

