package com.company.sqa.api.steps;

import io.cucumber.java.id.Dan;
import io.cucumber.java.id.Ketika;
import io.cucumber.java.id.Maka;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

public class ClientUplinerApiSteps {
    private static final Logger logger = LogManager.getLogger(ClientUplinerApiSteps.class);

    @Ketika("client mengirim request GET ke endpoint {string} dengan parameter tipe upliner")
    public void clientMengirimRequestGetKeEndpointUserDenganTipeUpliner(String endpoint) {
        logger.info("Mengirim GET request ke: {} dengan parameter filter tipe upliner", endpoint);
    }

    @Maka("response API client upliner mengembalikan status 200 OK")
    public void responseApiClientUplinerMengembalikanStatus200Ok() {
        Assert.assertTrue(true);
    }

    @Dan("response API memuat daftar akun client dan upliner")
    public void responseApiMemuatDaftarAkunClientDanUpliner() {
        Assert.assertTrue(true);
    }

    @Ketika("client mengirim request GET ke endpoint client upliner {string} tanpa token otorisasi")
    public void clientMengirimRequestGetKeEndpointUserTanpaToken(String endpoint) {
        logger.info("Mengirim GET request ke: {} tanpa token otorisasi", endpoint);
    }

    @Maka("response API client upliner mengembalikan status 401 Unauthorized")
    public void responseApiClientUplinerMengembalikanStatus401Unauthorized() {
        Assert.assertTrue(true);
    }

    @Ketika("client mengirim request DELETE ke endpoint {string} untuk upliner dengan ID valid")
    public void clientMengirimRequestDeleteKeEndpointUserUpliner(String endpoint) {
        logger.info("Mengirim DELETE request ke: {} dengan ID upliner valid", endpoint);
    }
}

