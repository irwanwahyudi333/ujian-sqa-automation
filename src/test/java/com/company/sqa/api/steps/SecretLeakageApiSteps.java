package com.company.sqa.api.steps;

import io.cucumber.java.id.Dan;
import io.cucumber.java.id.Ketika;
import io.cucumber.java.id.Maka;
import io.qameta.allure.Allure;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class SecretLeakageApiSteps {
    private static final Logger logger = LogManager.getLogger(SecretLeakageApiSteps.class);

    private static final String STATIC_WEB_DIR = "src/test/resources/static-web";
    private static final String STATIC_UPLOADS_URL = "https://api-dev.dikahadir.com/hadir-api-magang/uploads/9650f149d622055c4b495e65e9148ebfc5e61b1bb00e83538e76afe48c496eaf.jpeg";

    private final List<String> leakedSecretsFound = new ArrayList<>();
    private final Map<String, String> leakedFileSnippets = new HashMap<>();
    private Response googleApiResponse;
    private Response uploadAccessResponse;
    private String loginHtmlContent;

    // ==========================================
    // TC-SEC-LEAK-01: Session Encryption Password in JS Bundles
    // ==========================================

    @Ketika("client mengunduh dan menganalisis seluruh bundel JavaScript frontend")
    public void clientMengunduhDanMenganalisisSeluruhBundelJavaScriptFrontend() throws IOException {
        logger.info("Menganalisis seluruh bundel JavaScript pada {}", STATIC_WEB_DIR);
        leakedSecretsFound.clear();
        leakedFileSnippets.clear();

        Path chunksPath = Paths.get(STATIC_WEB_DIR, "_next/static/chunks");
        if (!Files.exists(chunksPath)) {
            // Fallback ke target/test-classes
            chunksPath = Paths.get("target/test-classes/static-web/_next/static/chunks");
        }

        if (Files.exists(chunksPath)) {
            try (var stream = Files.walk(chunksPath)) {
                stream.filter(p -> p.toString().endsWith(".js"))
                        .forEach(p -> {
                            try {
                                String content = Files.readString(p, StandardCharsets.UTF_8);
                                if (content.contains("SESSION_PASSWORD")) {
                                    leakedSecretsFound.add(p.getFileName().toString() + " (SESSION_PASSWORD)");
                                    int idx = content.indexOf("SESSION_PASSWORD");
                                    String snippet = content.substring(Math.max(0, idx - 100), Math.min(content.length(), idx + 200));
                                    leakedFileSnippets.put(p.getFileName().toString(), snippet);
                                }
                                if (content.contains("qPatLEX16rTYBKQuaC1iyZk1R")) {
                                    leakedSecretsFound.add(p.getFileName().toString() + " (qPatLEX Secret Key)");
                                    int idx = content.indexOf("qPatLEX16rTYBKQuaC1iyZk1R");
                                    String snippet = content.substring(Math.max(0, idx - 100), Math.min(content.length(), idx + 200));
                                    leakedFileSnippets.put(p.getFileName().toString(), snippet);
                                }
                            } catch (Exception e) {
                                logger.error("Gagal membaca berkas JS: {}", p, e);
                            }
                        });
            }
        }

        StringBuilder attachment = new StringBuilder("Daftar Bundel Teranalisis:\n");
        for (String secret : leakedSecretsFound) {
            attachment.append("- ").append(secret).append("\n");
        }
        for (Map.Entry<String, String> entry : leakedFileSnippets.entrySet()) {
            attachment.append("\n=== Snippet dari ").append(entry.getKey()).append(" ===\n").append(entry.getValue()).append("\n");
        }
        Allure.addAttachment("Hasil Scan Bundel JavaScript Klien", attachment.toString());
    }

    @Maka("bundel JavaScript tidak boleh memuat kata kunci {string}")
    public void bundelJavaScriptTidakBolehMemuatKataKunci(String keyword) {
        boolean containsKeyword = leakedSecretsFound.stream().anyMatch(s -> s.contains(keyword));
        if (containsKeyword) {
            String report = """
                    TEMUAN KEAMANAN KRITIS (CWE-798: Use of Hard-coded Credentials / CWE-312):
                    Kata kunci sensitif '%s' ditemukan di dalam bundel JavaScript sisi klien!
                    Komponen Terdampak: _app-2ad6ec1cd5a42c22.js (Modul 98607)
                    Nilai Hardcoded: SESSION_PASSWORD: "qPatLEX16rTYBKQuaC1iyZk1RGhyV9q6"
                    Dampak: Kunci enkripsi sesi iron-session terkompromi secara publik. Penyerang dapat
                    mendekripsi cookie sesi hadir_web dan membuat sesi palsu (Session Forgery / Privilege Escalation).
                    Rekomendasi: Pindahkan seluruh konfigurasi sesi ke runtime server Node.js dan jangan pernah
                    mengekspor variabel SESSION_PASSWORD ke komponen yang dibundel oleh Webpack ke client.
                    """.formatted(keyword);
            Allure.addAttachment("Defect Audit Report - Exposed SESSION_PASSWORD in Client Bundle", report);
            Assert.fail("CRITICAL SECURITY DEFECT: Kata kunci '" + keyword + "' ditemukan secara publik di dalam bundel JavaScript klien!");
        }
    }

    @Dan("bundel JavaScript tidak boleh memuat secret key enkripsi sesi hardcoded")
    public void bundelJavaScriptTidakBolehMemuatSecretKeyEnkripsiSesiHardcoded() {
        boolean containsSecret = leakedSecretsFound.stream().anyMatch(s -> s.contains("qPatLEX"));
        if (containsSecret) {
            String report = """
                    TEMUAN KEAMANAN KRITIS (CWE-798: Use of Hard-coded Credentials):
                    Ditemukan secret key enkripsi sesi hardcoded pada artefak bundel JavaScript:
                    1. _app-2ad6ec1cd5a42c22.js -> "qPatLEX16rTYBKQuaC1iyZk1RGhyV9q6" (hadir_web)
                    2. 1662-19023e70d608d634.js -> "qPatLEX16rTYBKQuaC1iyZk1RsGhyV9q6e" (hadir_web_magang)
                    Dampak: Integritas cookie sesi pada aplikasi web Hadir dan Hadir Magang sepenuhnya cacat.
                    """.formatted();
            Allure.addAttachment("Defect Audit Report - Hardcoded Session Secret Keys", report);
            Assert.fail("CRITICAL SECURITY DEFECT: Bundel JavaScript memuat 2 secret key enkripsi sesi hardcoded!");
        }
    }

    // ==========================================
    // TC-SEC-LEAK-02: Google Maps API Key Restrictions
    // ==========================================

    @Ketika("client mengirim request ke Google Maps API menggunakan key {string} dengan referer tidak terdaftar {string}")
    public void clientMengirimRequestKeGoogleMapsApiDenganRefererTidakTerdaftar(String apiKey, String unauthorizedReferer) {
        logger.info("Menguji pembatasan domain Google Maps API Key: {} dengan Referer: {}", apiKey, unauthorizedReferer);

        googleApiResponse = RestAssured.given()
                .header("Referer", unauthorizedReferer)
                .when()
                .get("https://maps.googleapis.com/maps/api/js?key=" + apiKey)
                .then()
                .extract().response();

        Allure.addAttachment("Request Header Referer", unauthorizedReferer);
        Allure.addAttachment("Respons Google Maps API (HTTP " + googleApiResponse.getStatusCode() + ")",
                googleApiResponse.asPrettyString().substring(0, Math.min(800, googleApiResponse.asPrettyString().length())));
    }

    @Maka("Google API harus menolak request dan melarang akses dari domain luar")
    public void googleApiHarusMenolakRequestDanMelarangAksesDariDomainLuar() {
        int statusCode = googleApiResponse.getStatusCode();
        String body = googleApiResponse.getBody().asString();
        logger.info("Status respons Google Maps API: {}", statusCode);

        // Jika Google API mengembalikan HTTP 200 OK dengan payload JavaScript SDK utuh,
        // berarti API Key TIDAK memiliki HTTP Referrer Restriction!
        if (statusCode == 200 && body.contains("google.maps")) {
            String report = """
                    TEMUAN KEAMANAN (CWE-200: Information Disclosure / CWE-798: Unrestricted Cloud API Key):
                    Google Cloud Platform Maps API Key 'AIzaSyApRe1R2n-1TEgk5s0Eq0HRZDKo7WzBEWE'
                    dapat diakses dan memuat seluruh SDK Maps dari domain luar/penyerang (HTTP 200 OK)!
                    Header Referer yang Digunakan: https://unauthorized-attacker-domain.com
                    Status Aktual: 200 OK (Google Maps SDK Berhasil Diunduh)
                    Status Seharusnya: Ditolak / Forbidden jika restriction domain aktif.
                    Dampak: Pihak ketiga dapat mencuri key ini dan menggunakannya secara bebas pada website
                    mereka sendiri, menyedot kuota bulanan perusahaan, atau menyebabkan pembengkakan tagihan GCP.
                    Rekomendasi: Buka Google Cloud Console > APIs & Services > Credentials, lalu terapkan
                    'HTTP Referrer Restriction' khusus untuk domain *.dikahadir.com/*.
                    """;
            Allure.addAttachment("Defect Audit Report - Unrestricted Google Maps API Key", report);
            Assert.fail("SECURITY DEFECT TERDETEKSI: Google Maps API Key tidak memiliki HTTP Referrer Restriction dan berhasil diakses oleh domain penyerang (HTTP 200 OK)!");
        }

        Assert.assertTrue(statusCode == 403 || body.contains("REQUEST_DENIED"),
                "Google Maps API seharusnya menolak request dari referer yang tidak diizinkan!");
    }

    // ==========================================
    // TC-SEC-LEAK-03: Session Cookie Encryption Integrity
    // ==========================================

    @Ketika("client menguji integritas konfigurasi enkripsi cookie sesi sisi klien")
    public void clientMengujiIntegritasKonfigurasiEnkripsiCookieSesiSisiKlien() {
        logger.info("Memeriksa chunk 1662-19023e70d608d634.js untuk modul enkripsi sesi 35594");
        Path chunkPath = Paths.get(STATIC_WEB_DIR, "_next/static/chunks/1662-19023e70d608d634.js");
        if (!Files.exists(chunkPath)) {
            chunkPath = Paths.get("target/test-classes/static-web/_next/static/chunks/1662-19023e70d608d634.js");
        }

        try {
            if (Files.exists(chunkPath)) {
                String content = Files.readString(chunkPath, StandardCharsets.UTF_8);
                if (content.contains("hadir_web_magang") && content.contains("qPatLEX16rTYBKQuaC1iyZk1RsGhyV9q6e")) {
                    int idx = content.indexOf("hadir_web_magang");
                    String snippet = content.substring(Math.max(0, idx - 150), Math.min(content.length(), idx + 150));
                    Allure.addAttachment("Exposed Session Wrapper in Chunk 1662", snippet);
                }
            }
        } catch (Exception e) {
            logger.error("Gagal membaca chunk 1662", e);
        }
    }

    @Maka("sistem tidak boleh mengekspos secret key enkripsi sesi pada chunk klien")
    public void sistemTidakBolehMengeksposSecretKeyEnkripsiSesiPadaChunkKlien() {
        Path chunkPath = Paths.get(STATIC_WEB_DIR, "_next/static/chunks/1662-19023e70d608d634.js");
        if (!Files.exists(chunkPath)) {
            chunkPath = Paths.get("target/test-classes/static-web/_next/static/chunks/1662-19023e70d608d634.js");
        }

        boolean isExposed = false;
        try {
            if (Files.exists(chunkPath)) {
                String content = Files.readString(chunkPath, StandardCharsets.UTF_8);
                isExposed = content.contains("qPatLEX16rTYBKQuaC1iyZk1RsGhyV9q6e");
            }
        } catch (Exception ignored) {}

        if (isExposed) {
            String report = """
                    TEMUAN KEAMANAN KRITIS (CWE-312 / CWE-798 - BUG-MGMT-006):
                    Modul enkripsi sesi '35594' pada bundel '1662-19023e70d608d634.js' mengekspos kata sandi:
                    { password: "qPatLEX16rTYBKQuaC1iyZk1RsGhyV9q6e", cookieName: "hadir_web_magang" }
                    Dampak: Siapa pun dapat mendekripsi cookie sesi 'hadir_web_magang', memanipulasi identitas user,
                    dan melakukan pemalsuan sesi login magang secara bebas.
                    """;
            Allure.addAttachment("Defect Audit Report - Session Forgery Risk via Exposed Password", report);
            Assert.fail("CRITICAL SECURITY DEFECT (BUG-MGMT-006): Secret key 'hadir_web_magang' terekspos langsung pada chunk JavaScript klien!");
        }
    }

    // ==========================================
    // TC-SEC-LEAK-05: Unauthenticated Storage Access
    // ==========================================

    @Ketika("client mengirim request GET ke endpoint upload berkas selfie tanpa menyertakan token otorisasi")
    public void clientMengirimRequestGetKeEndpointUploadBerkasSelfieTanpaMenyertakanTokenOtorisasi() {
        logger.info("Menguji akses unauthenticated ke endpoint static upload foto selfie: {}", STATIC_UPLOADS_URL);

        uploadAccessResponse = RestAssured.given()
                .when()
                .get(STATIC_UPLOADS_URL)
                .then()
                .extract().response();

        Allure.addAttachment("Akses Unauthenticated ke Foto Selfie",
                "Target URL: " + STATIC_UPLOADS_URL + "\n" +
                        "HTTP Status: " + uploadAccessResponse.getStatusCode() + "\n" +
                        "Content-Type: " + uploadAccessResponse.getContentType() + "\n" +
                        "Content-Length: " + uploadAccessResponse.getHeader("Content-Length"));
    }

    @Maka("sistem backend harus menolak akses atau menerapkan proteksi otorisasi")
    public void sistemBackendHarusMenolakAksesAtauMenerapkanProteksiOtorisasi() {
        int statusCode = uploadAccessResponse.getStatusCode();
        String contentType = uploadAccessResponse.getContentType();
        logger.info("Status akses unauthenticated berkas foto: {}, Content-Type: {}", statusCode, contentType);

        // Jika endpoint mengembalikan HTTP 200 OK dengan format gambar tanpa token autentikasi,
        // maka ini adalah celah BOLA / IDOR / CWE-200 pada berkas pribadi karyawan.
        if (statusCode == 200 && contentType != null && contentType.contains("image/")) {
            String report = """
                    TEMUAN KEAMANAN (CWE-200: Information Disclosure / CWE-639: Authorization Bypass Through User-Controlled Key):
                    Berkas foto selfie absensi dan profil karyawan dapat diunduh langsung secara publik
                    tanpa menyertakan header 'Authorization: Bearer <token>'!
                    URL Berkas: %s
                    Status Aktual: 200 OK (Content-Type: %s)
                    Status Seharusnya: 401 Unauthorized / 403 Forbidden / Presigned URL bertempo singkat.
                    Dampak: Penyerang dapat mengikis (scrape) seluruh foto selfie, foto KTP, dan dokumen
                    pribadi karyawan HADIR secara massal tanpa memerlukan akun atau hak akses.
                    Rekomendasi: Lindungi endpoint static upload dengan token autentikasi atau gunakan presigned URL
                    yang kedaluwarsa dalam 5-10 menit.
                    """.formatted(STATIC_UPLOADS_URL, contentType);
            Allure.addAttachment("Defect Audit Report - Public Exposure of Private Selfie Storage", report);
            Assert.fail("SECURITY DEFECT TERDETEKSI: Berkas foto selfie karyawan dapat diakses langsung oleh publik tanpa autentikasi (HTTP 200 OK)!");
        }

        Assert.assertTrue(statusCode == 401 || statusCode == 403,
                "Akses ke berkas foto pribadi karyawan seharusnya ditolak tanpa autentikasi!");
    }

    // ==========================================
    // TC-SEC-LEAK-06: Hardcoded Plaintext Credentials in HTML Template
    // ==========================================

    @Ketika("client menganalisis file template autentikasi login HTML")
    public void clientMenganalisisFileTemplateAutentikasiLoginHtml() throws IOException {
        logger.info("Menganalisis file template login HTML pada {}", STATIC_WEB_DIR + "/authentication/login.html");
        Path loginPath = Paths.get(STATIC_WEB_DIR, "authentication/login.html");
        if (!Files.exists(loginPath)) {
            loginPath = Paths.get("target/test-classes/static-web/authentication/login.html");
        }

        if (Files.exists(loginPath)) {
            loginHtmlContent = Files.readString(loginPath, StandardCharsets.UTF_8);
            Allure.addAttachment("Ukuran File login.html", loginHtmlContent.length() + " bytes");
        } else {
            loginHtmlContent = "";
        }
    }

    @Maka("file template tidak boleh memuat kredensial akun dan password plaintext secara hardcoded")
    public void fileTemplateTidakBolehMemuatKredensialAkunDanPasswordPlaintextSecaraHardcoded() {
        boolean containsAdmin = loginHtmlContent.contains("admin@dikahadir.com");
        boolean containsPassword = loginHtmlContent.contains("P@ssw0rdMagang123");

        if (containsAdmin || containsPassword) {
            String report = """
                    TEMUAN KEAMANAN (CWE-798: Use of Hard-coded Credentials / CWE-259):
                    File template frontend 'authentication/login.html' memuat kredensial akun dan password plaintext:
                    - Akun Administratif: 'admin@dikahadir.com'
                    - Akun QA: 'qa.approved.user@dikahadir.com'
                    - Plaintext Password: 'P@ssw0rdMagang123'
                    - Mock Auth Token: 'dummy-jwt-token-sqa-approved'
                    Dampak: Jika akun ini ada pada lingkungan staging/produksi dengan password yang sama,
                    penyerang dapat langsung mengambil alih akun administratif.
                    Rekomendasi: Bersihkan seluruh kredensial hardcoded dari kode template frontend.
                    """;
            Allure.addAttachment("Defect Audit Report - Hardcoded Credentials in login.html", report);
            Assert.fail("SECURITY DEFECT TERDETEKSI: Ditemukan kredensial akun administratif dan password plaintext di dalam file template login.html!");
        }
    }

    // ==========================================
    // TC-SEC-LEAK-07: Insecure Token Storage in Client Web Storage (localStorage / sessionStorage)
    // ==========================================

    @Ketika("client menganalisis mekanisme penyimpanan sesi pada berkas skrip autentikasi login")
    public void clientMenganalisisMekanismePenyimpananSesiPadaBerkasSkripAutentikasiLogin() throws IOException {
        logger.info("Menganalisis mekanisme penyimpanan sesi pada berkas skrip autentikasi login");
        Path loginPath = Paths.get(STATIC_WEB_DIR, "authentication/login.html");
        if (!Files.exists(loginPath)) {
            loginPath = Paths.get("target/test-classes/static-web/authentication/login.html");
        }

        if (Files.exists(loginPath)) {
            loginHtmlContent = Files.readString(loginPath, StandardCharsets.UTF_8);
            Allure.addAttachment("Isi Script login.html untuk Audit Storage", loginHtmlContent);
        } else {
            loginHtmlContent = "";
        }
    }

    @Maka("skrip autentikasi tidak boleh menyimpan token akses pada localStorage atau sessionStorage")
    public void skripAutentikasiTidakBolehMenyimpanTokenAksesPadaLocalStorageAtauSessionStorage() {
        boolean usesLocalStorage = loginHtmlContent.contains("localStorage.setItem('token'") || loginHtmlContent.contains("localStorage.setItem(\"token\"");
        boolean usesSessionStorage = loginHtmlContent.contains("sessionStorage.setItem('token'") || loginHtmlContent.contains("sessionStorage.setItem(\"token\"");

        if (usesLocalStorage || usesSessionStorage) {
            String report = """
                    TEMUAN KEAMANAN (CWE-922: Insecure Storage of Sensitive Information / CWE-312):
                    Skrip autentikasi frontend menyimpan token akses autentikasi secara langsung pada Web Storage browser:
                    - Bukti: localStorage.setItem('token', 'dummy-jwt-token-sqa-approved')
                    - Bukti: sessionStorage.setItem('token', 'dummy-jwt-token-sqa-approved')
                    Dampak: Web Storage (localStorage dan sessionStorage) tidak memiliki atribut keamanan HttpOnly,
                    sehingga token dapat dibaca dan dieksfiltrasi oleh skrip berbahaya melalui serangan Cross-Site Scripting (XSS).
                    Rekomendasi: Jangan simpan token autentikasi pada Web Storage. Gunakan Cookie dengan flag HttpOnly,
                    Secure, dan SameSite=Lax/Strict untuk mengelola sesi login secara aman.
                    """;
            Allure.addAttachment("Defect Audit Report - Insecure Token Storage in Web Storage", report);
            Assert.fail("SECURITY DEFECT TERDETEKSI: Skrip autentikasi menyimpan token pada localStorage/sessionStorage yang rentan terhadap XSS (CWE-922)!");
        }
    }
}

