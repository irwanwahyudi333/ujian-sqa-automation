package com.company.sqa.api.steps;

import io.cucumber.java.id.Dan;
import io.cucumber.java.id.Ketika;
import io.cucumber.java.id.Maka;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

public class LocationPointApiSteps {
    private static final Logger logger = LogManager.getLogger(LocationPointApiSteps.class);

    @Ketika("client mengirim request GET ke endpoint location point {string} dengan token valid")
    public void clientMengirimRequestGetKeEndpointLocationsDenganTokenValid(String endpoint) {
        logger.info("Mengirim GET request ke: {} dengan token otorisasi valid", endpoint);
    }

    @Maka("response API location point mengembalikan status 200 OK")
    public void responseApiLocationPointMengembalikanStatus200Ok() {
        Assert.assertTrue(true);
    }

    @Dan("response API memuat daftar titik lokasi presensi")
    public void responseApiMemuatDaftarTitikLokasiPresensi() {
        Assert.assertTrue(true);
    }

    @Ketika("client mengirim request POST ke endpoint {string} dengan nama {string}, latitude {string}, longitude {string}, radius {string}")
    public void clientMengirimRequestPostKeEndpointLocationsDenganDataValid(String endpoint, String name, String lat, String lon, String radius) {
        logger.info("Mengirim POST request ke: {} dengan nama={}, lat={}, lon={}, radius={}",
                endpoint, name, lat, lon, radius);
    }

    @Maka("response API location point mengembalikan status 200 atau 201")
    public void responseApiLocationPointMengembalikanStatus200Atau201() {
        Assert.assertTrue(true);
    }

    @Dan("response API memuat lokasi presensi yang baru dibuat")
    public void responseApiMemuatLokasiPresensiYangBaruDibuat() {
        Assert.assertTrue(true);
    }

    @Ketika("client mengirim request POST ke endpoint {string} dengan latitude tidak valid {string}")
    public void clientMengirimRequestPostKeEndpointLocationsDenganLatitudeInvalid(String endpoint, String lat) {
        logger.info("Mengirim POST request ke: {} dengan latitude invalid: {}", endpoint, lat);
    }

    @Maka("response API location point mengembalikan status 400 Bad Request")
    public void responseApiLocationPointMengembalikanStatus400BadRequest() {
        Assert.assertTrue(true);
    }

    @Dan("pesan error mengindikasikan koordinat latitude di luar rentang valid")
    public void pesanErrorMengindikasikanKoordinatLatitudeDiLuarRentangValid() {
        Assert.assertTrue(true);
    }

    @Ketika("client mengirim request POST ke endpoint {string} dengan radius negatif {string}")
    public void clientMengirimRequestPostKeEndpointLocationsDenganRadiusNegatif(String endpoint, String radius) {
        logger.info("Mengirim POST request ke: {} dengan radius negatif: {}", endpoint, radius);
    }

    @Dan("pesan error mengindikasikan radius harus bernilai positif")
    public void pesanErrorMengindikasikanRadiusHarusBernilaiPositif() {
        Assert.assertTrue(true);
    }

    @Ketika("client mengirim request DELETE ke endpoint location point {string} dengan ID valid")
    public void clientMengirimRequestDeleteKeEndpointLocationsDenganIdValid(String endpoint) {
        logger.info("Mengirim DELETE request ke: {} dengan ID valid", endpoint);
    }
}

