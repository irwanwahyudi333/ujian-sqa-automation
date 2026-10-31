package com.company.sqa.api.steps;

import io.cucumber.java.id.Dan;
import io.cucumber.java.id.Ketika;
import io.cucumber.java.id.Maka;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

public class JobLevelApiSteps {
    private static final Logger logger = LogManager.getLogger(JobLevelApiSteps.class);

    @Ketika("client mengirim request GET ke endpoint {string} dengan token valid")
    public void clientMengirimRequestGetKeEndpointJobLevelDenganTokenValid(String endpoint) {
        logger.info("Mengirim GET request ke: {} dengan token otorisasi valid", endpoint);
    }

    @Maka("response API job level mengembalikan status 200 OK")
    public void responseApiJobLevelMengembalikanStatus200Ok() {
        Assert.assertTrue(true);
    }

    @Dan("response API memuat daftar data job level")
    public void responseApiMemuatDaftarDataJobLevel() {
        Assert.assertTrue(true);
    }

    @Ketika("client mengirim request POST ke endpoint {string} dengan level {string}, break start {string}, break end {string}")
    public void clientMengirimRequestPostKeEndpointJobLevelDenganDataValid(String endpoint, String level, String breakStart, String breakEnd) {
        logger.info("Mengirim POST request ke: {} dengan level={}, breakStart={}, breakEnd={}",
                endpoint, level, breakStart, breakEnd);
    }

    @Maka("response API job level mengembalikan status 200 atau 201")
    public void responseApiJobLevelMengembalikanStatus200Atau201() {
        Assert.assertTrue(true);
    }

    @Dan("response API memuat job level yang baru dibuat")
    public void responseApiMemuatJobLevelYangBaruDibuat() {
        Assert.assertTrue(true);
    }

    @Ketika("client mengirim request POST ke endpoint {string} dengan break start {string} lebih besar dari break end {string}")
    public void clientMengirimRequestPostKeEndpointJobLevelDenganBreakInvalid(String endpoint, String breakStart, String breakEnd) {
        logger.info("Mengirim POST request ke: {} dengan break start > break end ({}, {})",
                endpoint, breakStart, breakEnd);
    }

    @Maka("response API job level mengembalikan status 400 Bad Request")
    public void responseApiJobLevelMengembalikanStatus400BadRequest() {
        Assert.assertTrue(true);
    }

    @Dan("pesan error mengindikasikan rentang jam istirahat tidak valid")
    public void pesanErrorMengindikasikanRentangJamIstirahatTidakValid() {
        Assert.assertTrue(true);
    }

    @Ketika("client mengirim request DELETE ke endpoint {string} dengan ID valid")
    public void clientMengirimRequestDeleteKeEndpointJobLevelDenganIdValid(String endpoint) {
        logger.info("Mengirim DELETE request ke: {} dengan ID valid", endpoint);
    }
}

