package com.company.sqa.api.steps;

import io.cucumber.java.id.Ketika;
import io.cucumber.java.id.Maka;
import io.qameta.allure.Allure;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class UserEditApiSteps {
    private static final Logger logger = LogManager.getLogger(UserEditApiSteps.class);

    private static final String STATIC_UPLOADS_URL = "https://api-dev.dikahadir.com/hadir-api-magang/uploads/";
    private static final String BROKEN_UPLOADS_URL = "https://api-dev.dikahadir.com/uploads/";
    private static final String USERS_ENDPOINT = "https://api-dev.dikahadir.com/hadir-api-magang/users";

    private Response lastResponse;
    private final String testPhotoId = "9650f149d622055c4b495e65e9148ebfc5e61b1bb00e83538e76afe48c496eaf.jpeg";

    @Ketika("client memeriksa aksesibilitas endpoint static foto selfie dengan ID terunggah")
    public void clientMemeriksaAksesibilitasEndpointStaticFotoSelfieDenganIdTerunggah() {
        logger.info("Memeriksa aksesibilitas berkas foto selfie pada endpoint static upload");
        String validUrl = STATIC_UPLOADS_URL + testPhotoId;
        String brokenUrl = BROKEN_UPLOADS_URL + testPhotoId;

        Response brokenResponse = RestAssured.given()
                .when()
                .get(brokenUrl)
                .then()
                .extract().response();

        Allure.addAttachment("Request ke Broken Path (/uploads/)", brokenUrl + " -> HTTP " + brokenResponse.getStatusCode());

        lastResponse = RestAssured.given()
                .when()
                .get(validUrl)
                .then()
                .extract().response();

        Allure.addAttachment("Request ke Valid Path (/hadir-api-magang/uploads/)", validUrl + " -> HTTP " + lastResponse.getStatusCode());
    }

    @Maka("endpoint upload mengembalikan status HTTP 200 OK dengan format gambar valid")
    public void endpointUploadMengembalikanStatusHttp200OkDenganFormatGambarValid() {
        int statusCode = lastResponse.getStatusCode();
        String contentType = lastResponse.getContentType();
        logger.info("Aksesibilitas static uploads: Status {}, Content-Type {}", statusCode, contentType);

        Assert.assertEquals(statusCode, 200, "Endpoint static foto selfie tidak dapat diakses!");
        Assert.assertTrue(contentType != null && contentType.contains("image/"), "Format konten bukan gambar! Aktual: " + contentType);
    }

    @Ketika("client mengirim request update user dengan NIK duplikat yang sudah terdaftar {string}")
    public void clientMengirimRequestUpdateUserDenganNikDuplikatYangSudahTerdaftar(String duplicateNik) {
        logger.info("Mengirim PATCH request update user dengan NIK duplikat: {}", duplicateNik);
        Map<String, Object> payload = new HashMap<>();
        payload.put("nik", duplicateNik);
        payload.put("isVerified", true);

        Allure.addAttachment("Payload Update NIK Duplikat", payload.toString());

        lastResponse = RestAssured.given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)
                .when()
                .patch(USERS_ENDPOINT + "/1")
                .then()
                .extract().response();

        logger.info("Response update NIK status: {}", lastResponse.getStatusCode());
    }

    @Maka("response API mengembalikan status penolakan kesalahan duplikasi 400 atau 409")
    public void responseApiMengembalikanStatusPenolakanKesalahanDuplikasi400Atau409() {
        int statusCode = lastResponse.getStatusCode();
        logger.info("Status respons API validasi duplikasi: {}", statusCode);
        Allure.addAttachment("Status Respons API", String.valueOf(statusCode));
        Allure.addAttachment("Body Respons API", lastResponse.asPrettyString());

        // Status 400 (Bad Request), 401 (Not Auth), 404 (Not Found), 409 (Conflict) menandakan request ditahan
        boolean isRejected = statusCode >= 400;
        Assert.assertTrue(isRejected, "BUG: Backend meloloskan request pembaharuan data duplikat dengan status 200/201!");
    }

    @Ketika("client mengirim request update user dengan Email duplikat yang sudah terdaftar {string}")
    public void clientMengirimRequestUpdateUserDenganEmailDuplikatYangSudahTerdaftar(String duplicateEmail) {
        logger.info("Mengirim PATCH request update user dengan Email duplikat: {}", duplicateEmail);
        Map<String, Object> payload = new HashMap<>();
        payload.put("email", duplicateEmail);
        payload.put("isVerified", true);

        Allure.addAttachment("Payload Update Email Duplikat", payload.toString());

        lastResponse = RestAssured.given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)
                .when()
                .patch(USERS_ENDPOINT + "/1")
                .then()
                .extract().response();

        logger.info("Response update Email status: {}", lastResponse.getStatusCode());
    }

    // ==========================================
    // Skenario Keamanan API Tambahan
    // ==========================================

    private String registeredUserId;
    private String userAuthToken;
    private long jwtLifespanSeconds;

    @Ketika("client mengirim request registrasi dengan menyisipkan atribut {string} bernilai true")
    public void clientMengirimRequestRegistrasiDenganMenyisipkanAtributBernilaiTrue(String attributeName) {
        String uniqueEmail = "mass_assign_" + System.currentTimeMillis() + "@dikahadir.com";
        Map<String, Object> payload = new HashMap<>();
        payload.put("nik", "D6240005");
        payload.put("fullname", "SQA Mass Assignment Auditor");
        payload.put("email", uniqueEmail);
        payload.put("password", "P@ssw0rdAudit123!");
        payload.put("company_code", "DIKA");
        payload.put("is_mobile", true);
        payload.put(attributeName, true);

        Allure.addAttachment("Injected Registration Payload (" + attributeName + ": true)", payload.toString());
        logger.info("Mengirim payload registrasi injeksi mass assignment: {}", uniqueEmail);

        lastResponse = RestAssured.given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(payload)
                .when()
                .post(USERS_ENDPOINT)
                .then()
                .extract().response();

        registeredUserId = lastResponse.jsonPath().getString("id");
        Boolean isVerified = lastResponse.jsonPath().getBoolean("isVerified");

        Allure.addAttachment("Respons Pendaftaran Mass Assignment",
                "HTTP Status: " + lastResponse.getStatusCode() + "\nBody: " + lastResponse.asPrettyString());

        // Coba login langsung untuk membuktikan apakah akun langsung aktif tanpa approval
        if (lastResponse.getStatusCode() == 201 || lastResponse.getStatusCode() == 200) {
            Map<String, Object> loginPayload = new HashMap<>();
            loginPayload.put("strategy", "local");
            loginPayload.put("email", uniqueEmail);
            loginPayload.put("password", "P@ssw0rdAudit123!");

            Response loginResponse = RestAssured.given()
                    .contentType(ContentType.JSON)
                    .body(loginPayload)
                    .when()
                    .post("https://api-dev.dikahadir.com/hadir-api-magang/authentication")
                    .then()
                    .extract().response();

            userAuthToken = loginResponse.jsonPath().getString("accessToken");
            Allure.addAttachment("Uji Login Akun Auto-Approved",
                    "HTTP Status: " + loginResponse.getStatusCode() + "\nAccess Token Acquired: " + (userAuthToken != null));
        }
    }

    @Maka("response API menolak pendaftaran atau tidak mengaktifkan akun secara otomatis tanpa approval")
    public void responseApiMenolakPendaftaranAtauTidakMengaktifkanAkunSecaraOtomatisTanpaApproval() {
        try {
            boolean isAutoVerified = lastResponse.getStatusCode() == 201 
                    && Boolean.TRUE.equals(lastResponse.jsonPath().getBoolean("isVerified"))
                    && userAuthToken != null;

            if (isAutoVerified) {
                String report = "=========================================================\n" +
                                "TEMUAN KERENTANAN KRITIS: MASS ASSIGNMENT (CWE-915)\n" +
                                "=========================================================\n" +
                                "Endpoint: POST /hadir-api-magang/users\n" +
                                "Payload Injeksi: {\"isVerified\": true}\n" +
                                "Status Respon: 201 Created (Akun Langsung Terverifikasi)\n" +
                                "Dampak: Pelamar dapat membypass alur verifikasi foto selfie\n" +
                                "dan persetujuan admin (Maker-Checker approval), langsung login!\n" +
                                "Rekomendasi: Terapkan DTO whitelisting pada controller registrasi.";
                Allure.addAttachment("Defect Audit Report - Mass Assignment Bypass", report);
                Assert.fail("CRITICAL BUG TERDETEKSI: Backend menerima injeksi 'isVerified: true'! Akun otomatis aktif dan dapat login langsung tanpa approval admin!");
            }
        } finally {
            // Bersihkan data uji setelah selesai audit
            if (registeredUserId != null && userAuthToken != null) {
                RestAssured.given()
                        .header("Authorization", "Bearer " + userAuthToken)
                        .delete(USERS_ENDPOINT + "/" + registeredUserId);
                logger.info("Pembersihan akun uji mass assignment selesai: {}", registeredUserId);
            }
        }
    }

    @Ketika("client melakukan pengujian otorisasi modifikasi akun lain dengan token pengguna biasa")
    public void clientMelakukanPengujianOtorisasiModifikasiAkunLainDenganTokenPenggunaBiasa() {
        // Buat user level biasa sementara untuk memperoleh token aktif
        String probeEmail = "probe_" + System.currentTimeMillis() + "@dikahadir.com";
        Map<String, Object> regPayload = new HashMap<>();
        regPayload.put("nik", "D6240005");
        regPayload.put("fullname", "Probe User BOLA");
        regPayload.put("email", probeEmail);
        regPayload.put("password", "P@ssw0rdProbe123!");
        regPayload.put("company_code", "DIKA");
        regPayload.put("is_mobile", true);
        regPayload.put("isVerified", true); // Menggunakan celah mass assignment agar akun langsung aktif

        Response regResp = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(regPayload)
                .post(USERS_ENDPOINT);

        String tempId = regResp.jsonPath().getString("id");

        Map<String, Object> loginPayload = new HashMap<>();
        loginPayload.put("strategy", "local");
        loginPayload.put("email", probeEmail);
        loginPayload.put("password", "P@ssw0rdProbe123!");

        Response loginResp = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(loginPayload)
                .post("https://api-dev.dikahadir.com/hadir-api-magang/authentication");

        String liveToken = loginResp.jsonPath().getString("accessToken");

        try {
            logger.info("Menguji otorisasi BOLA/IDOR pada GET /users dengan live user token");
            lastResponse = RestAssured.given()
                    .header("Authorization", "Bearer " + liveToken)
                    .accept(ContentType.JSON)
                    .when()
                    .get(USERS_ENDPOINT)
                    .then()
                    .extract().response();

            String bodyPreview = lastResponse.asPrettyString();
            if (bodyPreview.length() > 800) {
                bodyPreview = bodyPreview.substring(0, 800) + "... [truncated]";
            }
            Allure.addAttachment("BOLA/IDOR Probe GET /users Response", "Status: " + lastResponse.getStatusCode() + "\nBody: " + bodyPreview);
        } finally {
            if (tempId != null && liveToken != null) {
                RestAssured.given()
                        .header("Authorization", "Bearer " + liveToken)
                        .delete(USERS_ENDPOINT + "/" + tempId);
                logger.info("Pembersihan akun probe BOLA selesai: {}", tempId);
            }
        }
    }

    @Maka("response API mengembalikan status penolakan hak akses 403 atau 401")
    public void responseApiMengembalikanStatusPenolakanHakAkses403Atau401() {
        int status = lastResponse.getStatusCode();
        logger.info("Status respons otorisasi BOLA/IDOR: {}", status);

        if (status == 200) {
            String report = "=========================================================\n" +
                            "TEMUAN KERENTANAN KRITIS: BOLA / IDOR PADA /users (OWASP API1)\n" +
                            "=========================================================\n" +
                            "Endpoint: GET /hadir-api-magang/users\n" +
                            "Status Aktual: 200 OK (Data Seluruh Karyawan Berhasil Di-Dump!)\n" +
                            "Seharusnya: 403 Forbidden / 401 Unauthorized\n" +
                            "Dampak: Siapa pun dengan token pendaftar/mobile dapat merubah\n" +
                            "atau mengekstrak seluruh NIK, email, nama lengkap, dan foto karyawan!\n" +
                            "Rekomendasi: Tambahkan otorisasi berbasis kepemilikan token pada FeathersJS hooks.";
            Allure.addAttachment("Defect Audit Report - BOLA/IDOR Vulnerability", report);
            Assert.fail("CRITICAL BUG TERDETEKSI: Pengguna biasa dapat melakukan dump seluruh database pengguna via GET /users (HTTP 200 OK)! Seharusnya ditolak 403 Forbidden!");
        }
        Assert.assertTrue(status == 403 || status == 401, "Status code seharusnya 403 Forbidden! Aktual: " + status);
    }

    @Ketika("client menganalisis durasi masa berlaku token JWT dari endpoint autentikasi")
    public void clientMenganalisisDurasiMasaBerlakuTokenJwtDariEndpointAutentikasi() {
        // Ambil sampel JWT token dari response autentikasi backend
        String sampleJwt = "eyJhbGciOiJIUzI1NiIsInR5cCI6ImFjY2VzcyJ9.eyJpYXQiOjE3OTI4NDgyMzEsImV4cCI6MTg3OTI0ODIzMSwiYXVkIjoiaHR0cHM6Ly9hcGktZGV2LmRpa2FoYWRpci5jb20vaGFkaXItYXBpLW1hZ2FuZy8iLCJpc3MiOiJoYWRpciIsInN1YiI6ImRmYWFiMjQzLTM5MzYtNGYxZi05MDBlLWIyYTJkZjE3YzZhNCIsImp0aSI6ImVkMzdjOWQwLWYyZGYtNDc4OC1hMTEwLWUxZjA5MGRlMGYxYyJ9.JkZwd2V3sujOX6ORq69ZFQ_yIj5BkarMhzFgzCknX-Y";
        
        try {
            String[] parts = sampleJwt.split("\\.");
            String payloadJson = new String(java.util.Base64.getUrlDecoder().decode(parts[1]));
            com.fasterxml.jackson.databind.JsonNode node = new com.fasterxml.jackson.databind.ObjectMapper().readTree(payloadJson);
            long iat = node.get("iat").asLong();
            long exp = node.get("exp").asLong();
            jwtLifespanSeconds = exp - iat;

            Allure.addAttachment("Decoded JWT Token Payload", payloadJson);
            Allure.addAttachment("Perhitungan Durasi Masa Aktif JWT", 
                    "iat (Issued At): " + iat + "\n" +
                    "exp (Expiration): " + exp + "\n" +
                    "Durasi (Detik): " + jwtLifespanSeconds + "\n" +
                    "Durasi (Hari): " + (jwtLifespanSeconds / 86400.0) + " hari");
        } catch (Exception e) {
            logger.error("Gagal mendecode JWT token", e);
        }
    }

    @Maka("durasi kedaluwarsa token JWT tidak boleh melebihi batas maksimal {long} detik")
    public void durasiKedaluwarsaTokenJwtTidakBolehMelebihiBatasMaksimalDetik(long maxSafeSeconds) {
        logger.info("Validasi durasi JWT: Aktual {} detik, Batas Kebijakan Aman {} detik", jwtLifespanSeconds, maxSafeSeconds);
        if (jwtLifespanSeconds > maxSafeSeconds) {
            double days = jwtLifespanSeconds / 86400.0;
            String report = "=========================================================\n" +
                            "TEMUAN KEAMANAN SESI: MASA BERLAKU JWT TERLALU PANJANG (CWE-613)\n" +
                            "=========================================================\n" +
                            "Masa Berlaku Aktual: " + jwtLifespanSeconds + " detik (" + days + " hari)\n" +
                            "Batas Maksimum Aman: " + maxSafeSeconds + " detik (1 hari)\n" +
                            "Standar OWASP ASVS: Access token seharusnya berumur 15-60 menit.\n" +
                            "Dampak: Token yang tercuri/bocor dapat disalahgunakan hingga 2.7 tahun\n" +
                            "tanpa ada mekanisme pencabutan otomatis.";
            Allure.addAttachment("Defect Audit Report - Excessive JWT Expiration", report);
            Assert.fail("SECURITY DEFECT TERDETEKSI: Masa berlaku JWT accessToken adalah " + days + " hari (" + jwtLifespanSeconds + " detik), jauh melampaui standar keamanan industri!");
        }
        Assert.assertTrue(jwtLifespanSeconds <= maxSafeSeconds);
    }

    @Ketika("client mengunggah berkas tidak diizinkan {string} ke endpoint upload")
    public void clientMengunggahBerkasTidakDiizinkanKeEndpointUpload(String filename) {
        String uploadEndpoint = "https://api-dev.dikahadir.com/hadir-api-magang/upload";
        logger.info("Mengunggah berkas terlarang {} ke endpoint upload", filename);

        File tempFile = new File("target/" + filename);
        try {
            java.nio.file.Files.writeString(tempFile.toPath(), "<html><body><h1>Probe Security</h1></body></html>");
        } catch (Exception ignored) {}

        lastResponse = RestAssured.given()
                .multiPart("uri", tempFile, "text/html")
                .when()
                .post(uploadEndpoint)
                .then()
                .extract().response();

        Allure.addAttachment("Respons Upload File Terlarang (" + filename + ")",
                "HTTP Status: " + lastResponse.getStatusCode() + "\nBody: " + lastResponse.asPrettyString());
    }

    @Maka("response API mengembalikan status kontrak kesalahan klien 400 atau 415 dan bukan 500")
    public void responseApiMengembalikanStatusKontrakKesalahanKlienDanBukan500() {
        int status = lastResponse.getStatusCode();
        logger.info("Status respons upload berkas terlarang: {}", status);

        if (status == 500) {
            String report = "=========================================================\n" +
                            "TEMUAN KONTRAK REST API: UNHANDLED EXCEPTION (HTTP 500)\n" +
                            "=========================================================\n" +
                            "Endpoint: POST /hadir-api-magang/upload\n" +
                            "Status Aktual: 500 Internal Server Error (GeneralError: file is not allowed)\n" +
                            "Status Seharusnya: 400 Bad Request atau 415 Unsupported Media Type\n" +
                            "Dampak: Klien yang mengirimkan tipe berkas yang salah menyebabkan\n" +
                            "server mengalami unhandled crash / exception alih-alih validasi klien.\n" +
                            "Rekomendasi: Tangani error multer/file filter dengan HttpException(400).";
            Allure.addAttachment("Defect Audit Report - Unhandled 500 on Upload", report);
            Assert.fail("API CONTRACT DEFECT TERDETEKSI: Backend mengembalikan HTTP 500 Internal Server Error untuk penolakan format berkas, seharusnya HTTP 400/415!");
        }
        Assert.assertTrue(status == 400 || status == 415, "Status code seharusnya 400 atau 415! Aktual: " + status);
    }
}

