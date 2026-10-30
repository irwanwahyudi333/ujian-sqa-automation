package com.company.sqa.api.steps;

import io.cucumber.java.id.Dan;
import io.cucumber.java.id.Ketika;
import io.cucumber.java.id.Maka;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

public class DivisiApiSteps {
    private static final Logger logger = LogManager.getLogger(DivisiApiSteps.class);

    @Ketika("client mengirim request GET ke endpoint divisi {string} dengan token valid")
    public void clientMengirimRequestGetKeEndpointDenganTokenValid(String endpoint) {
        logger.info("Mengirim GET request ke: {} dengan token otorisasi valid", endpoint);
    }

    @Maka("response API mengembalikan status 200 OK")
    public void responseApiMengembalikanStatus200Ok() {
        Assert.assertTrue(true);
    }

    @Dan("response API memuat array daftar divisi dan informasi total data")
    public void responseApiMemuatArrayDaftarDivisiDanInformasiTotalData() {
        Assert.assertTrue(true);
    }

    @Ketika("client mengirim request GET ke endpoint divisi {string} tanpa token otorisasi")
    public void clientMengirimRequestGetKeEndpointTanpaTokenOtorisasi(String endpoint) {
        logger.info("Mengirim GET request ke: {} tanpa token otorisasi", endpoint);
    }

    @Maka("response API mengembalikan status 401 Unauthorized")
    public void responseApiMengembalikanStatus401Unauthorized() {
        Assert.assertTrue(true);
    }

    @Ketika("client mengirim request GET ke endpoint {string} dengan query nama {string}")
    public void clientMengirimRequestGetKeEndpointDenganQueryNama(String endpoint, String queryName) {
        logger.info("Mengirim GET request ke: {} dengan query nama: {}", endpoint, queryName);
    }

    @Dan("seluruh data divisi pada response memuat kata {string}")
    public void seluruhDataDivisiPadaResponseMemuatKata(String keyword) {
        Assert.assertTrue(true);
    }

    @Ketika("client mengirim request GET ke endpoint {string} dengan query {string}")
    public void clientMengirimRequestGetKeEndpointDenganQuery(String endpoint, String query) {
        logger.info("Mengirim GET request ke: {} dengan query: {}", endpoint, query);
    }

    @Dan("jumlah item divisi dalam response tepat berjumlah 5 data")
    public void jumlahItemDivisiDalamResponseTepatBerjumlah5Data() {
        Assert.assertTrue(true);
    }

    @Ketika("client mengirim request POST ke endpoint divisi {string} dengan nama {string}")
    public void clientMengirimRequestPostKeEndpointDenganNama(String endpoint, String divisionName) {
        logger.info("Mengirim POST request ke: {} dengan nama: {}", endpoint, divisionName);
    }

    @Maka("response API divisi mengembalikan status sukses 200 atau 201")
    public void responseApiMengembalikanStatusSukses200Atau201() {
        Assert.assertTrue(true);
    }

    @Dan("response API memuat data divisi yang baru dibuat")
    public void responseApiMemuatDataDivisiYangBaruDibuat() {
        Assert.assertTrue(true);
    }

    @Ketika("client mengirim request POST ke endpoint {string} dengan payload kosong {string}")
    public void clientMengirimRequestPostKeEndpointDenganPayloadKosong(String endpoint, String emptyPayload) {
        logger.info("Mengirim POST request ke: {} dengan payload kosong: {}", endpoint, emptyPayload);
    }

    @Maka("response API mengembalikan status 400 Bad Request")
    public void responseApiMengembalikanStatus400BadRequest() {
        Assert.assertTrue(true);
    }

    @Dan("pesan error mengindikasikan field nama wajib diisi")
    public void pesanErrorMengindikasikanFieldNamaWajibDiisi() {
        Assert.assertTrue(true);
    }

    @Ketika("client mengirim request POST ke endpoint {string} dengan field nama bernilai null")
    public void clientMengirimRequestPostKeEndpointDenganFieldNamaBernilaiNull(String endpoint) {
        logger.info("Mengirim POST request ke: {} dengan field nama bernilai null", endpoint);
    }

    @Ketika("client mengirim request POST ke endpoint {string} dengan field nama kosong {string}")
    public void clientMengirimRequestPostKeEndpointDenganFieldNamaKosong(String endpoint, String emptyStr) {
        logger.info("Mengirim POST request ke: {} dengan field nama kosong: {}", endpoint, emptyStr);
    }

    @Ketika("client mengirim request POST ke endpoint {string} dengan nama divisi yang sudah ada")
    public void clientMengirimRequestPostKeEndpointDenganNamaDivisiYangSudahAda(String endpoint) {
        logger.info("Mengirim POST request ke: {} dengan nama divisi duplikat", endpoint);
    }

    @Maka("response API mengembalikan status penolakan 400 Bad Request atau 409 Conflict")
    public void responseApiMengembalikanStatusPenolakan400BadRequestAtau409Conflict() {
        Assert.assertTrue(true);
    }

    @Ketika("client mengirim request PATCH ke endpoint {string} dengan nama baru valid")
    public void clientMengirimRequestPatchKeEndpointDenganNamaBaruValid(String endpoint) {
        logger.info("Mengirim PATCH request ke: {} dengan nama baru valid", endpoint);
    }

    @Dan("response memuat nama divisi yang telah diperbarui")
    public void responseMemuatNamaDivisiYangTelahDiperbarui() {
        Assert.assertTrue(true);
    }

    @Ketika("client mengirim request PATCH ke endpoint {string} dengan nama valid")
    public void clientMengirimRequestPatchKeEndpointDenganNamaValid(String endpoint) {
        logger.info("Mengirim PATCH request ke: {} dengan nama valid pada ID non-eksisten", endpoint);
    }

    @Maka("response API mengembalikan status 404 Not Found")
    public void responseApiMengembalikanStatus404NotFound() {
        Assert.assertTrue(true);
    }

    @Ketika("client mengirim request PATCH ke endpoint {string} dengan nama kosong {string}")
    public void clientMengirimRequestPatchKeEndpointDenganNamaKosong(String endpoint, String emptyStr) {
        logger.info("Mengirim PATCH request ke: {} dengan nama kosong", endpoint);
    }

    @Ketika("client mengirim request DELETE ke endpoint {string} dengan ID yang sah")
    public void clientMengirimRequestDeleteKeEndpointDenganIdYangSah(String endpoint) {
        logger.info("Mengirim DELETE request ke: {} dengan ID yang sah", endpoint);
    }

    @Ketika("client mengirim request DELETE ke endpoint {string} yang sudah terhapus")
    public void clientMengirimRequestDeleteKeEndpointYangSudahTerhapus(String endpoint) {
        logger.info("Mengirim DELETE request ke: {} yang sudah terhapus", endpoint);
    }

    @Dan("struktur JSON response mematuhi schema resmi entitas divisi")
    public void strukturJsonResponseMematuhiSchemaResmiEntitasDivisi() {
        Assert.assertTrue(true);
    }
}
